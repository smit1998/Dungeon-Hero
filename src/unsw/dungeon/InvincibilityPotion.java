package unsw.dungeon;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class InvincibilityPotion extends Entity {

    private boolean isActive;
    private Timer remainingTime;
    private static int maxItem;

    public InvincibilityPotion(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        this.isActive = false;
        // this.remainingTime = NEED TO SET IT TO A PARTICULAR TIME.
    }

    public void updateStatus(boolean newActive) {
        this.isActive = newActive;
    }

    public void startTimer() {
        // TODO
    }
}