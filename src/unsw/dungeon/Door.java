package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * A Door entity that opens with the right key.
 */
public class Door extends Entity {

    private int id;
    private BooleanProperty isOpen;

    /**
     * Constructs a door at coordinate (x,y) in the dungeon with the give id
     * 
     * @param x       the horizontal position of the door
     * @param y       the vertical position of the door
     * @param dungeon the dungeon the entity belongs to
     * @param id      the id of the door
     */
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
    private boolean open(Key key) {
        if (key.getID() == getID()) {
            isOpen.setValue(true);
            return true;
        }
        return false;
    }

    /**
     * Interact with this entity. If the caller is a player, they will open this
     * door if they have the right key.
     * 
     * @return whether the interaction was successful
     */
    public boolean interact(Entity caller) {
        if (getIsOpen()) {
            return true;
        }
        if (caller instanceof Player) {
            Player player = (Player) caller;
            Key key = player.getKey();
            if (key != null) {
                if (open(key)) {
                    key.setVisibility(false);
                    key.setPickedUp(false);
                    player.removeItem(key);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * no changes made when the game state updates
     */
    @Override
    public void tick(Dungeon dungeon) {
        // TODO Auto-generated method stub

    }
}