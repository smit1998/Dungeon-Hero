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
     * 
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
     * 
     * @return the item that has been picked up if the item is not picked up, due to
     *         the player not being able to, null is returned otherwise, the item is
     *         added to the inventory
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

    /**
     * @return a Key item if the player is holding one
     */
    public Key getKey() {
        return inventory.getKey();
    }

    /**
     * Method for player to attack another entity, using a weapon
     * 
     * @param e the entity that is being attacked
     * @return true if the attack was able to be carried through (player has a valid
     *         weapon), otherwise false
     */
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

    /**
     * @return a Weapon with the highest priority in the players inventory
     */
    public Weapon getWeapon() {
        return inventory.getWeapon();
    }

    /**
     * Changes the life status of the player - whether the player is alive or dead
     * 
     * @param newLifeStatus - a boolean, representing the new life status of the
     *                      player
     */
    public void updateLifeStatus(boolean newLifeStatus) {
        this.isAlive = newLifeStatus;
    }

    /**
     * @return the life status of the player true - the player is alive false - the
     *         player is dead
     */
    public boolean getLifeStatus() {
        return this.isAlive;
    }

    /**
     * Invokes the interaction of another entity on the player if possible
     * 
     * @param caller - the entity who wants to interact with the player
     * @return - true if caller was able to interact with the player otherwise false
     */
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

    /**
     * @return a boolean representing whether the player has a potion or not True -
     *         player has potion False - player does not have potion
     */
    public boolean hasPotion() {
        return getWeapon() instanceof InvincibilityPotion;
    }

    /**
     * attaches an observer to the player, by storing inside the observerList
     * 
     * @param o the observer that wants to observe the player
     */
    public void attach(Observer o) {
        observers.add(o);
    }

    /**
     * removes an observer from the observer list
     * 
     * @param o the observer to be removed
     */
    public void detach(Observer o) {
        observers.remove(o);
    }

    /**
     * invokes the update method of all observers in the observer list
     */
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    /**
     * attempts to move up the player up in the dungeon, and notify all observers of
     * the positional change
     */
    @Override
    public void moveUp() {
        super.moveUp();
        notifyObservers();
    }

    /**
     * attempts to move down the player up in the dungeon, and notify all observers
     * of the positional change
     */
    @Override
    public void moveDown() {
        super.moveDown();
        notifyObservers();
    }

    /**
     * attempts to move the player left in the dungeon, and notify all observers of
     * the positional change
     */
    @Override
    public void moveLeft() {
        super.moveLeft();
        notifyObservers();
    }

    /**
     * attempts to move the player right in the dungeon, and notify all observers of
     * the positional change
     */
    @Override
    public void moveRight() {
        super.moveRight();
        notifyObservers();
    }

}
