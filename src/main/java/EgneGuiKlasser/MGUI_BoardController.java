package EgneGuiKlasser;

/**
 * Kopi af Maven:diplomitdtu:matadorgui:3.1.7.gui_codebehind.GUI_boardcontroller, som er tilrettet
 */

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import javax.swing.*;

import gui_codebehind.GUI_Center;
import gui_fields.GUI_Board;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;

import EgneGuiKlasser.MGUI_Board;
import EgneGuiKlasser.MGUI_BoardController;

/**
 * Provides access to GUI
 * @author Ronnie
 */
public final class MGUI_BoardController {
    private String userInput = null;
    private MGUI_Board board;
    private static volatile Random rand = null;

    public static Random rand() {
        if(rand == null) {
            synchronized (gui_codebehind.GUI_BoardController.class) {
                if(rand == null) rand = new Random();
            }
        }
        return rand;
    }
    /**
     * Contains service methods for board for controlling the board.
     * @param fields
     */
    /*
    public MGUI_BoardController(MGUI_Field[] fields) {
        this.board = new GUI_Board(fields);
    }

     */
    public MGUI_BoardController(MGUI_Field[] fields, Color backGroundColor)
    {
        this.board = new MGUI_Board(fields, backGroundColor);
        System.out.println("Board Controller created");
    }
    /**
     * Displays a message for the user. The user presses OK when the message is read Is a breaking
     * call.<br>
     * @param msg The message for the user.
     */
    public void showMessage(String msg) {
        final CountDownLatch latch = new CountDownLatch(1);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                EgneGuiKlasser.MGUI_BoardController.this.board.clearInputPanel();
                latch.countDown();
            }
        });

        okButton.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    EgneGuiKlasser.MGUI_BoardController.this.board.clearInputPanel();
                    latch.countDown();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) { }
        });

        this.board.getUserInput(msg, okButton);
        getFocus(okButton);
        try {
            latch.await();
        } catch(InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    private void getFocus(JButton okButton) {
        try {
            Thread.sleep(100);
            okButton.requestFocusInWindow();
        } catch (InterruptedException e1) {

        }
    }
    /**
     * Displays a message for the user and a textfield for the user to fill out.<br>
     * Is a breaking call.<br>
     * @param msg The message for the user.
     * @return The text entered by the user.
     */
    public String getUserString(String msg) {
        final CountDownLatch latch = new CountDownLatch(1);
        final JTextField tf = new JTextField(20);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                EgneGuiKlasser.MGUI_BoardController.this.userInput = tf.getText();
                EgneGuiKlasser.MGUI_BoardController.this.board.clearInputPanel();
                latch.countDown();
            }
        });
        tf.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    EgneGuiKlasser.MGUI_BoardController.this.userInput = tf.getText();
                    EgneGuiKlasser.MGUI_BoardController.this.board.clearInputPanel();
                    latch.countDown();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) { }
        });
        okButton.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    EgneGuiKlasser.MGUI_BoardController.this.userInput = tf.getText();
                    EgneGuiKlasser.MGUI_BoardController.this.board.clearInputPanel();
                    latch.countDown();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) { }
        });
        this.board.getUserInput(msg, tf, okButton);
        tf.requestFocusInWindow();
        try {
            latch.await();
            return this.userInput;
        } catch(InterruptedException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * Displays a message for the user and a textfield for the user to fill out. Only numbers are
     * allowed if the number isn't in range the user can't press OK.<br>
     * Is a breaking call.<br>
     * @param msg The message for the user.
     * @param min The low end of the valid range (inclusive).
     * @param max The high end of the valid range (inclusive).
     * @return The number entered by the user.
     */
    public int getUserInteger(String msg, final int min, final int max) {
        if((min < 0) || (max < 1) || (max <= min)) {
            return -1;
        }
        if("".equals(msg)) {
            return -1;
        }
        final CountDownLatch latch = new CountDownLatch(1);
        final JButton okButton = new JButton("OK");
        okButton.setEnabled(false);

        final JTextField tf = new JTextField(20);
        tf.setHorizontalAlignment(SwingConstants.RIGHT);
        tf.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {}
            @Override
            public void keyReleased(KeyEvent ke) {
                if(ke.getKeyCode() == KeyEvent.VK_ENTER){
                    EgneGuiKlasser.MGUI_BoardController.this.userInput = tf.getText();
                    EgneGuiKlasser.MGUI_BoardController.this.board.clearInputPanel();
                    latch.countDown();
                }
                String input = tf.getText() + ke.getKeyChar();
                String output = "";
                for(int i = 0; i < input.toCharArray().length - 1; i++) {
                    char c = input.toCharArray()[i];
                    if(c >= '0' && c <= '9' && i < 9) {
                        output += c;
                    }
                }
                tf.setText(output);
                int val = -1;
                try {
                    val = Integer.parseInt(output);
                    if((min <= val) && (val <= max)) {
                        tf.setForeground(Color.BLACK);
                    } else {
                        tf.setForeground(Color.RED);
                    }
                } catch(Exception ex) {
                    tf.setForeground(Color.RED);
                }

                okButton.setEnabled(tf.getForeground().equals(Color.BLACK));
            }
            @Override
            public void keyPressed(KeyEvent ke) {}
        });
        okButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                EgneGuiKlasser.MGUI_BoardController.this.userInput = tf.getText();
                EgneGuiKlasser.MGUI_BoardController.this.board.clearInputPanel();
                latch.countDown();
            }
        });
        this.board.getUserInput(msg, tf, okButton);

        try {
            latch.await();
            return Integer.parseInt(this.userInput);
        } catch(InterruptedException ex) {
            ex.printStackTrace();
            return -1;
        } catch(NumberFormatException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
    /**
     * Displays a message for the user along with a row of buttons.<br>
     * Is a breaking call.<br>
     * @param msg The message for the user.
     * @param buttonTexts The labels on the buttons.
     * @return The label of the button pressed by the user.
     */
    public String getUserButtonPressed(String msg, String... buttonTexts) {
        if((buttonTexts == null) || (buttonTexts.length < 1)) {
            return null;
        }
        final CountDownLatch latch = new CountDownLatch(1);
        JButton[] buttons = new JButton[buttonTexts.length];
        JButton primaryButton = null;
        for(int i = 0; i < buttonTexts.length; i++) {
            String string = buttonTexts[i];
            if("".equals(string)) {
                return null;
            }
            JButton btn = new JButton(string);
            primaryButton = btn;
            btn.setName(string);
            btn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    EgneGuiKlasser.MGUI_BoardController.this.userInput = ((JButton) e.getSource()).getName();
                    EgneGuiKlasser.MGUI_BoardController.this.board.clearInputPanel();
                    latch.countDown();
                }
            });
            btn.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) { }
                @Override
                public void keyReleased(KeyEvent e) {
                    if(e.getKeyCode() == KeyEvent.VK_ENTER){
                        EgneGuiKlasser.MGUI_BoardController.this.userInput = ((JButton) e.getSource()).getName();
                        EgneGuiKlasser.MGUI_BoardController.this.board.clearInputPanel();
                        latch.countDown();
                    }
                }
                @Override
                public void keyPressed(KeyEvent e) { }
            });

            buttons[i] = btn;
        }
        this.board.getUserInput(msg, buttons);
        getFocus(primaryButton);

        try {
            latch.await();
            return this.userInput;
        } catch(InterruptedException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * Displays a message and a drop-down menu with option for the user.<br>
     * Is a breaking call.<br>
     * @param msg The message for the user
     * @param options The options the user can choose from
     * @return The selected option
     */
    public String getUserSelection(String msg, String... options) {
        if((options == null) || (options.length < 1)) {
            return null;
        }
        final CountDownLatch latch = new CountDownLatch(1);
        for(int i = 0; i < options.length; i++) {
            String string = options[i];
            if("".equals(string)) {
                return null;
            }
        }
        final JComboBox<String> dropdown = new JComboBox<String>(options);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                EgneGuiKlasser.MGUI_BoardController.this.userInput = dropdown.getSelectedItem().toString();
                EgneGuiKlasser.MGUI_BoardController.this.board.clearInputPanel();
                latch.countDown();
            }
        });

        okButton.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    EgneGuiKlasser.MGUI_BoardController.this.userInput = dropdown.getSelectedItem().toString();
                    EgneGuiKlasser.MGUI_BoardController.this.board.clearInputPanel();
                    latch.countDown();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) { }
        });


        this.board.getUserInput(msg, dropdown, okButton);
        okButton.requestFocusInWindow();
        try {
            latch.await();
            return this.userInput;
        } catch(InterruptedException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * Adds a player to the board.<br>
     * A new player with the same color will replace the old.<br>
     * Max. 6 players.<br>
     * @param player : The player must be created beforehand
     * @return true if player is added, otherwise false
     */
    public boolean addPlayer(MGUI_Player player) {
        return this.board.addPlayer(player);
    }
    /**
     * Shows two dice on the board. The dice will have the specified values, but the placement is
     * random.<br>
     * @param faceValue1 : int [1:6]
     * @param faceValue2 : int [1:6]<br>
     *        (If a faceValue is out of bounds nothing will happen!)
     */
    public void setDice(int faceValue1, int faceValue2) {
        int rotation1 = rand().nextInt(360);
        int rotation2 = rand().nextInt(360);
        setDice(faceValue1, rotation1, faceValue2, rotation2);
    }
    /**
     * Shows two dice on the board. The dice will have the specified values and location.<br>
     * @param faceValue1 : int [1:6]
     * @param x1 : int [0:10]
     * @param y1 : int [0:10]
     * @param faceValue2 : int [1:6]
     * @param x2 : int [0:10]
     * @param y2 : int [0:10]
     */
    public void setDice(int faceValue1, int x1, int y1, int faceValue2, int x2, int y2) {
        int rotation1 = rand().nextInt(360);
        int rotation2 = rand().nextInt(360);
        setDice(faceValue1, rotation1, x1, y1, faceValue2, rotation2, x2, y2);
    }
    /**
     * Shows two dice on the board. The dice will have the specified values, but the placement is
     * random.<br>
     * @param faceValue1 : int [1:6]
     * @param rotation1 : int [0:2]
     * @param faceValue2 : int [1:6]
     * @param rotation2 : int [0:2]<br>
     *        (If a faceValue is out of bounds nothing will happen!)
     */
    public void setDice(int faceValue1, int rotation1, int faceValue2, int rotation2) {
        // Make an accepted locations for a dice

        List<Point> dicePlaces = new ArrayList<Point>();
        if (getFields().length ==40) {
            for(int x = 1; x < 10; x++) {
                for(int y = 1; y < 10; y++) {
                    if(x >= 4 && x <= 6 && y >= 4 && y <= 6) {
                        continue;
                    } // Do not add the points in the center.
                    if(x > 6 && y > (9 - this.board.getPlayerCount())) {
                        continue;
                    } // Do not add the points used for the players names and balances.
                    dicePlaces.add(new Point(x, y));
                }
            }
        } else if (getFields().length <40 && getFields().length>=32){
            for (int x = 2; x <4; x++) {
                for (int y = 2; y<9; y++) {
                    dicePlaces.add(new Point(x, y));
                }
            }

        } else {
            for(int x=1; x<9; x++) {
                for (int y = 0; y<2; y++) {
                    dicePlaces.add(new Point(x, y));
                }
            }
        }

        // Randomly choose a location for die1
        int index1 = 0;
        index1 = (int) (Math.random() * dicePlaces.size());
        Point dice1Position = dicePlaces.remove(index1);

        // Remove all locations "far" away from die1
        final int MAX_DISTANCE = 2;
        ArrayList<Point> toBeRemoved = new ArrayList<Point>();
        for(Point p : dicePlaces) {
            if(p.x < dice1Position.x - MAX_DISTANCE || p.x > dice1Position.x + MAX_DISTANCE
                    || p.y < dice1Position.y - MAX_DISTANCE || p.y > dice1Position.y + MAX_DISTANCE) {
                toBeRemoved.add(p);
            }
        }
        dicePlaces.removeAll(toBeRemoved);

        // Randomly choose a location for die2
        int index2 = 0;
        index2 = (int) (Math.random() * dicePlaces.size());
        Point dice2Position = dicePlaces.get(index2);

        setDice(faceValue1, rotation1, dice1Position.x, dice1Position.y, faceValue2, rotation2,
                dice2Position.x, dice2Position.y);
    }
    /**
     * Shows two dice on the board. The dice will have the specified values, location and rotation.<br>
     * @param faceValue1 : int [1:6]
     * @param rotation1 : int [0:2]
     * @param x1 : int [0:10]
     * @param y1 : int [0:10]
     * @param faceValue2 : int [1:6]
     * @param rotation2 : int [0:2]
     * @param x2 : int [0:10]
     * @param y2 : int [0:10]
     */
    public void setDice(int faceValue1, int rotation1, int x1, int y1, int faceValue2,
                        int rotation2, int x2, int y2) {
        boolean faceValuesAreValid = areFaceValuesValid(faceValue1, faceValue2);
        boolean rotationsAreValid = areRotationsValid(rotation1, rotation2);
        boolean positionsAreValid = arePositionsValid(x1, y1, x2, y2);
        if(faceValuesAreValid && rotationsAreValid && positionsAreValid) {
            this.board.setDice(x1, y1, faceValue1, rotation1, x2, y2, faceValue2, rotation2);
        }
    }
    private boolean arePositionsValid(int x1, int y1, int x2, int y2) {
        return x1 >= 0 && x1 <= 10
                && y1 >= 0 && y1 <= 10
                && x2 >= 0 && x2 <= 10
                && y2 >= 0 && y2 <= 10;
    }
    private boolean areRotationsValid(int rotation1, int rotation2) {
        return rotation1 >= 0 && rotation1 <= 359
                && rotation2 >= 0 && rotation2 <= 359;
    }
    private boolean areFaceValuesValid(int faceValue1, int faceValue2) {
        return faceValue1 >= 1 && faceValue1 <= 6
                && faceValue2 >= 1 && faceValue2 <= 6;
    }
    public void displayChanceCard(String txt) {
        GUI_Center.getInstance().setChanceCard(txt);
        GUI_Center.getInstance().displayChanceCard();
    }
    public void setChanceCard(String txt) {
        GUI_Center.getInstance().setChanceCard(txt);
    }
    public void displayChanceCard() {
        GUI_Center.getInstance().displayChanceCard();
    }

    public MGUI_Field[] getFields() { return board.getFields(); }

    public void setDie(int faceValue) {
        int rotation1 = rand().nextInt(360);
        int x = rand().nextInt(9);
        setDice(faceValue, rotation1, x, 9, faceValue, rotation1, x, 9);

    }

    public void close() {
        this.board.dispose();
    }

// Metoder tilf??jet af Torben
    // https://www.guru99.com/java-swing-gui.html, https://docs.oracle.com/javase/tutorial/uiswing/components/textfield.html
    public void showTextOnBoard(String msg)
    {
  //      final CountDownLatch latch = new CountDownLatch(1);
        JLabel label = new JLabel("Dette er pr??ve");
        label.setText(msg);
        label.setBounds(50,50,50,50);
        label.setHorizontalTextPosition(10);
        label.setVerticalTextPosition(1);
        label.setVisible(true);
        this.board.add(label);

        this.board.setVisible(true);
        this.board.pack();
        this.board.repaint();



        /*
        okButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                EgneGuiKlasser.MGUI_BoardController.this.board.clearInputPanel();
                latch.countDown();
            }
        }
        );

        okButton.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    EgneGuiKlasser.MGUI_BoardController.this.board.clearInputPanel();
                    latch.countDown();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) { }
        });

        this.board.getUserInput(msg, okButton);
        getFocus(okButton);
        try {
            latch.await();
        } catch(InterruptedException ex) {
            ex.printStackTrace();
        }

         */
    }
}