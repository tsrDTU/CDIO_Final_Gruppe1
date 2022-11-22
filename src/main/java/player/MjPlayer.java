package player;

import GameMechanics.Fields;
import cardClasses.ChanceOverdragelseskort;
import cardClasses.Chancekort;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import gui_main.GUI;

/**
 * MjPlayer arver fra GUI_Player og har selv en brugerrolle og funktioner til at håndtere at modtage et ChanceOverdragelseskort og et amnistiKort
 */
public class MjPlayer extends GUI_Player
{
    private String userRole;
    private boolean kortModtaget;
    private ChanceOverdragelseskort actChancekort;

    private boolean amnistiKortHaves;

    public MjPlayer(String name)
    {
        super(name);
    }



    public MjPlayer(String name, int balance){
        super(name, balance, new GUI_Car());
    }



    public MjPlayer(String name, int balance, GUI_Car car){
      super(name,balance,car);
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
    public int haandterChanceKortModtaget(int pos, int slagIn, GUI_Street[] fields, int[][] Ownedtrue, GUI gui)
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
           System.out.println("Chance kort er modtaget");
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
               notOwned_FieldFound=Fields.noOwnerShipCheck(Ownedtrue,n);
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
