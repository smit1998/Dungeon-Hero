package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import unsw.dungeon.Player;

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
            Player player = (Player) caller;
            if (!getIsOpen()) {
                // TODO
                // for (Item k : player.getInventory()) {
                // if (k instanceof Key) {
                // Key key = (Key) k;
                // open(key);
                // }
                // }
            }
        }
        return false;
    }

<<<<<<< HEAD
=======
    // @Override
    // public void attach(Observer o) {
    // observers.add(o);
    // }

    // @Override
    // public void detach(Observer o) {
    // observers.remove(o);
    // }

    // @Override
    // public void notifyObservers() {
    // for (Observer obs : observers) {
    // obs.update(this);
    // }
    // }
>>>>>>> 4829bdb3f8e1b5ea6966a018754ed8b8251a5f18

}