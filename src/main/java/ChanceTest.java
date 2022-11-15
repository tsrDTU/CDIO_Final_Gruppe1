import GameMechanics.Cars;
import TheBoard.Base;
import TheBoard.BoardCreator;
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


        /*
        Chancekort kortTrukket =mjChance.traekEtChanceKort();
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
        TheBoard.BoardCreator.InitBoardFieldsGuts();

        GUI gui = new GUI(Base.fields, Color.WHITE);
        language="Dansk";
        TheBoard.Language.initializeDialog(dialog, language);
        antal_kant = 6;
        AmountofPlayers=2;


        MjPlayer[] PlayerArray = new MjPlayer[AmountofPlayers];
        GUI_Car[] playerCars = new GUI_Car[AmountofPlayers];
        String[] PlayerName = new String[AmountofPlayers];

        BoardCreator.PersonCreator(AmountofPlayers,PlayerArray,PlayerName,playerCars);

//  Sets names for each player in a for loop and gives an adjacent car with a private color
        for ( i = 0; i < AmountofPlayers; i++) {
            //  Sets the car of each player
            PlayerName[i] = (gui.getUserString(dialog[3]+(i+1)+"?"));
            if (PlayerName[i].length() == 0) PlayerName[i] = ("Player" + (i + 1));
            playerCars[i] = new GUI_Car(Color.RED, Color.BLACK, Cars.setCarType(i+1), GUI_Car.Pattern.FILL);
            PlayerArray[i] = new MjPlayer(PlayerName[i], 20 - ((AmountofPlayers - 2) * (2)), playerCars[i]);
            GameMechanics.Cars.CarColor(playerCars, PlayerArray, String.valueOf(AmountofPlayers), i);
            //Set users role
            PlayerArray[i].setUserRole(userRoles[i]);



            gui.addPlayer(PlayerArray[i]);
        }
        Cars.restart(PlayerArray,fields, AmountofPlayers,fieldNR());


    }




}