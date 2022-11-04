import gui_codebehind.GUI_Center;
import gui_fields.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.Objects;


public class Fields {
    public static boolean OwnedCheck(int[][] Ownedtrue, int AmountofSpaces, int PlayerNR) {
        for (int i = 0; i < AmountofSpaces; i++) {
            if (Ownedtrue[i][PlayerNR + 1] == 1)
                return true;
        }
        return false;
    }


    public static String wannaBuyDoYou(int[][] Ownedtrue,
                                       int AmountofPlayers,
                                       GUI_Player selectedPlayer,
                                       GUI_Street[] fields,
                                       boolean boolforBUY,
                                       GUI_Player[] PlayerArray,
                                       int AmountofSpaces,
                                       int CurrentSpaceForSelectedPlayer,
                                       int[] CosttoOwn,
                                       File TitleF) {
        boolean wannaBuy = false;
        boolean[] Playerboughtspace = new boolean[AmountofPlayers];
        int THEfieldsNR = 0;
        String NewBal;
        for (int i = 0; i < AmountofSpaces; i++) {
            if (fields[i].hasCar(selectedPlayer))
                THEfieldsNR = i;
        }
        if (Objects.equals(fields[THEfieldsNR].getTitle(), "JAIL")){
//-----------------------------------------------------------------------------------------------------
//
//      HER SKAL DER STÅ HVAD DER SKER PÅ JAIL
//
//-----------------------------------------------------------------------------------------------------
            return "0"; //Jail
        }
        if (Objects.equals(fields[THEfieldsNR].getTitle(), "JAIL VISIT"))
            return "0";
        //  This checks if the field is even when devided by 3 twice - the location of the chance spaces
        if ((THEfieldsNR%3)%3==0) {
//-----------------------------------------------------------------------------------------------------
//
//      HER SKAL DER STÅ HVAD DER SKER PÅ CHANCEKORT
//
//-----------------------------------------------------------------------------------------------------
            //System.out.println(" 3%3 ");      | EMPTY NOTE |
            return "0";
        }
        //  Defines an owner of a given space
        int SpaceOwner = 0;
        boolean GoOn= true;
        //  Checks if someone owns the space
        for (int i=0;i<AmountofPlayers;i++) {
            if (Ownedtrue[THEfieldsNR][i + 1] == 0){
            GoOn = true;
            }
            //  Found an owner - SpaceOwner
            else {
                GoOn = false;
                SpaceOwner=i;
                break;
            }
        }

        //  This checks if the field is owned, and continues if it is not
        if (GoOn)
            //  This is a check for if the player wants to buy, ((It does not function because the player is forced to buy))
            if (boolforBUY) {
                //  This checks if the selected player has enough money, And buys the space if it does.
                if (PlayerArray[selectedPlayer.getNumber()].getBalance() >= CosttoOwn[THEfieldsNR] && GoOn) {
                    //System.out.println("you bought the space");   | EMPTY NOTE |
                    Ownedtrue[CurrentSpaceForSelectedPlayer][selectedPlayer.getNumber()+1] = 1;
                    //  Puts the name of the player who bought the space onto the title of the field
                    fields[THEfieldsNR].setTitle(fields[THEfieldsNR].getTitle()+" "+selectedPlayer.getName());
                    //  Returns a string that is used to add to the amount of money for the selected player
                    //System.out.println(-CosttoOwn[THEfieldsNR]);      | EMPTY NOTE |
                    return String.valueOf(-CosttoOwn[THEfieldsNR]);
                }
            }
            else return "Error"; // returns error message in case there is an error

        //  Knows that someone owns the field, Pays rent and adds the rent to the SpaceOwners balance
        else {
            NewBal = String.valueOf(-CosttoOwn[THEfieldsNR]);
            //System.out.println("Payed Rent");     | EMPTY NOTE |

            PlayerArray[SpaceOwner].setBalance(PlayerArray[SpaceOwner].getBalance()+CosttoOwn[THEfieldsNR]);
            //System.out.println(-CosttoOwn[THEfieldsNR] + "   " +CosttoOwn[THEfieldsNR]);      | EMPTY NOTE |
            return NewBal;
        }
        //  The selected player has landed on their own field and 0 is added to their account
        //System.out.println("    0");      | EMPTY NOTE |
        return "0";
    }

    public static Color ColorSpace(int description, int spaceamount){
        //  if the description returns a value above the amount of colors 9 is subtracted to not return an error
        if (spaceamount==9)
            description = description-9;
        //  Defines all colors based off a value from 0-9
        if (description==0) return Color.WHITE;
        else if (description==1) return Color.RED;
        else if (description==2) return Color.BLUE;
        else if (description==3) return Color.getHSBColor(0.10f,1f,1f);
        else if (description==4) return Color.getHSBColor(0.90f,1f,1f);
        else if (description==5) return Color.CYAN;
        else if (description==6) return Color.YELLOW;
        else if (description==7) return Color.getHSBColor(0.80f,0.77f,0.8f);
        else if (description==8) return Color.GREEN;
        else return Color.WHITE;    // if something goes wrong, White color is returned.
    }

    public static void displayDescriptions(GUI_Player selected, int diceSum, GUI_Street[] fields){
        //  Checks the space a player lands on, If the rent is positive, displays a green middle with explanation.
        if (Integer.parseInt(fields[diceSum - 2].getRent()) > 0)
            GUI_Center.getInstance().setBGColor(Color.GREEN);
        //  Checks the space a player lands on, If it is a chance field, displays white middle.
        else if (Integer.parseInt(fields[diceSum - 2].getRent()) == -80)
            GUI_Center.getInstance().setBGColor(Color.WHITE);
        //  Checks the space a player lands on, If the rent is negative, displays a red middle with explanation.
        else if (Integer.parseInt(fields[diceSum - 2].getRent()) < 0)
            GUI_Center.getInstance().setBGColor(Color.RED);
        //  If all else fails, returns yellow color
        else
            GUI_Center.getInstance().setBGColor(Color.YELLOW);
    }


    static void RestartFieldTitles(File file,int AmountofSpaces,GUI_Street[] fields) throws FileNotFoundException {
        //  Resets all titles
        for (int i=0;i<AmountofSpaces;i++){
            fields[i].setTitle(Main.txtReadAndReturn(file, String.valueOf(i)));
        }
    }

    static void RestartOwnStatus(int[][] OwnedtrueOwnedFalse,int fieldNR, int AmountofPlayers) {
        //  Goes through all fields and sets owned status to "Not Owned" - with an int 0
        for (int n = 0; n < fieldNR; n++) {
            OwnedtrueOwnedFalse[n][0] = n;
            for (int i = 0; i < AmountofPlayers; i++) {
                OwnedtrueOwnedFalse[n][i] = 0;
            }
        }
    }
}


