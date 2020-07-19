package unsw.dungeon;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class InvincibilityPotion extends Entity implements Item, Weapon, Observer, Subject {

    public final static int MAX_PICKUP = 1;
    public final static int MAX_HITS = Integer.MAX_VALUE;
    public final static int PRIORITY = 100; 

    private final static int DURATION_MS = 5000;

    private ArrayList<Observer> observers = new ArrayList<Observer>();

    private boolean isActive;

    public InvincibilityPotion(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        this.isActive = false;
    }

    private void usePotion() {
        TimerTask effectsWearOff = new TimerTask() {
            public void run() {
                isActive = false;
                notifyObservers();
            }
        };
        new Timer().schedule(effectsWearOff, DURATION_MS);
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public int getMaxPickup() {
        return MAX_PICKUP;
    }

    @Override
    public int getRemainingHits() {
        return MAX_HITS;
    }

    @Override
    public void updateHitsRemaining() {
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

    public boolean interact(Entity caller) {
        if (caller instanceof Player) {
            Player player = (Player) caller;
            if (player.pickupItem(this) != null) {
                player.attach(this);
                usePotion();
            }
        }
        if (caller instanceof Boulder) {
            return false;
        }
        return true;
    }

    public void update(Subject obj) {
        if (obj instanceof Player) {
            Player player = (Player) obj;
            x().set(player.getX());
            y().set(player.getY());
        }
    }

    @Override
    public int getPriority() {
        return PRIORITY; 
    }
}