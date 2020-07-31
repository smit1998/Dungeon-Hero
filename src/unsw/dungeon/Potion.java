package unsw.dungeon;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Potion extends ItemEntity {

    private final int DURATION_TICKS = 150;

    private ArrayList<EffectBehaviour> effects;
    private int ticksElapsed;
    private boolean active;
    private BooleanProperty isPickedUp = new SimpleBooleanProperty(false);

    public Potion(int x, int y, Dungeon dungeon, ArrayList<EffectBehaviour> effects) {
        super(x, y, dungeon);
        this.ticksElapsed = 0;
        this.effects = effects;
        this.active = false;
    }

    public boolean isActive() {
        return active;
    }

    public void startEffects() {
        isPickedUp.setValue(true);
        active = true;
        for (EffectBehaviour e : effects) {
            e.startEffect();
        }
    }

    public void stopEffects() {
        for (EffectBehaviour e : effects) {
            e.stopEffect();
        }
        active = false;
        isPickedUp.setValue(false);
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
        player.pickupItem(this);
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

    public BooleanProperty isPickedUp() {
        return isPickedUp;
    }
}