package unsw.dungeon;

import java.util.HashSet;
import java.util.Set;

/**
 * A treasure entity that can be collected by the player
 */
public class Treasure extends Entity implements Subject {

    private Set<Observer> observers = new HashSet<Observer>();

    /**
     * Constructs a new treasure entity at (x,y) in the dungeon
     * 
     * @param x       the horizontal position
     * @param y       the vertical position
     * @param dungeon the dungeon this entity belongs to
     */
    public Treasure(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
    }

    /**
     * Interact with this entity. If the caller is a player, it will collect this
     * treasure.
     * 
     * @param caller the calling entity
     * @return whether the interaction was successful
     */
    public boolean interact(Entity caller) {
        if (caller instanceof Player) {
            notifyObservers();
            setVisibility(false);
        }
        if (caller instanceof Boulder) {
            return false;
        }
        return true;
    }

    /**
     * Attach the observer to the subject
     * 
     * @param o observer to be attached
     */
    public void attach(Observer o) {
        observers.add(o);
    }

    /**
     * Detach an observer from the subject
     * 
     * @param o observer to be removed
     */
    public void detach(Observer o) {
        observers.remove(o);
    }

    /**
     * Notifies all observers of a change to the subject
     */
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    @Override
    public void tick(Dungeon dungeon) {
        // TODO Auto-generated method stub

    }
}