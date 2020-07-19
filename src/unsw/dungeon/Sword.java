package unsw.dungeon;

import java.util.ArrayList;

public class Sword extends Entity implements Item, Observer, Weapon, Subject {

    // use strategy pattern here
    public final static int MAX_PICKUP = 1;
    public final static int STARTING_DURABILITY = 5;

    private boolean isPickedUp;
    private int remainingHits;
    private Inventory inventory;
    private Dungeon dungeon;
    private ArrayList<Observer> observers; 

    public Sword(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        this.remainingHits = STARTING_DURABILITY;
        this.inventory = null;
        this.dungeon = dungeon;
        this.observers = new ArrayList<Observer>(); 
    }

    public void setInventory(Inventory i) {
        inventory = i;
    }

    // returns the current status of the sword.
    public boolean getStatus() {
        return this.isPickedUp;
    }

    // changes the stauts of the sword if picked up or not.
    public void updateStatus(boolean newIsPickedUp) {
        this.isPickedUp = newIsPickedUp;
    }

    // drceases the remaining hits by 1.
    public void updateHitsRemaining() {
        this.remainingHits--;
        System.out.println("MAH WEAPON"); 
        if (remainingHits == 0) {
            setVisibility(false);
            notifyObservers(); 
        }
    }

    // returns the number of hits remaining.
    public int getRemainingHits() {
        return this.remainingHits;
    }

    @Override
    public int getMaxPickup() {
        return MAX_PICKUP;
    }

    @Override
    public boolean interact(Entity caller) {
        // makes the player pickup the sword
        if (caller instanceof Player) {
            Player player = (Player) caller;
            if (player.pickupItem(this) != null) {
                player.attach(this);
            }
            return true; 
        }
        return false;
    }

    @Override
    public void update(Subject obj) {
        if (obj instanceof Player) {
            Player player = (Player) obj;
            x().set(player.getX());
            y().set(player.getY());
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