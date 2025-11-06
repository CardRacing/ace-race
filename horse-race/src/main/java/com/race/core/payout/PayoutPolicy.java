package main.java.com.race.core.payout;

import main.java.com.race.model.bet.Bet;
import main.java.com.race.model.bet.BetResult;
import main.java.com.race.model.card.Suit;

import java.util.List;
import java.util.Map;

public interface PayoutPolicy {
    List<BetResult> settle(Suit winner, List<Bet> bets, Map<String, Integer> currentBalances);
}
