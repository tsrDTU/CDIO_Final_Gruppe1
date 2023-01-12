package Tests;

import EgneGuiKlasser.MGUI;
import EgneGuiKlasser.MGUI_Car;
import EgneGuiKlasser.MGUI_Player;
import EgneGuiKlasser.MGUI_Street;
import GameMechanics.Cars;

import GameMechanics.Fields;
import TheBoard.Base;
import TheBoard.BoardCreator;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.FileNotFoundException;

import static GameMechanics.Fields.*;
import static org.junit.jupiter.api.Assertions.*;

class FieldsClassTest {
    @Test
    public void noOwnerShipCheckTest(){         // Method is never used
        int list = 9;
        boolean OwnerValue = noOwnerShipCheck(2);
        assertEquals(2, 2);
    }
//    THE FOLLOWING TEST IS FOR SOMETHING THAT WAS NEVER IMPLEMENTED
//    @Test
//    public void OwnedCheckTest(){
//        int[][] OwnedTrue = new int[Base.fieldNR()][4+1]; // right side is players (this case 4 players)
////        OwnedTrue = Base.InitializeOwnedStat(4);
//        for (int i = 0; i < Base.fieldNR(); i++) {
//            OwnedTrue[i][0]=i;}
//        int pn1 =0; // player number 1
//        int pn2 = 1;
//        int pn3 = 2;
//        int pn4 = 3;
//        int ftt1 = 1; // field to test 1
//        int ftt2 = 2;
//        int ftt3 = 4;
//        int ftt4 = 7;
//        OwnedTrue[ftt1][pn1]=1;
//        OwnedTrue[ftt2][pn2]=1;
//        OwnedTrue[ftt3][pn3]=1;
//        OwnedTrue[ftt4][pn4]=1;
//        boolean T1 = OwnedCheck(OwnedTrue,pn1, ftt1);
//        boolean T2 = OwnedCheck(OwnedTrue,pn2, ftt2);
//        boolean T3 = OwnedCheck(OwnedTrue,pn3, ftt3);
//        boolean T4 = OwnedCheck(OwnedTrue,pn4, ftt4);
//    }

    @Test
    public void MoveInJailTest() throws FileNotFoundException {
    int JailLocation = Base.JailLocationOnBoard;
    int JailVisit = Base.JAILvisitlocation;
        MGUI_Street[] fields = BoardCreator.InitBoardFieldsGuts();
        MGUI_Player[] PlayerArray = new MGUI_Player[4];
        MGUI_Car[] playerCars = new MGUI_Car[4];
        playerCars[1] = new MGUI_Car(Color.RED, Color.BLACK, Cars.setCarType(1), MGUI_Car.Pattern.FILL);
        PlayerArray[1]= new MGUI_Player("Hannibal", 30000, playerCars[0]);
        boolean[] JailOn = new boolean[Base.fieldNR()];
        int[] Plocation = new int[Base.fieldNR()];
        int CSSP = 32; // Current Space for Selected Player
        MGUI_Player selectedplayer = PlayerArray[1];
        int FNR = 10; // FieldNR
//        Plocation = fields[selectedplayer.getNumber()];
        MoveInJail(fields, PlayerArray, CSSP, selectedplayer,
                FNR, JailOn, Plocation);
    }

    @Test
    public void Find_TheFieldsNRTest() throws FileNotFoundException {
        MGUI_Street[] fields = BoardCreator.InitBoardFieldsGuts();
        MGUI_Player[] PlayerArray = new MGUI_Player[4];
        MGUI_Car[] playerCars = new MGUI_Car[4];
        MGUI GUI = new MGUI(fields, Color.BLACK);
//        String[] Playernames = {"P1","P2","P3"};

        playerCars[0] = new MGUI_Car(Color.RED, Color.BLACK, Cars.setCarType(1), MGUI_Car.Pattern.FILL);
        playerCars[1] = new MGUI_Car(Color.GREEN, Color.BLACK, Cars.setCarType(1), MGUI_Car.Pattern.FILL);
        playerCars[2] = new MGUI_Car(Color.BLUE, Color.BLACK, Cars.setCarType(1), MGUI_Car.Pattern.FILL);
//        BoardCreator.PersonCreator(4, PlayerArray,Playernames,playerCars);

        PlayerArray[0]= new MGUI_Player("Hannibal", 30000, playerCars[0]);
        PlayerArray[1]= new MGUI_Player("Hannibal2", 30000, playerCars[1]);
        PlayerArray[2]= new MGUI_Player("Hannibal3", 30000, playerCars[2]);

        MGUI_Player selectedplayer1 = PlayerArray[0];
        MGUI_Player selectedplayer2 = PlayerArray[1];
        MGUI_Player selectedplayer3 = PlayerArray[2];


        Cars.moveCars(2,selectedplayer1,PlayerArray,3,Base.fieldNR()); // move cars
        Cars.moveCars(7,selectedplayer2,PlayerArray,3,Base.fieldNR()); // to locations
        Cars.moveCars(14,selectedplayer3,PlayerArray,3,Base.fieldNR());

        int FL1 = Find_THEfieldsNR(fields, selectedplayer1); // Field Location of Player1
        int FL2 = Find_THEfieldsNR(fields, selectedplayer2);
        int FL3 = Find_THEfieldsNR(fields, selectedplayer3);

        assertEquals(2, FL1);
        assertEquals(7, FL2);
        assertEquals(14, FL3);
    }

    @Test
    public void BuyCurrentPropertyTest(){
    // already in another class test
    }

    @Test
    public void OwnerShipTest(){
    int[] Ownedtrue = new int[Base.fieldNR()];
        for (int i = 0; i < Base.fieldNR(); i++) {Ownedtrue[i] = 0;} // empty array
        int CS1 = 0; // determining "Current Space 1"
        int CS2 = 1;
        int CS3 = 5;
        int CS4 = 2;
        int CS5 = 10;

        Ownedtrue[CS1]=1; // setting owners
        Ownedtrue[CS2]=1;
        Ownedtrue[CS3]=1;
        Ownedtrue[CS4]=1;
        Ownedtrue[CS5]=1;

        boolean Result1 = OwnerShip(Ownedtrue, CS1); // uses method to check if owned
        boolean Result2 = OwnerShip(Ownedtrue, CS2);
        boolean Result3 = OwnerShip(Ownedtrue, CS3);
        boolean Result4 = OwnerShip(Ownedtrue, CS4);
        boolean Result5 = OwnerShip(Ownedtrue, CS5);

        assertTrue(Result1); // test
        assertTrue(Result2);
        assertTrue(Result3);
        assertTrue(Result4);
        assertTrue(Result5);
        assertNotEquals(true, Ownedtrue[20]); // test not equal on empty spaces
        assertNotEquals(true, Ownedtrue[32]);
    }

    @Test
    public void OwnStatusTest(){
    boolean[] ownstatus = OwnStatus();
    boolean x = false;
        for (int i = 0; i < Base.fieldNR(); i++) {
            if (ownstatus[i])
                x=true;}
        assertEquals(false,ownstatus[0]);
        assertEquals(false, ownstatus[39]);
        assertFalse(x); // checks if any spaces are owned when initialising ownstatus
    }

    @Test
    public void FindOwnerNumberTest() throws FileNotFoundException {
        int[] OwnerList = InitialiseOwnerList();
        int S1 = 4; // Space 4
        int S2 = 6;
        int S3 = 12;
        int S4 = 0;
        int S5 = 20;

        int P1 = 0; // Player 1
        int P2 = 1;
        int P3 = 2;

        OwnerList[S1] = P1;
        OwnerList[S2] = P2;
        OwnerList[S3] = P3;
        OwnerList[S4] = P1;
        OwnerList[S5] = P3;

        int R1 = FindOwnerNumber(OwnerList, S1); // Result 1
        int R2 = FindOwnerNumber(OwnerList, S2);
        int R3 = FindOwnerNumber(OwnerList, S3);
        int R4 = FindOwnerNumber(OwnerList, S4);
        int R5 = FindOwnerNumber(OwnerList, S5);

        assertEquals(P1, R1); // tests if the right players own the right spaces
        assertEquals(P2, R2); // after input to FindOwnerNumber
        assertEquals(P3, R3);
        assertEquals(P1, R4);
        assertEquals(P3, R5);
    }

    @Test
    public void WannabuyTest(){

    }

    @Test
    public void PayTheOwnerTest() throws FileNotFoundException {
        MGUI_Street[] fields = BoardCreator.InitBoardFieldsGuts();
        MGUI_Player[] PlayerArray = new MGUI_Player[4];
        MGUI_Car[] playerCars = new MGUI_Car[4];
        MGUI GUI = new MGUI(fields, Color.BLACK);

        playerCars[0] = new MGUI_Car(Color.RED, Color.BLACK, Cars.setCarType(1), MGUI_Car.Pattern.FILL);
        playerCars[1] = new MGUI_Car(Color.GREEN, Color.BLACK, Cars.setCarType(1), MGUI_Car.Pattern.FILL);

        MGUI_Player selectedplayer1 = new MGUI_Player("hannibal1", 30000, playerCars[0]) ;
        MGUI_Player selectedplayer2 = new MGUI_Player("hannibal2", 30000, playerCars[1]) ;
        boolean[] OwnStatus = OwnStatus();
        int[] OwnerList = Fields.InitialiseOwnerList();
        OwnerList[4] = 1;
        OwnerList[7] = 0;

        int startMoney = 30000;
        int currentlocation1 = 4;
        int currentlocation2 = 8;
        int currentlocation3 = 10;
        //Test if balance of player changes after pay
        assertEquals(startMoney,selectedplayer1.getBalance());
        assertEquals(startMoney,selectedplayer2.getBalance());
    PayTheOwner(fields, currentlocation1, selectedplayer1, PlayerArray, OwnStatus, OwnerList);
    PayTheOwner(fields, currentlocation2, selectedplayer2, PlayerArray, OwnStatus, OwnerList);
        System.out.println(selectedplayer1.getBalance()+ " bal player1");
        System.out.println(selectedplayer2.getBalance()+ " bal player2");
        assertEquals(startMoney - Integer.parseInt(fields[4].getRent()), selectedplayer1.getBalance());
        assertEquals(startMoney - Integer.parseInt(fields[8].getRent()), selectedplayer2.getBalance());

        //Reset player balance to see if money is removed for one and added for another
        selectedplayer1.setBalance(30000);
        selectedplayer2.setBalance(30000);
        OwnerList[10] = 1;
        OwnStatus[10] = true;
        PayTheOwner(fields, currentlocation3, selectedplayer1, PlayerArray, OwnStatus, OwnerList);
        System.out.println("P1B "+selectedplayer1.getBalance());
        System.out.println("P2B "+selectedplayer2.getBalance());
    }

    @Test
    public void InitialiseOwnerTest(){

    }

    @Test
    public void displayDiscriptionsTest(){

    }

    @Test
    public void RestartFieldTitlesTest(){

    }

    @Test
    public void RestartOwnStatusTest(){

    }

    @Test
    public void RestartOnePlayerOwnStatusTest(){

    }

    @Test
    public void MoveNewPositionTest(){

    }
}