package GameMechanics;

import Files.FileReference;
import TheBoard.Base;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;

import java.io.FileNotFoundException;

import static TheBoard.Base.*;
import static GameMechanics.textReaderClass.textRDR;
import static Files.FileReference.*;

public class DoubleProperty {

    public static int[] CostCheck(int currentFieldNR) throws FileNotFoundException {
        int CostOCF = Integer.parseInt(textRDR(DescriptionF(), String.valueOf(currentFieldNR+1)));
        int[] cost = new int[fieldNR()+4];
        int[] costToCheck = new int[fieldNR()+2];
        for (int i = 1; i <= fieldNR(); i++)
        {
            costToCheck[i]=Integer.parseInt(textRDR(DescriptionF(),String.valueOf(i)));
        }
        for (int i = 1; i <= fieldNR(); i++)
        {
            if (costToCheck[i]==CostOCF && CostOCF!=0)
               cost[i]=1;
        }
        //for (int i = 1; i <= fieldNR(); i++){System.out.print(" "+i +"|"+ cost[i]);}
        return cost;
    }

    public static boolean DoubleCost(int[][] OwnedTrueOwnedFalse, int selectedPlayersNR, int spaceNumber) throws FileNotFoundException {
        int[] InvestegationFields = CostCheck(spaceNumber);
        int Cost = Integer.parseInt(textReaderClass.textRDR(FileReference.CostToOwnFieldF,String.valueOf(spaceNumber)));
        //if (Fields.OwnedCheck(OwnedTrueOwnedFalse, selectedPlayersNR, spaceNumber)&&costoffield[spaceNumber]==Cost)
        for (int j = 0; j < AmountofPlayers; j++) {
            for (int i = 1; i < fieldNR() ; i++) {
                if (InvestegationFields[i]==1 && OwnedTrueOwnedFalse[i-1][j]==1 && OwnedTrueOwnedFalse[i][j]==1){
                    /*System.out.println("Wants to Double The cost "+ selectedPlayersNR );*/ return true; }

            }
        }

        return false;
    }
}
