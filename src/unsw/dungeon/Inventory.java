package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * Inventory class - stores items for the player
 */
public class Inventory {

    private Weapon weapon; 
    private Key key; 
    private Potion potion;

    public boolean addItem(ItemEntity item) {
        if (item instanceof Weapon) {
            return setWeapon((Weapon) item); 
        } 
        if (item instanceof Key) {
            return setKey((Key) item); 
        }
        if (item instanceof Potion) {
            return setPotion((Potion) item); 
        }
        return false; 
    }
    public boolean setWeapon (Weapon weapon) {
        boolean isEmpty = (this.weapon == null) ? true : false; 
        this.weapon = (this.weapon == null) ? this.weapon : weapon; 
        return isEmpty; 
    }

    public boolean setKey (Key key) {
        boolean isEmpty = (this.key == null) ? true : false; 
        this.key = (this.key == null) ? this.key : key; 
        return isEmpty; 
    }

    public boolean setPotion (Potion potion) {
        boolean isEmpty = (this.potion == null) ? true : false; 
        this.potion = (this.potion == null) ? this.potion : potion; 
        return isEmpty; 
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