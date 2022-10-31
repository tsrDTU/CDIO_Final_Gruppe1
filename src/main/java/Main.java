import gui_fields.GUI_Board;
import gui_fields.GUI_Player;
import gui_main.GUI;
import gui_fields.GUI_Street;
import gui_fields.GUI_Car;
import java.awt.Color;
import java.io.IOException;
import java.util.Objects;

public class Main {


    public static void main(String[] args) throws IOException {

        String string_in, language, answer_game;
        String[] dialog = new String[12];
        int antal_kant;
        boolean language_ok, game_running, answerGameOk;

        game_running = true;

        GUI_Street[] fields = new GUI_Street[11];   //setup streets
        Fields.initialiseFields(fields,"12"); //setup fields
        GUI gui = new GUI(fields, Color.WHITE); //setup GUI

        language_ok = false;
        do {
            language = gui.getUserButtonPressed("Select Langage:","Dansk", "English", "Francias", "German"); // Select language for the game dialog
            if (language.equals("Dansk")) language_ok = true;
            if (language.equals("English")) language_ok = true;
            if (language.equals("Francias")) language_ok = true;
            if (language.equals("German")) language_ok = true;
        } while (!language_ok);

        Language.initializeDialog(dialog, language); // Initialize the game dialog

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
        GUI_Player[] PlayerArray = new GUI_Player[Integer.parseInt(Players)];
        GUI_Car[] playerCars = new GUI_Car[Integer.parseInt(Players)];
        String[] players = new String[Integer.parseInt(Players)];
        String[] PlayerName = new String[Integer.parseInt(Players)];
        for (int i = 0; i<Integer.parseInt(Players);i++) {
            PlayerName[i] = (gui.getUserString(dialog[i]));
            if (PlayerName[i].length()==0) PlayerName[i]=("Player"+String.valueOf(i+1));
            playerCars[i] = new GUI_Car(Color.RED, Color.BLACK, GUI_Car.Type.CAR, GUI_Car.Pattern.FILL);
            PlayerArray[i] = new GUI_Player(PlayerName[i],1000,playerCars[i]);
            Cars.CarColor(playerCars,PlayerArray, Players, i);
            gui.addPlayer(PlayerArray[i]);
        }



        //Sets the initial car location
        for (int i = 0; i<Integer.parseInt(Players);i++) {
            fields[0].setCar(PlayerArray[i], true);
        }


        //Selects who starts the game by selection in gui
        boolean selection = gui.getUserLeftButtonPressed(dialog[3], PlayerArray[0].getName(), PlayerArray[1].getName());

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

        //Game loop
        while (PlayerArray[0].getBalance() < 3000 && PlayerArray[1].getBalance() < 3000 && !gameEnd) {
            if (selection) selectedPlayer = PlayerArray[0];
            else selectedPlayer = PlayerArray[1];

            //roll the dices
            d1.dice_roll();
            d2.dice_roll();

            //Inform which user is playing
            gui.getUserButtonPressed(dialog[4] + " " + selectedPlayer.getName() + dialog[5], dialog[6]);
            //Uses balance value in GUI, since it displays on GUI at all times, and works like a score.

            int AmountofSpaces = 11;

            //Moving cars on the fields - and taking consequence of field
            if (PlayerArray[0].getBalance() < 3000 && PlayerArray[1].getBalance() < 3000) {

               Cars.moveCars(Die.getSum(d1, d2),  selectedPlayer, PlayerArray, fields, AmountofPlayers, AmountofSpaces);

                //Deposit/Withdraw money from fields on the board
                int konsekvens = Integer.parseInt(fields[Die.getSum(d1, d2) - 2].getRent());
                selectedPlayer.setBalance(selectedPlayer.getBalance() + konsekvens);

                //Negative balance is not allowed
                if (selectedPlayer.getBalance() < 0) selectedPlayer.setBalance(0);
            }

            //Shows description of the space you land on, and changes color
            gui.displayChanceCard(selectedPlayer.getName() + " | " + fields[Die.getSum(d1, d2) - 2].getTitle() + "\n" + fields[Die.getSum(d1, d2) - 2].getSubText());
            Fields.displayDescriptions(selectedPlayer, Die.getSum(d1,d2), fields);
            //Display Die on the Board
            Die.OnBoard(d1, d2, gui);

            //Switch selected player
            if (!(Objects.equals(fields[Die.getSum(d1, d2) - 2].getTitle(), fields[8].getTitle())))
                selection = !selection;

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

                        int i=2;
                        Cars.restart(PlayerArray, fields);
                    }
                }
                while (!answerGameOk);
                if (!game_running)
                    System.exit(0);
            }
        }
    }
}