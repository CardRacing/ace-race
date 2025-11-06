package main.java.com.race.model.race;

import main.java.com.race.model.card.Suit;

import java.util.*;

/// 한 라운드의 경주 상태(Horse, Track, RaceLog)를 묶는 컨텍스트
public class TrackContext {

    private Track track;
    private final Map<Suit, Horse> horses;
    private final RaceLog log;

    public TrackContext(Track track, Collection<Horse> horses) {
        Objects.requireNonNull(track, "Track cannot be null");
        Objects.requireNonNull(horses, "Horses cannot be null");
        if(horses.isEmpty()) throw new IllegalArgumentException("No horses provied");

        this.track = track;
        this.horses = new EnumMap<>(Suit.class);
        for (Horse h : horses) {
            this.horses.put(h.suit(), h);
        }
        this.log = new RaceLog();
    }

    /// 현재 트랙 반환
    public Track track() {
        return track;
    }

    /// 현재 로그 반환
    public RaceLog raceLog() {
        return log;
    }

    /// 특정 무늬 말 반환
    public Horse horseOf(Suit suit) {
        return horses.get(suit);
    }

    /// 현재 위치 반환
    public Map<Suit, Integer> positions() {
        Map<Suit, Integer> pos = new EnumMap<>(Suit.class);
        for (Map.Entry<Suit, Horse> e : horses.entrySet()) {
            pos.put(e.getKey(), e.getValue().position());
        }
        return Collections.unmodifiableMap(pos);
    }

    /// 선두 말 조회
    public Horse leader() {
        return horses.values().stream()
                .max(Comparator.comparingInt(Horse::position))
                .orElse(null);
    }

    /// 트랙 교체 + 말 위치 리셋 + 로그 초기화
    public void resetRound(Track newTrack) {
        Objects.requireNonNull(newTrack, "Track cannot be null");
        this.track = newTrack;
        horses.values().forEach(Horse::reset);
        log.clear();
    }

}
