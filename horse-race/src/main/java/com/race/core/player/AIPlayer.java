package main.java.com.race.core.player;

import main.java.com.race.core.GameView;
import main.java.com.race.model.bet.Bet;

/**
 * AI 플레이어의 공통 부모(추상 클래스).
 * - 이름/잔액 공통 보유
 * - 베팅 로직은 하위 클래스가 결정
 */
public abstract class AIPlayer implements Player {
    protected final String name;
    protected int balance;

    protected AIPlayer(String name, int initialBalance) {
        this.name = name;
        this.balance = initialBalance;
    }

    @Override
    public String name() { return name; }

    @Override
    public int balance() { return balance; }

    /** AI의 베팅 전략(무늬/금액 결정)은 하위 클래스에서 구현. */
    @Override
    public abstract Bet placeBet(GameView io, int min, int max);

    @Override
    public void applyResult(com.race.model.bet.BetResult result) {
        if (result != null) this.balance = result.finalBalance();
    }
}

