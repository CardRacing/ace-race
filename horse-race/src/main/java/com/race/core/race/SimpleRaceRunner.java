package main.java.com.race.core.race;

import main.java.com.race.core.GameView;
import main.java.com.race.core.rule.RaceRule;
import main.java.com.race.model.card.Card;
import main.java.com.race.model.card.Deck;
import main.java.com.race.model.card.Suit;
import main.java.com.race.model.race.Horse;
import main.java.com.race.model.race.RaceLog;
import main.java.com.race.model.race.TrackContext;

import java.util.Map;

/**
 * 기본 레이스 실행기 구현.
 */
public class SimpleRaceRunner implements RaceRunner {

    private static final long TURN_DELAY = 1000;
    private static final long FINISH_DELAY = 1200;


    @Override
    public Suit run(Deck deck, TrackContext ctx, RaceRule rule, GameView io, RaceLog log) {
        int turn = 1;
        while (true) {
            if (deck.isEmpty()) {
                Horse leader = ctx.leader();
                if (leader == null) {
                    throw new IllegalStateException("덱이 비었지만 말 정보가 없습니다.");
                }
                log.recordWinner(leader);
                io.println("덱이 모두 사용되었습니다. 선두 말이 우승합니다.");
                return leader.suit();
            }

            Card card = deck.draw();
            Horse horse = ctx.horseOf(card.suit());

            log.recordTurn(turn);
            log.recordDraw(card);

            io.println(String.format("Draw: %s %s → %s +1",
                    card.suit().symbol(), card.rank().display(), card.suit().name()));

            horse.moveOne(ctx);
            log.recordMove(horse, horse.position());

            Map<Suit, Integer> positions = ctx.positions();
            io.showStatus(positions, ctx.track().goal());

            sleep(TURN_DELAY);

            if (rule.isFinish(horse, ctx.track())) {
                log.recordWinner(horse);
                return horse.suit();
            }

            turn++;
        }
    }
    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

