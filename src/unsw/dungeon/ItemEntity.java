package unsw.dungeon;

import java.util.HashSet;
import java.util.Set;

/**
 * interface for an item
 */
public abstract class ItemEntity extends Entity implements Subject {

    private Set<Observer> observers = new HashSet<Observer>();

    /**
     * Constructor for item entity
     * 
     * @param x       coordinate position of entity
     * @param y       coordinate position of entity
     * @param dungeon entity belongs to
     */
    public ItemEntity(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
    }

    /**
     * @return an integer >= 1 representing the maximum amount of the item type that
     *         a player can pick up
     */
    public abstract int getMaxPickup();

    /**
     * attach an observer to observer list
     * 
     * @param o observer to be added
     */
    public void attach(Observer o) {
        observers.add(o);
    }

    /**
     * remove an observer from the observer list
     * 
     * @param o observer to be removed
     */
    public void detach(Observer o) {
        observers.remove(o);
    }

    /**
     * calls the update method for all observers in the observer list uses the
     * concept of pulling, by passing the whole potion object to the observer
     */
    public void notifyObservers() {
        for (Observer obs : observers) {
            obs.update(this);
        }
    }

}