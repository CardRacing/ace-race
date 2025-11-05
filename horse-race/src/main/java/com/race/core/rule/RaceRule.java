package main.java.com.race.core.rule;

import main.java.com.race.model.race.Horse;
import main.java.com.race.model.race.Track;

import java.util.Random;

/**
 * 규칙 전략 계약.
 * - 트랙 길이 결정
 * - 결승선 도달 판정
 */
public interface RaceRule {
    /** 7~10 사이에서 트랙 길이를 결정한다. */
    int trackLength(Random rng);

    /** 말이 결승선에 도달했는지 판정한다. */
    boolean isFinish(Horse horse, Track track);
}
