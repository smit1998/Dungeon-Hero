package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Sword extends Entity{
    private  String ID;
    private boolean isPickedUp;
    private int remainingHits;
    static private int maxPickUp;

    public Sword (String ID, int remainingHits) {
        this.ID = ID;
        this.remainingHits = remainingHits;
    }

    public String getID() {
        return this.ID;
    } 

    public boolean getStatus() {
        return this.isPickedUp;
    }

    public void updateStatus(boolean newIsPickedUp) {

    }

    public void updateHitsRemaining() {
        
    }

}