package main.java.com.race.core;

import main.java.com.race.model.bet.Bet;
import main.java.com.race.model.card.Suit;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleView implements GameView{

    private final Scanner sc = new Scanner(System.in);

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

    }

    @Override
    public void showBets(List<Bet> bets) {

    }

    @Override
    public void showWinner(Suit winner) {

    }

    @Override
    public void showBalances(Map<String, Integer> balances) {

    }
}
