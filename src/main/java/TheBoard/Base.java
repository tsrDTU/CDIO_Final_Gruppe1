package TheBoard;

import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import player.MjPlayer;

import static TheBoard.Language.dialog;

public class Base {
//----------------------------------------------------------------------------------------------
//
//          This class makes all universal values used in most other classes
//
//----------------------------------------------------------------------------------------------
    public static int WinnerMoney = 0;
    public static String Winner = " ";
    public static int WinnerInt = 0;
    public static int fieldNR(){return 40;}
    public static int JailLocationOnBoard=10;
    //  makes the amount of fields on the board - changing it to 40 will make 40 spaces instead of the 24 currently

    //public static String Players;
    public static int[][] InitializeOwnedStat(int AmountofPlayers) {
        int[][] OwnedtrueOwnedFalse = new int[Base.fieldNR()][AmountofPlayers + 1];
        return OwnedtrueOwnedFalse;
    }





    static int[] CosttoOwn = new int[Base.fieldNR()];
    //  defines the cost to own a field array (list)


    public static int AmountofPlayers;


    public static GUI_Street[] fields = new GUI_Street[Base.fieldNR()];
    //  This makes the class for all the Streets based off the fieldNR


    public static MjPlayer[] PlayerArray = new MjPlayer[AmountofPlayers];
    public static boolean[] JailOn = new boolean[AmountofPlayers];
    public static MjPlayer[] PlayerArray(){return PlayerArray;}// = new MjPlayer[AmountofPlayers];
    public static boolean[] JailOn(){return JailOn;}

//    public static boolean[] JailOn(){return new boolean[AmountofPlayers];}


}
