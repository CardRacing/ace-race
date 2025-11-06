package main.java.com.race.model.bet;

public class BetResult {

        private Bet bet;          // 결과
        private boolean isWin;    // 승리 여부
        private int payout;   // 보상금액
        private int finalBalance; // 최종금액

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
