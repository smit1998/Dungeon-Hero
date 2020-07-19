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

    public BooleanProperty isOpen() {
        return isOpen;
    }

    public boolean getIsOpen() {
        return isOpen.getValue();
    }

    public boolean open(Key key) {
        if (key.getID() == getID()) {
            isOpen.setValue(true);
            return true;
        }
        return false;
    }

    @Override
    public boolean interact(Entity caller) {
        if (getIsOpen()) {
            return true;
        }
        if (caller instanceof Player) {
            Player player = (Player) caller;
            if (!getIsOpen()) {
                Inventory inventory = player.getInventory();
                Key key = inventory.getKey();
                if (key != null) {
                    if (open(key)) {
                        key.isVisible().setValue(false);
                        inventory.removeItem(key);
                        return true;
                    }
                }

            }
        }
        return false;
    }

}