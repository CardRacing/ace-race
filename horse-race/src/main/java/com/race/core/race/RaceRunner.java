package main.java.com.race.core.race;

import main.java.com.race.core.GameView;
import main.java.com.race.core.rule.RaceRule;
import main.java.com.race.model.card.Deck;
import main.java.com.race.model.card.Suit;
import main.java.com.race.model.race.RaceLog;
import main.java.com.race.model.race.TrackContext;

/**
 * 레이스 실행기 계약.
 * - 카드를 뒤집고, 해당 무늬 말 1칸 전진.
 * - 매 턴 상태를 GameView로 출력.
 * - rule.isFinish(...)가 true가 되는 순간 우승 무늬 반환.
 * - 덱 고갈 비상시 선두마 우승 처리(구현에서 보장).
 */
public interface RaceRunner {
    Suit run(Deck deck, TrackContext ctx, RaceRule rule, GameView io, RaceLog log);
}
