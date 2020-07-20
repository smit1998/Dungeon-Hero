package unsw.dungeon;

import java.util.ArrayList;

public class Key extends Entity implements Item, Observer, Subject {

    public final static int MAX_PICKUP = 1;

    private int id;
    private ArrayList<Observer> observers; 

    public Key(int x, int y, Dungeon dungeon, int id) {
        super(x, y, dungeon);
        this.id = id;
        this.observers = new ArrayList<Observer>(); 
    }

    public int getID() {
        return id;
    }

    @Override
    public boolean interact(Entity caller) {
        // makes the player pickup the sword
        if (caller instanceof Player) {
            Player player = (Player) caller;
            if (player.pickupItem(this) != null) {
                player.attach(this);
            }
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