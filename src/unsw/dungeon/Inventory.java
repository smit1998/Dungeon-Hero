package unsw.dungeon;

/**
 * Inventory class - stores items for the player
 */
public class Inventory {

    private Weapon weapon;
    private Key key;
    private Potion potion;

    /**
     * adds an item to the inventory and does nothing if item type already exists
     */
    public void addItem(ItemEntity item) {
        if (item == null) {
            return; 
        }
        if (item.getClass() == Weapon.class) {
            setWeapon((Weapon) item);
        }
        if (item.getClass() == Key.class) {
            setKey((Key) item);
        }
        if (item.getClass() == Potion.class) {
            setPotion((Potion) item);
        }
    }

    /**
     * removes an item from the inventory, does nothing if item doesn't exist
     */
    public void removeItem(ItemEntity item) {
        if (item.getClass() == Weapon.class) {
            this.weapon = null;
        }
        if (item.getClass() == Key.class) {
            this.key = null;
        }
        if (item.getClass() == Potion.class) {
            this.potion = null;
        }
    }

    /**
     * sets the weapon slot in the inventory to the weapon provided
     */
    private void setWeapon(Weapon weapon) {
        if (this.weapon == null) {
            this.weapon = weapon;
            weapon.attachInventory(this);
            weapon.setVisibility(false);
            weapon.setIsPickedUp(true);
        }
    }

    /**
     * sets the key slot in the inventory to the key provided
     */
    private void setKey(Key key) {
        if (this.key == null) {
            this.key = key;
            key.setVisibility(false);
            key.setPickedUp(true);
        }
    }

    /**
     * sets the potion slot in the inventory to the potion provided
     */
    private void setPotion(Potion potion) {
        if (this.potion == null) {
            this.potion = potion;
            potion.setVisibility(false);
            potion.startEffects();
        }
    }

    // returns current weapon in inventory
    public Weapon getWeapon() {
        return weapon;
    }

    // return current key in inventory
    public Key getKey() {
        return key;
    }

    public Potion getPotion() {
        return potion;
    }

    // update of the game state ticks the potion effects
    public void tick(Dungeon dungeon) {
        tickPotion(dungeon);
    }

    // tick the potion
    private void tickPotion(Dungeon dungeon) {
        if (potion != null) {
            potion.tick(dungeon);
            if (!potion.isActive()) {
                potion = null;
            }
        }
    }
}