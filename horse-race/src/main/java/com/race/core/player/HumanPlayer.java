package main.java.com.race.core.player;

import main.java.com.race.core.GameView;
import main.java.com.race.model.bet.Bet;
import main.java.com.race.model.bet.BetResult;

public class HumanPlayer implements Player{

    private final String name;
    private int balance;

    public HumanPlayer(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    @Override
    public String name() {
        return "";
    }

    @Override
    public int balance() {
        return 0;
    }

    @Override
    public Bet placeBet(GameView io, int min, int max) {
        return null;
    }

    @Override
    public void applyResult(BetResult result) {
        if (result != null) {
            this.balance = result.finalBalance();
        }
    }


}
