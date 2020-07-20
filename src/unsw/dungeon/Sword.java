package unsw.dungeon;

import java.util.HashSet;
import java.util.Set;

public class Sword extends Entity implements Item, Observer, Weapon, Subject {

    public final static int MAX_PICKUP = 1;
    public final static int STARTING_DURABILITY = 5;
    public final static int PRIORITY = 50;

    private boolean isPickedUp;
    private int remainingHits;
    private Set<Observer> observers = new HashSet<Observer>();

    public Sword(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        this.remainingHits = STARTING_DURABILITY;
    }

    public boolean getStatus() {
        return this.isPickedUp;
    }

    public void updateStatus(boolean newIsPickedUp) {
        this.isPickedUp = newIsPickedUp;
    }

    public void updateHitsRemaining() {
        this.remainingHits--;
        if (remainingHits == 0) {
            setVisibility(false);
            notifyObservers();
        }
    }

    public int getRemainingHits() {
        return this.remainingHits;
    }

    @Override
    public int getMaxPickup() {
        return MAX_PICKUP;
    }

    @Override
    public boolean interact(Entity caller) {
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

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public void attack(Enemy e) {
        e.updateLifeStatus(false);
        updateHitsRemaining();
    }
}