package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class Exit extends Entity implements Subject {

    private List<Observer> observers = new ArrayList<Observer>();
    private boolean isExiting;

    public Exit(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        isExiting = false;
    }

    public void updateGameState() {
        return;
    }

    public boolean getExitStatus() {
        return isExiting;
    }

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