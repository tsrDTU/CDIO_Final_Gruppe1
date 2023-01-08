package Tests;

import GameMechanics.Cars;
import GameMechanics.Colors;
import GameMechanics.Fields;
import TheBoard.BoardCreator;
import TheBoard.Language;
import cardClasses.Chance;
import gui_fields.GUI_Car;
import gui_main.GUI;
import player.MjPlayer;

import java.awt.*;
import java.io.IOException;

import static TheBoard.Base.*;
import static TheBoard.Base.fields;
import static TheBoard.Language.dialog;
import GameMechanics.Cars;
import GameMechanics.Colors;
import GameMechanics.Fields;
import TheBoard.Base;
import TheBoard.BoardCreator;
import TheBoard.Language;
import cardClasses.*;
import gui_fields.GUI_Car;
import gui_main.GUI;
import player.MjPlayer;

import java.awt.*;

import static TheBoard.Base.*;
import static TheBoard.Base.AmountofPlayers;
import static TheBoard.BoardCreator.JailInit;
import static TheBoard.Language.dialog;
import java.io.*;


public class FieldBuyTest {

    public static void main(String[] args) throws IOException {

        Chance mjChance = new Chance();
        String string_in, language, answer_game;
        int antal_kant, AmountofPlayers, i, j;
        String[] userRoles = {"Bil", "Skib", "Hund", "Kat"};
        String[] freeUserRoles;
        MjPlayer selectedPlayer;
        int CurrentSpaceForSelectedPlayer = 0;

        //  Initialises the TheBoard.Base.fields with values from txt files in - src/main/Field-Guts - and - Color.Colorspace
        BoardCreator.InitBoardFieldsGuts();

        GUI gui = new GUI(fields, Color.WHITE);
        language = "Dansk";
        Language.initializeDialog(dialog, language);
        antal_kant = 6;
        AmountofPlayers = 2;

        boolean[] JailOn = new boolean[AmountofPlayers + 1];
        MjPlayer[] PlayerArray = new MjPlayer[AmountofPlayers];
        GUI_Car[] playerCars = new GUI_Car[AmountofPlayers];
        String[] PlayerName = new String[AmountofPlayers];

        BoardCreator.PersonCreator(AmountofPlayers, PlayerArray, PlayerName, playerCars);

//  Sets names for each player in a for loop and gives an adjacent car with a private color
        for (i = 0; i < AmountofPlayers; i++) {
            //  Sets the car of each player
            PlayerName[i] = "";
            if (PlayerName[i].length() == 0) PlayerName[i] = ("Player" + (i + 1));
            playerCars[i] = new GUI_Car(Color.RED, Color.BLACK, Cars.setCarType(i + 1), GUI_Car.Pattern.FILL);
            PlayerArray[i] = new MjPlayer(PlayerName[i], 20 - ((AmountofPlayers - 2) * (2)), playerCars[i]);
            Colors.CarColor(playerCars, PlayerArray, String.valueOf(AmountofPlayers), i, fields);
            //Set users role
            PlayerArray[i].setUserRole(userRoles[i]);


            gui.addPlayer(PlayerArray[i]);
        }
        player.PlayerReset.restart(PlayerArray, fields, AmountofPlayers, fieldNR());

        int[][] OwnedtrueOwnedFalse = InitializeOwnedStat(AmountofPlayers).clone();

//--------------------------------------------------------------------------------
//
//        "GAME START"
//
//--------------------------------------------------------------------------------
        selectedPlayer = PlayerArray[1];
        int last_exp=30000;
        int rent;
        int expected;
        int Round = 0;

        while (selectedPlayer.getBalance() > 20000) {
            if (selectedPlayer==PlayerArray[0])
                selectedPlayer = PlayerArray[1];
            else if (selectedPlayer==PlayerArray[1])
                selectedPlayer = PlayerArray[0];
            //  Initialising something for GameMechanics.Jail and Start field
            int[] PlayerSpaceNRexcact = new int[AmountofPlayers];
//            gui.getUserButtonPressed(dialog[4] + " " + selectedPlayer.getName() + dialog[5], dialog[6]);


//-------------------------------------------------------------------------------
//
//        WHERE TO CHANGE WHAT SPACES THE CARS LAND ON
//
//-------------------------------------------------------------------------------

            Cars.moveCars(21, selectedPlayer, PlayerArray, AmountofPlayers, fieldNR());
            //  Sets the current space for the selected player to a value


            for (i = 0; i < fieldNR(); i++) {
                if (fields[i].hasCar(selectedPlayer))
                    CurrentSpaceForSelectedPlayer = i;
            }


            //  You get forced to buy the field, therefor (you want to buy)
            boolean wanttobuyYesNo = true;


            //  This handles the trades with rent and buying of fields - see at - src/main/java/GameMechanics.Fields
            rent = Integer.parseInt(fields[CurrentSpaceForSelectedPlayer].getRent());
            expected = last_exp-rent;
            last_exp=expected;
            if (wanttobuyYesNo/*&&Integer.parseInt(fields[CurrentSpaceForSelectedPlayer].getRent())!=0*/) {
                String NewBalance = Fields.wannaBuyDoYou(OwnedtrueOwnedFalse,
                        selectedPlayer,
                        //wanttobuyYesNo,
                        PlayerArray,
                        CurrentSpaceForSelectedPlayer,
                        PlayerSpaceNRexcact,
                        JailOn, mjChance, gui, fields);
                selectedPlayer.setBalance(selectedPlayer.getBalance() + Integer.parseInt(NewBalance));
                //System.out.println(NewBalance)
                // ;       | EMPTY NOTE |
                Round++;
                System.out.println("Round " +Round);
                System.out.println(PlayerArray[0].getBalance() + " Player1 - "+PlayerArray[1].getBalance()+" Player2");
                System.out.println("Price: "+fields[CurrentSpaceForSelectedPlayer].getRent()+" - Expected: "+ expected);
                System.out.println("");
//                String.valueOf(PlayerArray[0].getBalance()-Integer.parseInt(fields[CurrentSpaceForSelectedPlayer].getRent()));
            if (expected<-10000) break;
            }


        }
//        gui.getUserButtonPressed("Click Ok to End Showing: ", "Ok");
        System.exit(0);
    }
}

