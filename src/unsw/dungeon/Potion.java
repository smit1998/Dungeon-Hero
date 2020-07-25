package unsw.dungeon;

import java.util.ArrayList;

import unsw.dungeon.ItemEntity;

public class Potion extends ItemEntity {

    private final int DURATION_TICKS = 150; 

    private ArrayList<EffectBehaviour> effectsBehaviours; 
    private boolean isActive; 
    private int ticksElapsed; 

    public Potion(int x, int y, Dungeon dungeon, EffectBehaviour... effectBehaviours) {
        super(x, y, dungeon);
        this.isActive = false; 
        this.ticksElapsed = 0; 
        for (EffectBehaviour e : effectBehaviours) {
            this.effectsBehaviours.add(e); 
        }
    }

    public void startEffect() {

    } 

    public void endEffect() {

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

    @Override
    public void tick(Dungeon dungeon) {
        if (isActive) {
            ticksElapsed++;
            if (ticksElapsed > DURATION_TICKS) {
                endEffect();
            }
        }
    }
}