package unsw.dungeon;

import java.util.HashSet;
import java.util.Set;

public class FloorSwitch extends Entity implements Subject {

    private Set<Observer> observers = new HashSet<Observer>();
    private boolean isPressed;

    public FloorSwitch(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        this.isPressed = false;
    }

    public boolean isPressed() {
        return this.isPressed;
    }

    public void updateIsPressed(boolean newIsPressed) {
        this.isPressed = newIsPressed;
    }

    public boolean interact(Entity caller) {
        if (caller instanceof Boulder) {
            updateIsPressed(true);
            // TODO Uncomment when game state is implemented
            // if (dungeon().isComplete()) {
            // // Change dungeon state
            // System.out.println("VICTORY! All Switches pressed down");
            // }
        } else {
            updateIsPressed(false);
        }
        notifyObservers();
        return true;
    }

    public void attach(Observer o) {
        observers.add(o);
    }

    public void detach(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}