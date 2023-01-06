import EgneGuiKlasser.MGUI;
import GameMechanics.Cars;
import GameMechanics.Colors;
import GameMechanics.Fields;
import TheBoard.BoardCreator;
import TheBoard.Language;
import cardClasses.*;
import gui_fields.GUI_Car;
import gui_main.GUI;
import player.MjPlayer;

import java.awt.*;

import static TheBoard.Base.*;
import static TheBoard.Language.dialog;
import java.io.*;

class ChanceTest {

    public static void main(String[] args) throws IOException {
        System.out.println("main startet");
        Chance mjChance=new Chance();
        String string_in, language, answer_game;
        int antal_kant, AmountofPlayers,i,j;
        String[] userRoles={"Bil","Skib","Hund","Kat"};
        String[] freeUserRoles;
        MjPlayer selectedPlayer;
        int CurrentSpaceForSelectedPlayer = 0;
        /*
        Chancekort kortTrukket = mjChance.traekEtChanceKort();
        System.out.println(kortTrukket.getClass());

        if (kortTrukket.getClass().equals(ChanceBanktrans.class) )
        {
            System.out.println("Afsender: "+((ChanceBanktrans) kortTrukket).getAfsender());
            System.out.println("Modtager: "+((ChanceBanktrans) kortTrukket).getModtager());
            System.out.println("Beløb: "+((ChanceBanktrans) kortTrukket).getBeloeb());

        }
        if (kortTrukket.getClass().equals(ChanceAmnistiFeng.class))
        {
            System.out.println("Kort aktivt: "+ ((ChanceAmnistiFeng) kortTrukket).getAktivt()) ;
            System.out.println("Indehaver: "+((ChanceAmnistiFeng) kortTrukket).getIndehaver());
        }
        if (kortTrukket.getClass().equals(Chance2Farver.class))
        {
            System.out.println("Farve 1: "+((Chance2Farver) kortTrukket).getFarve1());
            System.out.println("Farve 2: "+((Chance2Farver) kortTrukket).getFarve2());
        }
        if (kortTrukket.getClass().equals(ChanceRykFremTilFelt.class))
        {
            System.out.println("Destination felt: "+ ((ChanceRykFremTilFelt) kortTrukket).getDestinationsFelt());
        }
        if (kortTrukket.getClass().equals(ChanceOverdragelseskort.class))
        {
            System.out.println("Modtager: "+((ChanceOverdragelseskort) kortTrukket).getModtager());
            System.out.println("Aktivt"+((ChanceOverdragelseskort) kortTrukket).getAktivt());
        }
        if (kortTrukket.getClass().equals(ChanceKortFarve.class))
        {
            System.out.println("Farve: "+ ((ChanceKortFarve) kortTrukket).getFarvekode());

        }

        System.out.println(kortTrukket.getKortNavnavn());
        System.out.println(kortTrukket.getKortInfo());
        System.out.println(kortTrukket);

         */

        //  Initialises the TheBoard.Base.fields with values from txt files in - src/main/Field-Guts - and - Color.Colorspace
        BoardCreator.InitBoardFieldsGuts();

        MGUI gui = new MGUI(fields, Color.WHITE);
        language="Dansk";
        Language.initializeDialog(dialog, language);
        antal_kant = 6;
        AmountofPlayers=3;

        boolean[] JailOn = new boolean[AmountofPlayers+1];
        MjPlayer[] PlayerArray = new MjPlayer[AmountofPlayers];
        GUI_Car[] playerCars = new GUI_Car[AmountofPlayers];
        String[] PlayerName = new String[AmountofPlayers];

        BoardCreator.PersonCreator(AmountofPlayers,PlayerArray,PlayerName,playerCars);

//  Sets names for each player in a for loop and gives an adjacent car with a private color
        for ( i = 0; i < AmountofPlayers; i++) {
            //  Sets the car of each player
       //     PlayerName[i] = (gui.getUserString(dialog[3]+(i+1)+"?"));
            PlayerName[i] = "";
            if (PlayerName[i].length() == 0) PlayerName[i] = ("Player" + (i + 1));
            playerCars[i] = new GUI_Car(Color.RED, Color.BLACK, Cars.setCarType(i+1), GUI_Car.Pattern.FILL);
            PlayerArray[i] = new MjPlayer(PlayerName[i], 20 - ((AmountofPlayers - 2) * (2)), playerCars[i]);
            Colors.CarColor(playerCars, PlayerArray, String.valueOf(AmountofPlayers), i, fields);
            //Set users role
            PlayerArray[i].setUserRole(userRoles[i]);



            gui.addPlayer(PlayerArray[i]);
        }
        Cars.restart(PlayerArray,fields, AmountofPlayers,fieldNR());

        int[][] OwnedtrueOwnedFalse = InitializeOwnedStat(AmountofPlayers).clone();

        //Initialise true false for OwnedNotOwnedFields
        //int[][] OwnedtrueOwnedFalse = new int[Base.fieldNR()][AmountofPlayers + 1];
        /*
        for (int n = 0; n < Base.fieldNR(); n++) {
            OwnedtrueOwnedFalse[n][0] = n;
            for (int i = 0; i < AmountofPlayers; i++) {
                OwnedtrueOwnedFalse[n][i] = 0;
            }
        }

         */

        selectedPlayer = PlayerArray[1];

        //  Initialising something for GameMechanics.Jail and Start field
        int[] PlayerSpaceNRexcact = new int[AmountofPlayers];


        System.out.println(PlayerArray[0].getBalance()+" Balanc3 b3fore");
        System.out.println(PlayerArray[1].getBalance()+ " bal pl 2 bf");
        System.out.println(selectedPlayer.getBalance()+" selected "+selectedPlayer.getNumber());
        //Det kort man ønsker at teste vælges
        mjChance.setTestKortMode(5);




        gui.getUserButtonPressed(dialog[4] + " " + selectedPlayer.getName() + dialog[5], dialog[6]);

        //DiceRollSum=5 rammer chancen
        Cars.moveCars(5, selectedPlayer, PlayerArray, AmountofPlayers, fieldNR());
        //  Sets the current space for the selected player to a value
        CurrentSpaceForSelectedPlayer = 0;

        for ( i = 0; i < fieldNR(); i++) {
            if (fields[i].hasCar(selectedPlayer))
                CurrentSpaceForSelectedPlayer = i;
        }



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
                    JailOn, mjChance, gui, fields);
            selectedPlayer.setBalance(selectedPlayer.getBalance() + Integer.parseInt(NewBalance));
            //System.out.println(NewBalance);       | EMPTY NOTE |
        }
        System.out.println(PlayerArray[0].getBalance()+" balanc3 after");
        System.out.println(PlayerArray[1].getBalance()+ " bal pl 2 after");
        System.out.println(selectedPlayer.getBalance()+" selected"+selectedPlayer.getNumber());

    }




}