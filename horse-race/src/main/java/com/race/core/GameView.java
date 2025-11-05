package main.java.com.race.core;

import main.java.com.race.model.bet.Bet;
import main.java.com.race.model.card.Suit;

import java.util.List;
import java.util.Map;

/**
 * UI 추상화. 콘솔/GUI 등 출력 수단을 교체 가능하게 하는 계약.
 * 콘크리트 구현(ConsoleView)은 Dev B가 작성.
 */
public interface GameView {
    void println(String s);
    String readLine();

    /** 말들의 현재 위치와 목표(트랙 길이)를 보여준다. */
    void showStatus(Map<Suit, Integer> positions, int goal);

    /** 이번 라운드의 베팅 목록을 보여준다. */
    void showBets(List<Bet> bets);

    /** 우승 무늬를 보여준다. */
    void showWinner(Suit winner);

    /** 이름 → 잔액 맵을 출력한다. */
    void showBalances(Map<String, Integer> balances);
}
