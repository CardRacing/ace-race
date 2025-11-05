package main.java.com.race.model.bet;

/**
 * 플레이어(또는 AI)의 한 번의 베팅 정보를 담는 클래스.
 * - 불변(immutable) 객체로 설계되어야 함.
 * - 단순 데이터 모델 역할만 수행 (로직 금지).
 */
public final class Bet {


    private final String suit; // 배팅한 문양
    private final int amount;  // 배팅금액

    // 배팅정보 생성자
    public Bet(String suit, int amount) {
        this.suit = suit;
        this.amount = amount;
    }


    public String getSuit() {
        return suit;
    }


    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("[Bet] %s 무늬에 %d원", suit, amount);
    }
}
