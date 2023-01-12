package EgneGuiKlasser;

/**
 * Kopi af af Maven:diplomitdtu:matadorgui:3.1.7.GUI_Ownable som er til rettet
 */


import java.awt.Color;
import javax.swing.border.Border;
import gui_codebehind.GUI_Center;
import gui_codebehind.SwingComponentFactory;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_resources.Attrs;

public abstract class MGUI_Ownable extends MGUI_Field {
    public String ownableLable, rentLable;
    private Color color1, color2;
    protected String ownerName;
    private String rent;

    public MGUI_Ownable(Color bgColor, Color fgColor, String title, String subText, String description, String leje){
        super(bgColor, fgColor, title, subText, description);
        this.rent = leje;
        ownableLable = Attrs.getString("GUI_Field.Label.owns");
        rentLable = Attrs.getString("GUI_Field.Label.rent");
        if(color1 == null) color1 = Color.BLACK;
        if(color2 == null) color2 = new Color(color1.getRed(), color1.getGreen(), color1.getBlue());
    }

    public void setBorder(Color color){
        setBorder(color, color);
    }
    public void setBorder(Color color1, Color color2){
        if(color1 == null) color1 = Color.BLACK;
        if(color2 == null) color2 = new Color(color1.getRed(), color1.getGreen(), color1.getBlue());
        this.color1 = color1;
        this.color2 = color2;
        SwingComponentFactory factory = new SwingComponentFactory();
        Border border = factory.createDashedBorder(2, 5, color1, color2);
        this.layered.setBorder(border);

    }
    public String getOwnerName(){ return this.ownerName; }
    /**
     * For display on center field
     * @param ownerName Mind the length
     */
    public void setOwnerName(String ownerName){ this.ownerName = ownerName; }
    public String getOwnableLabel(){ return this.ownableLable; }
    /**
     * For display on center field - Prefix for owner name
     * @param text
     */
    public void setOwnableLabel(String text){ this.ownableLable = text; }
    public String getRentLabel(){ return this.rentLable; }
    /**
     * For display on center field - Prefix for rent
     * @param text
     */
    public void setRentLabel(String text){ this.rentLable = text; }
    public String getRent(){ return this.rent; }
    public void setRent(String rent){ this.rent = rent; }
/*
    @Override
    protected void displayOnCenter(MGUI_Player[] playerList){
        super.displayOnCenter(playerList);
        Border border;
        if(this.ownerName != null){
            SwingComponentFactory factory = new SwingComponentFactory();
            border = factory.createDashedBorder(3, 10, color1, color2);
            GUI_Center.getInstance().getCenterPanel().setBorder(border);
        }else{
            border = javax.swing.BorderFactory.createLineBorder(Color.BLACK, 3);
        }
        GUI_Center.getInstance().getCenterPanel().setBorder(border);
    }

 */
}
