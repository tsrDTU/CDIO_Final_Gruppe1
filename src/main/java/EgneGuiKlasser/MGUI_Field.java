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
import gui_fields.FieldMouseListener;
//import gui_fields.GUI_Board;
import gui_fields.GUI_Field;
//import gui_fields.GUI_Player;
import gui_resources.Attrs;
import EgneGuiKlasser.*;


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
import gui_resources.Attrs;

/**
 * Abstract base-class for GUI fields. Pass array of GUI_Fields to GUI to create your own board.
 */
public abstract class MGUI_Field {
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

    private HashMap<Integer, JLabel> hotels = new HashMap<Integer, JLabel>(); // TSR


    //TODO add number to parameters - just for display
    protected MGUI_Field(Color bgColor, Color fgColor, String title, String subText, String description) {
        this(bgColor, fgColor, title, subText, description, BorderFactory.createLineBorder(Color.BLACK));
    }


    protected MGUI_Field(Color bgColor, Color fgColor, String title, String subText, String description, Border border) {
        title = title.replace("\n", "<BR>"); //$NON-NLS-1$ //$NON-NLS-2$
        subText = subText.replace("\n", "<BR>"); //$NON-NLS-1$ //$NON-NLS-2$
        description = description.replace("\n", "<BR>"); //$NON-NLS-1$ //$NON-NLS-2$

        this.bgColor = bgColor;
        this.fgColor = fgColor;
        this.makeLabels();
        this.setTitle(title);
        this.setSubText(subText);
        this.setDescription(description);
        this.layered.setBackground(bgColor);
        this.layered.setForeground(fgColor);
        this.layered.setOpaque(true);
        this.layered.setBorder(border);
// TSR        this.factory.setSize(this.layered, 1 * FIELDWIDTH, 1 * FIELDHEIGHT);
        this.factory.setSize(this.layered, 1 * 63, 1 * 63);
        this.layered.setLayout(new GridBagLayout());
    }


    private void makeLabels() {
        this.titleLabel = makeLabel(24);
        this.titleLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        this.titleLabel.setText(this.title);
        this.subTextLabel = makeLabel(10);
        this.subTextLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        this.subTextLabel.setText(this.subText);
    }


    /**
     * Checks whether or not a specific Player's car is currently
     * positioned on this field.
     *
     * @param player    The GUI_Player whose car to check for
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


    /**
     * Hides all the cars on the field
     */
    public void removeAllCars(){
        for(Integer key : cars.keySet()){
            cars.get(key).setVisible(false);
        }
    }


    /**
     * Makes a standard label
     * @param height - in px
     * @return a JLabel object
     */
    protected JLabel makeLabel(int height) {
        JLabel label = new JLabel();
 // TSR
        this.factory.setSize(label, 1 * FIELDWIDTH - 2, height);
//        this.factory.setSize(label, 1 * 63 - 2, 63);
        label.setFont(new java.awt.Font(MGUI_Board.FONT, 0, MGUI_Board.FONTSIZE));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBackground(this.bgColor);
        label.setForeground(this.fgColor);
        return label;
    }

    protected JLayeredPane getPanel()
    {
        return this.layered;
    }


    /**
     * Sets the title of this field. The title is displayed on the field,
     * and in the center square when the field is clicked.
     *
     * @param title The title (mind the length)
     */
    public void setTitle(String title) {
        this.title = "<html><center>" + title.replace("\\n", "<br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        this.titleLabel.setText(this.title);
    }


    /**
     * Sets the subtext of this field. The subtext is a small text displayed
     * on the buttom of the field, and in the center square whe field is
     * clicked.<br>
     * It's used for displaying price on streets.
     *
     * @param subText The subtext (mind the length).
     */
    public void setSubText(String subText) {
        this.subText = subText;
        this.subTextLabel.setText(subText);
    }


    /**
     * Sets the description of a field. The description is displayed in the center
     * sqaure, when the field is clicked.
     *
     * @param description The description
     */
    public void setDescription(String description) {
        if(description.length() > 20) {
            this.description = "<html><table><tr><td>" //$NON-NLS-1$
                    + description.replace("\\n", "<br>"); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            this.description = description;
        }
    }


    public String getTitle() {
        return title.replace("<html><center>", "").replace("<br>", "").replace("<BR>", ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
    }

    public String getSubText() {
        return subText.replace("<br>", "").replace("<BR>", ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    }

    public String getDescription() {
        return description.replace("<html><table><tr><td>", "").replace("<br>", "\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    }

    public void setBackGroundColor(Color color){
        this.bgColor = color;
        this.layered.setBackground(bgColor);
    }

    public void setForeGroundColor(Color color){
        this.fgColor = color;
        this.layered.setForeground(fgColor);
        titleLabel.setForeground(fgColor);
        subTextLabel.setForeground(fgColor);
    }


    protected void setCarIcons(JLabel[] cars) {
        this.carLabels = cars;
    }


    /**
     * Each type of field displays information on the center
     */
    protected void addMouseListener(MFieldMouseListener listener){
        this.layered.addMouseListener(listener);
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


    protected void displayCarOnCenter(MGUI_Player[] playerList) {
        for(int i = 0; i < MGUI_Board.MAX_PLAYER_COUNT; i++) {
            MGUI_Player p = playerList[i];
            if(p != null && hasCar(p)) {
                GUI_Center.cars[i].setIcon(new ImageIcon(p.getImage()));
                GUI_Center.cars[i].setVisible(true);
            } else {
                GUI_Center.cars[i].setIcon(null);
                GUI_Center.cars[i].setVisible(false);
            }
        }
    }
}