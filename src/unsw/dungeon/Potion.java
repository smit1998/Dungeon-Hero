package unsw.dungeon;

import java.util.ArrayList;

public class Potion extends ItemEntity {

    private final int DURATION_TICKS = 150; 
    private final static int MAX_PICKUP = 1;

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

    public void startEffects() {
        for (EffectBehaviour e : effectsBehaviours) {
            e.startEffect(); 
        }
    } 

    public void stopEffects() {
        for (EffectBehaviour e : effectsBehaviours) {
            e.stopEffect(); 
        }
    }
    
    @Override
    public boolean interact(Entity caller) {
        if (caller instanceof Player) {
            return interact((Player) caller); 
        }
        if (caller instanceof Boulder) {
            return interact((Boulder) caller); 
        }
        return false; 
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
                stopEffects(); 
            }
        }
    }

    @Override
    public int getMaxPickup() {
        return MAX_PICKUP; 
    }

}