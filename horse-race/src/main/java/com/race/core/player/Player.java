package main.java.com.race.core.player;

import main.java.com.race.core.GameView;
import main.java.com.race.model.bet.Bet;
import main.java.com.race.model.bet.BetResult;

/**
 * 플레이어 계약(사람/AI 공통).
 * - 베팅 입력/결정
 * - 정산 반영
 */
public interface Player {
    String name();
    int balance();


    /** 정산 결과를 잔액에 반영한다. */
    void applyResult(BetResult result);
}
