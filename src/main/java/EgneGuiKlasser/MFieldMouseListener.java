package EgneGuiKlasser;
/**
 * Kopi af Maven:diplomitdtu:matadorgui:3.1.7.gui_fields.FieldMouseListener som er tilrettet
 */


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;
import gui_codebehind.GUI_Center;
import EgneGuiKlasser.MGUI_Field;
import EgneGuiKlasser.MGUI_Player;


/**
 * Listens to mouse action on fields
 * @author Ronnie
 */
public class MFieldMouseListener implements MouseListener{
    private static final boolean PRINT_COUNTER = false;
    private MGUI_Field field;
    private MGUI_Player[] playerList;
    private boolean show = true;
    private static Timer timer = null, counter = null;

    public MFieldMouseListener(MGUI_Field field, MGUI_Player[] playerList){
        this.field = field;
        this.playerList = playerList;
    }
    /**
     * Called when the mouse is over a field
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        //    	this.field.displayOnCenter(playerList);
    }
    /**
     * Called when the mouse is no longer over a field
     */
    @Override
    public void mouseExited(MouseEvent e) {
        //    	GUI_Center.getInstance().displayDefault();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        //Do nothing
    }
    @Override
    public void mousePressed(MouseEvent e) {
        //Do nothing
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        if(show) {
            this.field.displayOnCenter(playerList);
            show = false;

            //Stop all running timers
            if(timer != null) timer.cancel();
            if(counter != null) counter.cancel();

            /*
             * Create a counter (Timer) which prints the progress of
             * the timer in the console
             */
            if(PRINT_COUNTER){
                int delay = 0;
                final int period = 500; // Half a second
                counter = new Timer();
                counter.schedule(new TimerTask() {
                    double d = 0;
                    @Override
                    public void run() {
                        if(PRINT_COUNTER) System.out.println("->"+(d/1000));
                        d += period;
                    }
                }, delay, period);
            }

            //Create the timer
            int delay = 3000;
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    GUI_Center.getInstance().displayDefault();
                    show = true;
                    if(counter != null) counter.cancel();
                }
            }, delay + 5); //Adds a slight overhead to delay
        }
        else  {
            GUI_Center.getInstance().displayDefault();
            show = true;
            if(counter != null) counter.cancel();
            if(timer != null) timer.cancel();
        }
    }
}
