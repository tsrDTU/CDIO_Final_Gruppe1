import gui_fields.GUI_Street;

public class Base {
//----------------------------------------------------------------------------------------------
//
//          This class makes all universal values used in most other classes
//
//----------------------------------------------------------------------------------------------

    public static int fieldNR(){return 24;}
    //  makes the amount of fields on the board - changing it to 40 will make 40 spaces instead of the 24 currently

    public static GUI_Street[] fields = new GUI_Street[Base.fieldNR()];
    //  This makes the class for all the Streets based off the fieldNR

    public static int[] CosttoOwn = new int[Base.fieldNR()];
    //  defines the cost to own a field array (list)



}
