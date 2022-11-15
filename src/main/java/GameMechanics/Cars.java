package GameMechanics;

import TheBoard.Base;
import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import java.awt.Color;

public class Cars {
//-------------------------------------------------------------------------------
//
//      Car Type Distributor
//
//-------------------------------------------------------------------------------
    private static GUI_Car.Type T1 = GUI_Car.Type.CAR;
    private static GUI_Car.Type T2 = GUI_Car.Type.RACECAR;
    private static GUI_Car.Type T3 = GUI_Car.Type.UFO;
    private static GUI_Car.Type T4 = GUI_Car.Type.TRACTOR;

    public static GUI_Car.Type setCarType(int i) {
        if (i==1) return T1;
        if (i==2) return T2;
        if (i==3) return T3;
        if (i==4) return T4;
        else return T1;
    }


    public static void CreateCars() {

    }

//-------------------------------------------------------------------------------
//
//          Moves car based off dice roll
//
//-------------------------------------------------------------------------------
    public static void moveCars(int DiceRollSum, GUI_Player currentplayer,
                         GUI_Player[] players, GUI_Street[] street,
                         int AmountofPlayers, int AmountofSpaces) {
        //  Initialise Values for movement of cars on the Board
        boolean[] PlayerNum =  new boolean[AmountofPlayers];
        boolean[] SpaceHasCurrentPlayer = new boolean[AmountofSpaces];
        int LocationCurrent=0;
        int Space = DiceRollSum-2;
        int LocationNEW = 0;

        //Checks the Location for the car that wants to move
        for (int i = 0;i<AmountofSpaces;i++){
            if (street[i].hasCar(currentplayer)) {
                SpaceHasCurrentPlayer[i] = true;
                LocationCurrent = i;
                LocationNEW = (LocationCurrent+Space)-24;
            }
            else SpaceHasCurrentPlayer[i] = false;
        }
        //Checks if each player is on the field and attaches a boolean value
        for (int i = 0;i<AmountofPlayers;i++) {
            if (street[LocationCurrent].hasCar(players[i]) && currentplayer!=players[i])
                PlayerNum[i] = true;
            else PlayerNum[i] = false;
        }
        //Removes all cars for the space that (the car that wants to move) is in
        for (int i = 0;i<AmountofSpaces;i++) {
            if (SpaceHasCurrentPlayer[i])
                street[LocationCurrent].removeAllCars();
        }
        //if boolean value of a car is true, they get put back into the space they were in
        for (int i = 0; i<AmountofPlayers;i++){
            if (PlayerNum[i])
                street[LocationCurrent].setCar(players[i], true);
        }
        //Moves the Player That wanted to move in the first place
        if (LocationCurrent+Space<=23)
        street[LocationCurrent+Space].setCar(currentplayer, true);
        else
            street[LocationNEW].setCar(currentplayer,true);
    }

//-------------------------------------------------------------------------------
//
//          Resets Cars to Start position
//
//-------------------------------------------------------------------------------
    //  Resets the GUI_Player
    public static void restart(GUI_Player PlayerArray[], GUI_Field[] fields, int AmountofPlayers, int AmountofSpaces) {
        for (int i = 0; i < AmountofSpaces; i++) fields[i].removeAllCars();
        for (int i=0;i<AmountofPlayers;i++){
            PlayerArray[i].setBalance(20-(AmountofPlayers-2)*(2));
            fields[0].setCar(PlayerArray[i], true);
        }
    }
//-------------------------------------------------------------------------------
//
//         Makes colors based off the HSB color system
//
//-------------------------------------------------------------------------------
    // Color creation for the cars - Look up HSB colors system online to learn more
    public static void CarColor(GUI_Car playerCars[], GUI_Player PlayerArray[], String Players, int PlayerNumber) {
        Color[] color = new Color[Integer.parseInt(Players)];
        for (int i =0;i<Integer.parseInt(Players);i++){
            String h = String.valueOf(i*0.18+1);
            color[i] = Color.getHSBColor(Float.parseFloat(h), 1f, 1f);
            PlayerArray[PlayerNumber].getCar().setPrimaryColor(color[PlayerNumber]);
        }

    }


//-------------------------------------------------------------------------------
//
//          Moves a specific car to a specific location
//
//-------------------------------------------------------------------------------
    static void moveCarTo(int AmountofPlayers, GUI_Player[] players, int LocationCurrent,
                           GUI_Player currentplayer, int LocationToMoveTo) {
        //Checks if each player is on the field and attaches a boolean value
        boolean[] PlayerNum = new boolean[AmountofPlayers];
        for (int i = 0; i < AmountofPlayers; i++) {
            PlayerNum[i] = false;
        }
        for (int i = 0; i < AmountofPlayers; i++) {
            if (Base.fields[LocationCurrent].hasCar(players[i]) && currentplayer != players[i])
                PlayerNum[i] = true;
            else PlayerNum[i] = false;
        }
        //Removes all cars for the space that (the car that wants to move) is in
        Base.fields[LocationCurrent].removeAllCars();

        //if boolean value of a car is true, they get put back into the space they were in
        //boolean[] JailOn = new boolean[2];
        for (int i = 0; i < AmountofPlayers; i++) {
            if (PlayerNum[i])
                Base.fields[LocationCurrent].setCar(players[i], true);
            Base.fields[LocationToMoveTo].setCar(currentplayer,true);
            //JailOn[0] = true;
            //JailOn[1] = true;
            }
        //return JailOn;
    }
}


