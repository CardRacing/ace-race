package main.java.com.race.model.card;

import java.util.*;

public final class Deck {
    private final List<Card> cards;


    private Deck() {
        this.cards = new ArrayList<>();
    }

    public static Deck standard52() {
        Deck deck = new Deck();

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.cards.add(new Card(suit, rank));
            }
        }
        return deck;
    }

    public void shuffle(Random rng) {
        if (rng == null) {
            throw new IllegalArgumentException("랜덤은 null 값이 될 수 없다");
        }
        Collections.shuffle(cards, rng);
    }


    public Card draw() {
        if (cards.isEmpty()) {
            throw new NoSuchElementException("Deck이 비었습니다.");
        }
        return cards.remove(0);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int remaining() {
        return cards.size();
    }
}