package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class Exit extends Entity implements Subject {

    private List<Observer> observers = new ArrayList<Observer>();
    private boolean isExiting;

    /**
     * Create an exit entity at the position (x, y)
     * @param x coordinate where the exit is placed
     * @param y coordinate where the exit is placed
     */
    public Exit(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        isExiting = false;
    }

    /**
     * Update the state of the game
     */
    public void updateGameState() {
        return;
    }

    /**
     * @return the exit status i.e. if the player can exit or not
     */
    public boolean getExitStatus() {
        return isExiting;
    }

    /**
     * Interacts with the player to decide if the player can enter exit or not
     */
    @Override
    public boolean interact(Entity caller) {
        if (caller instanceof Player) {
            isExiting = true;
            notifyObservers();
            if (dungeon().isComplete()) {
                // TODO Update game state
                System.out.println("VICTORY!");
                return true;
            }
            isExiting = false;
            notifyObservers();
        }
        return false;
    }

    /**
     * attaches an observer to the player, by storing it to the observerlist
     * @param o the observer that wants to observe the player
     */ 
    public void attach(Observer o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }

    }

    /**
     * removes an observer from the observer list
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

}