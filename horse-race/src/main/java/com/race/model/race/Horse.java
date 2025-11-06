package main.java.com.race.model.race;

import main.java.com.race.model.card.Suit;


public abstract class Horse {
    protected final Suit suit;
    protected int position;

    protected Horse(Suit suit) {
        this.suit = suit;
        this.position = 0;
    }

    public Suit suit() { return suit; }
    public int position() { return position; }


    public void reset() { this.position = 0; }


    protected int stepFor(TrackContext ctx) { return 1; }


    public void moveOne(TrackContext ctx) { this.position += stepFor(ctx); }
}
