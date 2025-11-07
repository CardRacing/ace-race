package main.java.com.race.core;

import main.java.com.race.model.bet.Bet;
import main.java.com.race.model.card.Suit;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleView implements GameView{

    private final Scanner sc = new Scanner(System.in);

    public int ireadLine() {
        return sc.nextInt();
    }

    @Override
    public void println(String s) {
        System.out.println(s);
    }

    @Override
    public String readLine() {
        return sc.nextLine().trim();
    }

    @Override
    public void showStatus(Map<Suit, Integer> positions, int goal) {
        if (positions == null || positions.isEmpty()) {
            println("[경주 정보 없음]");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Suit[] order = Suit.values();
        for (int i = 0; i < order.length; i++) {
            Suit suit = order[i];
            int pos = positions.getOrDefault(suit, 0);
            sb.append(suit.symbol()).append(pos);
            if (i < order.length - 1) sb.append(' ');
        }
        sb.append(" / Goal:").append(goal).append(']');
        println(sb.toString());
    }

    @Override
    public void showBets(List<Bet> bets) {
        if (bets == null || bets.isEmpty()) {
            println("베팅 정보가 없습니다.");
            return;
        }

        for (Bet bet : bets) {
            if (bet == null) continue;
            println(String.format("%s(%s, %d골드)",
                    bet.playerId(), bet.suit().symbol(), bet.amount()));
        }
    }

    @Override
    public void showWinner(Suit winner) {
        if (winner == null) {
            println("우승 정보가 없습니다.");
            return;
        }
        println(String.format("WINNER: %s %s 🏇", winner.symbol(), winner.name()));
    }

    @Override
    public void showBalances(Map<String, Integer> balances) {
        if (balances == null || balances.isEmpty()) {
            println("잔액 정보가 없습니다.");
            return;
        }
        balances.forEach((name, balance) ->
                println(String.format("%s: %d골드", name, balance)));
    }
}
