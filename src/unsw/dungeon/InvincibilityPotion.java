package unsw.dungeon;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class InvincibilityPotion extends Entity {

    private boolean isActive;
    private Timer timer = new Timer();
    //private TimerTask task = new TimerTask();
    public final static int maxItem = 1;

    public InvincibilityPotion(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        this.isActive = false;
       // this.remainingTime = 5;
    }

    public void updateStatus(boolean newActive) {
        this.isActive = newActive;
    }

    public void startTimer() {
        // attacks at every 100ms and runs for 5s
        int i = 0;
        while(i < 50) {
           // timer.schedule(task, 100);
            i++;
        }
        timer.cancel();
    }

}