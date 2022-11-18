package player;

import cardClasses.Chancekort;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;

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

    public int haandterChanceKortModtaget( int pos, int slagIn)
    {
        int slag;

       slag=slagIn;


       if (kortModtaget)
       {
           System.out.println(actChancekort.getKortInfo());
       }
       else
       {
           System.out.println("Chance kort er ikke modtaget");
       }



        return slag;
    }
}
