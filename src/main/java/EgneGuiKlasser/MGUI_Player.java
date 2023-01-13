package EgneGuiKlasser;

/**
 * Kopi af Maven:diplomitdtu:matadorgui:3.1.7.gui_fields.GUI_Player som er tilrettet.
 */

import java.awt.Color;
import java.awt.image.BufferedImage;

import GameMechanics.Fields;
import cardClasses.ChanceOverdragelseskort;
import gui_codebehind.Observable;
import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_resources.Attrs;
import EgneGuiKlasser.MGUI_Car;

/**
 * Class which represents the player object, and the information displayed on the
 * GUI regarding the player. This includes:
 *
 *  - Name (displayed on the board)
 *  - Balance (displayed on the board)
 *  - Car (position and color is determined by the car object, not the player)
 *
 * Once an object of this class is constructed (a player is created), it should be
 * added to the GUI using the {@link gui_main.GUI#addPlayer(gui_fields.GUI_Player)} method.
 *
 * Updating the GUI_Player object using the set methods, will also update the information
 * displayed on within the GUI.
 *
 * The position of the player (the player's car to be exact) is set  using the
 * {@link GUI_Field#setCar(gui_fields.GUI_Player, boolean)} method on the particular Field object.
 *
 * @author Ronnie
 */
public class MGUI_Player extends Observable{
    private int number = -1;
    private String name;
    private int balance;
    private MGUI_Car car;
    private static int nextId = 0;
    private int id;

    private String userRole;
    private boolean kortModtaget;
    private ChanceOverdragelseskort actChancekort;

    private boolean amnistiKortHaves;

    public static final int ICON_WIDTH = 41;
    public static final int ICON_HEIGHT = 22;


    // TODO: Remove this constructor, as it implements game logic
    /**
     * Constructs a new GUI_Player with a given name,
     * default balance of 1000 and a car with a random color.
     *
     * @param name Name of the player to be displayed on the board.
     */
    public MGUI_Player(String name){
        this(name, 1000, new MGUI_Car());
    }


    /**
     * Constructs a new GUI_Player with a given name and balance,
     * and a car with a random color.
     *
     * @param name Name of the player to be displayed on the board
     * @param balance Balance of the player to be displayed on the board
     */
    public MGUI_Player(String name, int balance){
        this(name, balance, new MGUI_Car());
    }


    /**
     * Constructs a new GUI_Player with a given name, balance and
     * custom car object.
     *
     * @param name Name of the player to be displayed on the board
     * @param balance Balance of the player to be displayed on the board
     * @param car Car object for to use
     */
    public MGUI_Player(String name, int balance, MGUI_Car car){
        this.name = name;
        this.balance = balance;
        this.car = car;
        this.id = nextId++;
    }


    //Getters
    public int getNumber(){ return this.number; }
    public String getName(){ return this.name; }
    public int getBalance(){ return this.balance; }
    public Color getPrimaryColor(){ return this.car.getPrimaryColor(); }
    public Color getSecondaryColor(){ return this.car.getSecondaryColor(); }
    protected BufferedImage getImage() { return this.car.getImage(); }
    public MGUI_Car getCar() { return car; }
    protected int getId(){ return id; }

    //Setters
    protected void setNumber(int number) { this.number = number; }
    public boolean setName(String name){
        if(validator == null) return false;
        if(!validator.checkName(name)) {
            System.err.println(Attrs.getString("Error.Conflict.PlayerName", name));
            return false;
        }
        this.name = name;
        notifyObservers();
        return true;
    }
    public void setBalance(int balance){
        this.balance = balance;
        notifyObservers();
    }


    // Mandatory
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((this.name == null) ? 0 : this.name.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof EgneGuiKlasser.MGUI_Player)) {
            return false;
        }
        EgneGuiKlasser.MGUI_Player other = (EgneGuiKlasser.MGUI_Player) obj;
        if (this.name == null) {
            if(other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
        return true;
    }


    public interface IPlayerNameValidator {
        boolean checkName(String name);
    }

    private EgneGuiKlasser.MGUI_Player.IPlayerNameValidator validator = null;

    public void setValidator(EgneGuiKlasser.MGUI_Player.IPlayerNameValidator validator){
        this.validator = validator;
    }


    @Override
    public String toString() {
        return "GUI_Player [number=" + number + ", name=" + name + ", balance="
                + balance + ", car=" + car + "]";
    }


    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setKortModtaget(boolean kortModt)
    {
        kortModtaget=kortModt;
    }

    public void setActChancekort(ChanceOverdragelseskort actChancekort) {
        this.actChancekort = actChancekort;
    }

    public void setAmnistkortHaves (boolean anmModt)
    {
        amnistiKortHaves=anmModt;
    }

    public boolean getAmnistiKortHaves()
    {
        return amnistiKortHaves;
    }

    /**
     * Checker om et chancekort er modtaget og håndterer i så fald dette
     * @param pos: Spillerens position på brættet
     * @param slagIn:Det antal spilleren har slået lige inden funktionen kaldes.
     * @param fields: Liste over felter
     * @param Ownedtrue:Liste over ejere
     * @param gui:GUI
     * @return: Evt ændret slag for brugeren. I tilfælde af at et chancekort har ændret positionen er slaget tilsvarende ændret
     */
    public int haandterChanceKortModtaget(int pos, int slagIn, MGUI_Street[] fields, int[][] Ownedtrue, MGUI gui)
    {
        int slag;
        // 2 trækkes fra slaget. Arvet fra sidste spil
        slag=slagIn-2;
        if (slag<0) slag=0;


        if (kortModtaget)
        {
            int n, nr_fields;
            boolean notOwned_FieldFound;
            System.out.println(actChancekort.getKortInfo());
            System.out.println("MjPlayer: "+userRole+".Chance kort er modtaget");
            gui.showMessage("Du har modtaget et chance kort som flytter dig til nærmeste ikke ejede felt, som du køber.");
            n=pos;
            if (n>23)
            {
                System.out.println("Pos > 23 modtaget. Pos: "+pos);

                n=0;
            }
            nr_fields=0;
            do
            {
                notOwned_FieldFound= Fields.noOwnerShipCheck(n);
                if (notOwned_FieldFound)
                {

                    //Flytter bilen til det første ledige felt. +1 fordi spilleren skal flytt
                    slag=n+1;
                    System.out.println("Not owned field found, slag: "+slag+" Pos: "+pos);
                    // Frigiv kortet så det kan bruges igen
                    actChancekort.setAktivt(false);
                    actChancekort.setModtager(null);
                    // Fjern kortet fra spilleren
                    setKortModtaget(false);
                    setActChancekort(null);
                }
                else
                {
                    n++;
                    if (n > 23) n = 0;
                    nr_fields++;
                }
            }while (nr_fields<23 && notOwned_FieldFound==false);

        }
       /*
       else
       {
           System.out.println("Chance kort er ikke modtaget");
       }

        */



        return slag+2;
    }

}