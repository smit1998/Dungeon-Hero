package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Door extends Entity{
    private String ID;
    private boolean isOpen;

    public Door(String ID) {
        this.ID = ID;
        this.isOpen = false;
    }

    public String getID(){
        return this.ID;
    }

    public boolean getIsOpen() {
        return this.isOpen;
    }

    public void updateIsOpen(boolean newIsOpen) {
        this.isOpen = newIsOpen;
    }
}