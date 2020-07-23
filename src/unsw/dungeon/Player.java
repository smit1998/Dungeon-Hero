package unsw.dungeon;

/**
 * The player entity
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Player extends LifeEntity {

    private Inventory inventory;

    /**
     * Create a player positioned in square (x,y) in the dungeon
     * 
     * @param dungeon the dungeon this player belongs to
     * @param x       coordinate where the player spawns at
     * @param y       coordinate where the player spawns at
     */
    public Player(int x, int y) {
        super(x, y);
        this.inventory = new Inventory();
    }

    /**
     * Pickup an item e
     * 
     * @return the item that has been picked up. if the item is not picked up, due
     *         to the player not being able to, null is returned otherwise, the item
     *         is added to the inventory
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

    /**
     * Attack the given entity using a weapon
     * 
     * @return whether the attack was successful
     */
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
     * Interact with this entity
     * 
     * @param caller - the entity who wants to interact with the player
     * @return - true if caller was able to interact with the player otherwise false
     */
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
     * @return whether the player has an invincibility potion
     */
    public boolean hasPotion() {
        return getWeapon() instanceof InvincibilityPotion;
    }
}
