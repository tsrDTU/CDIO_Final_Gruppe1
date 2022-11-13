import gui_main.GUI;
import gui_fields.GUI_Street;
import gui_fields.GUI_Car;

import java.awt.*;
import java.io.*;
import java.util.Objects;
import java.util.Scanner;

import player.MjPlayer;
//v1.0

public class Main {
    public static void main(String[] args) throws IOException {
        //  initialises language and gameanwser - makes dialog strings
        String string_in, language, answer_game;
        String[] dialog = new String[12];
        //  initialises the antal_kant for dice and language statements
        int antal_kant, j, k;
        boolean language_ok, game_running, answerGameOk;
        //  sets game to run - sets the amount of fields
        game_running = true;
        int fieldNR = 24, actualField;
        String[] userRoles={"Bil","Skib","Hund","Kat"};
        String[] freeUserRoles;

        //  Sets up the path to txt files in the
        File DescriptionF = new File("src/main/Field Guts/description");
        File TitleF = new File("src/main/Field Guts/Title");
        File subtextF = new File("src/main/Field Guts/subText");
        File rentF = new File("src/main/Field Guts/rent");
        File CostToOwnFieldF = new File("src/main/Field Guts/CostToOwnField");

        //  Initialises cost to own for every space based off the subsequent value in - src/main/Field Guts/CostToOwnField
        int[] CosttoOwn = new int[fieldNR];
        for (int i = 0; i < fieldNR; i++) {
            CosttoOwn[i] = (Integer.parseInt(Main.txtReadAndReturn(CostToOwnFieldF, String.valueOf(i))));
        }

        //  Initialises the fields with values from txt files in - src/main/Field-Guts - and - Color.Colorspace
        GUI_Street[] fields = new GUI_Street[fieldNR];
        for (int i = 0; i < fieldNR; i++) {
            fields[i] = new GUI_Street(
                    txtReadAndReturn(TitleF, String.valueOf(i + 1)),
                    txtReadAndReturn(subtextF, String.valueOf(i + 1)),
                    txtReadAndReturn(DescriptionF, String.valueOf(i + 1)),
                    txtReadAndReturn(rentF, String.valueOf(i + 1)),
                    //fields[i] = new GUI_Street("","","","1",
                    Fields.ColorSpace(Integer.parseInt(txtReadAndReturn(DescriptionF, String.valueOf(i + 1))), i),
                    Color.BLACK);
        }
        //  Sets up the background GUI (Graphical User Interface) to a plain white
        GUI gui = new GUI(fields, Color.WHITE);

        //  Asks if the language has been initialised and makes a button for user to select language
        language_ok = false;
        String sprog = "English";
        do {
            language = gui.getUserButtonPressed("Select Langage:", "Dansk", "English", "Francias", "German"); // Select language for the game dialog
            if (language.equals("Dansk")) language_ok = true;
            sprog = "Dansk";
            if (language.equals("English")) language_ok = true;
            sprog = "English";
            if (language.equals("Francias")) language_ok = true;
            sprog = "Francias";
            if (language.equals("German")) language_ok = true;
            sprog = "German";
        } while (!language_ok);

        //  Initialize the game dialog
        Language.initializeDialog(dialog, language);
  //      System.out.println("over");


        //Asks how many players, and sets cars and players
        String Players = gui.getUserButtonPressed(dialog[11], "2", "3", "4");

        int AmountofPlayers = Integer.parseInt(Players);

        MjPlayer[] PlayerArray = new MjPlayer[Integer.parseInt(Players)];
 //       System.out.println("3");
        GUI_Car[] playerCars = new GUI_Car[Integer.parseInt(Players)];
        String[] PlayerName = new String[Integer.parseInt(Players)];
 //       System.out.println(Integer.parseInt(Players));

        //  Sets names for each player in a for loop and gives an adjacent car with a private color
        //  for loop, asks for each player to input a name

        for (int i = 0; i < Integer.parseInt(Players); i++) {
            PlayerName[i] = gui.getUserString(dialog[1]+(i+1)+"?");
            //  if No name is given - player is named fx. Player2
            if (PlayerName[i].length() == 0) PlayerName[i] = ("Player" + (i+1));
            //  Sets the car of each player
            playerCars[i] = new GUI_Car(Color.RED, Color.BLACK, GUI_Car.Type.CAR, GUI_Car.Pattern.FILL);
            //  Sets the staring balance
            PlayerArray[i] = new MjPlayer(PlayerName[i], 20 - ((AmountofPlayers - 2) * (2)), playerCars[i]);
            //  Sets the Car Color with CarColor method under Cars - src/main/java/Cars
            Cars.CarColor(playerCars, PlayerArray, Players, i);
            //Set users role
            PlayerArray[i].setUserRole(gui.getUserButtonPressed(dialog[2],
                    userRoles));
            //Remove this role from the list.
            for (j=0;j<userRoles.length;j++)
            {
                if (userRoles[j].equals(PlayerArray[i].getUserRole()))
                {
                    userRoles[j]=" ";
                    /*
                    for (k=j;k<userRoles[j].length() -1;k++)
                    {
                        userRoles[k]=userRoles[k+1];
                        freeUserRoles.
                    }

                     */


                }
            }
            //  Adds it all to the gui
            gui.addPlayer(PlayerArray[i]);
        }

 //       System.out.println("7");
        //Initialise true false for OwnedNotOwnedFields
        int[][] OwnedtrueOwnedFalse = new int[fieldNR][AmountofPlayers + 1];
        for (int n = 0; n < fieldNR; n++) {
            OwnedtrueOwnedFalse[n][0] = n;
            for (int i = 0; i < AmountofPlayers; i++) {
                OwnedtrueOwnedFalse[n][i] = 0;
            }
        }
        // The player who starts has been selected
        boolean selection = true;
        //Sets all player locations on the board to space 0
        for (int i = 0; i < AmountofPlayers; i++) {
            if (i > 0 && fields[i] != null) fields[0].setCar(PlayerArray[i], true);
        }


        int intselect = 0;
        //Create a selected player that will point at active player
        MjPlayer selectedPlayer;
        boolean gameEnd = false; //, lastMax = false;

        //Create the dices. Default 6 sides
        Die d1 = new Die();
        Die d2 = new Die();



        int playingPlayer = intselect;
        int playingPlayer2;

        //  Initialising something for Jail and Start field
        int[] PlayerSpaceNRexcact = new int[AmountofPlayers];
        for (int i = 0; i < AmountofPlayers; i++) {
            PlayerSpaceNRexcact[i] =0;
        }

        boolean[][] JailOn = new boolean[AmountofPlayers][2];
        for (int i = 0; i < AmountofPlayers; i++) {
            JailOn[i][0] = false;
            JailOn[i][1] = false;
        }

        Chance chanKort=new Chance();

        int amountOfGameLoops = 0;
        //Game loop
        while (!gameEnd) {
            //while (PlayerArray[0].getBalance() < 3000 && PlayerArray[1].getBalance() < 3000 && !gameEnd) {
            if (amountOfGameLoops == AmountofPlayers)
                amountOfGameLoops = 0;
            if (playingPlayer == AmountofPlayers)
                playingPlayer = 0;
            playingPlayer2 = playingPlayer;
            if (selection) selectedPlayer = PlayerArray[playingPlayer];
            else selectedPlayer = PlayerArray[playingPlayer2];

            //if (amountOfGameLoops == 0);
            //Jail.JailRegister(selectedPlayer,AmountofPlayers, fieldNR, fields);
            //roll the dices
            d1.dice_roll();
            d2.dice_roll();

            //Inform which user is playing
            gui.getUserButtonPressed(dialog[4] + " " + selectedPlayer.getName() + dialog[5], dialog[6]);
            //Uses balance value in GUI, since it displays on GUI at all times, and works like a score.

            int DieSum = Die.getSum(d1, d2) + 2;

            //if the game hasn't ended, continue
            if (!gameEnd) {
                // Moves the cars around the field and gives consequence- see the Cars Class under - src/main/java/Cars
                actualField=Cars.moveCars(DieSum, selectedPlayer, PlayerArray, fields, AmountofPlayers, fieldNR);
      //          System.out.println(actualField);
                if (actualField==3 || actualField==9 || actualField==15 || actualField==21)
                {
                    //The cas is on a chance field
                    //System.out.println("Landet på Chance");

                   // chanKort.chanceFieldIsHit(actualField,selectedPlayer, gui,fields);
                    chanKort.chanceFieldIsHit(selectedPlayer,PlayerArray,fields, actualField, AmountofPlayers,fieldNR, gui);

                }

                //  Sets the current space for the selected player to a value
                int CurrentSpaceForSelectedPlayer = 0;
                for (int i = 0; i < fieldNR; i++) {
                    if (fields[i].hasCar(selectedPlayer))
                        CurrentSpaceForSelectedPlayer = i;
                }

                //  You get forced to buy the field, therefor (you want to buy)
                boolean wanttobuyYesNo = true;


                //  This handles the trades with rent and buying of fields - see at - src/main/java/Fields
                if (wanttobuyYesNo) {
                    String NewBalance = Fields.wannaBuyDoYou(OwnedtrueOwnedFalse,
                            AmountofPlayers,
                            selectedPlayer,
                            fields,
                            wanttobuyYesNo,
                            PlayerArray,
                            fieldNR,
                            CurrentSpaceForSelectedPlayer,
                            CosttoOwn, TitleF,
                            PlayerSpaceNRexcact,
                            JailOn);
                    selectedPlayer.setBalance(selectedPlayer.getBalance() + Integer.parseInt(NewBalance));
                    //System.out.println(NewBalance);       | EMPTY NOTE |
                }

                amountOfGameLoops++;

                //Negative balance is not allowed
                if (selectedPlayer.getBalance() < 0) selectedPlayer.setBalance(0);
            }

            //Shows description of the space you land on, and changes color
            gui.displayChanceCard(selectedPlayer.getName() + " | " + fields[DieSum].getTitle() + "\n" + fields[DieSum].getSubText());
            Fields.displayDescriptions(selectedPlayer, DieSum, fields);
            //Display Die on the Board
            Die.OnBoard(d1, d2, gui);

            //Switch selected player
            if (!(Objects.equals(fields[DieSum].getTitle(), "extra"))) {
                selection = !selection;
                playingPlayer++;
            }
            //Extra tour
            else if ((selectedPlayer.getBalance() <= -1)) {
                gui.showMessage(selectedPlayer.getName() + dialog[7]);
            }

            answerGameOk = false;

            //  Initialises values for displaying a winner
            String Winner = " ";
            int WinnerMoney = 0;
            int WinnerInt = 0;

            //  if someone loses, the game ends and a winner/ winners are decided
            if (selectedPlayer.getBalance() < 1) {

                //  Makes an array of potential winners
                String[] Winners = new String[AmountofPlayers];
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
                        for (int l=1;l<AmountofPlayers;l++) {
                            Winners[i] = (Winners[i-1] + " " + PlayerArray[i].getName());
                        }
                }

                //  Displaying the Winners
                gui.showMessage(Winners[WinnerInt] + dialog[8] + WinnerMoney);

            do {
                //  Select language for the game dialog
                answer_game = gui.getUserButtonPressed(dialog[9], dialog[10], dialog[11]);

                //  if anwser to "a new game" is no - stop the game
                if (answer_game.equals(dialog[11])) {
                    game_running = false;
                    answerGameOk = true;
                }
                //  if anwser to "a new game" is yes - keep it going
                if (answer_game.equals("y") || answer_game.equals("j") || answer_game.equals("o") || answer_game.equals(dialog[10])) {
                    answerGameOk = true;
                    game_running = true;

                    //  if game is restarting - Resets the Board
                    Cars.restart(PlayerArray, fields, AmountofPlayers, fieldNR);
                    Fields.RestartFieldTitles(TitleF, fieldNR, fields);
                    Fields.RestartOwnStatus(OwnedtrueOwnedFalse, fieldNR, AmountofPlayers);
                    for (int i = 0; i <AmountofPlayers; i++) {
                        PlayerSpaceNRexcact[i] = 0;
                    }

                }
            }
            while (!answerGameOk);
        }
            //end game if last selection to (wanna keep playing?) is no
            if (!game_running)
                System.exit(0);
        }
    }
    public static String txtReadAndReturn(File file, String LineNR) throws FileNotFoundException {
        Scanner TXTRDRscanner = new Scanner(file);
        int ReadLineNR = Integer.parseInt(LineNR);

        for (int i = 0; i <= ReadLineNR; i++) {
            if (i == ReadLineNR - 1) {
                //
                return TXTRDRscanner.nextLine();
            } else TXTRDRscanner.nextLine();
        }
        //  Closes the scanner after use
        TXTRDRscanner.close();
        TXTRDRscanner = new Scanner(file);
        return LineNR;
    }
}