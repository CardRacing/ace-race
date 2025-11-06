package main.java.com.race.app;

import main.java.com.race.core.ConsoleView;
import main.java.com.race.core.player.AIPlayer;
import main.java.com.race.core.player.HumanPlayer;
import main.java.com.race.core.rule.ClassicRule;
import main.java.com.race.model.bet.Bet;
import main.java.com.race.model.card.Suit;

import java.util.ArrayList;

public class Application {
    public static void main(String[] args) {

        ClassicRule rule = new ClassicRule();
        ConsoleView cview = new ConsoleView();
        HumanPlayer humanPlayer = new HumanPlayer();
        AIPlayer CPU1 = new AIPlayer();
        AIPlayer CPU2 = new AIPlayer();
        AIPlayer CPU3 = new AIPlayer();
        AIPlayer[] AiList = new AIPlayer[5];
        AiList[0] = CPU1;
        AiList[1] = CPU2;
        AiList[2] = CPU3;

        Bet bet1 = new Bet();
        Bet bet2 = new Bet();
        Bet bet3 = new Bet();
        Bet bet4 = new Bet();
        Bet[] BetList = new Bet[4];
        BetList[0] = bet1;
        BetList[1] = bet2;
        BetList[2] = bet3;
        BetList[3] = bet4;

        int count = 0;
        while(true){
            System.out.print("이름을 입력하세요 : ");
            String playerName = cview.readLine();
            cview.readLine();
            System.out.println("배팅할 금액을 입력하세요 : ");
            int playerBalance = cview.ireadLine();
            System.out.println("심볼을 입력하세요. (♠ = 0 , ♥ = 1, ♦ = 2, ♣ = 3) : ");
            int num = cview.ireadLine();
            Suit s = Suit.fromCode(num);

            humanPlayer.setName(playerName);
            humanPlayer.setBalance(playerBalance);
            humanPlayer.setSuit(s);
            BetList[0] = humanPlayer.random();
            for(int i=0;i<3;i++){
                AiList[i].setName("CPU" + i+1);
                AiList[i].setBalance(1000);
                AiList[i].setSuit(s);
                BetList[i] = AiList[i].random(s);
            }

            

        }
    }
}
