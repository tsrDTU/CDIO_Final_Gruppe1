package UserStory_Tests;

import EgneGuiKlasser.MGUI;
import EgneGuiKlasser.MGUI_Car;
import EgneGuiKlasser.MGUI_Player;
import GameMechanics.Cars;
import GameMechanics.Colors;
import GameMechanics.Die;
import GameMechanics.Fields;
import TheBoard.BoardCreator;
import TheBoard.Language;
import cardClasses.Chance;
//import gui_fields.MGUI_Car;
//import player.MGUI_Player;


import java.awt.*;
import java.io.IOException;

import static TheBoard.Base.*;
import static TheBoard.Language.dialog;

public class K14_Køb_grunde {
    public static void main(String[] args) throws IOException {

        int[] OwnerList = Fields.InitialiseOwnerList();
        Chance mjChance = new Chance();
        String string_in, language, answer_game;
        int antal_kant, AmountofPlayers, i, j;
        MGUI_Player selectedPlayer;
        int CurrentSpaceForSelectedPlayer = 0;
      //  int[] OwnerList = Fields.InitialiseOwnerList();
        boolean[] ownstatus = Fields.OwnStatus();

        //  Initialises the TheBoard.Base.fields with values from txt files in - src/main/Field-Guts - and - Color.Colorspace
        BoardCreator.InitBoardFieldsGuts();

        MGUI gui = new MGUI(fields, Color.BLACK);
        language = "Dansk";
        Language.initializeDialog(dialog, language);
        antal_kant = 6;
        AmountofPlayers = 2;

        boolean[] JailOn = new boolean[AmountofPlayers + 1];
        MGUI_Player[] PlayerArray = new MGUI_Player[AmountofPlayers];
        MGUI_Car[] playerCars = new MGUI_Car[AmountofPlayers];
        String[] PlayerName = new String[AmountofPlayers];

        BoardCreator.PersonCreator(AmountofPlayers, PlayerArray, PlayerName, playerCars);

//  Sets names for each player in a for loop and gives an adjacent car with a private color
        //  Sets the car of each player
        for (i = 0; i < AmountofPlayers; i++) {
            //  Sets the car of each player
            PlayerName[i] = (gui.getUserString(dialog[3]+(i+1)+"?"));
            if (PlayerName[i].length() == 0) PlayerName[i] = ("Player" + (i + 1));
            playerCars[i] = new MGUI_Car(Color.RED, Color.BLACK, Cars.setCarType(i + 1), MGUI_Car.Pattern.FILL);
            PlayerArray[i] = new MGUI_Player(PlayerName[i], 20 - ((AmountofPlayers - 2) * (2)), playerCars[i]);
            Colors.CarColor(playerCars, PlayerArray, String.valueOf(AmountofPlayers), i, fields);



            gui.addPlayer(PlayerArray[i]);
        }
        player.PlayerReset.restart(PlayerArray, fields, AmountofPlayers, fieldNR());

        int[][] OwnedtrueOwnedFalse = InitializeOwnedStat(AmountofPlayers);
        boolean[] OwnStatus = Fields.OwnStatus();

        selectedPlayer = PlayerArray[1];
        int last_exp=30000;
        int rent;
        int expected = 30000;
        int expected1 = 30000;
        int Round = 0;
        Die d1 = new Die();
        Die d2 = new Die();

        Chance chankort=new Chance();
        boolean skipPlayer = false;

//--------------------------------------------------------------------------------
//
//        "GAME START"
//
//--------------------------------------------------------------------------------

        while (selectedPlayer.getBalance() > 0) {
            if (selectedPlayer==PlayerArray[0])
                selectedPlayer = PlayerArray[1];
            else if (selectedPlayer==PlayerArray[1])
                selectedPlayer = PlayerArray[0];
            //  Initialising something for GameMechanics.Jail and Start field
            int[] PlayerSpaceNRexcact = new int[AmountofPlayers];
          //  gui.getUserButtonPressed( selectedPlayer.getName() + dialog[5], dialog[6]);




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
           /* boolean wanttobuyYesNo = true;


            //  This handles the trades with rent and buying of fields - see at - src/main/java/GameMechanics.Fields

            if (Integer.parseInt(fields[CurrentSpaceForSelectedPlayer].getRent())!=0) {
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
                if (selectedPlayer.getNumber()==1) {
                    Round++;

                }
                if (expected<=-10000) break;*/

            //  You get forced to buy the field, therefor (you want to buy)
            boolean wanttobuyanswer;

            if (Integer.parseInt(fields[CurrentSpaceForSelectedPlayer].getRent())==0)
                wanttobuyanswer=true;
            else if (ownstatus[CurrentSpaceForSelectedPlayer])
                wanttobuyanswer=false;
            else {
                String wanttobuy = gui.getUserButtonPressed("Do you want to buy?", "Yes", "No");
                if (wanttobuy.equals("Yes")) wanttobuyanswer = true;
                else wanttobuyanswer = false;
            }

            //  This handles the trades with rent and buying of fields - see at - src/main/java/GameMechanics.Fields
            if (wanttobuyanswer && !ownstatus[CurrentSpaceForSelectedPlayer]) {
                System.out.println(fields[CurrentSpaceForSelectedPlayer].getRent());
                String NewBalance = Fields.wannaBuyDoYou(OwnedtrueOwnedFalse,
                        selectedPlayer,
                        //wanttobuyYesNo,
                        PlayerArray,
                        CurrentSpaceForSelectedPlayer,
                        PlayerSpaceNRexcact,
                        JailOn,chankort, gui, fields, ownstatus, OwnerList);
                selectedPlayer.setBalance(selectedPlayer.getBalance() + Integer.parseInt(NewBalance));
                //System.out.println(NewBalance);       | EMPTY NOTE |
            }
            else
                Fields.PayTheOwner(fields, CurrentSpaceForSelectedPlayer, selectedPlayer
                        , PlayerArray, ownstatus, OwnerList);
            }


        }
//        gui.getUserButtonPressed("Click Ok to End Showing: ", "Ok");

    }


