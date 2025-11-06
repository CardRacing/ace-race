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
        return name;
    }

    @Override
    public int balance() {
        return balance;
    }

    @Override
    public Bet placeBet(GameView io, int min, int max) {

    }

    @Override
    public void applyResult(BetResult result) {
        if (result != null){
            return;
        }
        this.balance = result.finalBalance();
        boolean isWin = result.win();
        int payout = result.payout();
        int finalBalance = result.finalBalance();

        System.out.println("|===============[경기결과]===============|");
        System.out.println(result.bet().toString() + " | 승리: " + (isWin ? "O" : "X") + " | 획득금: " + payout + " | 잔액: " + finalBalance);
        System.out.println("|=======================================|");
    }


}
