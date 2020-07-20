package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;

/**
 * class for the invisibility potion fears all enemies, and kills all enemies
 * upon impact for 5 seconds
 */
public class InvincibilityPotion extends ItemEntity implements Weapon {

    public final static int MAX_PICKUP = 1;
    public final static int PRIORITY = 100;

    private final static int DURATION_MS = 5000;

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
     * sets a timer task, for DURATION_MS amount of time during this time all
     * enemies run from the entity that uses potion
     */
    private void usePotion() {
        TimerTask effectsWearOff = new TimerTask() {
            public void run() {
                notifyObservers();
            }
        };
        new Timer().schedule(effectsWearOff, DURATION_MS);
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
    @Override
    public int getPriority() {
        return PRIORITY;
    }

    /**
     * Attacks a given enemy, by setting their life status to false (kills enemy);
     * 
     * @param e enemy to be attacked
     */
    @Override
    public void attack(LifeEntity e) {
        e.kill();
    }
}