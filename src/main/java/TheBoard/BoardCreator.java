package TheBoard;
import Files.FileReference;
import GameMechanics.Fields;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;

import java.awt.*;
import java.io.FileNotFoundException;

import static Files.FileReference.*;
import static GameMechanics.textReaderClass.textRDR;
import static TheBoard.Base.*;
//import static TheBoard.Base.Players;


public class BoardCreator{

    public static int[] CostofField() throws FileNotFoundException {
//  Initialises cost to own for every space based off the subsequent value in - src/main/Field Guts/CostToOwnField
        for (int i = 0; i < Base.fieldNR(); i++) {
            Base.CosttoOwn[i] = (Integer.parseInt(textRDR(FileReference.CostToOwnFieldF, String.valueOf(i))));
        }
        return CosttoOwn;
    }
    public static void SetGUItext() throws FileNotFoundException {
        //  Initialises the TheBoard.Base.fields with values from txt files in - src/main/Field-Guts - and - Color.Colorspace
        /*for (int i = 0; i < Base.fieldNR(); i++) {
            fields[i] = new GUI_Street(
                    textRDR(TitleF, String.valueOf(i + 1)),
                    textRDR(subtextF, String.valueOf(i + 1)),
                    textRDR(DescriptionF, String.valueOf(i + 1)),
                    textRDR(rentF, String.valueOf(i + 1)),
                    //fields[i] = new GUI_Street("","","","1",
                    Fields.ColorSpace(Integer.parseInt(textRDR(DescriptionF, String.valueOf(i + 1))), i),
                    Color.BLACK);
        }*/
    }

    public static void PersonCreator(int AmountofPlayers, GUI_Player[] PlayerArray, String[] PlayerName, GUI_Car[] playerCars) {

    }
}

