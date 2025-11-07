package main.java.com.race.core.player;

import main.java.com.race.model.bet.BetResult;

import java.util.Objects;

/**
 * 실제 유저를 나타내는 플레이어 모델.
 */
public class HumanPlayer implements Player {

    private final String name;
    private int balance;

    public HumanPlayer(String name, int initialBalance) {
        this.name = Objects.requireNonNull(name, "name");
        if (initialBalance < 0) {
            throw new IllegalArgumentException("initialBalance must be >= 0");
        }
        this.balance = initialBalance;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int balance() {
        return balance;
    }

    @Override
    public void applyResult(BetResult result) {
        if (result == null) return;
        this.balance = result.finalBalance();
    }
}

