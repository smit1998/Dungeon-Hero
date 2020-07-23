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
    private BooleanProperty isVisible;

    /**
     * Constructor for entity
     * 
     * @param x       coordinate position of entity
     * @param y       coordinate position of entity
     * @param dungeon entity belongs to
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        isVisible = new SimpleBooleanProperty(true);
    }

    /**
     * Interact with this entity
     * 
     * @param caller the calling entity
     * @return whether the calling entity is able to interact with this one
     */
    public abstract boolean interact(Entity caller);

    /**
     * @return an IntegerProperty, representing x coordinate of the entity
     */
    public IntegerProperty x() {
        return x;
    }

    /**
     * @return an IntegerProperty, representing y coordinate of the entity
     */
    public IntegerProperty y() {
        return y;
    }

    /**
     * @return an integer, representing y coordinate of the entity
     */
    public int getY() {
        return y().get();
    }

    /**
     * @return an integer, representing x coordinate of the entity
     */
    public int getX() {
        return x().get();
    }

    /**
     * Sets the x position of the entity on the dungeon
     * 
     * @param x coordinate where the entity belongs
     */
    public void setX(int x) {
        x().set(x);
    }

    /**
     * Sets the y position of the entity on the dungeon
     * 
     * @param y coordinate where the entity belongs
     */
    public void setY(int y) {
        y().set(y);
    }

    /**
     * @return a BooleanProperty, which determines whether an entity is visible in
     *         the dungeon or not
     */
    public BooleanProperty isVisible() {
        return isVisible;
    }

    /**
     * Changes the visibility of an entity to the specified argument provided
     * 
     * @param isVisible a boolean representing visibility
     */
    public void setVisibility(boolean isVisible) {
        this.isVisible.setValue(isVisible);
    }

    public abstract void tick();

}
