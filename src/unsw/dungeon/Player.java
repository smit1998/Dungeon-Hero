package unsw.dungeon;

import java.util.HashSet;
import java.util.Set;

/**
 * The player entity
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Player extends MoveableEntity implements Subject {

    private boolean isAlive;
    private Inventory inventory;
    private Set<Observer> observers = new HashSet<Observer>();

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
    }

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

    public Key getKey() {
        return inventory.getKey();
    }

    public boolean attack(Enemy e) {
        Weapon weapon = getWeapon();
        if (weapon != null) {
            weapon.attack(e);
            return true;
        }
        return false;
    }

    public Weapon getWeapon() {
        return inventory.getWeapon();
    }

    public void updateLifeStatus(boolean newLifeStatus) {
        this.isAlive = newLifeStatus;
        if (isAlive == false) {
            setVisibility(false);
        }
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
                weapon.attack(enemy);
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
