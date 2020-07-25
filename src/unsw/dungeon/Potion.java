package unsw.dungeon;

import java.util.ArrayList;

public class Potion extends ItemEntity {

    private final int DURATION_TICKS = 150; 
    private final static int MAX_PICKUP = 1;

    private ArrayList<EffectBehaviour> effects; 
    private boolean isActive; 
    private int ticksElapsed; 

    public Potion(int x, int y, Dungeon dungeon, ArrayList<EffectBehaviour> effects) {
        super(x, y, dungeon);
        this.isActive = false; 
        this.ticksElapsed = 0; 
        this.effects = effects; 
    }

    public void startEffects() {
        for (EffectBehaviour e : effects) {
            e.startEffect(); 
            isActive = true; 
        }
    } 

    public void stopEffects() {
        for (EffectBehaviour e : effects) {
            e.stopEffect(); 
            isActive = false; 
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
            startEffects(); 
        }
        return true; 
    }

    public boolean interact(Boulder boulder) {
        return false; 
    }

    @Override
    public void tick(Dungeon dungeon) {
        // System.out.println(ticksElapsed + " " + DURATION_TICKS); 
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