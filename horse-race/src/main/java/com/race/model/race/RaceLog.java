package main.java.com.race.model.race;

import main.java.com.race.model.card.Card;

import java.util.ArrayList;
import java.util.List;

/// 한 라운드의 경주 진행 상황을 문자열 형태로 순차 기록
/// 레이스 도중 발생하는 모든 이벤트를 문자열로 축적하고, 경기 후 GameView가 해당 정보를 콘솔에 보여줌
public final class RaceLog {
    private final List<String> history;  // 로그를 저장하는 리스트

    public RaceLog() {
        this.history = new ArrayList<>();
    }

    /// 이번 턴에 나온 카드를 기록
    public void recordDraw(Card card) {
        history.add("카드 드로우: " + card.toString());
    }

    /// 카드 무늬와 이동 후 위치 기록
    public void recordMove(Horse horse, int newPos) {
        history.add(horse.suit().symbol() + " 말 이동: " + newPos + "칸");
    }

    /// 우승한 말 기록
    public void recordWinner(Horse horse) {
        history.add("🏆 우승: " + horse.suit().symbol());
    }

    ///  턴 구분선
    public void recordTurn(int turnNumber) {
        history.add("--- 턴 " + turnNumber + " ---");
    }

    /// 현재까지 기록된 로그를 리스트 형태로 반환
    public List<String> history() {
        return history;
    }

    /// 로그 초기화 용
    public void clear() {
        history.clear();
    }

    /// 모든 로그를 한 문자열로 합쳐서 반환함
    public String getFullLog() {
        String result = "";
        for (int i = 0; i < history.size(); i++) {
            result += history.get(i);
            if (i < history.size() - 1) {
                result += "\n";
            }
        }
        return result;
    }
}