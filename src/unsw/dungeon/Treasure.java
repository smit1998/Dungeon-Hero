package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class Treasure extends Entity implements Subject {

    private List<Observer> observers = new ArrayList<Observer>();

    public Treasure(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
    }

    public boolean interact(Entity caller) {
        if (caller instanceof Player) {
            notifyObservers();
            setVisibility(false);

            // TODO Uncomment when implemented dungeon state
            // if (dungeon().isComplete()) {
            // System.out.println("VICTORY! All treasure picked up!");
            // }
        }
        if (caller instanceof Boulder) {
            return false;
        }
        return true;
    }

    public void attach(Observer o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }

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