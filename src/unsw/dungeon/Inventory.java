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
        if (this.weapon == null) {
            this.weapon = weapon; 
            weapon.setVisibility(false);
            return true; 
        } else {
            return false; 
        }
    }

    public boolean setKey (Key key) {
        if (this.key == null) {
            this.key = key; 
            key.setVisibility(false);
            return true; 
        } else {
            return false; 
        }
    }

    public boolean setPotion (Potion potion) {
        if (this.potion == null) {
            this.potion = potion; 
            potion.setVisibility(false);
            potion.startEffects();
            return true; 
        } else {
            return false; 
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