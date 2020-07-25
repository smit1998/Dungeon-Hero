package unsw.dungeon;

/**
 * An invisibility potion entity that shakes fear in all enemies, and kills all
 * enemies upon impact. Effects lasts for 5 seconds
 */
public class InvincibilityPotion extends ItemEntity implements Weapon {

    private final static int MAX_PICKUP = 1;
    private final static int PRIORITY = 100;

    private boolean isActive = false;
    private final int TPS = 30;
    private final int DURATION_SEC = 5;
    private final int DURATION_TICKS = TPS * DURATION_SEC;

    private int ticksElapsed = 0;

    /**
     * Constructor for an invincibility potion
     * 
     * @param dungeon that the potion belongs to
     * @param x       coordinate where the potion spawns
     * @param y       coordinate where the potion spawns
     */
    public InvincibilityPotion(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
    }

    /**
     * @return integer MAX_PICKUP, the maximum number of invincibilityPotions
     *         someone can pickup
     */
    public int getMaxPickup() {
        return MAX_PICKUP;
    }

    /**
     * Used to determine interaction between a calling entity and the invincibility
     * potion if calling object is a player, determines if the potion can be picked
     * up
     * 
     * @param caller - the entity who calls the interact method
     * @return true if the interaction can go through otherwise false
     */
    public boolean interact(Entity caller) {
        if (caller instanceof Player) {
            Player player = (Player) caller;
            if (player.pickupItem(this) != null) {
                usePotion();
            }
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
    }

    private void usePotion() {
        isActive = true;
    }

    private void wearOff() {
        isActive = false;
        notifyObservers();
    }

    @Override
    public void tick(Dungeon dungeon) {
        // TODO Auto-generated method stub
        if (isActive) {
            ticksElapsed++;
            if (ticksElapsed > DURATION_TICKS) {
                wearOff();
            }
        }

    }
}