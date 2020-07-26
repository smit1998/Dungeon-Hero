package unsw.dungeon;

//import sun.net.www.content.text.plain;

/**
 * A key entity that can be used to unlock a door
 */
public class Key extends ItemEntity {

    private final static int MAX_PICKUP = 1;

    private int id;

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
        // makes the player pickup the sword
        if (caller instanceof Player) {
            Player player = (Player) caller;
            player.pickupItem(this);
        }
        if (caller instanceof Boulder) {
            return false;
        }
        return true;
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
        // TODO Auto-generated method stub

    }

}