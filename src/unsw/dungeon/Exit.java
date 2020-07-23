package unsw.dungeon;

import java.util.HashSet;
import java.util.Set;

/**
 * An exit entity representing the dungeon exit
 */
public class Exit extends Entity implements Subject {

    private Set<Observer> observers = new HashSet<Observer>();
    private boolean isExiting;

    /**
     * Create an exit entity at the position (x, y)
     * 
     * @param x coordinate where the exit is placed
     * @param y coordinate where the exit is placed
     */
    public Exit(int x, int y) {
        super(x, y);
        isExiting = false;
    }

    /**
     * Get whether the player is trying to exit
     * 
     * @return whether the player is exiting
     */
    public boolean getExitStatus() {
        return isExiting;
    }

    /**
     * Interacts with the player to decide if the player can enter exit or not
     */
    @Override
    public boolean interact(Entity caller) {
        // if (caller instanceof Player) {
        // isExiting = true;
        // notifyObservers();
        // if (dungeon().isComplete()) {
        // return true;
        // }
        // isExiting = false;
        // notifyObservers();
        // }
        return false;
    }

    /**
     * attaches an observer to the player, by storing it to the observerlist
     * 
     * @param o the observer that wants to observe the player
     */
    public void attach(Observer o) {
        observers.add(o);

    }

    /**
     * removes an observer from the observer list
     * 
     * @param o the observer to be removed
     */
    public void detach(Observer o) {
        observers.remove(o);

    }

    /**
     * invokes the update method of all observers in the observer list
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