package GameMechanics;

import TheBoard.Base;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;

import EgneGuiKlasser.*;

public class Jail {
//-------------------------------------------------------------------------------
//
//          Makes a Register that keeps info on who has been jailed - sets free after board turn
//
//-------------------------------------------------------------------------------
    public static boolean[] JailRegister( int AmountofPlayers, int AmountofSpaces, MGUI_Street[] fields) {
        //  Finds the Space with JailVisit
        int JailVisitSpace=0;
        for (int i = 0; i < AmountofSpaces; i++) {
            if (fields[i].getTitle() == "JAIL VISIT")
                JailVisitSpace = i;}
        //
        boolean[] JailOn = new boolean[AmountofPlayers];
        for (int i = 0; i < AmountofPlayers; i++) {
            JailOn[i]=false;}
        //  sets GameMechanics.Jail time
        //JailOn[selectedPlayer.getNumber()] = true;
        return JailOn;
    }
//-----------------------------------------------------------------------------------------------------
//
//      HER SKAL DER STÅ HVAD DER SKER PÅ JAIL
//      spiller lander på JAIL, bil placeres på JAIL VISIT,      SKIP TURN - boolean value og NO MONEY AT NEXT GO - boolean
//      hvis skip selectplayer+1 spiller neste gang og SKIP TURN boolean bliver false - næste gang omkring GO ændres boolean og der trækkes 2 fra balancen
//
//-----------------------------------------------------------------------------------------------------

    //     If person has GetOutOfJailFree card - don't skip player
    public static boolean bailOut(MGUI_Player selectedPlayer, boolean skipPlayer) {
        if (selectedPlayer.getAmnistiKortHaves()) {
            skipPlayer = false;
            selectedPlayer.setAmnistkortHaves(false);
        }
        return skipPlayer;
    }

    //      If person is jailed - skip turn - make person not jailed.
    public static boolean jailed(MGUI_Player selectedPlayer, boolean skipPlayer) {
        if (JailRegister(Base.AmountofPlayers, Base.fieldNR(), Base.fields)[selectedPlayer.getNumber()])
            skipPlayer=true;
        JailRegister(Base.AmountofPlayers, Base.fieldNR(), Base.fields)[selectedPlayer.getNumber()] = false;
        return skipPlayer;
    }

    public static boolean JailsetTrue(MGUI_Player selectedPlayer, boolean skipPlayer) {
        if (skipPlayer)
            return false;
        return true;
    }




}
