package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Door extends Entity {

    private int id;
    private BooleanProperty isOpen;

    public Door(int x, int y, Dungeon dungeon, int id) {
        super(x, y, dungeon);
        this.id = id;
        this.isOpen = new SimpleBooleanProperty(false);
    }

    public int getID() {
        return id;
    }

    // returns true if door is open and false otherwise.
    // public boolean isOpen() {
    // return this.isOpen.getValue();
    // }

    public BooleanProperty isOpen() {
        return isOpen;
    }

    // opens the door if the given key is valid
    public boolean open(Key key) {
        if (key.getID() == this.id) {
            this.isOpen.setValue(true);
            return true;
        }
        return false;
    }

    @Override
    public boolean interact(Entity caller) {
        if (caller instanceof Player) {
            // TODO Implement proper opening using key
            if (!isOpen.getValue()) {
                isOpen.setValue(true);
            }
        }
        return true;
    }

}