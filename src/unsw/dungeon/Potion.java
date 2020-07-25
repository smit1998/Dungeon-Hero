package unsw.dungeon;

import java.util.ArrayList;

public class Potion extends ItemEntity {

    private final int DURATION_TICKS = 150; 

    private ArrayList<EffectBehaviour> effects; 
    private int ticksElapsed; 
    private boolean active; 

    public Potion(int x, int y, Dungeon dungeon, ArrayList<EffectBehaviour> effects) {
        super(x, y, dungeon);
        this.ticksElapsed = 0; 
        this.effects = effects; 
        this.active = false; 
    }

    public void startEffects() {
        active = true; 
        for (EffectBehaviour e : effects) {
            e.startEffect(); 
        }
    } 

    public void stopEffects() {
        for (EffectBehaviour e : effects) {
            e.stopEffect(); 
            active = false; 
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

    private boolean interact(Player player) {
        if (player.pickupItem(this)) {
            startEffects(); 
        }
        return true; 
    }

    private boolean interact(Boulder boulder) {
        return false; 
    }

    @Override
    public void tick(Dungeon dungeon) {
        if (active == true) {
            ticksElapsed++;
            if (ticksElapsed > DURATION_TICKS) {
                stopEffects(); 
            }
        }
    }
}