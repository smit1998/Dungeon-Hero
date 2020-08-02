package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

//import sun.net.www.content.text.plain;

/**
 * A key entity that can be used to unlock a door
 */
public class Key extends ItemEntity {

    private final static int MAX_PICKUP = 1;
    private int id;
    private BooleanProperty isPickedUp = new SimpleBooleanProperty(false);

    /**
     * Constructs a new key entity at the (x, y) coordinate in the dungeon with the
     * id
     * 
     * @param x       the horizontal position
     * @param y       the vertical position
     * @param dungeon the dungeon this key belongs to
     * @param id      the id of the key
     */
    public Key(int x, int y, Dungeon dungeon, int id) {
        super(x, y, dungeon);
        this.id = id;
    }

    /**
     * Get the ID of this key
     * 
     * @return the id of this key
     */
    public int getID() {
        return id;
    }

    /**
     * Interact with this key entity. If the caller is a player, the player will
     * attempt to pick this key up if they can.
     */
    public boolean interact(Entity caller) {
        if (caller.getClass() == Player.class) {
            return interact((Player) caller); 
        }
        if (caller.getClass() == Boulder.class) {
            return interact((Boulder) caller); 
        }
        return true;
    }

    /**
     * player picks up key
     */
    private boolean interact(Player player) {
        player.pickupItem(this); 
        return true; 
    }

    /**
     * boulder cannot pass a key
     */
    private boolean interact(Boulder boulder) {
        return false;
    }

    /**
     * Get the maximum number of keys that can be picked up
     * 
     * @return the maximum number of keys that be picked up
     */
    public int getMaxPickup() {
        return MAX_PICKUP;
    }

    @Override
    public void tick(Dungeon dungeon) {
    }

    public BooleanProperty isPickedUp() {
        return isPickedUp;
    }

    public void setPickedUp(boolean newIsPickedUp) {
        isPickedUp.setValue(newIsPickedUp);
    }

}