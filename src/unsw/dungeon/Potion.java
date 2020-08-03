package unsw.dungeon;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Potion extends ItemEntity implements DetailedItem {

    private final int DURATION_TICKS = 150;

    private ArrayList<EffectBehaviour> effects;
    private int ticksElapsed;
    private boolean active;
    private BooleanProperty isPickedUp = new SimpleBooleanProperty(false);
    private StringProperty info;

    public Potion(int x, int y, Dungeon dungeon, ArrayList<EffectBehaviour> effects) {
        super(x, y, dungeon);
        this.ticksElapsed = 0;
        this.effects = effects;
        this.active = false;
        info = new SimpleStringProperty((int) (DURATION_TICKS / 30) + "s");
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
        if (caller.getClass() == Player.class) {
            return interact((Player) caller);
        }
        if (caller.getClass() == Boulder.class) {
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
            info.setValue((int) ((DURATION_TICKS - ticksElapsed) / 30) + "s");
            if (ticksElapsed > DURATION_TICKS) {
                stopEffects();
            }
        }
    }

    public BooleanProperty isPickedUp() {
        return isPickedUp;
    }

    @Override
    public boolean canCollide(Entity entity) {
        return true;
    }

    public MeleeKillEffect getMeleeKillEffect() {
        for (EffectBehaviour effect : effects) {
            if (effect.getClass() == MeleeKillEffect.class) {
                return (MeleeKillEffect) effect;
            }
        }
        return null;
    }

    public StringProperty getInfoProperty() {
        return info;
    }

}