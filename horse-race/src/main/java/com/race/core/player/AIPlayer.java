package main.java.com.race.core.player;

import main.java.com.race.model.bet.Bet;
import main.java.com.race.model.bet.BetResult;
import main.java.com.race.model.card.Suit;

import java.util.Map;
import java.util.Random;


public class AIPlayer implements Player {
    protected String name;
    protected int balance = 1000;
    private Suit suit;

    public AIPlayer() {
    }

    protected AIPlayer(String name, int initialBalance) {
        this.name = name;
        this.balance = initialBalance;
    }

    public Bet random(Suit suit){
        Random r = new Random();
        Map<Integer,Suit> hmap = Map.of(
                0, Suit.CLUB,
                1, Suit.SPADE,
                2, Suit.DIAMOND,
                3, Suit.HEART
        );
        int randSuit;
        int randBalance = r.nextInt(this.balance)+100;

//        for (Map.Entry<Integer, Suit> entry : hmap.entrySet()) {
//            System.out.println(entry.getKey() + " : " + entry.getValue());
//            if()
//        }

        while(true){
            randSuit = r.nextInt(4);
            if(hmap.get(randSuit) == suit){
                continue;
            } else {
                break;
            }
        }
        Bet bet1 = new Bet(this.name,hmap.get(randSuit),randBalance);

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
    public String name() { return name; }

    @Override
    public int balance() { return balance; }


    @Override
    public void applyResult(BetResult result) {
        if (result != null) this.balance = result.finalBalance();
    }
}

