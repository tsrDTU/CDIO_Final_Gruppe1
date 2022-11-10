package GameMechanics;

import gui_fields.GUI_Player;
import gui_fields.GUI_Street;

public class Jail {
//-------------------------------------------------------------------------------
//
//          Makes a Register that keeps info on who has been jailed - sets free after board turn
//
//-------------------------------------------------------------------------------
    static boolean[][] JailRegister(GUI_Player selectedPlayer, int AmountofPlayers, int AmountofSpaces, GUI_Street[] fields) {
        //  Finds the Space with JailVisit
        int JailVisitSpace=0;
        for (int i = 0; i < AmountofSpaces; i++) {
            if (fields[i].getTitle() == "JAIL VISIT")
                JailVisitSpace = i;
        }
        boolean[][] JailOn = new boolean[4][2];
        for (int i = 0; i < AmountofPlayers; i++) {
            JailOn[i][0]=false;
            JailOn[i][1]=false;
        }
        //  sets GameMechanics.Jail time
        JailOn[selectedPlayer.getNumber()][0] = true;
        JailOn[selectedPlayer.getNumber()][1] = true;
        return JailOn;
    }
//-----------------------------------------------------------------------------------------------------
//
//      HER SKAL DER STÅ HVAD DER SKER PÅ JAIL
//      spiller lander på JAIL, bil placeres på JAIL VISIT,      SKIP TURN - boolean value og NO MONEY AT NEXT GO - boolean
//      hvis skip selectplayer+1 spiller neste gang og SKIP TURN boolean bliver false - næste gang omkring GO ændres boolean og der trækkes 2 fra balancen
//
//-----------------------------------------------------------------------------------------------------
}
