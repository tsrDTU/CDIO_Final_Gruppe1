import gui_fields.GUI_Car;
import gui_fields.GUI_Player;

public class MjPlayer extends GUI_Player
{
    private String userRole;

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
}
