package Tests;

import EgneGuiKlasser.MGUI;
import EgneGuiKlasser.MGUI_Car;
import EgneGuiKlasser.MGUI_Player;
import EgneGuiKlasser.MGUI_Street;
import GUI_Beskeder.Help;
import GameMechanics.*;
import TheBoard.Base;
import TheBoard.BoardCreator;
import cardClasses.Chance;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import static GameMechanics.Die.getSum;
import static TheBoard.Base.*;
import static TheBoard.Base.WinnerMoney;
import static TheBoard.Language.dialog;

    public class TestSituation_FlereSlag {

        /**
         * Parametrene til TestSituation_FlereSlag stiller 3 spilleres biler på definerede felter og bestemmer de første chance kort, som trækkes.
         * Når de første slag er slået de og første
         * chancekort er trukket kører spillet videre som normalt. TestSituation er en kopi af klassen main, som er rettet til.
         * @param pos_pl1: Position for player 1
         * @param pospl2: Position for player 2
         * @param pos_pl3: Position for player 3
         * @param foerste_terning_slag: Slag for spiller 1
         * @param chancekort_nr: Nummeret på det chancekort, som trækkes. Se venligst i cardClasses/Chance hvilket type kort det er.
         * @param antal_slag: Antal slag hvor spillernes position er bestemt af kaldet og hvor Player1's slag og træk af chancekort er bestemt.
         * @param bal1: Player1's start pengebeholdning.
         * @param bal2: Player2's startpengebeholdning.
         * @param bal3: Player3's start pengebeholdning.
         * @throws IOException
         */


//hej
//v1.2

        public TestSituation_FlereSlag(int[] pos_pl1, int[] pospl2,int[] pos_pl3, int[] foerste_terning_slag, int[] chancekort_nr, int antal_slag, int bal1, int bal2, int bal3) throws IOException {
            {
                int slag_nr=0;

// kalhauge/BoardEngine


                String string_in, language, answer_game;
                int antal_kant, j, DialogNR = 2;
                boolean game_running, answerGameOk;
                game_running = true;
                //int fieldNR = 24;
                //  ArrayList<String> userRoles = new ArrayList<>(Arrays.asList("Bil","Skib","Hund","Kat"));
                //String[] userRoles={"Bil","Skib","Hund","Kat"};
                String[] freeUserRoles;

                int[] OwnerList = Fields.InitialiseOwnerList();
                boolean[] ownstatus = Fields.OwnStatus();

                int DieSum;

//-------------------------------------------------------------------------------------------
//
//          Game Definitions
//
//-------------------------------------------------------------------------------------------


                //  Initialises the TheBoard.Base.fields with values from txt files in - src/main/Field-Guts - and - Color.Colorspace
                //TheBoard.BoardCreator.InitBoardFieldsGuts();
                MGUI_Street[] fields = TheBoard.BoardCreator.InitBoardFieldsGuts();
                //System.out.println(DescriptionF);

                //BoardCreator.SetGUItext();
                //  Sets up the background GUI (Graphical User Interface) to a plain white
//        MGUI gui = new MGUI(Base.fields, Color.WHITE);
                MGUI gui = new MGUI(fields, Color.BLACK);

//            Help help=new Help(gui);
                //  Asks if the language has been initialised and makes a button for user to select language
//            language = gui.getUserButtonPressed("Select Langage:", "Dansk", "English", "Francias", "Deutsch"); // Select language for the game dialog
                language = "dansk";
                //  Initialize the game dialog
                TheBoard.Language.initializeDialog(dialog, language);
/*
        do {
            //  Asks for the number of dots on the sides of the dice - 2 dices
            string_in = gui.getUserButtonPressed(dialog[1], "2", "3", "4", "5", "6"); //Quest the number of sides for the dice
            //Set the number sides for the dices
            if (string_in.length() > 0) {
                antal_kant = (int) string_in.charAt(0) - '0';
            } else antal_kant = 6;
        } while (antal_kant < 2 || antal_kant > 6);
*/

                antal_kant = 6;
/*
            //Asks how many players, and sets cars and players
            String Players = gui.getUserButtonPressed(dialog[DialogNR], "2", "3", "4"); DialogNR++;
            AmountofPlayers = Integer.parseInt(Players);


 */
                AmountofPlayers = 3;
//        JailInit(JailOn);
//        boolean[] JailOn = Base.JailOn();
                boolean[] JailOn = new boolean[AmountofPlayers];
                BoardCreator.JailInit(JailOn);

//        MGUI_Player[] PlayerArray = new MGUI_Player[AmountofPlayers];
                MGUI_Player[] PlayerArray = new MGUI_Player[AmountofPlayers];
                System.out.println(PlayerArray.length);


                MGUI_Car[] playerCars = new MGUI_Car[AmountofPlayers];
//        MGUI_Car[] playerCars[AmountofPlayers];
                String[] PlayerName = {"Player1", "Player2", "Player3"};

                //BoardCreator.PersonCreator(AmountofPlayers,PlayerArray,PlayerName,playerCars);
                System.out.println(JailOn.length);


//  Sets names for each player in a for loop and gives an adjacent car with a private color
                int k = AmountofPlayers;
                for (int i = 0; i < AmountofPlayers; i++) {
                    //  Sets the car of each player
//                PlayerName[i] = (gui.getUserString(dialog[DialogNR]+(i+1)+"?"));
//                if (PlayerName[i].length() == 0) PlayerName[i] = ("Player" + (i + 1));
                    playerCars[i] = new MGUI_Car(Color.RED, Color.BLACK, Cars.setCarType(i + 1), MGUI_Car.Pattern.FILL);
                    PlayerArray[i] = new MGUI_Player(PlayerName[i], 20 - ((AmountofPlayers - 2) * (2)), playerCars[i]);
                    GameMechanics.Colors.CarColor(playerCars, PlayerArray, String.valueOf(AmountofPlayers), i, fields);
                    //Set users role
                    //  int first = 0; for (int l = 0; l < AmountofPlayers; l++) {if (userRoles.size()>AmountofPlayers)
                    //      userRoles.remove(first); first++;}
                    //   String[] RoleArray = new String[k];
                    //  PlayerArray[i].setUserRole(gui.getUserButtonPressed(dialog[DialogNR+1], userRoles.toArray(RoleArray)));
                    //Remove this role from the list.
                    //     for (j=0;j<userRoles.size();j++)
                    //    {
                    //      if (userRoles.get(j).equals(PlayerArray[i].getUserRole())) {
                    //          userRoles.remove(j);
                    //          k--;
                    //userRoles[j] = userRoles[j-1];
                    //     }
                    //hi
                    //  }
                    gui.addPlayer(PlayerArray[i]);
                }
                DialogNR += 2;
                player.PlayerReset.restart(PlayerArray, fields, AmountofPlayers, fieldNR());


                int[][] OwnedtrueOwnedFalse = Base.InitializeOwnedStat(AmountofPlayers);

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
                MGUI_Player selectedPlayer;
                boolean gameEnd = false; //, lastMax = false;

                //Create the dices. Default 6 sides
                //String AmountofDice = gui.getUserButtonPressed("how many dice?", "1", "2");
                Die d1 = new Die();
                Die d2 = new Die();
//        System.out.println(d1.getFaceValue()+d2.getFaceValue()+" 22222222");


                // If sides are different from 6, set the number of sides.
        /*if (antal_kant != 6) {
            d1.setNumberOfSides(antal_kant);
            //d2.setNumberOfSides(antal_kant);
        }*/

                int playingPlayer = intselect;
                int playingPlayer2;

                //  Initialising something for GameMechanics.Jail and Start field
                int[] PlayerSpaceNRexcact = new int[AmountofPlayers];
                for (int i = 0; i < AmountofPlayers; i++) {
                    PlayerSpaceNRexcact[i] = 0;
                }
                //TheBoard.BoardCreator.JailInit(0);
                Chance chankort = new Chance();


                boolean[] skipPlayer = new boolean[AmountofPlayers];
                for (int i = 0; i < AmountofPlayers; i++) {skipPlayer[i]=false;}

//-------------------------------------------------------------------------------------------
//
//          Game Starts officially
//
//-------------------------------------------------------------------------------------------
                int amountOfGameLoops = 0;

                PlayerArray[0].setBalance(bal1);
                PlayerArray[1].setBalance(bal2);
                PlayerArray[2].setBalance(bal3);
                GameMechanics.Fields.setTestMode();

                while (!gameEnd) {
                    //while (PlayerArray[0].getBalance() < 3000 && PlayerArray[1].getBalance() < 3000 && !gameEnd) {
/*
                    if (slag_nr < antal_slag) {
                        if (slag_nr == 0) {
                            Fields.moveNewPosition(fields, PlayerArray, 0, PlayerArray[0], pos_pl1[slag_nr], PlayerSpaceNRexcact);
                            Fields.moveNewPosition(fields, PlayerArray, 0, PlayerArray[1], pospl2[slag_nr], PlayerSpaceNRexcact);
                            Fields.moveNewPosition(fields, PlayerArray, 0, PlayerArray[2], pos_pl3[slag_nr], PlayerSpaceNRexcact);
                        }
                        else
                        {

                            Fields.moveNewPosition(fields, PlayerArray, pos_pl1[slag_nr -1], PlayerArray[0], pos_pl1[slag_nr], PlayerSpaceNRexcact);
                            Fields.moveNewPosition(fields, PlayerArray, pospl2[slag_nr - 1], PlayerArray[1], pospl2[slag_nr], PlayerSpaceNRexcact);


                          Fields.moveNewPosition(fields, PlayerArray, pos_pl3[slag_nr - 1], PlayerArray[2], pos_pl3[slag_nr], PlayerSpaceNRexcact);


                        }
                    }
                    */

                    DialogNR = 5;
                    if (amountOfGameLoops == AmountofPlayers)
                        amountOfGameLoops = 0;
                    if (playingPlayer == AmountofPlayers)
                        playingPlayer = 0;


                    playingPlayer2 = playingPlayer;
                    if (selection) selectedPlayer = PlayerArray[playingPlayer];
                    else selectedPlayer = PlayerArray[playingPlayer2];


                    // Føste chsance kort som trækkes skal være chancekort_nr som specificeret i kaldet

                    if (slag_nr < antal_slag && selectedPlayer == PlayerArray[0]) {
                        chankort.setTestKortMode(chancekort_nr[slag_nr]);
                    }


//            if (selectedPlayer.getBalance() <= 0) {
//                Fields.ResetOnePlayerOwnStatus(selectedPlayer, OwnedtrueOwnedFalse,fields, CurrentSpaceForSelectedPlayer);
//                selectedPlayer.setBalance(0);
//                skipPlayer = true;
//                playingPlayer++;
//                if(playingPlayer>=AmountofPlayers)
//                    playingPlayer=0;
//            Jail.JailsetTrue(selectedPlayer, skipPlayer);
//
//        }


                    //skipPlayer = (Jail.jailed(selectedPlayer,skipPlayer));
                    if (selectedPlayer.getAmnistiKortHaves() && JailOn[selectedPlayer.getNumber()]) {
                        gui.showMessage("Da du har et amnesti kort løslades du hermed fra fængslet og kan køre videre");
                        Jail.bailOut(selectedPlayer, skipPlayer);
                        JailOn[selectedPlayer.getNumber()] = false;
                        //System.out.println("Spiller skippes ikke pga. GOJF kort");
                    } else if (JailOn[selectedPlayer.getNumber()]) {
                        skipPlayer[selectedPlayer.getNumber()] = true;
                        JailOn[selectedPlayer.getNumber()] = false;
                    }
                    if (skipPlayer[selectedPlayer.getNumber()]) {
                        playingPlayer++;
                        skipPlayer[selectedPlayer.getNumber()] = false;
                        //System.out.println("Player "+selectedPlayer.getNumber()+" smoked in jail");

                        if (amountOfGameLoops == AmountofPlayers)
                            amountOfGameLoops = 0;
                        if (playingPlayer == AmountofPlayers)
                            playingPlayer = 0;

                        playingPlayer2 = playingPlayer;
                        if (selection) selectedPlayer = PlayerArray[playingPlayer];
                        else selectedPlayer = PlayerArray[playingPlayer2];
                    }
                    //if (amountOfGameLoops == 0);
                    //GameMechanics.Jail.JailRegister(AmountofPlayers, TheBoard.Base.fieldNR(), fields);
                    //roll the dices

                    d1.dice_roll();
                    d2.dice_roll();
//            d1 = new Die();
//            d2 = new Die();


                    //Inform which user is playing
                    gui.getUserButtonPressed(dialog[DialogNR] + " " + selectedPlayer.getName() + dialog[DialogNR + 1] + " ", dialog[DialogNR + 2]);
                    DialogNR += 3;
                    //Uses balance value in GUI, since it displays on GUI at all times, and works like a score.

                    //int DieSum = d1.getFaceValue(); /*, getSum(d1,d2)*/
                    // Første slag skal ramme et felt defineret i kaldet. Derefter skal der slås normalt
                    if (slag_nr < antal_slag && selectedPlayer == PlayerArray[0]) {
                        DieSum = foerste_terning_slag[slag_nr];
                        System.out.println("DieSum: "+ DieSum);
                        System.out.println("Antal øjne: "+ foerste_terning_slag[slag_nr]);
                    } else DieSum = getSum(d1, d2);

//            System.out.println(d1.getFaceValue()+" "+d2.getFaceValue());

                    int CurrentSpaceForSelectedPlayer = 0;
                    CurrentSpaceForSelectedPlayer = 0;
                    for (int i = 0; i < Base.fieldNR(); i++) {
                        if (Base.fields[i].hasCar(selectedPlayer)/*fields[i].hasCar(selectedPlayer)*/)
                            CurrentSpaceForSelectedPlayer = i;
                    }

                    // Check om chancekort er modtaget
                    DieSum = selectedPlayer.haandterChanceKortModtaget(CurrentSpaceForSelectedPlayer, DieSum, fields, OwnedtrueOwnedFalse, gui);
                    //if the game hasn't ended, continue

                    if (!gameEnd) {
                        // Moves the cars around the field and gives consequence- see the GameMechanics.Cars Class under - src/main/java/GameMechanics.Cars
                        Cars.moveCars(DieSum, selectedPlayer, PlayerArray, /*fields*/ AmountofPlayers, Base.fieldNR());

                        //  Sets the current space for the selected player to a value
                        CurrentSpaceForSelectedPlayer = 0;
                        for (int i = 0; i < Base.fieldNR(); i++) {
                            if (Base.fields[i].hasCar(selectedPlayer)/*fields[i].hasCar(selectedPlayer)*/)
                                CurrentSpaceForSelectedPlayer = i;
                        }

                        //System.out.println(Fields.noOwnerShipCheck(5 ));

                        //System.out.println(d1.getFaceValue()+" ");


                        //  You get forced to buy the field, therefor (you want to buy)
                        boolean wanttobuyanswer;

                        if (Integer.parseInt(fields[CurrentSpaceForSelectedPlayer].getRent()) == 0)
                            wanttobuyanswer = true;
                        else if (ownstatus[CurrentSpaceForSelectedPlayer])
                            wanttobuyanswer = false;
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
                                    JailOn, chankort, gui, fields, ownstatus, OwnerList);
                            selectedPlayer.setBalance(selectedPlayer.getBalance() + Integer.parseInt(NewBalance));
                            //System.out.println(NewBalance);       | EMPTY NOTE |
                        } else
                            Fields.PayTheOwner(fields, CurrentSpaceForSelectedPlayer, selectedPlayer
                                    /*, OwnedtrueOwnedFalse*/, PlayerArray, ownstatus, OwnerList);

//                GameMechanics.textReaderClass.textRDR(DescriptionF, "12");
                        amountOfGameLoops++;

                        //Fields.OwnedCheck(OwnedtrueOwnedFalse,selectedPlayer.getNumber(), CurrentSpaceForSelectedPlayer);
                        //DoubleProperty.CostCheck(CurrentSpaceForSelectedPlayer);
                        //DoubleProperty.DoubleCost(OwnedtrueOwnedFalse,selectedPlayer.getNumber(),CurrentSpaceForSelectedPlayer);

                        //Negative balance is not allowed
                        if (selectedPlayer.getBalance() < 0) selectedPlayer.setBalance(0);
                    }

                    //Shows description of the space you land on, and changes color
                    int CSSP = CurrentSpaceForSelectedPlayer;
                    if (fields[PlayerSpaceNRexcact[selectedPlayer.getNumber()]].getTitle() == "Prøv lykken") {
//               gui.displayChanceCard(Chance.chanceCards[DieSum - 5].getKortNavnavn());
                    } else
                        gui.displayChanceCard(selectedPlayer.getName() + " | " + fields[PlayerSpaceNRexcact[selectedPlayer.getNumber()]
                                ].getTitle() + "\n" + fields[PlayerSpaceNRexcact[selectedPlayer.getNumber()]].getSubText());
                    Fields.displayDescriptions(fields, CurrentSpaceForSelectedPlayer/*, amountOfGameLoops*/);
                    //Display GameMechanics.Die on the Board
                    GameMechanics.Die.OnBoard(d1, d2, gui);/*, d2*/

                    //Changes currentSpaceForSelected Player to the new location
                    if (CurrentSpaceForSelectedPlayer + DieSum > Base.fieldNR())
                        CurrentSpaceForSelectedPlayer = CurrentSpaceForSelectedPlayer + DieSum - Base.fieldNR();

                    // Jeg kan ikke overskue hvorfor, men Diesum kan ramme udenfor arrayet efter den er lavet med haandterChanceKortModtaget
//            if (DieSum>=Base.fieldNR()) DieSum-= 23;
//            if (DieSum<0) DieSum= 0;

                    //Switch selected player
                    if (!(Objects.equals(fields[DieSum % Base.fieldNR()].getTitle(), "extra"))) {
                        selection = !selection;
                        playingPlayer++;
                    }
                    //Extra tour
                    else if ((selectedPlayer.getBalance() <= -1)) {
                        gui.showMessage(selectedPlayer.getName() + dialog[DialogNR]);
                        DialogNR++;
                    }
                    answerGameOk = false;


//            GameMechanics.Fields.RestartOnePlayerOwnStatusOwnStatus(selectedPlayer,OwnedtrueOwnedFalse);


//-------------------------------------------------------------------------------------------
//
//          Game Winner display
//
//-------------------------------------------------------------------------------------------
                    //  Initialises values for displaying a winner

                    //  if someone loses, the game ends and a winner/ winners are decided
                    if (selectedPlayer.getBalance() < 1) {

                        String Winners = new String();

                        Winners = GameMechanics.Winner.Values(PlayerArray, selectedPlayer);
                        WinnerMoney = GameMechanics.Winner.Money(PlayerArray, selectedPlayer);

                /*

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

                */

                        //  Displaying the Winners
                        gui.showMessage(Winners + dialog[DialogNR] + WinnerMoney);
                        DialogNR++;
//-------------------------------------------------------------------------------------------
//
//          Game End
//
//-------------------------------------------------------------------------------------------
                        do {

                            //  Ask for new game
                            answer_game = gui.getUserButtonPressed(dialog[DialogNR], dialog[DialogNR + 1], dialog[DialogNR + 2]);
                            //  if anwser to "a new game" is no - stop the game

                            game_running = EndGameQuestionController.AskEndQuestion(answer_game, game_running, answerGameOk
                                    , OwnedtrueOwnedFalse, DialogNR, PlayerSpaceNRexcact, PlayerArray, OwnerList, ownstatus);
                            answerGameOk = true;
//                    if (answer_game.equals(dialog[DialogNR+2])) {
//                        game_running = false;
//                        answerGameOk = true;
//                    }   //  else restart the game
//                    else {
//                        answerGameOk = true;
//                        GameMechanics.Cars.restart(PlayerArray, Base.fields, AmountofPlayers, Base.fieldNR());
//                        GameMechanics.Fields.RestartFieldTitles(/*fields*/TitleF, Base.fieldNR(), Base.fields);
//                        GameMechanics.Fields.RestartOwnStatus(OwnedtrueOwnedFalse, Base.fieldNR(), AmountofPlayers);
//                        for (int i = 0; i < AmountofPlayers; i++) {
//                            PlayerSpaceNRexcact[i] = 0;
//                        }
//                    }


                        }
                        //more
                        while (!answerGameOk);
                    }
                    if (gameEnd) System.exit(0);

                    //end game if last selection to (wanna keep playing?) is no
                    // if
//                System.exit(0);

                  if (selectedPlayer==PlayerArray[0]) {
                      slag_nr++;
                      System.out.println("Næste slag");
                  }
                }
            }
        }
        //-------------------------------------------------------------------------------------
        //
        //          Text reader
        //
        //-------------------------------------------------------------------------------------
        public static String txtReadAndReturn (File file, String LineNR) throws FileNotFoundException {
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




