package main.java.com.race.model.race;

import java.util.Objects;

///  이번 라운드의 결승선(목표 거리)과 말의 위치가 결승에 도달했는지 판단
public class Track {

    private final int goal;

    public Track(int goal) {
        if (goal < 1) throw new IllegalArgumentException("Track goal must be >= 1");
        this.goal = goal;
    }

    /// 결승선 (목표 거리)
    public int goal() {
        return goal;
    }

    /// 말이 결승선에 도착했는지 판단
    public boolean reached(int position) {
        return position >= goal;
    }

    /// 위치가 0...goal 범위인지 확인
    public boolean inBound(int position) {
        return (position >= 0) && (position <= goal);
    }

    /// 결승점까지 남은 거리 (음수일 경우 0으로 보정)
    public int distanceToGoal(int position) {
        int remain = position - goal;
        return Math.max(remain, 0);
    }

    /// 방어적 보정: 범위를 벗어난 위치를 0..goal로 클램프(필요 시 사용)
    public int clamp(int position) {
        if (position < 0) return 0;
        if (position > goal) return goal;
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return goal == track.goal;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(goal);
    }

    @Override
    public String toString() {
        return "Track{" +
                "goal=" + goal +
                '}';
    }
}
