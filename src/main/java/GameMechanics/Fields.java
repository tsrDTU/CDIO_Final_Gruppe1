package GameMechanics;
import TheBoard.Base;
import cardClasses.Chance;
import gui_codebehind.GUI_Center;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;

import EgneGuiKlasser.*;


import static TheBoard.Base.*;
import static TheBoard.Language.dialog;

import EgneGuiKlasser.*;
//import static cardClasses.*;

public class Fields {
    private static boolean testMode=false;
//
//
//            Checks if anyone owns the space you give it
//
//
    public static boolean noOwnerShipCheck( int cellToCheck){
        for (int i = 0; i <= AmountofPlayers; i++) {
            if (Base.InitializeOwnedStat(AmountofPlayers)[cellToCheck][i]==1  &&  i!=i%3)
                return false;
        }
        return true;
    }




    public static boolean OwnedCheck(int[][] Ownedtrue, int selectedPlayersNR, int spaceNumber) {
        int AmountofPlayerOwnedSpaces=0;
        //boolean[] ArrayofOwnership = new boolean[fieldNR()];
        int[][] ArrayofOwnership = Base.InitializeOwnedStat(AmountofPlayers);
        for (int i = 0; i < fieldNR(); i++) {ArrayofOwnership[i][selectedPlayersNR] = 1;}
        for (int i = 0; i < fieldNR(); i++)
        {
            if (Ownedtrue[i][selectedPlayersNR + 1] == 0){
                AmountofPlayerOwnedSpaces++;
                ArrayofOwnership[i][selectedPlayersNR] = 0;
            }
            //System.out.println(i + " "+ ArrayofOwnership[i]);
        }
        return ArrayofOwnership[spaceNumber][selectedPlayersNR]==1;
        //System.out.println("---------------");
    }
    public static String MoveInJail(MGUI_Street[] fields,MGUI_Player[] PlayerArray,int CurrentSpaceForSelectedPlayer
            , MGUI_Player selectedPlayer, int THEfieldsNR, boolean[] JailOn, int[] PlayerSpaceNRexcact){
            int JailVisitSpace=Base.JailLocationOnBoard;
            Cars.moveCarTo(AmountofPlayers, PlayerArray, CurrentSpaceForSelectedPlayer, selectedPlayer, JailVisitSpace, fields);
            PlayerSpaceNRexcact[selectedPlayer.getNumber()] = JailVisitSpace;
            JailOn[selectedPlayer.getNumber()]=true;
            // System.out.println("player "+selectedPlayer.getNumber()+ " got jailed");  //Jail TestLine
            return "0";  // return value to add to someones balance
    }
    // ALL BELOW IS JAIL

    // ALL ABOVE IS JAIL

    public static int Find_THEfieldsNR(MGUI_Street[] fields, MGUI_Player selectedPlayer){
        int THEfieldsNR = 0;
        for (int i = 0; i < Base.fieldNR(); i++)
        {
            if (fields[i].hasCar(selectedPlayer))
                THEfieldsNR = i;
        }
        return THEfieldsNR;
    }

    public static void BuyCurrentProperty(MGUI_Player[] PlayerArray, MGUI_Player selectedPlayer, MGUI_Street[] fields,
                                          int THEfieldsNR, boolean GoOn, int[][] Ownedtrue, int CurrentSpaceForSelectedPlayer
            , boolean[] OwnStatus, int[] OwnerList)
                                        throws FileNotFoundException {

            //System.out.println("you bought the space");   | EMPTY NOTE
                Ownedtrue[CurrentSpaceForSelectedPlayer][selectedPlayer.getNumber() + 1] = 1;
//                OwnStatus[CurrentSpaceForSelectedPlayer] = true;
        OwnStatus[CurrentSpaceForSelectedPlayer]=true;
        OwnerList[CurrentSpaceForSelectedPlayer]=selectedPlayer.getNumber()+1;
//        System.out.println("changed own status");
//        System.out.println(Ownedtrue[CurrentSpaceForSelectedPlayer][selectedPlayer.getNumber()+1]);
//        System.out.println(Ownedtrue[CurrentSpaceForSelectedPlayer][selectedPlayer.getNumber()]);
        selectedPlayer.setBalance(selectedPlayer.getBalance()-Integer.parseInt(fields[THEfieldsNR].getRent()));
                //  Puts the name of the player who bought the space onto the title of the field
                fields[THEfieldsNR].setTitle(fields[THEfieldsNR].getTitle() + " " + selectedPlayer.getName());
                //  Returns a string that is used to add to the amount of money for the selected player
                //System.out.println(-CosttoOwn[THEfieldsNR]);      | EMPTY NOTE |
        //BoardCreator.CostofField();


        // OVENFOR KAN K??BSV??RDI ??NDRES


    }



    public static boolean OwnerShip(int[] Ownedtrue,int TheFieldsNR) {
        boolean x = false;
            if (Ownedtrue[TheFieldsNR]==1)
                x = true;
            return x;
    }
    public static boolean[] OwnStatus() {
        boolean[] ownstatus = new boolean[Base.fieldNR()];
            for (int i = 0; i < Base.fieldNR(); i++)
            {
                ownstatus[i] = false;
            }
        return ownstatus;
    }

    public static int FindOwnerNumber(int[] OwnerList,int currentspace){
        return OwnerList[currentspace];
    }


    public static String wannaBuyDoYou(int[][] Ownedtrue,
                                       MGUI_Player selectedPlayer,
                                       //boolean boolforBUY,
                                       MGUI_Player[] PlayerArray,
                                       int CurrentSpaceForSelectedPlayer,
                                       int[] PlayerSpaceNRexcact,
                                       boolean[] JailOn, Chance chankort, MGUI gui, MGUI_Street[] fields,
                                       boolean[] OwnStatus, int[] OwnerList/*NEW*/) throws FileNotFoundException {
        boolean wannaBuy = false;
        boolean[] Playerboughtspace = new boolean[AmountofPlayers];
        int ny_bilPos;
        String NewBal;
        int owner;
        int THEfieldsNR = Find_THEfieldsNR(fields, selectedPlayer);
        for (int i = 0; i < Base.fieldNR(); i++) {
            if (fields[i].hasCar(selectedPlayer))
                THEfieldsNR = i;
  //          fields[i].setHotel(true); // TSR
  //          fields[i].setHouses(4); // TSR
        }

        boolean PassedGo = false;
        //  defines the exact locations for each player (used for the jail and Go spaces)
        if (PlayerSpaceNRexcact[selectedPlayer.getNumber()] < THEfieldsNR)
            PlayerSpaceNRexcact[selectedPlayer.getNumber()] = THEfieldsNR;
        else if (PlayerSpaceNRexcact[selectedPlayer.getNumber()] > THEfieldsNR) {
            PlayerSpaceNRexcact[selectedPlayer.getNumber()] = THEfieldsNR;
            PassedGo = true;
        }



// WHERE JAIL IS LOCATED
        if (THEfieldsNR== JailLocationOnBoard) {
            MoveInJail(fields, PlayerArray, CurrentSpaceForSelectedPlayer, selectedPlayer, THEfieldsNR,
                    JailOn, PlayerSpaceNRexcact);
            System.out.println("JAILJAILJAIL");
//            //  Finds the Space with JailVisit
//            int JailVisitSpace=7;
//            //  Moves car to JailVisitSpace
            Cars.moveCarTo(AmountofPlayers, PlayerArray, CurrentSpaceForSelectedPlayer, selectedPlayer, Base.JAILvisitlocation, fields);
            //  Changes PlayerSpace Info to new location and activates the JailOn Array
            PlayerSpaceNRexcact[selectedPlayer.getNumber()] = JAILvisitlocation;

            CurrentSpaceForSelectedPlayer = JAILvisitlocation;
            JailOn[selectedPlayer.getNumber()]=true;
            // System.out.println("player "+selectedPlayer.getNumber()+ " got jailed");  //Jail TestLine
//            return "-1";  // return value to add to someones balance
        }

// What happenes on jailvisit landing
        if (THEfieldsNR==Base.JAILvisitlocation) {
            System.out.println("Passed JAIL VISIT - - - - - - - -");
            return "0";
        }


// ADDS MONEY TO ACCOUNT AFTER PASSING START
        if (PassedGo) {
            PlayerArray[selectedPlayer.getNumber()].setBalance(selectedPlayer.getBalance() /*+ 4000*/);
            System.out.println(selectedPlayer.getName() + " Passed GO - - - - - - - -");
            //  sets balance according to jail status - and removes jail status for next trip around the board
            //System.out.println("BOARD PASSED");    // | EMPTY NOTE |

            if (JailOn[selectedPlayer.getNumber()]) {
                //System.out.println("Subtracted 2 from balance cause JAIL");       //  | EMPTY NOTE |
                selectedPlayer.setBalance(selectedPlayer.getBalance());
                JailOn[selectedPlayer.getNumber()] = false;
            }  //else System.out.println("Did not subtract 2 Player"+(selectedPlayer.getNumber()+1));
        }
        PassedGo = false;
//CHANCEKORT
        if (THEfieldsNR==2 || THEfieldsNR==7 || THEfieldsNR==17||THEfieldsNR== 22 || THEfieldsNR==30 || THEfieldsNR==34 || THEfieldsNR==37) {
//-----------------------------------------------------------------------------------------------------
//
//      HER SKAL DER ST?? HVAD DER SKER P?? CHANCEKORT
//
//-----------------------------------------------------------------------------------------------------


            //           Chance landetPaaChance = new Chance();
            //           landetPaaChance.traekEtChanceKort();

 //           Chance landetPaaChance = new Chance();
 //           landetPaaChance.traekEtChanceKort();


            //bilen har muligvis f??et ny positon efter chancekortet er eksekveret
            ny_bilPos = chankort.chanceFieldIsHit(selectedPlayer, PlayerArray, CurrentSpaceForSelectedPlayer, AmountofPlayers, 3, gui, fields);





            if (CurrentSpaceForSelectedPlayer != ny_bilPos)
            {
                // Hvis chankort.chanceFieldIsHit s??tter ny_bilPos == 10 er et g?? i f??ngsel chancekort trukket.
                if (ny_bilPos == JAILvisitlocation)
                {
//                    CurrentSpaceForSelectedPlayer=32;
//                    MoveInJail(fields, PlayerArray, CurrentSpaceForSelectedPlayer, selectedPlayer, THEfieldsNR,
//                            JailOn, PlayerSpaceNRexcact);
                    //            //  Moves car to JailVisitSpace
//                    wannaBuyDoYou(Ownedtrue,selectedPlayer,PlayerArray,CurrentSpaceForSelectedPlayer,
//                            PlayerSpaceNRexcact,JailOn,chankort,gui,fields,OwnStatus(),OwnerList);
                    //


                    MoveInJail(fields, PlayerArray, CurrentSpaceForSelectedPlayer, selectedPlayer, THEfieldsNR,
                            JailOn, PlayerSpaceNRexcact);
                    Cars.moveCarTo(AmountofPlayers, PlayerArray, CurrentSpaceForSelectedPlayer, selectedPlayer, Base.JAILvisitlocation, fields);
                    //  Changes PlayerSpace Info to new location and activates the JailOn Array
                    PlayerSpaceNRexcact[selectedPlayer.getNumber()] = JAILvisitlocation;
                    CurrentSpaceForSelectedPlayer = JAILvisitlocation;
                    JailOn[selectedPlayer.getNumber()]=true;

                    //
                    fields[JailLocationOnBoard].removeAllCars();
                    selectedPlayer.setBalance(selectedPlayer.getBalance()-4000);

//                    Cars.moveCarTo(AmountofPlayers, PlayerArray, CurrentSpaceForSelectedPlayer, selectedPlayer, Base.JAILvisitlocation, fields);
   //                 CurrentSpaceForSelectedPlayer=2;
                    /*
                    for (int i = 0; i < Base.fieldNR(); i++) {
                        if (Base.fields[i].hasCar(selectedPlayer)/*fields[i].hasCar(selectedPlayer)*/ //)
    //                        CurrentSpaceForSelectedPlayer = i;
                 //   }

//                    Cars.moveCarTo(AmountofPlayers, PlayerArray, 0, selectedPlayer, Base.JAILvisitlocation, fields);

                    //  Changes PlayerSpace Info to new location and activates the JailOn Array
                    PlayerSpaceNRexcact[selectedPlayer.getNumber()] = 6;
                    JailOn[selectedPlayer.getNumber()]=true;


                }
                else
                {
                    if (CurrentSpaceForSelectedPlayer + 1 > fieldNR())
                        CurrentSpaceForSelectedPlayer -= fieldNR();
                    Cars.moveCarTo(AmountofPlayers, PlayerArray, CurrentSpaceForSelectedPlayer, selectedPlayer, ny_bilPos, fields);
                }
            }
            //System.out.println(landetPaaChance.traekEtChanceKort());
            //System.out.println(" 3%3 ");     // | EMPTY NOTE |
            return "0";
        }

//READY FOR ASKING IF BUYING IS IN ORDER
        //  Defines an owner of a given space
        int SpaceOwner = 0;
        boolean GoOn = true;
        //  Checks if someone owns the space
        System.out.println("Er pladsen ejet? "+OwnStatus[THEfieldsNR]);
            if (OwnStatus[THEfieldsNR])
                    GoOn=true;

            //  Found an owner - SpaceOwner
            /*else {
                GoOn = false;
                SpaceOwner = i;
                break;
            }
        }*/
//BUYS A FIELD
        //  This checks if the field is owned, and continues if it is not
        if (GoOn)
            //  This is a check for if the player wants to buy, ((It does not function because the player is forced to buy))
            ///if (boolforBUY) {
            //  This checks if the selected player has enough money, And buys the space if it does.

            if (!OwnStatus[CurrentSpaceForSelectedPlayer] && Integer.parseInt(fields[CurrentSpaceForSelectedPlayer].getRent())!=0){
                System.out.println(CurrentSpaceForSelectedPlayer+" "+ fields[CurrentSpaceForSelectedPlayer].getRent()+" CSSP");
                BuyCurrentProperty(PlayerArray, selectedPlayer, fields, THEfieldsNR, GoOn, Ownedtrue,
                        CurrentSpaceForSelectedPlayer, OwnStatus, OwnerList);
// TSR

                if (testMode) {
                    String valg = gui.getUserButtonPressed("Du ejer dette felt. Vil du k??be et hus?", "Ja", "Nej");

                    if (valg.equals("Ja")) {
                        fields[CurrentSpaceForSelectedPlayer].setHouses(1);
                    }
                }

//                fields[CurrentSpaceForSelectedPlayer].setTitle(fields[CurrentSpaceForSelectedPlayer].getTitle()+ " "+selectedPlayer.getName());
//                OwnStatus[CurrentSpaceForSelectedPlayer]=true;
                System.out.println("bought space");
            }



            //            if (PlayerArray[selectedPlayer.getNumber()].getBalance() >= BoardCreator.CostofField()[THEfieldsNR] && GoOn) {
//                //System.out.println("you bought the space");   | EMPTY NOTE |
//                Ownedtrue[CurrentSpaceForSelectedPlayer][selectedPlayer.getNumber()+1] = 1;
//                //  Puts the name of the player who bought the space onto the title of the field
//                fields[THEfieldsNR].setTitle(fields[THEfieldsNR].getTitle()+" "+selectedPlayer.getName());
//                //  Returns a string that is used to add to the amount of money for the selected player
//                //System.out.println(-CosttoOwn[THEfieldsNR]);      | EMPTY NOTE |
//                return String.valueOf(-BoardCreator.CostofField()[THEfieldsNR]);
//                // OVENFOR KAN K??BSV??RDI ??NDRES
//                ///}
//            }
//            else return "Missing code in fields line 196 on saying no to buying a field"; // returns error message in case there is an error

//  Knows that someone owns the field, Pays rent and adds the rent to the SpaceOwners balance


            /*else {
                System.out.println("landed on own field");
                return "0";
            *//*{
            NewBal = String.valueOf(-BoardCreator.CostofField()[THEfieldsNR]);
            if (DoubleProperty.DoubleCost(Ownedtrue, selectedPlayer.getNumber(),CurrentSpaceForSelectedPlayer, fields)) {
                NewBal = String.valueOf(-BoardCreator.CostofField()[THEfieldsNR]*2);
                PlayerArray[SpaceOwner].setBalance(PlayerArray[SpaceOwner].getBalance()+BoardCreator.CostofField()[THEfieldsNR]*2);}
            else {NewBal = String.valueOf(-BoardCreator.CostofField()[THEfieldsNR]);
                PlayerArray[SpaceOwner].setBalance(PlayerArray[SpaceOwner].getBalance()+BoardCreator.CostofField()[THEfieldsNR]);}
            //System.out.println(-CosttoOwn[THEfieldsNR] + "   " +CosttoOwn[THEfieldsNR]);      | EMPTY NOTE |
            System.out.println("returned New Bal            - - - - - -");
            return NewBal;
        }*//*
                //  The selected player has landed on their own field and 0 is added to their account
                //System.out.println("    0");      | EMPTY NOTE |
                //return "0";

            }*/
/*

        System.out.println("Payed the owner x amount");
        System.out.println(Ownedtrue[CurrentSpaceForSelectedPlayer][selectedPlayer.getNumber()]);
        System.out.println(Ownedtrue[CurrentSpaceForSelectedPlayer][selectedPlayer.getNumber()+1]);
*/

        System.out.println("pay the owner");
        if (OwnStatus[CurrentSpaceForSelectedPlayer]) {
            PayTheOwner(fields, CurrentSpaceForSelectedPlayer, selectedPlayer, PlayerArray, OwnStatus, OwnerList);
                System.out.println("payed the owner");
        }
        return "0";
    }

    public static void PayTheOwner(MGUI_Street[] fields, int currentlocation, MGUI_Player selectedPlayer,
                                   MGUI_Player[] PlayerArray, boolean[] OwnStatus, int[] Ownerlist) {
        int owner;
        selectedPlayer.setBalance(selectedPlayer.getBalance()-Integer.parseInt(fields[currentlocation].getRent()));

            if (OwnStatus[currentlocation]){
                owner = FindOwnerNumber(Ownerlist,currentlocation)-1;
        PlayerArray[owner].setBalance(PlayerArray[owner].getBalance()+Integer.parseInt(fields[currentlocation].getRent()));}

    }

    public static int[] InitialiseOwnerList() {
        int[] OwList = new int[Base.fieldNR()];
        for (int i = 0; i < Base.fieldNR(); i++) {OwList[i] = 0;}
        return OwList;
    }
//    LINE STOP


    public static void displayDescriptions(MGUI_Street[] fields,int currentLocation) throws FileNotFoundException {
        //  Changes the color of the Discription space
        Color NewColor = Colors.ColorSpace(Integer.parseInt(fields[currentLocation].getDescription())/*Integer.parseInt(textReaderClass.textRDR(FileReference.DescriptionF*/, Integer.parseInt(String.valueOf(currentLocation+1)));
        //System.out.println(textReaderClass.textRDR(FileReference.DescriptionF, String.valueOf(currentLocation+1)));
        GUI_Center.getInstance().setBGColor(NewColor);
}


    public static void RestartFieldTitles(/*MGUI_Street[]*/File file, int AmountofSpaces, MGUI_Street[] fields) throws FileNotFoundException {
        //  Resets all titles
        for (int i=0;i<AmountofSpaces;i++){
            fields[i].setTitle(textReaderClass.textRDR(file, String.valueOf(i+1)));
        }
        // Backup -
        /*for (int i = 0; i < fieldNR(); i++) {
            fields[i].setTitle(String.valueOf(file));
        }*/
    }

    public static void RestartOwnStatus(int[][] OwnedtrueOwnedFalse, int fieldNR, int AmountofPlayers,
                                        boolean[] ownstatus, int[] OwnerList) {
        //  Goes through all fields and sets owned status to "Not Owned" - with an int 0
        for (int n = 0; n < fieldNR; n++) {
            //OwnedtrueOwnedFalse[n][0] = n;
            for (int i = 0; i < AmountofPlayers+1; i++) {
                OwnedtrueOwnedFalse[n][i] = 0;
            }
            for (int i = 0; i < Base.fieldNR(); i++) {
                ownstatus[i]=false;
                OwnerList[i]=0;
            }
        }
    }

    public static void ResetOnePlayerOwnStatus(MGUI_Player selectedplayer, int[][] OwnedtrueOwnedFalse){
        //  Goes through all fields and sets owned status to "Not Owned" - with an int 0
        for (int n = 0; n < Base.fieldNR(); n++) {
//OwnedtrueOwnedFalse[n][0] = n;
            if (OwnedtrueOwnedFalse[n][selectedplayer.getNumber()+1]!=0) {
                OwnedtrueOwnedFalse[n][selectedplayer.getNumber()+1] = 0;
            }
        }

    }

    public static void moveNewPosition (MGUI_Street[] fields,MGUI_Player[] PlayerArray,int CurrentSpaceForSelectedPlayer
            , MGUI_Player selectedPlayer, int NewPos, int[] PlayerSpaceNRexcact){

        Cars.moveCarTo(AmountofPlayers, PlayerArray, CurrentSpaceForSelectedPlayer, selectedPlayer, NewPos, fields);
        PlayerSpaceNRexcact[selectedPlayer.getNumber()] = NewPos;


    }

    public static void setTestMode()
    {
        testMode=true;
    }



/* TSR
    public void setHouse(MGUI_Street[] fields ,int field_nr, int antal_huse)
    {

    }


 */

}
/*

            FEJL DER H??BES AT BLIVE FIXED

1. - FIXED -
ved start af spil 2 pladerne har sv??rere ved at s??tte pladerne
2. - TB -
Terningen virker men skal s??ttes ned til 1  ( spil er bedre )
3. - FIXED -
forskellige sprog skal fungerer bedre
4.  - FIXED -
Vinderen skal vises rigtigt ( det g?? den ikke ved flere end 2 spillere )
5. - FIXED -
spiller 4 ser ud til altid at vinde
6. - FIXED -
spillere dupleres ved skift til fengsel



            TING TIL AT TILF??GE
1.
Forklaring m??ske? om hvordan spillet virker.
2.
Spring en tur over hvis spilleren kommer i fenglsel
3.
Vis flere vindere hvis de har samme m??ngde penge
4.
Kartoffel           Priority one
5.




*/
