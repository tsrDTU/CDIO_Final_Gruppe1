package player;

import GameMechanics.Fields;
import cardClasses.Chancekort;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;

public class MjPlayer extends GUI_Player
{
    private String userRole;
    private boolean kortModtaget;
    private Chancekort actChancekort;

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

    public void setActChancekort(Chancekort actChancekort) {
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

    public int haandterChanceKortModtaget(int pos, int slagIn, GUI_Street[] fields,int[][] Ownedtrue)
    {
        int slag;

       slag=slagIn;


       if (kortModtaget)
       {
           int n, nr_fields;
           boolean notOwned_FieldFound;
           System.out.println(actChancekort.getKortInfo());
           System.out.println("Chance kort er modtaget");
           n=pos;
           if (n>23)
           {
               System.out.println("Pos > 23 modtaget. Pos: "+pos);
               // 2 trækkes fra slaget. Arvet fra sidste spil.
               n=3;
           }
           nr_fields=0;
           do
           {
               notOwned_FieldFound=Fields.noOwnerShipCheck(Ownedtrue,n);
               if (notOwned_FieldFound)
               {

                   //Flytter bilen til det første ledige felt.
                   slag=n;
                   System.out.println("Not owned field found, slag: "+slag+" Pos: "+pos);
               }
               else
               {
                   n++;
                   if (n > 23) n = 0;
                   nr_fields++;
               }
           }while (nr_fields<23 && notOwned_FieldFound==false);

       }
       else
       {
           System.out.println("Chance kort er ikke modtaget");
       }



        return slag;
    }
}
