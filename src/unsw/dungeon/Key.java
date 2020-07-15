package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Key extends Entity{
    private String ID;
    private int maxPickedUp;
    
    public Key(String ID) {
        this.ID = ID;
        this.maxPickedUp = 1;
    }

    public String getID() {
        return this.ID;
    }
}