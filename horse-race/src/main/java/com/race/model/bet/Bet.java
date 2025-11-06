package main.java.com.race.model.bet;

import main.java.com.race.model.card.Suit;

public class Bet {
    private final String playerId; // 누가 배팅했는지
    private final Suit suit;       // 배팅한 문양 (enum)
    private final int amount;      // 배팅 금액

    public Bet(String playerId, Suit suit, int amount) {
        this.playerId = playerId;
        this.suit = suit;
        this.amount = amount;
    }

    public String playerId() { return playerId; }
    public Suit suit()       { return suit; }
    public int amount()      { return amount; }

    @Override
    public String toString() {
        return String.format("[Bet] %s: %s 무늬에 %d원", playerId, suit, amount);
    }
}
