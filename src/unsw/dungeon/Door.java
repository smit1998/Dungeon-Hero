package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * A Door entity that opens with the right key.
 */
public class Door extends Entity {

    private int id;
    private BooleanProperty isOpen;

    public Door(int x, int y, Dungeon dungeon, int id) {
        super(x, y, dungeon);
        this.id = id;
        this.isOpen = new SimpleBooleanProperty(false);
    }

    /**
     * @return the id of the door.
     */
    public int getID() {
        return id;
    }

    /**
     * @return if the door is open or closed.
     */
    public BooleanProperty isOpen() {
        return isOpen;
    }

    /**
     * @return the value of the open or closed door.
     */
    public boolean getIsOpen() {
        return isOpen.getValue();
    }

    /**
     * @param key used to open the door
     * @return wether the door can be opened or not.
     */
    public boolean open(Key key) {
        if (key.getID() == getID()) {
            isOpen.setValue(true);
            return true;
        }
        return false;
    }

    /**
     * opens the door if the player has the right key for that door.
     */
    @Override
    public boolean interact(Entity caller) {
        if (getIsOpen()) {
            return true;
        }
        if (caller instanceof Player) {
            Player player = (Player) caller;
            if (!getIsOpen()) {
                Key key = player.getKey();
                if (key != null) {
                    if (open(key)) {
                        key.isVisible().setValue(false);
                        key.notifyObservers();
                        return true;
                    }
                }
            }
        }
        return false;
    }

}