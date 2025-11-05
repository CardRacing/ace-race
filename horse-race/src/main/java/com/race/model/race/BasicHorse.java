package main.java.com.race.model.race;

import main.java.com.race.model.card.Suit;

public final class BasicHorse extends Horse {


    public BasicHorse(Suit suit) {
        super(suit);
    }

    @Override
    protected int stepFor(TrackContext ctx) {
        return 1;
    }
}