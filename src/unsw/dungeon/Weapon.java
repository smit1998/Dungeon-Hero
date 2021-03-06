package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Interface for a weapon, used for items that can be used to attack enemies
 */
public class Weapon extends ItemEntity implements DetailedItem {

    private AttackBehaviour attackBehaviour;
    private int hits;
    private Inventory inventory;
    private BooleanProperty isPickedUp = new SimpleBooleanProperty(false);
    private StringProperty info;

    public Weapon(int x, int y, Dungeon dungeon, AttackBehaviour attackBehaviour, int hits) {
        super(x, y, dungeon);
        this.attackBehaviour = attackBehaviour;
        this.hits = hits;
        info = new SimpleStringProperty("x" + hits);
    }

    /**
     * attacks an enemy, with unique interaction depending on the weapon used
     * 
     * @param the enemy to be attacked
     */
    public void attack(LifeEntity e) {
        attackBehaviour.attack(e);
        decrementHits();
    }

    public void attachInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    private void decrementHits() {
        hits--;
        info.setValue("x" + hits);
        if (hits == 0) {
            deleteWeapon();
            setIsPickedUp(false);
        }
    }

    private void deleteWeapon() {
        inventory.removeItem(this);
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
    }

    public BooleanProperty isPickedUp() {
        return isPickedUp;
    }

    public void setIsPickedUp(boolean newIsPickedUp) {
        isPickedUp.setValue(newIsPickedUp);
    }

    @Override
    public boolean canCollide(Entity entity) {
        return !(entity instanceof Boulder);
    }

    public StringProperty getInfoProperty() {
        return info;
    }

}