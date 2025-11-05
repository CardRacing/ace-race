package main.java.com.race.core.payout;

import main.java.com.race.model.bet.Bet;
import main.java.com.race.model.bet.BetResult;
import main.java.com.race.model.card.Suit;

import java.util.List;
import java.util.Map;

/**
 * 정산 정책 계약.
 * - 우승 무늬, 베팅 목록, 현재 잔액들을 받아
 *   각 플레이어의 증감/최종 잔액을 반환한다.
 */
public interface PayoutPolicy {
    List<BetResult> settle(Suit winner, List<Bet> bets, Map<String, Integer> currentBalances);
}
