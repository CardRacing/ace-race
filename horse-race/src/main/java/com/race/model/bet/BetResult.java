package main.java.com.race.model.bet;

/**
 * 베팅 결과를 담는 클래스.
 * - 승패 여부, 최종 잔액, 베팅 금액 및 무늬 등 결과 정보를 포함.
 * - 불변(immutable) 객체로 설계되어야 함.
 * - 결과 계산은 GameEngine 또는 판정 로직에서 수행해야 하며,
 * 이 클래스 내부에 로직이 포함되어선 안 됨.
 */
public final class BetResult {


    private final Bet bet;          // 결과
    private final boolean isWin;    // 승리 여부
    private final int payout;   // 보상금액
    private final int finalBalance; // 최종금액

    /**
     * 베팅 결과를 생성한다.
     *
     * @param bet          원본 베팅 정보
     * @param win          승리 여부
     * @param payout       획득 금액 (패배 시 0)
     * @param finalBalance 결과 반영 후 최종 잔액
     */
    public BetResult(Bet bet, boolean win, int payout, int finalBalance) {
        this.bet = bet;
        this.isWin = win;
        this.payout = payout;
        this.finalBalance = finalBalance;
    }

    public Bet bet() {
        return bet;
    }

    public boolean win() {
        return isWin;
    }

    public int payout() {
        return payout;
    }

    public int finalBalance() {
        return finalBalance;
    }

    @Override
    public String toString() {
        return String.format("[Result] %s | 승리: %s | 획득금: %d | 잔액: %d",
                bet.toString(), isWin ? "O" : "X", payout, finalBalance);
    }
}

