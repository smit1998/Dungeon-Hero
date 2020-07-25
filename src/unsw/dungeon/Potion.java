package unsw.dungeon;

import unsw.dungeon.ItemEntity;

public class Potion extends ItemEntity {

    EffectBehaviour effectBehaviour; 

    public Potion(int x, int y, Dungeon dungeon, EffectBehaviour effectBehaviour) {
        super(x, y, dungeon);
        this.effectBehaviour = effectBehaviour; 
    }

    public void startEffect() {

    } 
    
    public boolean interact(Player player) {
        if (player.pickupItem(this) != null) {
            startEffect(); 
        }
        return true; 
    }

    public boolean interact(Boulder boulder) {
        return false; 
    }
}