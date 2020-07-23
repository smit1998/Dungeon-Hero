package unsw.dungeon;

/**
 * A sword entity that can be used to defeat enemies
 */
public class Sword extends ItemEntity implements Weapon {

    private final static int MAX_PICKUP = 1;
    private final static int STARTING_DURABILITY = 5;
    private final static int PRIORITY = 50;

    private boolean isPickedUp;
    private int remainingHits;

    /**
     * Constructor for an sword
     * 
     * @param dungeon that the sword belongs to
     * @param x       coordinate where the sword spawns
     * @param y       coordinate where the sword spawns
     */
    public Sword(int x, int y) {
        super(x, y);
        this.remainingHits = STARTING_DURABILITY;
    }

    /**
     * @return true if sword has been picked up, otherwise false
     */
    public boolean getStatus() {
        return this.isPickedUp;
    }

    /**
     * changes the status of the sword being picked up
     * 
     * @param newIsPickedUp boolean that the pickedUp attribute will change to
     */
    public void updateStatus(boolean newIsPickedUp) {
        this.isPickedUp = newIsPickedUp;
    }

    /**
     * reduces the remaining hits of a sword by 1 if no more hits, then the sword is
     * removed from dungeon and inventory (by notifying observers)
     */
    public void updateHitsRemaining() {
        this.remainingHits--;
        if (remainingHits == 0) {
            setVisibility(false);
            notifyObservers();
        }
    }

    /**
     * @return an integer between 0 - STARTING DURABILITY representing how many hits
     *         the sword has left
     */
    public int getRemainingHits() {
        return this.remainingHits;
    }

    /**
     * @return an integer representing the maximum number of swords a player can
     *         carry
     */
    public int getMaxPickup() {
        return MAX_PICKUP;
    }

    /**
     * Used to determine interaction between a calling entity and the sword if
     * calling object is a player, determines if the sword can be picked up
     * 
     * @param caller - the entity who calls the interact method
     * @return true if the interaction can go through otherwise false
     */
    public boolean interact(Entity caller) {
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
     * @return an integer (0 - MAX_INTEGER) representing the priority of the current
     *         weapon
     */
    public int getPriority() {
        return PRIORITY;
    }

    /**
     * Attacks a given enemy, by setting their life status to false (kills enemy);
     * 
     * @param e enemy to be attacked
     */
    public void attack(LifeEntity e) {
        e.kill();
        updateHitsRemaining();
    }

    @Override
    public void tick(Dungeon dungeon) {
        // TODO Auto-generated method stub

    }
}