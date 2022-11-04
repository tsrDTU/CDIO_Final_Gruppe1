import gui_codebehind.GUI_Center;
import gui_fields.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;


public class Fields {
    public static boolean OwnedCheck(int[][] Ownedtrue, int AmountofSpaces, int PlayerNR) {
        for (int i = 0; i < AmountofSpaces; i++) {
            if (Ownedtrue[i][PlayerNR + 1] == 1)
                return true;
            System.out.println("Fields OwnedCheck for loop returned true");
        }
        System.out.println("Fields OwnedCheck NOT for loop returned false");
        return false;
    }


    public static String wannaBuyDoYou(int[][] Ownedtrue,
                                       int price,
                                       int AmountofPlayers,
                                       GUI_Player selectedPlayer,
                                       GUI_Street[] fields,
                                       boolean boolforBUY,
                                       GUI_Player[] PlayerArray,
                                       int AmountofSpaces,
                                       int PlayerNR,
                                       int CurrentSpaceForSelectedPlayer,
                                       int[] CosttoOwn,
                                       File TitleF) throws IOException {
        boolean wannaBuy = false;
        boolean[] Playerboughtspace = new boolean[AmountofPlayers];
        int THEfieldsNR = 0;
        String NewBal = "0";
        for (int i = 0; i < AmountofSpaces; i++) {
            if (fields[i].hasCar(selectedPlayer))
                THEfieldsNR = i;
        }
        if (Objects.equals(fields[THEfieldsNR].getTitle(), "JAIL"))
            return "0"; //Jail
        if (Objects.equals(fields[THEfieldsNR].getTitle(), "JAIL VISIT"))
            return "0"; // Jail visit

        if ((THEfieldsNR%3)%3==0) {
            System.out.println("number is devideble by 3 twice");
//-----------------------------------------------------------------------------------------------------
//
//      HER SKAL DER STÅ HVAD DER SKER PÅ CHANCEKORT
//
//-----------------------------------------------------------------------------------------------------
            return "0";
        }

        int SpaceOwner = 0;
        boolean GoOn= true;
        for (int i=0;i<AmountofPlayers;i++) {
            if (Ownedtrue[THEfieldsNR][i + 1] == 0){
            GoOn = true;
        }
            else {
                GoOn = false;
                SpaceOwner=i;
                break;
            }
        }

        //  This checks if the field is owned, and continues if it is not
        if ((Ownedtrue[THEfieldsNR][selectedPlayer.getNumber()]) == 0) {
            //  This is a check for if the player wants to buy, ((It does not function because the player is forced to buy))
            if (boolforBUY) {
                //  This checks if the selected player has enough money, And buys the space if it does.
                if (PlayerArray[selectedPlayer.getNumber()].getBalance() >= CosttoOwn[THEfieldsNR] && GoOn) {
                    System.out.println("you bought the space");
                    Ownedtrue[CurrentSpaceForSelectedPlayer][selectedPlayer.getNumber()+1] = 1;
                    return String.valueOf(-CosttoOwn[THEfieldsNR]);
                    //  Returns a string that is used to add to the amount of money for the selected player
                }


                /*
                for (int i = 0; i < AmountofSpaces; i++) {
                    if (fields[i].hasCar(selectedPlayer)) {
                        if (PlayerArray[selectedPlayer.getNumber()].getBalance() >= price) {
                            PlayerArray[selectedPlayer.getNumber()].setBalance(PlayerArray[selectedPlayer.getNumber()].getBalance() - price);
                            System.out.println("you bought the space");
                            for (int k = 0; k < AmountofPlayers; k++) {
                                Ownedtrue[i][selectedPlayer.getNumber() + 1] = 1;
                                System.out.println("attempted to set field to bought state");
                                */
                                //if (selectedPlayer == PlayerArray[k])
                                //    Ownedtrue[i][1] = 1;

                        } else return "Error";
                    }



            else if (Ownedtrue[THEfieldsNR][selectedPlayer.getNumber()] ==1 && GoOn) {
                System.out.println("selected player owned field detected");
                //int moneys = CosttoOwn[THEfieldsNR];
                //selectedPlayer.setBalance(selectedPlayer.getBalance() - moneys);
                return "0";
            }
                /*
                for (int i = 0; i < AmountofSpaces; i++) {
                    if (fields[i].hasCar(selectedPlayer)) {
                        int moneys = Integer.parseInt(fields[i].getRent());
                        selectedPlayer.setBalance(selectedPlayer.getBalance() - moneys);
                    }
                }
                */
                        /*
                        for (int k = 0; k < AmountofPlayers; k++) {
                            if (Objects.equals(PlayerArray[i].getName(), selectedPlayer.getName()))
                                selectedPlayer.setBalance(selectedPlayer.getBalance() - moneys);
                        }
                        */




                else {
                NewBal = String.valueOf(-CosttoOwn[THEfieldsNR]);
            System.out.println("Payed Rent");
            PlayerArray[SpaceOwner].setBalance(PlayerArray[SpaceOwner].getBalance()+CosttoOwn[THEfieldsNR]);
                    return NewBal;
                /*
                    for (int i =0;i<AmountofSpaces;i++) {
                    if (fields[i].hasCar(selectedPlayer)){
                        NewBal = String.valueOf(selectedPlayer.getBalance()+fields[i].getRent());
                        //selectedPlayer.setBalance(selectedPlayer.getBalance() + Integer.parseInt(fields[i].getRent()));
                        return NewBal;
                    }
                    else System.out.println("couldn't find the field of the player");
                }
                */

            }
        System.out.println("you own this field");;
        return "0";
        

    }








    /*    NOTE FOR NEW FIELD COLOR SYSTEM
    public static void initialiseFields(GUI_Fields[] fields, String felter, AmountofFields);
    String[] ColorNR = new String[8];
    String[0] ColorNR = "white";
    String[1] ColorNR = "red;
    String[2] ColorNR = "yellow";
    String[3] ColorNR = "green";
    String[4] ColorNR = "blue";
    String[5] ColorNR = "brown";
    String[6] ColorNR = "cyan";
    String[7] ColorNR = "magenta";
    String[8] ColorNR = "orange";

//Define Colors based on description
    GUI_Color[] Colors = new GUI_Color[8] // this makes the amount of colors to display
    Colors[0] = new GUI_Color(Color.WHITE)
    Colors[1] = new GUI_Color(Color.RED)
    Colors[2] = new GUI_Color(Color.YELLOW)
    Colors[3] = new GUI_Color(Color.GREEN)
    Colors[4] = new GUI_Color(Color.BLUE)
    Colors[5] = new GUI_Color(Color.decode("#a52a2a")
    Colors[6] = new GUI_Color(Color.CYAN)
    Colors[7] = new GUI_Color(Color.MAGENTA)
    Colors[8] = new GUI_Color(Color.ORANGE)

for (int i = 0;i<AmountofFields;i++){
    if (ColorNR[i] == get color from description)
        field[i].setcolor(Colors[i])
    else field[i].setcolor(Color.WHITE);
}

    //Define all fields
    for (int i=0;i<AmountofFields;i++) {
    fields[i] = new GUI_Street(Get Line From Txt Title, GLFT subText,
     GLFT Description, GLFT rent, get Color based on description, Color.BLACK);
     if (getDescription == red)
     fields[i].setColor Color[descripNum];
     }
     */

    public static Color ColorSpace(int description, int spaceamount){
            if (spaceamount==9)
                description = description-9;//*(spaceamount)/9;
        if (description==0) return Color.WHITE;
        else if (description==1) return Color.RED;
        else if (description==2) return Color.BLUE;
        else if (description==3) return Color.getHSBColor(0.10f,1f,1f);
        else if (description==4) return Color.getHSBColor(0.90f,1f,1f);
        else if (description==5) return Color.CYAN;
        else if (description==6) return Color.YELLOW;
        else if (description==7) return Color.getHSBColor(0.80f,0.77f,0.8f);
        else if (description==8) return Color.GREEN;
        else return Color.WHITE;
    }

    public static void initialiseFields(GUI_Street[] fields,String fieldNR) {
        //GUI_Street[] fields = new GUI_Street[Integer.parseInt(fieldNR)];
        /*
        for (int i =0;i<Integer.parseInt(fieldNR)-1;i++){
            fields[i] = new GUI_Street("get this","20", "description","0", Color.GREEN, Color.BLACK);
        }

         */
        //GUI gui = new GUI(felter, Color.WHITE);
        /*
        if (felter.equals("12")) {
            //GUI_Street[] fields = new GUI_Street[11];
            fields[0] = new GUI_Street("2 Tower ", "+250", "", "250", Color.GREEN, Color.BLACK);
            fields[1] = new GUI_Street("3 Crater ", "-100", "", "-100", Color.RED, Color.BLACK);
            fields[2] = new GUI_Street("4 Palace gates", "+100", "", "+100", Color.GREEN, Color.BLACK);
            fields[3] = new GUI_Street("5 Cold Desert", "-20", "", "-20", Color.RED, Color.BLACK);
            fields[4] = new GUI_Street("6 Walled city", "+180", "", "+180", Color.GREEN, Color.BLACK);
            fields[5] = new GUI_Street("7 Monastery ", "0", "", "0", Color.YELLOW, Color.BLACK);
            fields[6] = new GUI_Street("8 Black cave ", "-70", "", "-70", Color.RED, Color.BLACK);
            fields[7] = new GUI_Street("9 Huts in the mountain", "+60", "", "+60", Color.GREEN, Color.BLACK);
            fields[8] = new GUI_Street("10 The Werewall", "-80 Ekstra tur", "", "-80", Color.GRAY, Color.BLACK);
            fields[9] = new GUI_Street("11 The pit ", "-50", "", "-50", Color.RED, Color.BLACK);
            fields[10] = new GUI_Street("12 Goldmine ", "+650", "", "+650", Color.GREEN, Color.BLACK);
        }

         */
    }
    public static void displayDescriptions(GUI_Player selected, int diceSum, GUI_Street[] fields){
        if (Integer.parseInt(fields[diceSum - 2].getRent()) > 0)
            GUI_Center.getInstance().setBGColor(Color.GREEN);
        else if (Integer.parseInt(fields[diceSum - 2].getRent()) == -80)
            GUI_Center.getInstance().setBGColor(Color.GRAY);
        else if (Integer.parseInt(fields[diceSum - 2].getRent()) < 0)
            GUI_Center.getInstance().setBGColor(Color.RED);
        else
            GUI_Center.getInstance().setBGColor(Color.YELLOW);
    }

    /*
    public static void Properties(GUI_Street[] fields, int AmountofFields, int AllowedAmountofOwnableFields, File DescriptionF, File subTextF, File TitleF, File rentF) throws FileNotFoundException {
        GUI_Ownable[] OwnableField = new GUI_Ownable[AllowedAmountofOwnableFields];
        for (int i=0;i<AllowedAmountofOwnableFields;i++){
            String I = String.valueOf(i);
            OwnableField[i] = new GUI_Ownable(
                    ColorSpace(Integer.parseInt(Main.txtReadAndReturn(DescriptionF,I)), AllowedAmountofOwnableFields),
                    Color.BLACK,
                    Main.txtReadAndReturn(TitleF,I),
                    Main.txtReadAndReturn(subTextF,I),
                    Main.txtReadAndReturn(rentF,I),
                    Main.txtReadAndReturn(rentF,I));

        }

    }
     */
    static void RestartFieldTitles(File file,int AmountofSpaces,GUI_Street[] fields) throws FileNotFoundException {
for (int i=0;i<AmountofSpaces;i++){
    fields[i].setTitle(Main.txtReadAndReturn(file, String.valueOf(i)));
}
    }

    static void RestartOwnStatus(int[][] OwnedtrueOwnedFalse,int fieldNR, int AmountofPlayers) {
        for (int n = 0; n < fieldNR; n++) {
            OwnedtrueOwnedFalse[n][0] = n;
            for (int i = 0; i < AmountofPlayers; i++) {
                OwnedtrueOwnedFalse[n][i] = 0;
            }
        }
    }
    }


