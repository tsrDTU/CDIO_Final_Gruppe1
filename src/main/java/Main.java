import GameMechanics.Cars;
import GameMechanics.Die;
import GameMechanics.Fields;
import GameMechanics.Jail;
import TheBoard.Base;
import TheBoard.BoardCreator;
import TheBoard.Language;
import cardClasses.Chance;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import gui_main.GUI;
import gui_fields.GUI_Car;
import java.awt.*;
import java.io.*;
import java.util.Objects;
import java.util.Scanner;

import static Files.FileReference.*;
import static GameMechanics.textReaderClass.textRDR;
import static TheBoard.Base.*;
import static TheBoard.BoardCreator.JailInit;

//v1.0

public class Main {



    public static void main(String[] args) throws IOException {
        //  initialises language and gameanwser - makes dialog strings
        String string_in, language, answer_game;
        String[] dialog = new String[12];
        //  initialises the antal_kant for dice and language statements
        int antal_kant;
        boolean language_ok, game_running, answerGameOk;
        //  sets game to run - sets the amount of fields
        game_running = true;
        //int fieldNR = 24;


        //  Initialises the TheBoard.Base.fields with values from txt files in - src/main/Field-Guts - and - Color.Colorspace
        for (int i = 0; i < Base.fieldNR(); i++) {
            fields[i] = new GUI_Street(
                    textRDR(TitleF, String.valueOf(i + 1)),
                    textRDR(subtextF, String.valueOf(i + 1)),
                    textRDR(DescriptionF, String.valueOf(i + 1)),
                    textRDR(rentF, String.valueOf(i + 1)),
                    //fields[i] = new GUI_Street("","","","1",
                    Fields.ColorSpace(Integer.parseInt(textRDR(DescriptionF, String.valueOf(i + 1))), i),
                    Color.BLACK);
        }


        //BoardCreator.SetGUItext();
        //  Sets up the background GUI (Graphical User Interface) to a plain white
        GUI gui = new GUI(Base.fields, Color.WHITE);

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
        TheBoard.Language.initializeDialog(dialog, language);

        do {
            //  Asks for the number of dots on the sides of the dice - 2 dices
            string_in = gui.getUserButtonPressed(dialog[0], "2", "3", "4", "5", "6"); //Quest the number of sides for the dice
            //Set the number sides for the dices
            if (string_in.length() > 0) {
                antal_kant = (int) string_in.charAt(0) - '0';
            } else antal_kant = 6;
        } while (antal_kant < 2 || antal_kant > 6);



            //Asks how many players, and sets cars and players
            String Players = gui.getUserButtonPressed(dialog[11], "2", "3", "4");
            int AmountofPlayers = Integer.parseInt(Players);


        GUI_Player[] PlayerArray = new GUI_Player[Integer.parseInt(Players)];
        GUI_Car[] playerCars = new GUI_Car[Integer.parseInt(Players)];
        String[] PlayerName = new String[Integer.parseInt(Players)];
        BoardCreator.PersonCreator(AmountofPlayers,PlayerArray,PlayerName,playerCars);



//  Sets names for each player in a for loop and gives an adjacent car with a private color
        for (int i = 0; i < AmountofPlayers; i++) {
            //  Sets the car of each player
            PlayerName[i] = (gui.getUserString(dialog[i]));
            if (PlayerName[i].length() == 0) PlayerName[i] = ("Player" + (i + 1));
            playerCars[i] = new GUI_Car(Color.RED, Color.BLACK, GUI_Car.Type.CAR, GUI_Car.Pattern.FILL);
            PlayerArray[i] = new GUI_Player(PlayerName[i], 20 - ((AmountofPlayers - 2) * (2)), playerCars[i]);
            GameMechanics.Cars.CarColor(playerCars, PlayerArray, String.valueOf(AmountofPlayers), i);
            gui.addPlayer(PlayerArray[i]);
        }

        int[][] OwnedtrueOwnedFalse = Base.InitializeOwnedStat(AmountofPlayers).clone();

        //Initialise true false for OwnedNotOwnedFields
        //int[][] OwnedtrueOwnedFalse = new int[Base.fieldNR()][AmountofPlayers + 1];
        for (int n = 0; n < Base.fieldNR(); n++) {
            OwnedtrueOwnedFalse[n][0] = n;
            for (int i = 0; i < AmountofPlayers; i++) {
                OwnedtrueOwnedFalse[n][i] = 0;
            }
        }
        // The player who starts has been selected
        boolean selection = true;
        //Sets all player locations on the board to space 0
        for (int i = 0; i < AmountofPlayers; i++) {
            if (i > 0 && Base.fields[i] != null) Base.fields[0].setCar(PlayerArray[i], true);
        }


        int intselect = 0;
        //Create a selected player that will point at active player
        GUI_Player selectedPlayer;
        boolean gameEnd = false; //, lastMax = false;

        //Create the dices. Default 6 sides
        //String AmountofDice = gui.getUserButtonPressed("how many dice?", "1", "2");
            Die d1 = new Die();
            Die d2 = new Die();


        // If sides are different from 6, set the number of sides.
        if (antal_kant != 6) {
            d1.setNumberOfSides(antal_kant);
            d2.setNumberOfSides(antal_kant);
        }

        int playingPlayer = intselect;
        int playingPlayer2;

        //  Initialising something for GameMechanics.Jail and Start field
        int[] PlayerSpaceNRexcact = new int[AmountofPlayers];
        for (int i = 0; i < AmountofPlayers; i++) {
            PlayerSpaceNRexcact[i] =0;
        }
        TheBoard.BoardCreator.JailInit(AmountofPlayers);
/*
        boolean[][] JailOn = new boolean[AmountofPlayers][2];
        for (int i = 0; i < AmountofPlayers; i++) {
            JailOn[i][0] = false;
            JailOn[i][1] = false;
        }
*/
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
            //GameMechanics.Jail.JailRegister(selectedPlayer,AmountofPlayers, TheBoard.Base.fieldNR(), fields);
            //roll the dices
            d1.dice_roll();
            d2.dice_roll();

            //Inform which user is playing
            gui.getUserButtonPressed(dialog[4] + " " + selectedPlayer.getName() + dialog[5], dialog[6]);
            //Uses balance value in GUI, since it displays on GUI at all times, and works like a score.

            int DieSum = Die.getSum(d1, d2) + 2;

            //if the game hasn't ended, continue
            int CurrentSpaceForSelectedPlayer = 0;
            if (!gameEnd) {
                // Moves the cars around the field and gives consequence- see the GameMechanics.Cars Class under - src/main/java/GameMechanics.Cars
                Cars.moveCars(DieSum, selectedPlayer, PlayerArray, Base.fields, AmountofPlayers, Base.fieldNR());

                //  Sets the current space for the selected player to a value
                CurrentSpaceForSelectedPlayer = 0;
                for (int i = 0; i < Base.fieldNR(); i++) {
                    if (Base.fields[i].hasCar(selectedPlayer))
                        CurrentSpaceForSelectedPlayer = i;
                }

                //  You get forced to buy the field, therefor (you want to buy)
                boolean wanttobuyYesNo = true;


                //  This handles the trades with rent and buying of fields - see at - src/main/java/GameMechanics.Fields
                if (wanttobuyYesNo) {
                    String NewBalance = Fields.wannaBuyDoYou(OwnedtrueOwnedFalse,
                            AmountofPlayers,
                            selectedPlayer,
                            Base.fields,
                            wanttobuyYesNo,
                            PlayerArray,
                            Base.fieldNR(),
                            CurrentSpaceForSelectedPlayer,
                            BoardCreator.CostofField(), TitleF,
                            PlayerSpaceNRexcact,
                            JailInit(AmountofPlayers));
                    selectedPlayer.setBalance(selectedPlayer.getBalance() + Integer.parseInt(NewBalance));
                    //System.out.println(NewBalance);       | EMPTY NOTE |
                }

                amountOfGameLoops++;

                //Negative balance is not allowed
                if (selectedPlayer.getBalance() < 0) selectedPlayer.setBalance(0);
            }

            //Changes currentSpaceForSelected Player to the new location
            if (CurrentSpaceForSelectedPlayer + DieSum > Base.fieldNR())
                CurrentSpaceForSelectedPlayer = CurrentSpaceForSelectedPlayer + DieSum - Base.fieldNR();

            //Shows description of the space you land on, and changes color
            if (Base.fields[PlayerSpaceNRexcact[selectedPlayer.getNumber()]].getTitle() == "CHANCE") {
                gui.displayChanceCard(Chance.chanceCards[DieSum - 5].getKortNavnavn());
            } else
                gui.displayChanceCard(selectedPlayer.getName() + " | " + Base.fields[PlayerSpaceNRexcact[selectedPlayer.getNumber()]
                        ].getTitle() + "\n" + Base.fields[PlayerSpaceNRexcact[selectedPlayer.getNumber()]].getSubText());
            Fields.displayDescriptions(selectedPlayer, DieSum, Base.fields);
            //Display GameMechanics.Die on the Board
            GameMechanics.Die.OnBoard(d1, d2, gui);

            //Switch selected player
            if (!(Objects.equals(Base.fields[DieSum].getTitle(), "extra"))) {
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
                        for (int l = 1; l < AmountofPlayers; l++) {
                            Winners[i] = (Winners[i - 1] + " " + PlayerArray[i].getName());
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
                        GameMechanics.Cars.restart(PlayerArray, Base.fields, AmountofPlayers, Base.fieldNR());
                        GameMechanics.Fields.RestartFieldTitles(TitleF, Base.fieldNR(), Base.fields);
                        GameMechanics.Fields.RestartOwnStatus(OwnedtrueOwnedFalse, Base.fieldNR(), AmountofPlayers);
                        for (int i = 0; i < AmountofPlayers; i++) {
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