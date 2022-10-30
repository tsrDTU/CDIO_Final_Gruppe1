import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import java.awt.Color;
import org.w3c.dom.css.RGBColor;

import java.awt.*;
import java.awt.color.ColorSpace;

public class Cars {

    static void moveCars(int DiceRollSum, GUI_Player currentplayer, GUI_Player p1,
                         GUI_Player p2, GUI_Street[] street) {
        //Fields.initialiseFields(fields, "12");
        for (int i = 0; i <= 11; i++) {
            //Checks for 2 players on the same field
            if (street[i].hasCar(p1) && street[i].hasCar((p2))) {
                street[i].removeAllCars();
                if (currentplayer == p1) {
                    street[i].setCar(p2, true);
                }
                //Sets car back in its original place
                else street[i].setCar(p1, true);
                street[DiceRollSum - 2].setCar(currentplayer, true);
                i = 11;
            }
            //Push through with changing car location
            else if (street[i].hasCar(currentplayer)) {
                street[i].removeAllCars();
                street[DiceRollSum - 2].setCar(currentplayer, true);
                i = 11;
            }
        }
    }

    static void restart(GUI_Player PlayerArray[], GUI_Field[] fields) {
        PlayerArray[0].setBalance(1000);
        PlayerArray[1].setBalance(1000);
        for (int i = 0; i < 11; i++) fields[i].removeAllCars();
        fields[0].setCar(PlayerArray[0], true);
        fields[0].setCar(PlayerArray[1], true);
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


