package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class InvincibilityPotion extends Entity{
    private int ID;
    private boolean isActive;
    private Timer remainingTime;
    private static int maxItem;
    
    public InvincibilityPotion(int ID) {
        this.ID = ID;
        this.isActive = false;
        //this.remainingTime =  NEED TO SET IT TO A PARTICULAR TIME.
    }

    public int getID(){
        return this.ID;
    }

    public void updateStatus(boolean newActive) {
        this.isActive = newActive;
    }

    public void startTimer() {
        // TODO
    }
}