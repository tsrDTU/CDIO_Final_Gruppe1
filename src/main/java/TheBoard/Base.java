package TheBoard;

import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;

public class Base {
//----------------------------------------------------------------------------------------------
//
//          This class makes all universal values used in most other classes
//
//----------------------------------------------------------------------------------------------

    public static int fieldNR(){return 24;}
    //  makes the amount of fields on the board - changing it to 40 will make 40 spaces instead of the 24 currently

    //public static String Players;
    public static int[][] InitializeOwnedStat(int AmountofPlayers) {
        int[][] OwnedtrueOwnedFalse = new int[Base.fieldNR()][AmountofPlayers + 1];
        return OwnedtrueOwnedFalse;
    }




    static int[] CosttoOwn = new int[Base.fieldNR()];
    //  defines the cost to own a field array (list)



    //public static GUI_Player[] PlayerArray = new GUI_Player[AmountofPlayers];
      //  public static GUI_Car[] playerCars = new GUI_Car[AmountofPlayers];
       // public static String[] PlayerName = new String[AmountofPlayers];



    public static GUI_Street[] fields = new GUI_Street[Base.fieldNR()];
    //  This makes the class for all the Streets based off the fieldNR





}
