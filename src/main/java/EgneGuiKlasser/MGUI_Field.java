package EgneGuiKlasser;

/**
 * Kopi af Maven:diplomitdtu:matadorgui:3.1.7.gui_fields.GUI_Field som er tilrettet.
 */

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import gui_codebehind.GUI_Center;
import gui_codebehind.SwingComponentFactory;
import EgneGuiKlasser.MFieldMouseListener;
import gui_fields.GUI_Board;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_resources.Attrs;
import EgneGuiKlasser.MGUI_Player;

/**
 * Abstract base-class for GUI fields. Pass array of MGUI_Fields to GUI to create your own board.
 */
public abstract class MGUI_Field extends GUI_Field {
    public static final int FIELDWIDTH = 63;
    public static final int FIELDHEIGHT = 63;
    protected JLayeredPane layered = new JLayeredPane();
    protected JLabel titleLabel;
    protected JLabel subTextLabel;
    protected Color bgColor;
    protected Color fgColor;
    protected String title;
    protected String subText;
    protected String description;
    private SwingComponentFactory factory = new SwingComponentFactory();
    private HashMap<Integer, JLabel> cars = new HashMap<Integer, JLabel>();
    private JLabel[] carLabels;

    //Default values
    protected static final String TITLE = Attrs.getString("GUI_Field.Default_title");
    protected static final String SUBTEXT = Attrs.getString("GUI_Field.Default_SubText");
    protected static final String DESCRIPTION = Attrs.getString("GUI_Field.Default_Description");
    protected static final String PICTURE = Attrs.getString("GUI_Field.Default_Picture");
    protected static final String RENT = Attrs.getString("GUI_Field.Default_Rent");
    protected static final Color BG_COLOR = Color.LIGHT_GRAY;
    protected static final Color FG_COLOR = Color.BLACK;


    //TODO add number to parameters - just for display
     MGUI_Field(Color bgColor, Color fgColor, String title, String subText, String description)
     {
        super(bgColor,fgColor, title, subText, description );
    }


     MGUI_Field(Color bgColor, Color fgColor, String title, String subText, String description, Border border)
     {
        super(bgColor,fgColor,title,subText, description, border);
    }


    /**
     * Each type of field displays information on the center
     */
     public void addMouseListener(MFieldMouseListener listener){
        this.layered.addMouseListener(listener);
    }


    public JLayeredPane getPanel()
    {
        return this.layered;
    }


     public void setCarIcons(JLabel[] cars) {
        this.carLabels = cars;
    }


    /**
     * Checks whether or not a specific Player's car is currently
     * positioned on this field.
     *
     * @param player    The MGUI_Player whose car to check for
     * @return  True if 'player's car is on the field.
     */
    public boolean hasCar(MGUI_Player player) {
        return cars.get(player.getId()) != null && cars.get(player.getId()).isVisible();
    }


    /**
     * Places the car of a Player on this field.
     *
     * @param player The player, which car is to be placed on the field
     * @param display Whether or not the car should be displayed.
     */
    public void setCar(MGUI_Player player, boolean display) {
        JLabel l = cars.get(player.getId());
        if(l != null){
            l.setIcon(new ImageIcon(player.getImage()));
            l.setVisible(display);
        } else {
            for(JLabel lbl : carLabels){
                if(lbl.getIcon() == null){
                    lbl.setIcon(new ImageIcon(player.getImage()));
                    lbl.setVisible(display);
                    cars.put(player.getId(), lbl);
                    return;
                }
            }
        }
    }


    protected void displayOnCenter(MGUI_Player[] playerList){
        GUI_Center.getInstance().clearLabels();
        GUI_Center.getInstance().setBGColor(this.bgColor);
        GUI_Center.getInstance().setFGColor(this.fgColor);
        for(JLabel l : GUI_Center.label){
            l.setBackground(this.bgColor);
            l.setForeground(this.fgColor);
        }
        GUI_Center.label[0].setText("");
    }


}