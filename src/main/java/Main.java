import gui_fields.GUI_Player;
import gui_main.GUI;
import gui_fields.GUI_Street;
import gui_fields.GUI_Car;
import java.awt.Color;
import java.io.*;
import java.util.Objects;
import java.util.Scanner;

//v1.0

public class Main {


    public static void main(String[] args) throws IOException {

        String string_in, language, answer_game;
        String[] dialog = new String[12];
        int antal_kant;
        boolean language_ok, game_running, answerGameOk;

        game_running = true;
        int fieldNR = 24;
        //GUI_Street[] fields = new GUI_Street[11];   //setup streets

        File DescriptionF = new File("src/main/Field Guts/description");
        File TitleF = new File("src/main/Field Guts/Title");
        File subtextF = new File("src/main/Field Guts/subText");
        File rentF = new File("src/main/Field Guts/rent");


        GUI_Street[] fields = new GUI_Street[fieldNR];
        int line = 0;
        for (int i = 0; i < fieldNR; i++) {
            fields[i] = new GUI_Street(
                    txtReadAndReturn(TitleF,String.valueOf(i+1)),
                    txtReadAndReturn(subtextF,String.valueOf(i+1)),
                    txtReadAndReturn(DescriptionF,String.valueOf(i+1)),
                    txtReadAndReturn(rentF,String.valueOf(i+1)),
                    //fields[i] = new GUI_Street("","","","1",
                    Fields.ColorSpace(Integer.parseInt(txtReadAndReturn(DescriptionF,String.valueOf(i+1))), i),
                    Color.BLACK);

            /*for (int b=0;b<i;b++){
                if (LineIndex == i);
                fields[i].setBackGroundColor(Fields.ColorSpace((d.nextLine())));
                }
             */

        }



        /*
        for (int c = 1; c < fieldNR0; c++) {
            //Color FC = Fields.ColorSpace(StringRDR("src/main/Field Guts/description", fieldNR, c));
            //fields[c].setBackGroundColor(FC);
            Color FC = Fields.ColorSpace(d.nextLine());
            if (c == Integer.parseInt(d.nextLine())){
                FC = Fields.ColorSpace(d.nextLine());}
            fields[c].setBackGroundColor(FC);

        }
        */


            /*
            for (int i = 1;i<fieldNR;i++) {
                fields[i].setBackGroundColor(FC);
            }*/
        ////fields[i].setBackGroundColor(StringRDR("src/main/Field Guts/description",fieldNR,i));
        //fields[i].setBackGroundColor(Fields.ColorSpace(StringRDR("src/main/Field Guts/description", "1")));
/*for (int i =0; i<10;i++) {
    System.out.println("||"+d.nextLine());
}*/


        GUI gui = new GUI(fields, Color.WHITE);
        // GUI gui = new GUI(fields, Color.WHITE);
        //Fields.initialiseFields(fields, fieldNR); //setup fields
        //setup GUI
        //GUI gui = new GUI(fields, Color.WHITE);
        language_ok = false;
        String sprog = "English";
        do {
            language = gui.getUserButtonPressed("Select Langage:", "Dansk", "English", "Francias", "German"); // Select language for the game dialog
            if (language.equals("Dansk")) language_ok = true;
            sprog = "Dansk";
            if (language.equals("English")) language_ok = true;
            sprog = "English";
            if (language.equals("Francias")) language_ok = true;
            sprog = "Francias";
            if (language.equals("German")) language_ok = true;
            sprog = "German";
        } while (!language_ok);

        Language.initializeDialog(dialog, language); // Initialize the game dialog

        //GUI_Street[] fields = new GUI_Street[fieldNR];


        //GUI gui = new GUI(fields, Color.WHITE);


        do {
            string_in = gui.getUserButtonPressed(dialog[0], "2", "3", "4", "5", "6"); //Quest the number of sides for the dice
            //Set the number sides for the dices
            if (string_in.length() > 0) {
                antal_kant = (int) string_in.charAt(0) - '0';
            } else antal_kant = 6;
        } while (antal_kant < 2 || antal_kant > 6);

        //Makes Cars in different colors depending on player
        //GUI_Car p1car = new GUI_Car(Color.BLUE, Color.WHITE, GUI_Car.Type.CAR, GUI_Car.Pattern.FILL);
        //GUI_Car p2car = new GUI_Car(Color.RED, Color.WHITE, GUI_Car.Type.CAR, GUI_Car.Pattern.FILL);

        //String Players = gui.getUserButtonPressed("how many players?","2","3","4","5","6");
        String Players = gui.getUserButtonPressed(dialog[11], "2", "3", "4");
        int AmountofPlayers = Integer.parseInt(Players);
        int AmPl1l = Integer.parseInt(Players);
        GUI_Player[] PlayerArray = new GUI_Player[Integer.parseInt(Players)];
        GUI_Car[] playerCars = new GUI_Car[Integer.parseInt(Players)];
        String[] PlayerName = new String[Integer.parseInt(Players)];
        for (int i = 0; i < Integer.parseInt(Players); i++) {
            PlayerName[i] = (gui.getUserString(dialog[i]));
            if (PlayerName[i].length() == 0) PlayerName[i] = ("Player" + String.valueOf(i + 1));
            playerCars[i] = new GUI_Car(Color.RED, Color.BLACK, GUI_Car.Type.CAR, GUI_Car.Pattern.FILL);
            PlayerArray[i] = new GUI_Player(PlayerName[i], 1000, playerCars[i]);
            Cars.CarColor(playerCars, PlayerArray, Players, i);
            gui.addPlayer(PlayerArray[i]);
        }

        //Initialise true false for OwnedNotOwnedFields
        int[][] OwnedtrueOwnedFalse = new int[fieldNR][AmountofPlayers+1];
        for (int n=0;n<fieldNR;n++){
            OwnedtrueOwnedFalse[n][0] = n;
            for (int i =0;i<AmountofPlayers;i++){
            OwnedtrueOwnedFalse[n][i] = 0;
            }
        }



        //Integer.parseInt(Players)
        //Sets the initial car location
        /*
        for (int i = 0; i < Integer.parseInt(Players)+1; i++) {
            fields[0].setCar(PlayerArray[i], true);
        if (i==Integer.parseInt(Players)-2) break;
        }
          */
        boolean selection = true;
        String select = "";
        String[] pl = new String[AmountofPlayers];
        //Selects who starts the game by selection in gui
        //boolean selection = gui.getUserLeftButtonPressed(dialog[3], PlayerArray[0].getName(), PlayerArray[1].getName());
        /*
        for (int i = 0; i < AmountofPlayers; i++) {
            pl[i] = PlayerArray[i].getName();
        }

        if (AmountofPlayers == 2)
            select = gui.getUserButtonPressed(dialog[3], pl[0], pl[1]);
        if (AmountofPlayers == 3)
            select = gui.getUserButtonPressed(dialog[3], pl[0], pl[1], pl[2]);
        if (AmountofPlayers == 4)
            select = gui.getUserButtonPressed(dialog[3], pl[0], pl[1], pl[2], pl[3]);

        if (AmountofPlayers==5)
            select = gui.getUserButtonPressed(dialog[3], pl[0], pl[1], pl[2], pl[3], pl[4]);
        if (AmountofPlayers==6)
            select = gui.getUserButtonPressed(dialog[3], pl[0], pl[1], pl[2], pl[3], pl[4], pl[5]);
        if (AmountofPlayers==7)
            select = gui.getUserButtonPressed(dialog[3], pl[0], pl[1], pl[2], pl[3], pl[4],pl[5], pl[6]);
        if (AmountofPlayers==8)
            select = gui.getUserButtonPressed(dialog[3], pl[0], pl[1], pl[2], pl[3], pl[4],pl[5], pl[6], pl[7])
         */


        for (int i = 0; i < AmountofPlayers; i++) {
            GUI_Car car = PlayerArray[i].getCar();
            if (i > 0 && fields[i] != null) fields[0].setCar(PlayerArray[i], true);
        }


        int intselect = 0;
        int[] selectPlayer = new int[AmountofPlayers];
        for (int i = 0; i < AmountofPlayers; i++) {
            selectPlayer[i] = 0;
            if (Objects.equals(select, PlayerArray[i].getName())) {
                selectPlayer[i] = i;
                intselect = i;
            }
        }

        //Create a selected player that will point at active player
        GUI_Player selectedPlayer;
        boolean gameEnd = false; //, lastMax = false;

        //Create the dices. Default 6 sides
        Die d1 = new Die();
        Die d2 = new Die();






        // If sides are different from 6, set the number of sides.
        if (antal_kant != 6) {
            d1.setNumberOfSides(antal_kant);
            d2.setNumberOfSides(antal_kant);
        }

        int playingPlayer = intselect;
        int playingPlayer2 = 0;

        Cars.restart(PlayerArray,fields , AmountofPlayers);


        //Game loop
        while (!gameEnd) {
            //while (PlayerArray[0].getBalance() < 3000 && PlayerArray[1].getBalance() < 3000 && !gameEnd) {

            if (playingPlayer == AmountofPlayers)
                playingPlayer = 0;
            playingPlayer2 = playingPlayer;
            if (selection) selectedPlayer = PlayerArray[playingPlayer];
            else selectedPlayer = PlayerArray[playingPlayer2];

            //roll the dices
            d1.dice_roll();
            d2.dice_roll();

            //Inform which user is playing
            gui.getUserButtonPressed(dialog[4] + " " + selectedPlayer.getName() + dialog[5], dialog[6]);
            //Uses balance value in GUI, since it displays on GUI at all times, and works like a score.

            int AmountofSpaces = fieldNR;
            int DieSum = Die.getSum(d1,d2)+2;
            //Moving cars on the fields - and taking consequence of field
            if (!gameEnd) {

                Cars.moveCars(DieSum, selectedPlayer, PlayerArray, fields, AmountofPlayers, AmountofSpaces);
/*
                //Deposit/Withdraw money from fields on the board
                int konsekvens = Integer.parseInt(fields[DieSum].getRent());
                selectedPlayer.setBalance(selectedPlayer.getBalance() + konsekvens);
*/
                boolean wanttobuyYesNo = false;
                String wanttobuyYesNoS = gui.getUserButtonPressed("wanna buy?", "Yes", "No");
                if (Objects.equals(wanttobuyYesNoS, "Yes"))
                    wanttobuyYesNo = true;
                else wanttobuyYesNo = false;

                if (wanttobuyYesNo) {
                    String NewBalance = Fields.wannaBuyDoYou(OwnedtrueOwnedFalse,
                            Integer.parseInt(fields[selectedPlayer.getNumber()].getDescription()),
                            Integer.parseInt(fields[1].getRent()),
                            AmountofPlayers,
                            selectedPlayer,
                            fields,
                            wanttobuyYesNo,
                            PlayerArray,
                            AmountofSpaces);
                    selectedPlayer.setBalance(selectedPlayer.getBalance()+Integer.parseInt(NewBalance));
                }





                //Negative balance is not allowed
                if (selectedPlayer.getBalance() < 0) selectedPlayer.setBalance(0);
            }

            //Shows description of the space you land on, and changes color
            gui.displayChanceCard(selectedPlayer.getName() + " | " + fields[DieSum].getTitle() + "\n" + fields[DieSum].getSubText());
            Fields.displayDescriptions(selectedPlayer, DieSum, fields);
            //Display Die on the Board
            Die.OnBoard(d1, d2, gui);

            //Switch selected player
            if (!(Objects.equals(fields[DieSum].getTitle(), "extra"))) {
                selection = !selection;
                playingPlayer++;
            }
            //Extra tour
            else if (!(selectedPlayer.getBalance() > 3000)) {
                gui.showMessage(selectedPlayer.getName() + dialog[7]);
            }


            answerGameOk = false;
            if (selectedPlayer.getBalance() > 3000) {
                gui.showMessage(selectedPlayer.getName() + dialog[8] + selectedPlayer.getBalance());
                do {
                    answer_game = gui.getUserButtonPressed(dialog[9], dialog[10], dialog[11]); // Select language for the game dialog

                    if (answer_game.equals(dialog[11])) {
                        game_running = false;
                        answerGameOk = true;
                    }

                    if (answer_game.equals("y") || answer_game.equals("j") || answer_game.equals("o") || answer_game.equals(dialog[10])) {
                        answerGameOk = true;
                        game_running = true;

                        int i = 2;
                        Cars.restart(PlayerArray, fields, AmountofPlayers);
                    }
                }
                while (!answerGameOk);
                if (!game_running)
                    System.exit(0);

            }
        }
    }


    public static String txtReadAndReturn(File file, String LineNR) throws FileNotFoundException {
        Scanner TXTRDRscanner = new Scanner(file);
        int ReadLineNR = Integer.parseInt(LineNR);

        int AmountofColors = 7;
        for (int i = 0; i <= ReadLineNR; i++) {
            //if (i==AmountofColors) {
                //TXTRDRscanner.close();
                //TXTRDRscanner = new Scanner(file);
            //}

            if (i == ReadLineNR - 1) {
                //
                return TXTRDRscanner.nextLine();
            } else TXTRDRscanner.nextLine();
        }
        TXTRDRscanner.close();
        TXTRDRscanner = new Scanner(file);
        return LineNR;
    }






}

/*
    public static String StringRDR(String fileToRead, int max, int i1) throws IOException {
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(fileToRead));
        for (int lineNumber = 1; lineNumber < max; lineNumber++) {

                reader.readLine();
                for (int k=0;k<i;k++){
                    return reader.readLine();

                }
                String text = String.valueOf(i1);
            try {
                FileReader file = new FileReader("src/main/Field Guts/description");
                BufferedReader readbuffer = new BufferedReader(file);

                for (int i = 1; i < max; i++) {

                    if (i == i1) {
                        text = readbuffer.readLine();

                        return text;

                    } else return text;


                    }




            } finally {

            }

        return text;
    }}
*/



