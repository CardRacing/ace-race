package main.java.com.race.core.player;

import main.java.com.race.core.GameView;
import main.java.com.race.model.bet.Bet;
import main.java.com.race.model.bet.BetResult;
import main.java.com.race.model.card.Suit;

import java.util.Map;
import java.util.Random;

public class HumanPlayer implements Player{

    private String name;
    private int balance;
    private Suit suit;

    public HumanPlayer() {}

    public HumanPlayer(String name, int balance, Suit suit) {
        this.name = name;
        this.balance = balance;
        this.suit = suit;
    }

    public Bet random(){
        Bet bet1 = new Bet(this.name,this.suit,this.balance);

        return bet1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
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
