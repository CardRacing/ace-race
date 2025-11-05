package main.java.com.race.model.race;

import main.java.com.race.model.card.Suit;

/**
 * 말의 공통 추상 클래스.
 * - 기본 이동(stepFor)=1칸
 * - 특성마(예: 행운마)는 stepFor를 오버라이드하여 확장
 */
public abstract class Horse {
    protected final Suit suit;
    protected int position;

    protected Horse(Suit suit) {
        this.suit = suit;
        this.position = 0;
    }

    public Suit suit() { return suit; }
    public int position() { return position; }

    /** 라운드 시작시 위치 리셋 */
    public void reset() { this.position = 0; }

    /** 이 말이 한 번에 이동할 칸 수(기본 1). 컨텍스트 기반 확장 가능. */
    protected int stepFor(TrackContext ctx) { return 1; }

    /** 한 번 이동한다(=position += stepFor). */
    public void moveOne(TrackContext ctx) { this.position += stepFor(ctx); }
}
