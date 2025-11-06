package main.java.com.race.model.card;

public final class Card {
    private final Suit suit;
    private final Rank rank;


    public Card(Suit suit, Rank rank) {
        if (suit == null || rank == null) {
            throw new IllegalArgumentException("Suit and Rank cannot be null");
        }
        this.suit = suit;
        this.rank = rank;
    }
   public Suit suit() {
        return suit;
    }
  public Rank rank() {
        return rank;
    }
   @Override
    public String toString() {
        return suit.symbol() + rank.display();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Card)) return false;
        Card other = (Card) obj;
        return suit == other.suit && rank == other.rank;
    }

    @Override
    public int hashCode() {
        return 31 * suit.hashCode() + rank.hashCode();
    }
}