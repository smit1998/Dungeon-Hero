package unsw.dungeon;

/**
 * The player entity
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Player extends MoveableEntity {

    private boolean isAlive;
    private Inventory inventory;

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

    public boolean attack(Enemy e) {
        Weapon weapon = getWeapon();
        if (weapon != null) {
            weapon.attack(e);
            return true;
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
        if (isAlive == false) {
            setVisibility(false);
        }
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
                weapon.attack(enemy);
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
}
