package unsw.dungeon;

import java.util.HashSet;
import java.util.Set;

public class Key extends Entity implements Item, Observer, Subject {

    public final static int MAX_PICKUP = 1;

    private int id;
    private Set<Observer> observers = new HashSet<Observer>();

    public Key(int x, int y, Dungeon dungeon, int id) {
        super(x, y, dungeon);
        this.id = id;
    }

    public int getID() {
        return id;
    }

    @Override
    public boolean interact(Entity caller) {
        // makes the player pickup the sword
        if (caller instanceof Player) {
            Player player = (Player) caller;
            player.pickupItem(this);
        }
        if (caller instanceof Boulder) {
            return false;
        }
        return true;
    }

    @Override
    public int getMaxPickup() {
        return MAX_PICKUP;
    }

    @Override
    public void update(Subject obj) {
        if (obj instanceof Player) {
            Player player = (Player) obj;
            setX(player.getX());
            setY(player.getY());
        }
    }

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer obs : observers) {
            obs.update(this);
        }
    }
}