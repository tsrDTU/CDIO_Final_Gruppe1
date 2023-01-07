package TheBoard;
import Files.FileReference;
import GameMechanics.Colors;
import GameMechanics.Fields;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import player.MjPlayer;

import java.awt.*;
import java.io.FileNotFoundException;

import static Files.FileReference.*;
import static GameMechanics.textReaderClass.textRDR;
import static TheBoard.Base.*;


public class BoardCreator{
    public static GUI_Street[] InitBoardFieldsGuts() throws FileNotFoundException {
        for (int i = 0; i < Base.fieldNR(); i++) {
            fields[i] = new GUI_Street(
                    textRDR(TitleF, String.valueOf(i + 1)),
                    textRDR(subtextF, String.valueOf(i + 1)),
                    textRDR(DescriptionF, String.valueOf(i + 1)),
                    textRDR(rentF, String.valueOf(i + 1)),
                    //fields[i] = new GUI_Street("","","","1",
                    Colors.ColorSpace(Integer.parseInt(textRDR(DescriptionF, String.valueOf(i + 1))), i),
                    Color.BLACK);
            //fields[2] = new GUI_Street(title,subtext,description,rent,BgColor,Color.BLACK)
            System.out.println(fields[i]);
        }
//--------------------------------------------------------------------------
//
//        BACKUP
//
//--------------------------------------------------------------------------
        //fields[i] = new GUI_Street(title,		subtext,	description,	rent,	BgColor,			Color.BLACK)
        /*GUI_Street[] fields = new GUI_Street[Base.fieldNR()];
        fields[0] = new GUI_Street("GO",		"<------",		"0",		"0",	Fields.ColorSpace(0,0),Color.BLACK);
        fields[1] = new GUI_Street("BURGERBAR",	"1M",			"1",		"1",	Fields.ColorSpace(1,1),Color.BLACK);
        fields[2] = new GUI_Street("PIZZARIA",	"1M",			"1",		"1",	Fields.ColorSpace(1,2),Color.BLACK);
        fields[3] = new GUI_Street("CHANCE",	"CHANCE",		"0",		"0",	Fields.ColorSpace(0,3),Color.BLACK);
        fields[4] = new GUI_Street("CANDYSTORE",	"1M",			"2",		"1",	Fields.ColorSpace(2,4),Color.BLACK);
        fields[5] = new GUI_Street("ICECREAMSTORE","1M",		"2",		"1",	Fields.ColorSpace(2,5),Color.BLACK);
        fields[6] = new GUI_Street("JAIL VISIT",	"PÅ BESØG",		"0",		"0",	Fields.ColorSpace(0,6),Color.BLACK);
        fields[7] = new GUI_Street("MUSEUM",	"2M",			"3",		"2",	Fields.ColorSpace(3,7),Color.BLACK);
        fields[8] = new GUI_Street("LIBRARY",	"2M",			"3",		"2",	Fields.ColorSpace(3,8),Color.BLACK);
        fields[9] = new GUI_Street("CHANCE",	"CHANCE",		"0",		"0",	Fields.ColorSpace(0,9),Color.BLACK);
        fields[10] = new GUI_Street("SKATEPARK",	"2M",			"4",		"2",	Fields.ColorSpace(4,10),Color.BLACK);
        fields[11] = new GUI_Street("SWIMMINGPOOL","2M",		"4",		"2",	Fields.ColorSpace(4,11),Color.BLACK);
        fields[12] = new GUI_Street("FREE PARKING","PARKERING",	"0",		"0",	Fields.ColorSpace(0,12),Color.BLACK);
        fields[13] = new GUI_Street("GAMEHALL",	"3M",			"5",		"3",	Fields.ColorSpace(5,13),Color.BLACK);
        fields[14] = new GUI_Street("KINO",		"3M",			"5",		"3",	Fields.ColorSpace(5,14),Color.BLACK);
        fields[15] = new GUI_Street("CHANCE",	"CHANCE",		"0",		"0",	Fields.ColorSpace(0,15),Color.BLACK);
        fields[16] = new GUI_Street("TOYSTORE",	"3M",			"6",		"3",	Fields.ColorSpace(6,16),Color.BLACK);
        fields[17] = new GUI_Street("PETSTORE",	"3M",			"6",		"3",	Fields.ColorSpace(6,17),Color.BLACK);
        fields[18] = new GUI_Street("JAIL",		"GÅ I FENGSEL",	"0",		"0",	Fields.ColorSpace(0,18),Color.BLACK);
        fields[19] = new GUI_Street("BOWLING",	"4M",			"7",		"4",	Fields.ColorSpace(7,19),Color.BLACK);
        fields[20] = new GUI_Street("ZOO",		"4M",			"7",		"4",	Fields.ColorSpace(7,20),Color.BLACK);
        fields[21] = new GUI_Street("CHANCE",	"CHANCE",		"0",		"0",	Fields.ColorSpace(0,21),Color.BLACK);
        fields[22] = new GUI_Street("WATERWORLD",	"4M",		"8",		"4",	Fields.ColorSpace(8,22),Color.BLACK);
        fields[23] = new GUI_Street("SANDMARKET",	"4M",		"8",		"4",	Fields.ColorSpace(8,23),Color.BLACK);*/
//end of 24 space game
//fields[24] = new GUI_Street(title,subtext,description,rent,BgColor,Color.BLACK)



        return fields;
    }

    public static int[] CostofField() throws FileNotFoundException {
//  Initialises cost to own for every space based off the subsequent value in - src/main/Field Guts/CostToOwnField
        for (int i = 0; i < Base.fieldNR(); i++) {
            CosttoOwn[i] = (Integer.parseInt(textRDR(FileReference.CostToOwnFieldF, String.valueOf(i))));
        }

//        BackUP
//        for (int i = 0; i < Base.fieldNR(); i++) {
//            CosttoOwn[i] = Integer.parseInt(InitBoardFieldsGuts()[i].getRent());
//        }
        return CosttoOwn;
    }
    public static void SetGUItext() throws FileNotFoundException {
        //  Initialises the TheBoard.Base.fields with values from txt files in - src/main/Field-Guts - and - Color.Colorspace
        for (int i = 0; i < Base.fieldNR(); i++) {
            fields[i] = new GUI_Street(
                    textRDR(TitleF, String.valueOf(i + 1)),
                    textRDR(subtextF, String.valueOf(i + 1)),
                    textRDR(DescriptionF, String.valueOf(i + 1)),
                    textRDR(rentF, String.valueOf(i + 1)),
                    //fields[i] = new GUI_Street("","","","1",
                    Colors.ColorSpace(Integer.parseInt(textRDR(DescriptionF, String.valueOf(i + 1))), i),
                    Color.BLACK);
        }
    }

    public static boolean[] JailInit(boolean[] JailOn) {
            for (int i = 0; i < AmountofPlayers; i++) {
                JailOn[i] = false;
            }
        return JailOn;
        }





    public static void PersonCreator(int AmountofPlayers, GUI_Player[] PlayerArray, String[] PlayerName, GUI_Car[] playerCars) {

    }
}

