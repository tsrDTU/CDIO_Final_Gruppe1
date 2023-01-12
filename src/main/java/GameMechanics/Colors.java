package GameMechanics;

import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;

import java.awt.*;

import EgneGuiKlasser.*;

public class Colors {
    //-------------------------------------------------------------------------------
//
//         Makes colors based off the HSB color system
//
//-------------------------------------------------------------------------------
    // Color creation for the cars - Look up HSB colors system online to learn more
    public static void CarColor(MGUI_Car playerCars[], MGUI_Player PlayerArray[], String Players, int PlayerNumber, MGUI_Street[] fields) {
        Color[] color = new Color[Integer.parseInt(Players)];
        for (int i =0;i<Integer.parseInt(Players);i++){
            String h = String.valueOf(i*0.18+1);
            color[i] = Color.getHSBColor(Float.parseFloat(h), 1f, 1f);
            PlayerArray[PlayerNumber].getCar().setPrimaryColor(color[PlayerNumber]);
        }
    }
    public static Color ColorSpace(int description, int SpecificSpace){
        //  if the description returns a value above the amount of colors 9 is subtracted to not return an error
        if (SpecificSpace==9)
            description = description-9;
        //  Defines all colors based off a value from 0-9
        if (description==0) return Color.WHITE;
        else if (description==1) return Color.getHSBColor(0f,0.8f,0.8f);
        else if (description==2) return Color.getHSBColor(0.6f,0.6f,0.5f);
        else if (description==3) return Color.getHSBColor(0.1f,0.7f,0.8f);
        else if (description==4) return Color.getHSBColor(0.90f,0.7f,0.8f);
        else if (description==5) return Color.getHSBColor(0.48f,0.6f,0.37f);
        else if (description==6) return Color.getHSBColor(0.15f,0.6f,0.65f);
        else if (description==7) return Color.getHSBColor(0.1f,0.67f,0.35f);
        else if (description==8) return Color.getHSBColor(0.35f,0.51f,0.35f);
        else return Color.WHITE;    // if something goes wrong, White color is returned.
    }

}
