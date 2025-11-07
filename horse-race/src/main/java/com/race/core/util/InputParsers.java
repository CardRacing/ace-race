package main.java.com.race.core.util;

import main.java.com.race.model.card.Suit;

public final class InputParsers {

    private InputParsers() {}

    public static Suit parseSuit(String raw) {
        if (raw == null) throw new IllegalArgumentException("입력이 필요합니다.");
        String value = raw.trim();
        if (value.isEmpty()) throw new IllegalArgumentException("무늬를 입력하세요.");

        String upper = value.toUpperCase();
        switch (upper) {
            case "0":
            case "S":
            case "SPADE":
            case "SPADES":
                return Suit.SPADE;
            case "1":
            case "H":
            case "HEART":
            case "HEARTS":
                return Suit.HEART;
            case "2":
            case "D":
            case "DIAMOND":
            case "DIAMONDS":
                return Suit.DIAMOND;
            case "3":
            case "C":
            case "CLUB":
            case "CLUBS":
                return Suit.CLUB;
        }

        if (value.equals("♠")) return Suit.SPADE;
        if (value.equals("♥")) return Suit.HEART;
        if (value.equals("♦")) return Suit.DIAMOND;
        if (value.equals("♣")) return Suit.CLUB;

        throw new IllegalArgumentException("알 수 없는 무늬 입력: " + raw);
    }

    public static int parsePositiveInt(String raw) {
        if (raw == null) throw new IllegalArgumentException("입력이 필요합니다.");
        try {
            int value = Integer.parseInt(raw.trim());
            if (value <= 0) {
                throw new IllegalArgumentException("양수를 입력해야 합니다.");
            }
            return value;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("정수를 입력해야 합니다.");
        }
    }
}
