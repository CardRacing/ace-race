package main.java.com.race.model.race;

import main.java.com.race.model.card.Suit;


public abstract class Horse {
    protected final Suit suit;  // 말의 고유 식별자
    protected int position;     // 현재 트랙에서의 위치

    protected Horse(Suit suit) {
        this.suit = suit;
        this.position = 0;
    }

    ///  말의 식별자 무늬 반환
    public Suit suit() { return suit; }
    ///  현재 위치 반환
    public int position() { return position; }
    ///  위치 초기화
    public void reset() { this.position = 0; }
    /// 한 번에 얼마나 이동할 것인가 - 기본값 1
    protected int stepFor(TrackContext ctx) { return 1; }
    /// 실제 이동을 책임짐
    public void moveOne(TrackContext ctx) { this.position += stepFor(ctx); }
}
