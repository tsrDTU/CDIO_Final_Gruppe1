import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import java.awt.Color;
import org.w3c.dom.css.RGBColor;

import java.awt.*;
import java.awt.color.ColorSpace;

public class Cars {

    static void moveCars(int DiceRollSum, GUI_Player currentplayer, GUI_Player[] players, GUI_Street[] street, int AmountofPlayers, int AmountofSpaces) {
        //Fields.initialiseFields(fields, "12");
        boolean[] PlayerNum =  new boolean[AmountofPlayers];
        boolean[] SpaceHasCurrentPlayer = new boolean[AmountofSpaces];
            int LocationCurrent=0;
            int Space = DiceRollSum-2;

               //Checks the Location for the car that wants to move
                for (int i = 0;i<AmountofSpaces;i++){
                    if (street[i].hasCar(currentplayer)) {
                        SpaceHasCurrentPlayer[i] = true;
                        LocationCurrent = i;
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
        street[Space].setCar(currentplayer, true);

    }

    static void restart(GUI_Player PlayerArray[], GUI_Field[] fields, int AmountofPlayers) {
        for (int i = 0; i < 11; i++) fields[i].removeAllCars();
        for (int i=0;i<AmountofPlayers;i++){
            PlayerArray[i].setBalance(1000);
            fields[0].setCar(PlayerArray[i], true);
        }
    }

    static void CarColor(GUI_Car playerCars[], GUI_Player PlayerArray[], String Players, int PlayerNumber) {
        Color[] color = new Color[6];
        color[0] = Color.RED;
        color[1] = Color.BLUE;
        color[2] = Color.GREEN;
        color[3] = Color.YELLOW;
        color[4] = Color.LIGHT_GRAY;
        color[5] = Color.BLACK;
        //color[6] = Color.MAGENTA;
        //color[7] = Color.PINK;
        //color[8] = Color.CYAN;
        PlayerArray[PlayerNumber].getCar().setPrimaryColor(color[PlayerNumber]);
    }
}


