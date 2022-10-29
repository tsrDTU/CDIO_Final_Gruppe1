import gui_fields.GUI_Field;
import gui_fields.GUI_Street;
import gui_main.GUI;

import java.awt.*;



public class Fields {
       public static void initialiseFields(GUI_Field[] fields, String felter) {
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
    }
}
