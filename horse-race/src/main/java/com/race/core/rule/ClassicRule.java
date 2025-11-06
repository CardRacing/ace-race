package main.java.com.race.core.rule;

import main.java.com.race.model.race.Horse;
import main.java.com.race.model.race.Track;

import java.util.Objects;
import java.util.Random;

public class ClassicRule implements RaceRule {
    private static final int MIN = 7;
    private static final int MAX = 10;

    @Override
    public int trackLength(Random rng) {
        Objects.requireNonNull(rng, "Random cannot be null");
        return rng.nextInt(MAX - MIN + 1) + MIN;
    }

    @Override
    public boolean isFinish(Horse horse, Track track) {
        Objects.requireNonNull(horse, "Horse cannot be null");
        Objects.requireNonNull(track, "Track cannot be null");
        return horse.position() >= track.goal();
    }
}
