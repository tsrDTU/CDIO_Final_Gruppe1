package GUI_Beskeder;

import GameMechanics.Fields;
import cardClasses.ChanceOverdragelseskort;
import cardClasses.Chancekort;
import gui_codebehind.SwingComponentFactory;
import gui_fields.GUI_Board;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import gui_main.GUI;

import java.awt.image.BufferedImage;

import gui_resources.Attrs;

import javax.swing.*;

public class Help {
  public Help(GUI gui) {
 //   BufferedImage template = new SwingComponentFactory().createImage(Attrs.getImagePath("gui")).getSubimage(20, 30, 50, 20);
    JFileChooser helpfile=new JFileChooser();
    SwingComponentFactory template = new SwingComponentFactory();
    template.createGridBagConstraints(50,20);
    template.setSize(helpfile,50, 29);

    //gui.
   template.createImage( "");
   // template.createIcon("GUI");
  }

  public void   Help(GUI gui)
    {
      BufferedImage template = new SwingComponentFactory().createImage(Attrs.getImagePath("gui")).getSubimage(20, 30, 50, 20);

    }
}
