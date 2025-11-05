package main.java.com.race.model.race;

import main.java.com.race.model.card.Card;

import java.util.ArrayList;
import java.util.List;

public final class RaceLog {
    private final List<String> history;  // 로그를 저장하는 리스트

    public RaceLog() {
        this.history = new ArrayList<>();
    }

    public void recordDraw(Card card) {
        history.add("카드 드로우: " + card.toString());
    }

    public void recordMove(Horse horse, int newPos) {
        history.add(horse.suit().symbol() + " 말 이동: " + newPos + "칸");
    }


    public void recordWinner(Horse horse) {
        history.add("🏆 우승: " + horse.suit().symbol());
    }


    public void recordTurn(int turnNumber) {
        history.add("--- 턴 " + turnNumber + " ---");
    }


    public List<String> history() {
        return history;
    }

    public void clear() {
        history.clear();
    }

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