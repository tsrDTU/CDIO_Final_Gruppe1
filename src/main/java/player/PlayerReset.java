package player;


//import gui_fields.GUI_Field;
//import gui_fields.GUI_Player;
import EgneGuiKlasser.*;
public class PlayerReset {
    //  Resets the GUI_Player
    public static void restart(MGUI_Player[] PlayerArray, MGUI_Field[] fields, int AmountofPlayers, int AmountofSpaces) {
        for (int i = 0; i < AmountofSpaces; i++) fields[i].removeAllCars();
        for (int i=0;i<AmountofPlayers;i++){
            PlayerArray[i].setBalance(30000/*20-(AmountofPlayers-2)*(2)*/);
            fields[0].setCar(PlayerArray[i], true);
        }
    }
}
