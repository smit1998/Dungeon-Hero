package unsw.dungeon;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class InvincibilityPotion extends Entity implements Item, Weapon, Observer, Subject {

    private boolean isActive;
    private Timer remainingTime;
    public final static int MAX_PICKUP = 1;
    public final static int MAX_HITS = Integer.MAX_VALUE; 
    private ArrayList<Observer> observers; 

    public InvincibilityPotion(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        this.isActive = false;
        this.observers = new ArrayList<Observer>(); 
        // this.remainingTime = NEED TO SET IT TO A PARTICULAR TIME.
    }

    public void updateStatus(boolean newActive) {
        this.isActive = newActive;
    }

    public void startTimer() {
        // TODO
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
            }
            return true; 
        }
        return false;
    }

    public void update(Subject obj) {
        if (obj instanceof Player) {
            Player player = (Player) obj;
            x().set(player.getX());
            y().set(player.getY());
        }
    }
}