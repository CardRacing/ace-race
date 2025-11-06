package main.java.com.race.core.payout;

import main.java.com.race.model.bet.Bet;
import main.java.com.race.model.bet.BetResult;
import main.java.com.race.model.card.Suit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class EvenMoneyPolicy implements PayoutPolicy {
    @Override
    public List<BetResult> settle(Suit winner,
                                  List<Bet> bets,
                                  Map<String, Integer> currentBalances) {
        Objects.requireNonNull(winner, "winner cannot be null");
        Objects.requireNonNull(bets, "bets cannot be null");
        Objects.requireNonNull(currentBalances, "balances cannot be null");

        List<BetResult> results = new ArrayList<>(bets.size());

        for (Bet bet : bets) {
            if (bet == null) continue;

            final String playerId = bet.playerId();
            final Suit betSuit    = bet.suit();
            final int amount      = bet.amount();

            if (playerId == null || betSuit == null || amount <= 0) continue;

            final int before = currentBalances.getOrDefault(playerId, 0);
            final boolean win = (betSuit == winner);
            final int delta   = win ? amount : -amount;
            final int after   = Math.addExact(before, delta);

            currentBalances.put(playerId, after);

            // BetResult 생성자는 네 구현에 맞춰 조정
            // 흔한 형태: new BetResult(bet, win, delta, after)
            results.add(new BetResult(bet, win, delta, after));
        }

        return results;
    }
}
