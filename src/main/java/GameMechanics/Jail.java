package GameMechanics;

import TheBoard.Base;

import EgneGuiKlasser.*;

public class Jail {
//-------------------------------------------------------------------------------
//
//          Makes a Register that keeps info on who has been jailed - sets free after board turn
//
//-------------------------------------------------------------------------------
    public static boolean[] JailRegister(int AmountofPlayers, MGUI_Street[] fields) {
        //  Finds the Space with JailVisit
        int JailVisitSpace=Base.JAILvisitlocation;
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
    public static boolean bailOut(MGUI_Player selectedPlayer, boolean[] skipPlayer) {
        if (selectedPlayer.getAmnistiKortHaves()) {
            skipPlayer[selectedPlayer.getNumber()] = false;
            selectedPlayer.setAmnistkortHaves(false);
        }
        return skipPlayer[selectedPlayer.getNumber()];
    }

    //      If person is jailed - skip turn - make person not jailed.
    public static boolean jailed(MGUI_Player selectedPlayer, boolean skipPlayer) {
        if (JailRegister(Base.AmountofPlayers, Base.fields)[selectedPlayer.getNumber()])
            skipPlayer=true;
        System.out.println("jailed ");
        JailRegister(Base.AmountofPlayers, Base.fields)[selectedPlayer.getNumber()] = false;
        return skipPlayer;
    }

    public static boolean JailsetTrue(MGUI_Player selectedPlayer, boolean skipPlayer) {
        if (skipPlayer)
            return false;
        return true;
    }




}
