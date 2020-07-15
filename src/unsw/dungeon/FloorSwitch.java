package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class FloorSwitch extends Entity{
    private boolean ispressed;
    private int ID;

    public FloorSwitch (int ID) {
        this.ID = ID;
        this.ispressed = false;
    }
 
    public int getID() {
        return this.ID;
    }

    public boolean getIsPressed() {
        return this.ispressed;
    }

    public void updateIsPressed(boolean newIsPressed) {
        this.ispressed = newIsPressed;
    }

    public void updateObservers() {
        // TODO
    }
}