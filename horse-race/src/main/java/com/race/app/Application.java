package main.java.com.race.app;

import main.java.com.race.core.ConsoleView;
import main.java.com.race.core.GameView;
import main.java.com.race.core.player.AIPlayer;
import main.java.com.race.core.player.HumanPlayer;
import main.java.com.race.core.player.Player;
import main.java.com.race.core.payout.EvenMoneyPolicy;
import main.java.com.race.core.payout.PayoutPolicy;
import main.java.com.race.core.race.RaceRunner;
import main.java.com.race.core.race.SimpleRaceRunner;
import main.java.com.race.core.rule.ClassicRule;
import main.java.com.race.core.rule.RaceRule;
import main.java.com.race.core.util.InputParsers;
import main.java.com.race.model.bet.Bet;
import main.java.com.race.model.bet.BetResult;
import main.java.com.race.model.card.Deck;
import main.java.com.race.model.card.Suit;
import main.java.com.race.model.race.BasicHorse;
import main.java.com.race.model.race.Horse;
import main.java.com.race.model.race.RaceLog;
import main.java.com.race.model.race.Track;
import main.java.com.race.model.race.TrackContext;

import java.util.*;
import java.util.stream.Collectors;

public class Application {
    private static final int INITIAL_BALANCE = 1000;
    private static final int MIN_BET = 100;
    private static final Random RNG = new Random();

    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();
        view.println("=== HORSE RACE ===");

        String playerName = askNonEmpty(view, "플레이어 이름을 입력하세요: ");
        HumanPlayer human = new HumanPlayer(playerName, INITIAL_BALANCE);

        List<Player> players = new ArrayList<>();
        players.add(human);
        for (int i = 1; i <= 3; i++) {
            players.add(new AIPlayer("CPU" + i, INITIAL_BALANCE));
        }

        showParticipants(view, players);

        RaceRule rule = new ClassicRule();
        RaceRunner runner = new SimpleRaceRunner();
        PayoutPolicy payoutPolicy = new EvenMoneyPolicy();

        int round = 1;
        boolean continueGame = true;

        while (continueGame && human.balance() > 0) {
            int trackLength = rule.trackLength(RNG);
            Track track = new Track(trackLength);
            List<Horse> horses = Arrays.stream(Suit.values())
                    .map(BasicHorse::new)
                    .collect(Collectors.toList());
            TrackContext ctx = new TrackContext(track, horses);
            RaceLog log = ctx.raceLog();

            Deck deck = Deck.standard52();
            deck.shuffle(RNG);
            discardForTrack(deck, trackLength);

            view.println("");
            view.println(String.format("=== %d 라운드 ===", round));
            view.println("트랙 길이: " + trackLength);
            view.println("참가할 말: ♠, ♥, ♦, ♣");

            Map<String, Bet> bets = collectBets(view, players);

            view.println("");
            view.println("베팅 현황");
            view.showBets(new ArrayList<>(bets.values()));
            view.println("");

            Suit winner = runner.run(deck, ctx, rule, view, log);
            view.showWinner(winner);

            Map<String, Integer> beforeBalances = players.stream()
                    .collect(Collectors.toMap(Player::name, Player::balance, (a, b) -> b, LinkedHashMap::new));

            List<BetResult> results = payoutPolicy.settle(winner, new ArrayList<>(bets.values()), beforeBalances);
            Map<String, BetResult> resultMap = results.stream()
                    .collect(Collectors.toMap(r -> r.bet().playerId(), r -> r));

            view.println("");
            view.println("=== 정산 결과 ===");
            for (Player player : players) {
                BetResult result = resultMap.get(player.name());
                if (result != null) {
                    player.applyResult(result);
                    int delta = result.payout();
                    view.println(String.format("%s: %+d (잔액 %d)",
                            player.name(), delta, player.balance()));
                } else {
                    view.println(String.format("%s: 참여하지 않음 (잔액 %d)",
                            player.name(), player.balance()));
                }
            }

            if (human.balance() <= 0) {
                view.println("플레이어의 잔액이 0 이하입니다. 게임을 종료합니다.");
                break;
            }

            continueGame = askNextRound(view);
            if (continueGame) {
                round++;
            }
        }

        view.println("");
        view.println("=== 최종 결과 ===");
        List<Player> finalOrder = players.stream()
                .sorted(Comparator.comparingInt(Player::balance).reversed())
                .collect(Collectors.toList());
        int rank = 1;
        for (Player player : finalOrder) {
            view.println(String.format("%d위 %s (잔액 %d)", rank++, player.name(), player.balance()));
        }

        view.println("게임을 종료합니다.");
    }

    private static void discardForTrack(Deck deck, int trackLength) {
        for (int i = 0; i < trackLength && !deck.isEmpty(); i++) {
            deck.draw();
        }
    }

    private static Map<String, Bet> collectBets(GameView view, List<Player> players) {
        Map<String, Bet> bets = new LinkedHashMap<>();
        for (Player player : players) {
            Bet bet;
            if (player instanceof HumanPlayer) {
                bet = promptHumanBet(view, (HumanPlayer) player);
            } else if (player instanceof AIPlayer) {
                bet = createAIBet((AIPlayer) player);
                view.println(String.format("%s이(가) %s 무늬에 %d골드를 베팅했습니다.",
                        bet.playerId(), bet.suit().symbol(), bet.amount()));
            } else {
                throw new IllegalStateException("알 수 없는 플레이어 타입: " + player.getClass());
            }
            bets.put(player.name(), bet);
        }
        return bets;
    }

    private static Bet promptHumanBet(GameView view, HumanPlayer human) {
        Suit suit = null;
        while (suit == null) {
            view.println("베팅할 말의 무늬를 입력하세요 (0: spade ♠, 1: heart ♥, 2: diamond ♦, 3: clova ♣): ");
            String input = view.readLine();
            try {
                suit = InputParsers.parseSuit(input);
            } catch (IllegalArgumentException e) {
                view.println("잘못된 무늬입니다. 다시 입력해주세요.");
            }
        }

        int minBet = Math.min(MIN_BET, human.balance());
        int attempts = 0;
        int amount = 0;
        while (true) {
            view.println(String.format("베팅 금액을 입력하세요 (최소 %d골드, 보유 %d골드): ",
                    minBet, human.balance()));
            String input = view.readLine();
            try {
                amount = InputParsers.parsePositiveInt(input);
                if (amount < minBet) {
                    view.println("최소 베팅 금액보다 작습니다.");
                    attempts++;
                    if (attempts >= 3) break;
                    continue;
                }
                if (amount > human.balance()) {
                    view.println("보유 금액을 초과했습니다.");
                    attempts++;
                    if (attempts >= 3) break;
                    continue;
                }
                break;
            } catch (IllegalArgumentException e) {
                view.println(e.getMessage());
                attempts++;
                if (attempts >= 3) break;
            }
        }

        if (amount <= 0) {
            amount = minBet;
        }
        if (attempts >= 3) {
            amount = Math.min(minBet, human.balance());
            view.println(String.format("입력을 3회 이상 잘못하여 %d골드로 자동 베팅합니다.", amount));
        }

        return new Bet(human.name(), suit, amount);
    }

    private static Bet createAIBet(AIPlayer ai) {
        int balance = ai.balance();
        if (balance <= 0) {
            return new Bet(ai.name(), Suit.values()[RNG.nextInt(Suit.values().length)], 0);
        }

        int minAllowed = Math.max((int) Math.ceil(balance * 0.10), Math.min(MIN_BET, balance));
        int maxAllowed = Math.max(minAllowed, (int) Math.ceil(balance * 0.30));
        maxAllowed = Math.min(maxAllowed, balance);
        if (minAllowed > maxAllowed) {
            minAllowed = maxAllowed;
        }
        int amount = minAllowed;
        if (maxAllowed > minAllowed) {
            amount = minAllowed + RNG.nextInt(maxAllowed - minAllowed + 1);
        }

        Suit suit = Suit.values()[RNG.nextInt(Suit.values().length)];
        return new Bet(ai.name(), suit, amount);
    }

    private static boolean askNextRound(GameView view) {
        while (true) {
            view.println("다음 라운드를 진행하시겠습니까? (Y/N)");
            String input = view.readLine();
            if (input.equalsIgnoreCase("Y")) {
                return true;
            }
            if (input.equalsIgnoreCase("N")) {
                return false;
            }
            view.println("Y 또는 N으로 입력해주세요.");
        }
    }

    private static String askNonEmpty(GameView view, String message) {
        String input;
        do {
            view.println(message);
            input = view.readLine();
        } while (input == null || input.isEmpty());
        return input;
    }

    private static void showParticipants(GameView view, List<Player> players) {
        view.println("");
        view.println("=== 참가자 정보 ===");
        for (Player player : players) {
            view.println(String.format("%s (초기 자금 %d골드)", player.name(), player.balance()));
        }
        view.println("");
    }
}
