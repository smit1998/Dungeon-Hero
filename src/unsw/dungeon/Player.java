package unsw.dungeon;

import java.util.ArrayList;

/**
 * The player entity
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Player extends MoveableEntity implements Subject {

    // private Inventory inventory;
    private boolean isAlive;
    private Inventory inventory;
    private ArrayList<Observer> observers; 

    /**
     * Create a player positioned in square (x,y)
     * 
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y, dungeon);
        this.isAlive = true;
        this.inventory = new Inventory();
        this.observers = new ArrayList<Observer>(); 
    }

    // returns true if i can pickup item, otherwise false
    public Item pickupItem(Item item) {
        return inventory.addItem(item);
    }

    public ArrayList<Item> getInventory() {
        return this.inventory;
    }
    // public void removeItem(Item i) {
    // // todo
    // }

    public boolean attack(Entity e) {
        if ((e instanceof Enemy)) {
            Weapon weapon = getWeapon(); 
            if (weapon != null) {
                Enemy enemy = (Enemy) e; 
                enemy.updateLifeStatus(false);
                weapon.updateHitsRemaining();
                return true;
            }
            return false; 
        }
        return false; 
    }

    public Weapon getWeapon() {
        return inventory.getWeapon(); 
    }

    public void updateLifeStatus() {
        // todo
    }

    @Override
    public boolean interact(Entity caller) {
        if (caller instanceof Item) {
            Item item = (Item) caller; 
            if (pickupItem(item) == null) {
                return false; 
            } else {
                return true; 
            }
        }
        return false; 
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

    @Override
    public void moveUp() {
        super.moveUp(); 
        notifyObservers();
    }

    @Override
    public void moveDown() {
        super.moveDown(); 
        notifyObservers(); 
    }

    @Override
    public void moveLeft() {
        super.moveLeft(); 
        notifyObservers(); 
    }

    @Override
    public void moveRight() {
        super.moveRight(); 
        notifyObservers(); 
    }

}
