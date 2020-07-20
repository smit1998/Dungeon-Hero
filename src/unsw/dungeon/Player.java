package unsw.dungeon;

/**
 * The player entity
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Player extends LifeEntity{

    private Inventory inventory;

    /**
     * Create a player positioned in square (x,y)
     * 
     * @param x coordinate where the player spawns at
     * @param y coordinate where the player spawns at
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y, dungeon);
        this.inventory = new Inventory();
    }

    /**
     * Pickups an item e
     * 
     * @return the item that has been picked up if the item is not picked up, due to
     *         the player not being able to, null is returned otherwise, the item is
     *         added to the inventory
     */
    public ItemEntity pickupItem(ItemEntity e) {
        ItemEntity itemAdded = inventory.addItem(e);
        if (itemAdded != null) {
            e.setVisibility(false);
            return itemAdded;
        }
        return null;
    }

    /**
     * @return a Key item if the player is holding one
     */
    public Key getKey() {
        return inventory.getKey();
    }

    @Override
    public boolean attack(LifeEntity e) {
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
    private Weapon getWeapon() {
        return inventory.getWeapon();
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
