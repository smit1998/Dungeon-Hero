package unsw.dungeon;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * An entity in the dungeon.
 * 
 * @author Robert Clifton-Everest
 *
 */
public abstract class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    private Dungeon dungeon;
    private BooleanProperty isVisible;

    /**
     * Constructor for entity
     * 
     * @param x       coordinate position of entity
     * @param y       coordinate position of entity
     * @param dungeon entity belongs to
     */
    public Entity(int x, int y, Dungeon dungeon) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.dungeon = dungeon;
        isVisible = new SimpleBooleanProperty(true);
    }

    /**
     * @return dungeon the entity belongs to
     */
    public Dungeon dungeon() {
        return dungeon;
    }

    /**
     * Abstract method to implemented in the entity
     * 
     * @return a boolean to whether the calling entity is able to interact with the
     *         current one
     */
    public boolean interact(Entity caller) {
        return false;
    }

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }

    public void setX(int x) {
        x().set(x);
    }

    public void setY(int y) {
        y().set(y);
    }

    public BooleanProperty isVisible() {
        return isVisible;
    }

    public void setVisibility(boolean isVisible) {
        System.out.println("set isvisiable to " + isVisible);
        this.isVisible.setValue(isVisible);
    }

}
