package main.java.com.race.core;

import main.java.com.race.core.player.Player;

/**
 * 얇은 오케스트레이터 계약.
 * - 라운드 초기화, 베팅 수집 호출, 레이스 실행 호출, 정산 호출만 담당한다.
 * - 구체 구현은 Dev C가 작성.
 */
public interface GameEngine {

    /** 엔진에 플레이어를 추가한다(사람 1명 + AI N명). */
    void addPlayer(Player player);

    /**
     * 라운드를 시작한다.
     * @param maxRounds 최대 라운드 수(양수)
     */
    void start(int maxRounds);
}
