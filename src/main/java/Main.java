import Files.FileReference;
import GameMechanics.*;
import GUI_Beskeder.Help;
import TheBoard.Base;
import TheBoard.BoardCreator;
import cardClasses.Chance;
//import gui_fields.GUI_Player;
//import gui_fields.GUI_Street;
//import gui_main.GUI;
//import gui_fields.GUI_Car;
import java.awt.*;
import java.io.*;
import java.util.Objects;
import java.util.Scanner;

import static GameMechanics.Die.getSum;
import static TheBoard.Base.*;
import static TheBoard.Language.dialog;
import static java.awt.Color.GRAY;
//import player.MjPlayer;

import EgneGuiKlasser.*;
import gui_resources.Attrs;
//hej
//v1.2

public class Main {

// kalhauge/BoardEngine

    public static void main(String[] args) throws IOException {
        String string_in, language, answer_game;
        int antal_kant, j, DialogNR=2;
        boolean slaaet_ens;
        boolean game_running, answerGameOk;
        game_running = true;
        //int fieldNR = 24;

        // test
      //  ArrayList<String> userRoles = new ArrayList<>(Arrays.asList("Bil","Skib","Hund","Kat"));
        //String[] userRoles={"Bil","Skib","Hund","Kat"};
        String[] freeUserRoles;

        int[] OwnerList = Fields.InitialiseOwnerList();
        boolean[] ownstatus = Fields.OwnStatus();
        int[] antal_slag_i_faengsel=new int[7];
        String valg;


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

        Help help=new Help(gui);
        //  Asks if the language has been initialised and makes a button for user to select language
//            language = gui.getUserButtonPressed("Select Langage:", "Dansk", "English", "Francias", "Deutsch"); // Select language for the game dialog
            language= "Dansk";
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



//            String CheatAnswer = gui.getUserString(dialog[14]+". Cheat Code: ");

            //Asks how many players, and sets cars and players
            String Players = gui.getUserButtonPressed(dialog[DialogNR], "2", "3", "4","5","6"); DialogNR++;
            AmountofPlayers = Integer.parseInt(Players);

//        JailInit(JailOn);
//        boolean[] JailOn = Base.JailOn();
        boolean[] JailOn = new boolean[AmountofPlayers];
        BoardCreator.JailInit(JailOn);
//        MGUI_Player[] PlayerArray = new MGUI_Player[AmountofPlayers];
        MGUI_Player[] PlayerArray = new MGUI_Player[AmountofPlayers];
        System.out.println(PlayerArray.length);
        MGUI_Car[] playerCars = new MGUI_Car[AmountofPlayers];
        String[] PlayerName = new String[AmountofPlayers];

        //BoardCreator.PersonCreator(AmountofPlayers,PlayerArray,PlayerName,playerCars);
        System.out.println("Jail on"+ JailOn.length);


//  Sets names for each player in a for loop and gives an adjacent car with a private color
        int k = AmountofPlayers;
        for (int i = 0; i < AmountofPlayers; i++) {
            //  Sets the car of each player
            PlayerName[i] = (gui.getUserString(dialog[DialogNR]+(i+1)+"?"));
            if (PlayerName[i].length() == 0) PlayerName[i] = ("Player" + (i + 1));
            playerCars[i] = new MGUI_Car(Color.RED, Color.BLACK, Cars.setCarType(i+1), MGUI_Car.Pattern.FILL);
            PlayerArray[i] = new MGUI_Player(PlayerName[i], 30000, playerCars[i]);
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
        if (AmountofPlayers==6) playerCars[5].setPrimaryColor(Color.ORANGE);

        DialogNR+=2;
        player.PlayerReset.restart(PlayerArray,fields, AmountofPlayers,fieldNR());


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
//        if (Objects.equals(CheatAnswer, "C4"))
//            PlayerArray[0].setBalance(2000);
//        if (Objects.equals(CheatAnswer, "AB1"))
//            for (int i = 0; i < AmountofPlayers-1; i++) {
//                PlayerArray[i].setBalance(500);}

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
            PlayerSpaceNRexcact[i] =0;
        }
        //TheBoard.BoardCreator.JailInit(0);
        Chance chankort=new Chance();
        boolean[] skipPlayer = new boolean[AmountofPlayers];
        for (int a = 0; a < AmountofPlayers; a++) {skipPlayer[a]=false;}


//        if (AmountofPlayers>2 && Objects.equals(CheatAnswer, "MILLION"))
//            for (int i = 0; i < AmountofPlayers; i++) {PlayerArray[i].setBalance(5000000);}
//        if (Objects.equals(CheatAnswer,"MINMAX"))
//            for (int i = 0; i < AmountofPlayers; i++) {PlayerArray[i].setBalance(5);
//            PlayerArray[0].setBalance(9999999);
//            PlayerArray[1].setBalance(9999999);}
//
        int CurrentSpaceForSelectedPlayer = 0;
        boolean[] PlayerLost = new boolean[AmountofPlayers+1];
        for (int i = 0; i < AmountofPlayers+1; i++) {PlayerLost[i]=false;}
        PlayerArray[0].setBalance(1);
//-------------------------------------------------------------------------------------------
//
//          Game Starts officially
//
//-------------------------------------------------------------------------------------------
        int amountOfGameLoops = 0;
        int Round = 0;
        selectedPlayer = PlayerArray[0];
        playingPlayer2 = 1;

        slaaet_ens=false;

        while (!gameEnd) {
            //while (PlayerArray[0].getBalance() < 3000 && PlayerArray[1].getBalance() < 3000 && !gameEnd) {
            boolean[] SpaceHasCurrentPlayer = new boolean[AmountofPlayers];
            for (int i = 0; i < AmountofPlayers; i++) {SpaceHasCurrentPlayer[i]=false;}
            for (int i = 0; i < Base.fieldNR(); i++) {
                if (fields[i].hasCar(PlayerArray[selectedPlayer.getNumber()])&& PlayerLost[selectedPlayer.getNumber()]) {

                    for (int c = 0; c < AmountofPlayers; c++) {
                        if (fields[c].hasCar(PlayerArray[c]))
                            SpaceHasCurrentPlayer[c] = true;
                    }
                    fields[i].removeAllCars();
                    SpaceHasCurrentPlayer[selectedPlayer.getNumber()] = false;
                    for (int d = 0; d < AmountofPlayers; d++) {
                        if (SpaceHasCurrentPlayer[d]) fields[d].setCar(PlayerArray[d], true);
                    }
                    CurrentSpaceForSelectedPlayer=0;
                }

            }


//            for (int i = 0; i < AmountofPlayers; i++) {if (PlayerArray[i].getBalance()<=0) PlayerArray[i].setBalance(0);
//            }
//            for (int i = 0; i < AmountofPlayers; i++) {if (selectedPlayer.getBalance()==0) playingPlayer++;
//            }
                if (PlayerLost[selectedPlayer.getNumber()]){
                    playingPlayer2 = playingPlayer;
                    if (playingPlayer==AmountofPlayers) playingPlayer=0;
            if (selection) selectedPlayer = PlayerArray[playingPlayer];
            else selectedPlayer = /*PlayerArray[0]*/PlayerArray[playingPlayer2];}
//            if (playingPlayer2==AmountofPlayers) playingPlayer2=0;
//            if (PlayerLost[selectedPlayer.getNumber()]) {
//                playingPlayer= playingPlayer2
//                ;}
//            if (PlayerLost[selectedPlayer.getNumber()]) {
//                if (selectedPlayer.getNumber()+1==AmountofPlayers) {
//                    selectedPlayer = PlayerArray[0];}
//                    if (selectedPlayer == PlayerArray[0]){
//                        if (PlayerLost[AmountofPlayers]) selectedPlayer = PlayerArray[0];
//
//                        for (int i = 0; i < AmountofPlayers; i++) {
//                            if (PlayerLost[i]) {
//                                if (PlayerArray[i].getNumber() == AmountofPlayers) selectedPlayer = PlayerArray[0];
//                                else selectedPlayer = PlayerArray[i];
//                                if (!PlayerLost[selectedPlayer.getNumber()]) break;
//                            }
//
//                        }
//
//
//                    for (int i = 0; i < AmountofPlayers; i++) {if (PlayerArray[i].getBalance()<=0) PlayerArray[i].setBalance(0);
//                    }
//                }
//                else selectedPlayer = PlayerArray[selectedPlayer.getNumber()+1];
//            }

            if (!slaaet_ens) {
                DialogNR = 5;
                if (amountOfGameLoops == AmountofPlayers)
                    amountOfGameLoops = 0;

                if (playingPlayer >= AmountofPlayers)
                    playingPlayer = 0/*playingPlayer2*/;
//                for (int i = 0; i < Base.fieldNR(); i++) {
//                            if (!fields[i].hasCar(selectedPlayer)) {
//                                selectedPlayer=PlayerArray[(amountOfGameLoops%AmountofPlayers)];
//                                if (fields[i].hasCar(selectedPlayer)) selectedPlayer = PlayerArray[amountOfGameLoops];
////                                else selectedPlayer = PlayerArray[i];
//                                if (!PlayerLost[selectedPlayer.getNumber()]) break;
//                            }
//                        }
                playingPlayer2 = playingPlayer;
                if (selection) selectedPlayer = PlayerArray[playingPlayer];
                else selectedPlayer = /*PlayerArray[0]*/PlayerArray[playingPlayer2];
//                for (int i = 0; i < AmountofPlayers; i++) {
//                    if (PlayerLost[selectedPlayer.getNumber()]) {
//                        if (i==AmountofPlayers-1) selectedPlayer=PlayerArray[0];
//                            else selectedPlayer=PlayerArray[i+1];
//                    }
//                    else break;
//
//                }
            }
            else {
                if (amountOfGameLoops >= AmountofPlayers)
                    amountOfGameLoops = 0;
                if (!PlayerLost[selectedPlayer.getNumber()])
                    gui.showMessage(selectedPlayer.getName()+" Du har slået 2 ens og får et ekstra slag.");
                DialogNR = 5;
                slaaet_ens=false;
            }



//            if (selectedPlayer.getBalance() <= 0) {
//                Fields.ResetOnePlayerOwnStatus(selectedPlayer, OwnedtrueOwnedFalse/*,fields, CurrentSpaceForSelectedPlayer*/);
//                selectedPlayer.setBalance(0);
//                skipPlayer = true;
//                playingPlayer++;
//                if(playingPlayer>=AmountofPlayers)
//                    playingPlayer=0;
//            Jail.JailsetTrue(selectedPlayer, skipPlayer);
//
//        }



            //skipPlayer = (Jail.jailed(selectedPlayer,skipPlayer));
            ////////
            /*
            if (selectedPlayer.getAmnistiKortHaves() && JailOn[selectedPlayer.getNumber()]) {
                gui.showMessage("Da du har et amnesti kort løslades du hermed fra fængslet og kan køre videre");
                Jail.bailOut(selectedPlayer, skipPlayer);
                JailOn[selectedPlayer.getNumber()]=false;
                //System.out.println("Spiller skippes ikke pga. GOJF kort");
            } else if (JailOn[selectedPlayer.getNumber()]){
                skipPlayer[selectedPlayer.getNumber()] = true; JailOn[selectedPlayer.getNumber()]=false;}

             */
            if (JailOn[selectedPlayer.getNumber()])
            {
                if (selectedPlayer.getAmnistiKortHaves())
                {
                    gui.showMessage(selectedPlayer.getName()+" Da du har et amnesti kort løslades du hermed fra fængslet og kan køre videre");
                    Jail.bailOut(selectedPlayer, skipPlayer);
                    JailOn[selectedPlayer.getNumber()] = false;
                    //System.out.println("Spiller skippes ikke pga. GOJF kort");
                } else {
                    valg = gui.getUserButtonPressed(selectedPlayer.getName()+" Du sidder i fængsel. Vælg", "Slå", "Betal 1000 kr.", "Stå over");
                    if (valg.equals("Betal 1000 kr.")) {
                        selectedPlayer.setBalance(selectedPlayer.getBalance() - 1000);
                        Jail.bailOut(selectedPlayer, skipPlayer);
                        JailOn[selectedPlayer.getNumber()] = false;
                    } else if (valg.equals("Stå over"))
                    {
                        if (antal_slag_i_faengsel[selectedPlayer.getNumber()] > 0)
                        {
                            skipPlayer[selectedPlayer.getNumber()] = true;
                            JailOn[selectedPlayer.getNumber()] = true;
                            antal_slag_i_faengsel[selectedPlayer.getNumber()]--;
                        }
                        else
                        {
                            gui.showMessage(selectedPlayer.getName()+" Du har siddet i fængsel 3 gange nu og skal videre. Der trækkes 1000 kr. på din konto");
                            selectedPlayer.setBalance(selectedPlayer.getBalance() - 1000);
                            Jail.bailOut(selectedPlayer, skipPlayer);
                            JailOn[selectedPlayer.getNumber()] = false;
                        }
                    }
                    else
                    {
                        if (antal_slag_i_faengsel[selectedPlayer.getNumber()] > 0)
                        {
                            d1.dice_roll();
                            d2.dice_roll();
                            Die.OnBoard(d1,d2,gui);
                            if (d1.getFaceValue() == d2.getFaceValue())
                            {
                                Jail.bailOut(selectedPlayer, skipPlayer);
                                JailOn[selectedPlayer.getNumber()] = false;
                                gui.showMessage("Du slog ens og er fri");
                            }
                            else
                            {
                                gui.showMessage("Du slog ikke ens og er stadig i fængsel");
                                Jail.bailOut(selectedPlayer, skipPlayer);
                                JailOn[selectedPlayer.getNumber()] = true;
                                skipPlayer[selectedPlayer.getNumber()]=true;
                                antal_slag_i_faengsel[selectedPlayer.getNumber()]--;
                            }
                        }
                        else
                        {
                            gui.showMessage("Du har siddet i fængsel 3 gange nu og skal videre. Der trækkes 1000 kr. på din konto");
                            selectedPlayer.setBalance(selectedPlayer.getBalance() - 1000);
                            Jail.bailOut(selectedPlayer, skipPlayer);
                            JailOn[selectedPlayer.getNumber()] = false;
                        }

                    }
                }
            }


            if (skipPlayer[selectedPlayer.getNumber()]) {
                System.out.println("Player skipped");
                playingPlayer++;
                skipPlayer[selectedPlayer.getNumber()]=false;
                //System.out.println("Player "+selectedPlayer.getNumber()+" smoked in jail");

                if (amountOfGameLoops == AmountofPlayers)
                    amountOfGameLoops = 0;
                if (playingPlayer >= AmountofPlayers)
                    playingPlayer = 0;

                playingPlayer2 = playingPlayer;
                if (selection) selectedPlayer = PlayerArray[playingPlayer];
                else selectedPlayer = PlayerArray[playingPlayer2];
            }
            ///////


            //if (amountOfGameLoops == 0);
            //GameMechanics.Jail.JailRegister(AmountofPlayers, TheBoard.Base.fieldNR(), fields);
            //roll the dices

//            if (Objects.equals(CheatAnswer, "CHANCE")&&Round<3)
//                if (selectedPlayer.getNumber()==PlayerArray[0].getNumber()){
//                    d1.dice_rollT(1);
//                    d2.dice_rollT(1);
//                    int DieSum = 2;}
//
//            else if (Objects.equals(CheatAnswer, "JAIL")){
//                if (selectedPlayer.getNumber()==PlayerArray[0].getNumber()){
//                    d1.dice_rollT(16);
//                    d2.dice_rollT(16);
//                    int DieSum = 32;
//                }


                d1.dice_roll();
                d2.dice_roll();
//                d1.dice_rollT(4);
//                d2.dice_rollT(4);

                if (d1.getFaceValue() == d2.getFaceValue()) slaaet_ens=true;

  //          d1 = new Die();
  //          d2 = new Die();
            if (PlayerLost[selectedPlayer.getNumber()]){
                d1.dice_rollT(0);
                d2.dice_rollT(0);
                slaaet_ens=false;
            }






            //Inform which user is playing
            if (!PlayerLost[selectedPlayer.getNumber()])
                gui.getUserButtonPressed(dialog[DialogNR] + " " + selectedPlayer.getName() + dialog[DialogNR+1]+" ", dialog[DialogNR+2]); DialogNR+=3;
            //Uses balance value in GUI, since it displays on GUI at all times, and works like a score.
            if (PlayerLost[selectedPlayer.getNumber()]) {
                d1.dice_rollT(0);
                d2.dice_rollT(0);
            }
            //int DieSum = d1.getFaceValue(); /*, getSum(d1,d2)*/
            int DieSum = getSum(d1,d2);
            GameMechanics.Die.OnBoard(d1, d2, gui);

//            System.out.println(d1.getFaceValue()+" "+d2.getFaceValue());


            CurrentSpaceForSelectedPlayer = 0;
            if (!PlayerLost[selectedPlayer.getNumber()])
                for (int i = 0; i < Base.fieldNR(); i++) {
                if (Base.fields[i].hasCar(selectedPlayer)/*fields[i].hasCar(selectedPlayer)*/)
                    CurrentSpaceForSelectedPlayer = i;
            }

            // Check om chancekort er modtaget
            if (!PlayerLost[selectedPlayer.getNumber()])
                DieSum=selectedPlayer.haandterChanceKortModtaget(CurrentSpaceForSelectedPlayer,DieSum, fields,OwnedtrueOwnedFalse,gui);
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

                if (Integer.parseInt(fields[CurrentSpaceForSelectedPlayer].getRent())==0)
                    wanttobuyanswer=true;
                else if (ownstatus[CurrentSpaceForSelectedPlayer]) {
                    wanttobuyanswer = false;
                    /*
                   if (OwnerList[CurrentSpaceForSelectedPlayer]== selectedPlayer.getNumber())
                   {
                    valg=gui.getUserButtonPressed("Du ejer dette felt. Vil du købe et hus?", "Ja", "Nej");

                    if (valg.equals("ja"))
                    {
                        Base.fields[CurrentSpaceForSelectedPlayer].setHotel(true);
                    }
                   }

                     */
                }
                else {
                    String wanttobuy = gui.getUserButtonPressed("Do you want to buy?", "Yes", "No");
                    if (wanttobuy.equals("Yes")) wanttobuyanswer = true;
                        else wanttobuyanswer = false;
                }

                //  This handles the trades with rent and buying of fields - see at - src/main/java/GameMechanics.Fields
                if (wanttobuyanswer && !ownstatus[CurrentSpaceForSelectedPlayer]) {
                    System.out.println(fields[CurrentSpaceForSelectedPlayer].getRent());
                    if (CurrentSpaceForSelectedPlayer== JailLocationOnBoard)
                        gui.getUserButtonPressed(dialog[12], dialog[13]);
                    String NewBalance = Fields.wannaBuyDoYou(OwnedtrueOwnedFalse,
                            selectedPlayer,
                            //wanttobuyYesNo,
                            PlayerArray,
                            CurrentSpaceForSelectedPlayer,
                            PlayerSpaceNRexcact,
                            JailOn, chankort, gui, fields, ownstatus, OwnerList);
                    selectedPlayer.setBalance(selectedPlayer.getBalance() + Integer.parseInt(NewBalance));
                    antal_slag_i_faengsel[selectedPlayer.getNumber()]=2;
                    //System.out.println(NewBalance);       | EMPTY NOTE |
                }
                else if (ownstatus[CurrentSpaceForSelectedPlayer] || wanttobuyanswer)
                    Fields.PayTheOwner(fields, CurrentSpaceForSelectedPlayer, selectedPlayer
                            , PlayerArray, ownstatus, OwnerList);
                if (!slaaet_ens)
                    amountOfGameLoops++;
                Round++;


//                GameMechanics.textReaderClass.textRDR(DescriptionF, "12");

                //Fields.OwnedCheck(OwnedtrueOwnedFalse,selectedPlayer.getNumber(), CurrentSpaceForSelectedPlayer);
                //DoubleProperty.CostCheck(CurrentSpaceForSelectedPlayer);
                //DoubleProperty.DoubleCost(OwnedtrueOwnedFalse,selectedPlayer.getNumber(),CurrentSpaceForSelectedPlayer);

                //Negative balance is not allowed
                if (selectedPlayer.getBalance() < 0) {selectedPlayer.setBalance(0);
                PlayerLost[selectedPlayer.getNumber()]=true;
                CurrentSpaceForSelectedPlayer=0;
                }
            }

            //Shows description of the space you land on, and changes color
            int CSSP =CurrentSpaceForSelectedPlayer;
           if (fields[PlayerSpaceNRexcact[selectedPlayer.getNumber()]].getTitle() == "Prøv lykken") {
//               gui.displayChanceCard(Chance.chanceCards[DieSum - 5].getKortNavnavn());
            } else
                gui.displayChanceCard(selectedPlayer.getName() + " | " + fields[PlayerSpaceNRexcact[selectedPlayer.getNumber()]
                        ].getTitle() + "\n" + fields[PlayerSpaceNRexcact[selectedPlayer.getNumber()]].getSubText());
            Fields.displayDescriptions(fields, CurrentSpaceForSelectedPlayer);
            //Display GameMechanics.Die on the Board
            GameMechanics.Die.OnBoard(d1, d2, gui);/*, d2*/

            //Changes currentSpaceForSelected Player to the new location
            if (CurrentSpaceForSelectedPlayer + DieSum > Base.fieldNR())
                CurrentSpaceForSelectedPlayer = CurrentSpaceForSelectedPlayer + DieSum - Base.fieldNR();

            // Jeg kan ikke overskue hvorfor, men Diesum kan ramme udenfor arrayet efter den er lavet med haandterChanceKortModtaget
//            if (DieSum>=Base.fieldNR()) DieSum-= 23;
//            if (DieSum<0) DieSum= 0;

            //Switch selected player
            /*if (Objects.equals(d1.getFaceValue(),d2.getFaceValue())) {
                selection = !selection;
                playingPlayer++;
            }*/
            //Extra tour
            else if ((selectedPlayer.getBalance() <= -1)) {
                gui.showMessage(selectedPlayer.getName() + dialog[DialogNR]); DialogNR++;
            }

            /*boolean[] */SpaceHasCurrentPlayer = new boolean[AmountofPlayers];
            for (int i = 0; i < AmountofPlayers; i++) {SpaceHasCurrentPlayer[i]=false;}
            if (selectedPlayer.getBalance()<=0) {
                PlayerLost[selectedPlayer.getNumber()] = true;
                Fields.ResetOnePlayerOwnStatus(selectedPlayer, OwnedtrueOwnedFalse);
                for (int i = 0; i < AmountofPlayers; i++) {
                    if (fields[CurrentSpaceForSelectedPlayer].hasCar(PlayerArray[i])) SpaceHasCurrentPlayer[i]=true;}
                fields[CurrentSpaceForSelectedPlayer].removeAllCars();
                SpaceHasCurrentPlayer[selectedPlayer.getNumber()]=false;
                for (int i = 0; i < AmountofPlayers; i++) {if (SpaceHasCurrentPlayer[i]) fields[CurrentSpaceForSelectedPlayer].setCar(PlayerArray[i], true);                }
                for (int i = 0; i < Base.fieldNR(); i++) {
                    if (selectedPlayer.getNumber()+1 == OwnerList[i]){
                        OwnerList[i] = 0;
                        fields[i].setTitle(textReaderClass.textRDR(FileReference.TitleF, String.valueOf(i+1)));
                        ownstatus[i] = false;
                    }

                }
            }
            if (slaaet_ens == false) {
                playingPlayer = amountOfGameLoops;
            }
//            if (PlayerLost[selectedPlayer.getNumber()+1]) playingPlayer=amountOfGameLoops+2;

            answerGameOk = false;


//            GameMechanics.Fields.RestartOnePlayerOwnStatusOwnStatus(selectedPlayer,OwnedtrueOwnedFalse);




//-------------------------------------------------------------------------------------------
//
//          Game Winner display
//
//-------------------------------------------------------------------------------------------
            //  Initialises values for displaying a winner

            //  if someone loses, the game ends and a winner/ winners are decided
            int Amountof0BalP = 0;
            for (int i = 0; i < AmountofPlayers; i++) {
                if (PlayerArray[i].getBalance()==0)
                    Amountof0BalP++;}
            if (Amountof0BalP==AmountofPlayers-1) {

                String Winners = new String();

                Winners = GameMechanics.Winner.Values(PlayerArray,selectedPlayer);
                WinnerMoney = GameMechanics.Winner.Money(PlayerArray,selectedPlayer);


                //  Displaying the Winners
                gui.showMessage(Winners + dialog[DialogNR] + WinnerMoney); DialogNR++;
//-------------------------------------------------------------------------------------------
//
//          Game End
//
//-------------------------------------------------------------------------------------------
//                if (selectedPlayer.)
//                answerGameOk=false;
                do {

                    //  Ask for new game
                    answer_game = gui.getUserButtonPressed(dialog[DialogNR], dialog[DialogNR+1], dialog[DialogNR+2]);
                    //  if anwser to "a new game" is no - stop the game

                    game_running = EndGameQuestionController.AskEndQuestion(answer_game,game_running,answerGameOk
                    ,OwnedtrueOwnedFalse,DialogNR,PlayerSpaceNRexcact, PlayerArray, OwnerList, ownstatus);
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
        }
    }
    //-------------------------------------------------------------------------------------
    //
    //          Text reader
    //
    //-------------------------------------------------------------------------------------
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