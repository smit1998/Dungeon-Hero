package unsw.dungeon;

/**
 * A portal entity that can transport the player to a different linked portal
 */
public class Portal extends Entity {

    private int id;
    private Portal pair;
    private Dungeon dungeon;

    /**
     * Constructs a new portal at (x,y) in the dungeon with the id
     * 
     * @param x       the horizontal position
     * @param y       the vertical position
     * @param dungeon the dungeon this portal belongs to
     * @param id      the id of this portal
     */
    public Portal(int x, int y, int id, Dungeon dungeon) {
        super(x, y);
        this.id = id;
        this.dungeon = dungeon;
    }

    /**
     * Get the id of this portal
     * 
     * @return the id of this portal
     */
    public int getID() {
        return this.id;
    }

    /**
     * Connect this portal to the give portal if the id of the portals are the same
     * 
     * @param pair the portal to be connected to
     * @return whether the connection was established
     */
    public boolean addPair(Portal pair) {
        if (this == pair) {
            return false;
        }
        if (getID() != pair.getID()) {
            return false;
        }
        this.pair = pair;
        return true;
    }

    /**
     * Interact with this portal. The entity will be transported to the other portal
     * if possible
     * 
     * @param caller the calling entity
     * @return whether the interaction was successful
     */
    public boolean interact(Entity caller) {
        if (pair == null) {
            return false;
        }

        if (caller instanceof Player || caller instanceof Boulder) {
            int oldX = caller.getX();
            int oldY = caller.getY();

            int newX = getX() - caller.getX() + pair.getX();
            int newY = getY() - caller.getY() + pair.getY();

            caller.setX(pair.getX());
            caller.setY(pair.getY());
            if (dungeon.interact(caller, newX, newY)) {
                caller.setX(newX);
                caller.setY(newY);
            } else {
                caller.setX(oldX);
                caller.setY(oldY);
            }
        }
        return false;
    }

    @Override
    public void tick(Dungeon dungeon) {
        // TODO Auto-generated method stub

    }
}