package main.java.com.race.core;

import main.java.com.race.model.bet.Bet;
import main.java.com.race.model.card.Suit;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleView implements GameView{

    private final Scanner sc = new Scanner(System.in);

    public int ireadLine() {
        return sc.nextInt();
    }

    @Override
    public void println(String s) {
        System.out.println(s);
    }

    @Override
    public String readLine() {
        return sc.nextLine().trim();
    }

    @Override
    public void showStatus(Map<Suit, Integer> positions, int goal) {

        System.out.println(" 현재 경주 상황 \n");

        String finishLine = "|";

        for (Suit suit : Suit.values()) {
            int pos = positions.getOrDefault(suit, 0);

            // 진행된 거리: ■, 남은 거리: □
            StringBuilder track = new StringBuilder();
            for (int i = 0; i < pos && i < goal; i++) {
                track.append("■");
            }
            for (int i = pos; i < goal; i++) {
                track.append("□");
            }

            // 결승선 추가
            track.append(finishLine);

            // 우승 시 표시
            String status = pos >= goal ? " [우승]" : "";

            // 무늬 + 트랙 출력
            System.out.printf("%s %s%s%n", suit.symbol(), track.toString(), status);
        }
        }

    @Override
    public void showBets(List<Bet> bets) {
        if (bets == null || bets.isEmpty()) {
            println("베팅 정보가 없습니다.");
            return;
        }

        for (Bet bet : bets) {
            if (bet == null) continue;
            println(String.format("%s(%s, %d골드)",
                    bet.playerId(), bet.suit().symbol(), bet.amount()));
        }
    }

    @Override
    public void showWinner(Suit winner) {
        if (winner == null) {
            println("우승 정보가 없습니다.");
            return;
        }
        println(String.format("WINNER: %s %s ", winner.symbol(), winner.name()));
    }

    @Override
    public void showBalances(Map<String, Integer> balances) {
        if (balances == null || balances.isEmpty()) {
            println("잔액 정보가 없습니다.");
            return;
        }
        balances.forEach((name, balance) ->
                println(String.format("%s: %d골드", name, balance)));
    }
}
