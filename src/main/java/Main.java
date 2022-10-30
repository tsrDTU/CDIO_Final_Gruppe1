import gui_fields.GUI_Player;
import gui_main.GUI;
import gui_fields.GUI_Street;
import gui_fields.GUI_Car;
import java.awt.Color;
import java.util.Objects;

public class Main {


    public static void main(String[] args) {

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
        GUI_Car p1car = new GUI_Car(Color.BLUE, Color.WHITE, GUI_Car.Type.CAR, GUI_Car.Pattern.FILL);
        GUI_Car p2car = new GUI_Car(Color.RED, Color.WHITE, GUI_Car.Type.CAR, GUI_Car.Pattern.FILL);

        //Initializes players with name inputs
        String player1name = gui.getUserString(dialog[1]);
        String player2name = gui.getUserString(dialog[2]);
        //Creates automatic playernames in case none is given
        if (player1name.length() == 0) player1name = ("Player1");
        if (player2name.length() == 0) player2name = ("Player2");
        GUI_Player player1 = new GUI_Player(player1name, 1000, p1car);
        GUI_Player player2 = new GUI_Player(player2name, 1000, p2car);
        gui.addPlayer(player1);
        gui.addPlayer(player2);

        //Sets the initial car location
        fields[0].setCar(player1, true);
        fields[0].setCar(player2, true);

        //Selects who starts the game by selection in gui
        boolean selection = gui.getUserLeftButtonPressed(dialog[3], player1.getName(), player2.getName());

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
        while (player1.getBalance() < 3000 && player2.getBalance() < 3000 && !gameEnd) {
            if (selection) selectedPlayer = player1;
            else selectedPlayer = player2;

            //roll the dices
            d1.dice_roll();
            d2.dice_roll();

            //Inform which user is playing
            gui.getUserButtonPressed(dialog[4] + " " + selectedPlayer.getName() + dialog[5], dialog[6]);
            //Uses balance value in GUI, since it displays on GUI at all times, and works like a score.

            //Moving cars on the fields - and taking consequence of field
            if (player1.getBalance() < 3000 && player2.getBalance() < 3000) {

               Cars.moveCars(Die.getSum(d1, d2),  selectedPlayer, player1, player2, fields);

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
                        Cars.restart(player1, player2, fields);
                    }
                }
                while (!answerGameOk);
                if (!game_running)
                    System.exit(0);
            }
        }
    }
}