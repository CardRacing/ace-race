package main.java.com.race.model.card;

public enum Suit {
    SPADE(0,"♠"),
    HEART(1,"♥"),
    DIAMOND(2,"♦"),
    CLOVER(3,"♣");

    private final int code;
    private final String symbol;

    Suit(int code, String symbol) {
        this.code = code;
        this.symbol = symbol;
    }

    public int code() { return code; }
    public String symbol() { return symbol; }

    // 숫자로 찾기
    public static Suit fromCode(int code) {
        for (Suit s : values()) {
            if (s.code == code) return s;
        }
        throw new IllegalArgumentException("Invalid suit code: " + code);
    }

}