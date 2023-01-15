package GameMechanics;

import EgneGuiKlasser.MGUI;
import gui_main.GUI;

import java.util.concurrent.ThreadLocalRandom;

public class Die {
    private int faceValue, numberOfSides = 6;


    public Die() {
        faceValue = (int) (Math.random() * numberOfSides) + 1;

    }

    public Die(int upperBound) {
        faceValue = (int) (Math.random() * upperBound) + 1;

    }

    //Displays Dice on the Board:
    public static void OnBoard(Die d1, Die d2, MGUI gui) {/* Die d2,*/
    int random_numx = ThreadLocalRandom.current().nextInt(4, 6);
    int random_numy = ThreadLocalRandom.current().nextInt(5, 7);
    int random_numz = ThreadLocalRandom.current().nextInt(5, 7);
    //Show dice on screen
            gui.setDice(d1.getFaceValue(), random_numx, random_numy, d2.getFaceValue(), random_numx+1, random_numy);
    }/*d2.getFaceValue()*/
    public static int getSum(Die d1, Die d2) {
        return d1.getFaceValue()+ d2.getFaceValue();
    }
    public void Die_test (int lowerBound, int upperBound)
    {
 //       System.out.println("Die_test");
        int temp =(int)(Math.random() * upperBound-lowerBound) + 1;
//        faceValue = temp+lowerBound;
        //       System.out.println(temp + lowerBound);
        //       faceValue = upperBound;
    }

    public void dice_roll () { faceValue = (int)(Math.random() * numberOfSides) + 1;
    }
    public void dice_rollT (int Dice_Value) {faceValue = Dice_Value;}

    public int getFaceValue(){
        return faceValue;
    }


    public void setNumberOfSides(int newNumberOfSides)
    {
        numberOfSides=newNumberOfSides;
    }

}
