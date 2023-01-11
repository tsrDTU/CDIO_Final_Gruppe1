package GameMechanics;

import TheBoard.Base;
import gui_fields.GUI_Player;

import static TheBoard.Base.*;

import EgneGuiKlasser.*;

public class Winner {
    public static String Values(MGUI_Player[] PlayerArray, MGUI_Player selectedPlayer) {
        //  Initialises values for displaying a winner
        String[] Winners = new String[AmountofPlayers];
        //  if someone loses, the game ends and a winner/ winners are decided
        if (selectedPlayer.getBalance() < 1) {

            //  Sets the winner to player1
            Winners[0] = PlayerArray[0].getName();
            int WinnerMoneyL = PlayerArray[0].getBalance();
            for (int i = 1; i < AmountofPlayers; i++) {
                //  if the player has more money, set it as the new winner, by resetting the array and putting the new value in
                if (PlayerArray[i].getBalance() > WinnerMoneyL) {
                    for (int b = 0; b < i; b++) {
                        Winners[b] = " ";
                        System.out.println("HapLink");
                    }
                    Winners[i] = Winners[i-1]+PlayerArray[i].getName();
                    WinnerMoneyL = PlayerArray[i].getBalance();
                    WinnerInt = i;
                }
                else Winners[i]=" ";
                Winners[i]=Winners[i-1]+Winners[i];
                System.out.println("Winner int: "+ WinnerInt);
                System.out.println(Winners[WinnerInt]+" "+Winners[0]+ " "+Winners[1]);
                /*
                // if the next player has the same balance as previous player, set both as winners
                else if (PlayerArray[i].getBalance() == WinnerMoney)
                    for (int l = 1; l < AmountofPlayers; l++) {
                        Winners[i] = (Winners[i - 1] + " " + PlayerArray[i].getName());
                }
                 */

            }
        } return Winners[WinnerInt];
    }

    public static int winnerint(MGUI_Player PlayerArray){
        int TheInt = 2;
        return TheInt;
    }

    public static int Money(MGUI_Player[] PlayerArray, MGUI_Player selectedPlayer) {
        //  Initialises values for displaying a winner
        String[] Winners = new String[AmountofPlayers];
        //  if someone loses, the game ends and a winner/ winners are decided
        if (selectedPlayer.getBalance() < 1) {

            //  Sets the winner to player1
            Winners[0] = PlayerArray[0].getName();
            WinnerMoney = PlayerArray[0].getBalance();
            for (int i = 1; i < AmountofPlayers; i++) {
                //  if the player has more money, set it as the new winner, by resetting the array and putting the new value in
                if (PlayerArray[i].getBalance() > WinnerMoney) {
                    for (int b = 0; b < AmountofPlayers; b++) {
                        Winners[b] = " ";
                    }
                    Winners[i] = PlayerArray[i].getName();
                    WinnerMoney = PlayerArray[i].getBalance();
                    WinnerInt = i;
                }
                // if the next player has the same balance as previous player, set both as winners
                else if (PlayerArray[i].getBalance() == WinnerMoney)
                    for (int l = 1; l < AmountofPlayers; l++) {
                        Winners[i] = (Winners[i - 1] + " " + PlayerArray[i].getName());
                    }
            }
        } return WinnerMoney;
    }

}
