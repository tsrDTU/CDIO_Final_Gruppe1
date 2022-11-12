package GameMechanics;

import gui_fields.GUI_Player;

import static TheBoard.Base.AmountofPlayers;

public class Winner {
    public static String[] Values(GUI_Player[] PlayerArray, GUI_Player selectedPlayer, String Winner, int WinnerMoney, int WinnerInt) {
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
        } return Winners;
    }
}
