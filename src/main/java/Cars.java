import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;

public class Cars {

    static void moveCars(int DiceRollSum,GUI_Player currentplayer, GUI_Player p1,
                         GUI_Player p2, GUI_Street[] street) {
        //Fields.initialiseFields(fields, "12");
        for (int i = 0; i <= 11; i++) {
            //Checks for 2 players on the same field
            if (street[i].hasCar(p1) && street[i].hasCar((p2))) {
                street[i].removeAllCars();
                if (currentplayer == p1) {
                    street[i].setCar(p2, true);
                    i = 11;
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
}