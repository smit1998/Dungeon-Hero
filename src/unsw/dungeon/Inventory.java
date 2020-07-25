package unsw.dungeon;

/**
 * Inventory class - stores items for the player
 */
public class Inventory {

    private Weapon weapon; 
    private Key key; 
    private Potion potion;

    public void addItem(ItemEntity item) {
        if (item instanceof Weapon) {
            setWeapon((Weapon) item); 
        } 
        if (item instanceof Key) {
            setKey((Key) item); 
        }
        if (item instanceof Potion) {
            setPotion((Potion) item); 
        }
    }

    public void removeItem(ItemEntity item) {
        if (item instanceof Weapon) {
            this.weapon = null;
        } 
        if (item instanceof Key) {
            this.key = null; 
        }
        if (item instanceof Potion) {
            this.potion = null; 
        }
    }

    private void setWeapon (Weapon weapon) {
        if (this.weapon == null) {
            this.weapon = weapon; 
            weapon.setVisibility(false);
        } 
    }

    private void setKey (Key key) {
        if (this.key == null) {
            this.key = key; 
            key.setVisibility(false);
        }
    }

    private void setPotion (Potion potion) {
        if (this.potion == null) {
            this.potion = potion; 
        }
    }

    public Weapon getWeapon() {
        return weapon; 
    }

    public Key getKey() {
        return key; 
    }

    public void tick(Dungeon dungeon) {
    }
}