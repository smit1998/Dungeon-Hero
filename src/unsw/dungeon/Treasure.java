package unsw.dungeon;

import java.util.HashSet;
import java.util.Set;

public class Treasure extends Entity implements Subject {

    private Set<Observer> observers = new HashSet<Observer>();

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