package UserStory_Tests;

import EgneGuiKlasser.MGUI;
import EgneGuiKlasser.MGUI_Car;
import EgneGuiKlasser.MGUI_Player;
import EgneGuiKlasser.MGUI_Street;
import GameMechanics.Cars;
import GameMechanics.Colors;
import GameMechanics.Die;
import GameMechanics.Fields;
import TheBoard.Base;
import TheBoard.BoardCreator;
import TheBoard.Language;
import cardClasses.Chance;
import gui_fields.GUI_Car;
import gui_main.GUI;

import java.awt.*;
import java.io.IOException;

import static GameMechanics.Die.getSum;
import static TheBoard.Base.*;
import static TheBoard.Base.AmountofPlayers;
import static TheBoard.Language.dialog;

public class K9 {
    public static void main(String[] args) throws IOException {

        int[] OwnerList = Fields.InitialiseOwnerList();
        Chance mjChance = new Chance();
        String string_in, language, answer_game;
        int antal_kant, AmountofPlayers, i, j;
        String[] userRoles = {"Bil", "Skib", "Hund", "Kat"};
        String[] freeUserRoles;
        MGUI_Player selectedPlayer;
        int CurrentSpaceForSelectedPlayer = 0;

        //  Initialises the TheBoard.Base.fields with values from txt files in - src/main/Field-Guts - and - Color.Colorspace
        MGUI_Street[] fields = BoardCreator.InitBoardFieldsGuts();

        MGUI gui = new MGUI(fields, Color.BLACK);
        language = "Dansk";
        Language.initializeDialog(dialog, language);
        antal_kant = 6;
        Base.AmountofPlayers = 3;
        AmountofPlayers = Base.AmountofPlayers;

        boolean[] JailOn = new boolean[AmountofPlayers + 1];
        MGUI_Player[] PlayerArray = new MGUI_Player[Base.AmountofPlayers];
        MGUI_Car[] playerCars = new MGUI_Car[AmountofPlayers];
        String[] PlayerName = new String[AmountofPlayers];

        BoardCreator.PersonCreator(AmountofPlayers, PlayerArray, PlayerName, playerCars);

//  Sets names for each player in a for loop and gives an adjacent car with a private color
        for (i = 0; i < AmountofPlayers; i++) {
            //  Sets the car of each player
            PlayerName[i] = "";
            if (PlayerName[i].length() == 0) PlayerName[i] = ("Player" + (i + 1));
            playerCars[i] = new MGUI_Car(Color.RED, Color.BLACK, Cars.setCarType(i + 1), MGUI_Car.Pattern.FILL);
            PlayerArray[i] = new MGUI_Player(PlayerName[i], 30000, playerCars[i]);
            Colors.CarColor(playerCars, PlayerArray, String.valueOf(AmountofPlayers), i, fields);
            //Set users role
            PlayerArray[i].setUserRole(userRoles[i]);


            gui.addPlayer(PlayerArray[i]);
        }
        player.PlayerReset.restart(PlayerArray, fields, AmountofPlayers, fieldNR());

        int[][] OwnedtrueOwnedFalse = InitializeOwnedStat(AmountofPlayers);
        boolean[] OwnStatus = Fields.OwnStatus();


//--------------------------------------------------------------------------------
//
//        "GAME START"
//
//--------------------------------------------------------------------------------
        selectedPlayer = PlayerArray[1];
        int last_exp=30000;
        int rent;
        int expected = 30000;
        int expected1 = 30000;
        int Round = 0;
        Die d1 = new Die();
        Die d2 = new Die();
        PlayerArray[0].setBalance(1000);
        PlayerArray[1].setBalance(1000);
        int playingplayer = 0;
        int playingplayer2;
        int amountOfGameLoops = 0;

        while (selectedPlayer.getBalance() < 50000)  {
//            if (Round==0)
//                selectedPlayer = PlayerArray[0];
//            if (Round==1
//            )
//                selectedPlayer = PlayerArray[1];
//            else selectedPlayer = PlayerArray[2];
            if (amountOfGameLoops == AmountofPlayers) {
                amountOfGameLoops = 0;
            }
            if (playingplayer == AmountofPlayers) {
                playingplayer = 0;
            }
            amountOfGameLoops++;
            if (amountOfGameLoops == AmountofPlayers )
                amountOfGameLoops = 0;
//            playingplayer2 = playingplayer;
//            selectedPlayer = PlayerArray[playingplayer];
//            selectedPlayer = PlayerArray[playingplayer2];
            selectedPlayer = PlayerArray[amountOfGameLoops];




//            if (selectedPlayer==PlayerArray[0])
//                selectedPlayer = PlayerArray[1];
//            else if (selectedPlayer==PlayerArray[1])
//                selectedPlayer = PlayerArray[0];
            //  Initialising something for GameMechanics.Jail and Start field
            int[] PlayerSpaceNRexcact = new int[AmountofPlayers];
            gui.getUserButtonPressed(dialog[4] + " " + selectedPlayer.getName() + dialog[5], dialog[6]);


            d1.dice_roll();
            d2.dice_roll();
            int DieSum = 4;

//-------------------------------------------------------------------------------
//
//        WHERE TO CHANGE WHAT SPACES THE CARS LAND ON
//
//-------------------------------------------------------------------------------
//            gui.showMessage("Ok");
            Cars.moveCars(DieSum, selectedPlayer, PlayerArray, AmountofPlayers, fieldNR());
            //  Sets the current space for the selected player to a value

            ;
            for (i = 0; i < fieldNR(); i++) {
                if (fields[i].hasCar(selectedPlayer))
                    CurrentSpaceForSelectedPlayer = i;
            }

            GameMechanics.Die.OnBoard(d1, d2, gui);

            //  You get forced to buy the field, therefor (you want to buy)
            boolean wanttobuyYesNo = true;


            //  This handles the trades with rent and buying of fields - see at - src/main/java/GameMechanics.Fields

            if (wanttobuyYesNo) {
                String NewBalance = Fields.wannaBuyDoYou(OwnedtrueOwnedFalse,
                        selectedPlayer,
                        //wanttobuyYesNo,
                        PlayerArray,
                        CurrentSpaceForSelectedPlayer,
                        PlayerSpaceNRexcact,
                        JailOn, mjChance, gui, fields, OwnStatus, OwnerList);
                selectedPlayer.setBalance(selectedPlayer.getBalance() + Integer.parseInt(NewBalance));
                //System.out.println(NewBalance)
                // ;       | EMPTY NOTE |

//                String.valueOf(PlayerArray[0].getBalance()-Integer.parseInt(fields[CurrentSpaceForSelectedPlayer].getRent()));

                if (selectedPlayer.getBalance() < 0) selectedPlayer.setBalance(0);

                Round++;
                if (Round==20) break;
            }



        }
//        gui.getUserButtonPressed("Click Ok to End Showing: ", "Ok");
        System.exit(0);
    }
}
