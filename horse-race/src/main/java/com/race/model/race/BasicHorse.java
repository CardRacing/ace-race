package main.java.com.race.model.race;

import main.java.com.race.model.card.Suit;

import java.util.Objects;

public final class BasicHorse extends Horse {   // 기본 1칸 이동

    public BasicHorse(Suit suit) {
        super(requireNonNullSuit(suit));
    }

    private static Suit requireNonNullSuit(Suit suit) {
        if (suit == null) throw new IllegalArgumentException("Suit cannot be null");
        return suit;
    }

    @Override
    protected int stepFor(TrackContext ctx) {
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasicHorse)) return false;
        BasicHorse that = (BasicHorse) o;
        return this.suit == that.suit();
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit);
    }

    @Override
    public String toString() {
        return "BasicHorse{suit=" + suit() + ", position=" + position() + "}";
    }
}
