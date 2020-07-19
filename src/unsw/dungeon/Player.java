package unsw.dungeon;

import java.util.ArrayList;

/**
 * The player entity
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Player extends MoveableEntity implements Subject {

    private boolean isAlive;
    private Inventory inventory;
    private ArrayList<Observer> observers;

    /**
     * Create a player positioned in square (x,y)
     * @param x coordinate where the player spawns at
     * @param y coordinate where the player spawns at
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y, dungeon);
        this.isAlive = true;
        this.inventory = new Inventory();
        this.observers = new ArrayList<Observer>();
    }

    /**
     * Pickups an item e
     * @return the item that has been picked up
     * if the item is not picked up, due to the player not being able to, null is returned
     * otherwise, the item is added to the inventory
     */
    public Item pickupItem(Entity e) {
        if (e instanceof Item) {
            Item itemAdded = inventory.addItem((Item) e);
            if (itemAdded != null) {
                e.setVisibility(false);
                return itemAdded; 
            }
            return null;
        }
        return null;
    }

    public boolean attack(Entity e) {
        if ((e instanceof Enemy)) {
            Weapon weapon = getWeapon();
            if (weapon != null) {
                Enemy enemy = (Enemy) e;
                enemy.updateLifeStatus(false);
                enemy.setVisibility(false);
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

    public void updateLifeStatus(boolean newLifeStatus) {
        this.isAlive = newLifeStatus;
    }

    public boolean getLifeStatus() {
        return this.isAlive;
    }
    
    @Override
    public boolean interact(Entity caller) {
        if (caller instanceof Enemy) {
            Enemy enemy = (Enemy) caller;
            Weapon weapon = inventory.getWeapon();
            if (weapon != null) {
                enemy.setVisibility(false);
                enemy.updateLifeStatus(false);
                weapon.updateHitsRemaining();
                return false;
            } else {
                enemy.attack(this);
                return true;
            }
        }
        return false;
    }

    public boolean hasPotion() {
        return getWeapon() instanceof InvincibilityPotion;
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
