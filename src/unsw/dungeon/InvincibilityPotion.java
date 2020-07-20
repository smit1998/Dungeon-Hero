package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;
import java.util.HashSet;
import java.util.Set;

/**
 * class for the invisibility potion
 * fears all enemies, and kills all enemies upon impact for 5 seconds
 */
public class InvincibilityPotion extends Entity implements Item, Weapon, Observer, Subject {

    public final static int MAX_PICKUP = 1;
    public final static int MAX_HITS = Integer.MAX_VALUE;
    public final static int PRIORITY = 100;

    private final static int DURATION_MS = 5000;

    private Set<Observer> observers = new HashSet<Observer>();

    private boolean isActive;

    /**
     * Constructor for an invincibility potion
     * @param dungeon that the potion belongs to 
     * @param x coordinate where the potion spawns
     * @param y coordinate where the potion spawns
     */
    public InvincibilityPotion(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        this.isActive = false;
    }

    /**
     * sets a timer task, for DURATION_MS amount of time
     * during this time all enemies run from the entity that uses potion
     */
    private void usePotion() {
        TimerTask effectsWearOff = new TimerTask() {
            public void run() {
                isActive = false;
                notifyObservers();
            }
        };
        new Timer().schedule(effectsWearOff, DURATION_MS);
    }

    /**
     * @return a boolean, true if the potion effect is active, otherwise false
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * @return integer MAX_PICKUP, the maximum number of invincibilityPotions someone can pickup
     */
    @Override
    public int getMaxPickup() {
        return MAX_PICKUP;
    }

    /**
     * @return the number of remaining hits the potion has left
     */
    @Override
    public int getRemainingHits() {
        return MAX_HITS;
    }

    /**
     * updates the number of hits remaining in the potion (since it is infinity, this does nothing)
     */
    @Override
    public void updateHitsRemaining() {
    }

    /**
     * attach an observer to observer list
     * @param o observer to be added
     */
    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    /**
     * remove an observer from the observer list
     * @param o observer to be removed
     */
    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    /**
     * calles the update method for all observers in the observer list
     * uses the concept of pulling, by passing the whole potion object to the observer
     */
    @Override
    public void notifyObservers() {
        for (Observer obs : observers) {
            obs.update(this);
        }
    }

    /**
     * Used to determine interaction between a calling entity and the invincibility potion
     * if calling object is a player, determines if the potion can be picked up 
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
     * makes the player step on position of current item
     */
    public void update(Subject obj) {
        if (obj instanceof Player) {
            Player player = (Player) obj;
            setX(player.getX());
            setY(player.getY());
        }
    }

    /**
     * @return an integer (0 - MAX_INTEGER) representing the priority of the current weapon
     */
    @Override
    public int getPriority() {
        return PRIORITY;
    }

<<<<<<< HEAD
=======
    /**
     * Attacks a given enemy, by setting their life status to false (kills enemy); 
     * @param e enemy to be attacked
     */
    @Override
    public void attack(Enemy e) {
        e.updateLifeStatus(false);
    }
>>>>>>> adf275a8401dcce5c0471fe7c40adfbc989c4a6a
}