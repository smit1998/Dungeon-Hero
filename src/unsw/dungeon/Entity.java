package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * An entity in the dungeon.
 * 
 * @author Robert Clifton-Everest
 *
 */
public abstract class Entity {

    private Coordinate position;
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
        position = new Coordinate(x, y);
        this.dungeon = dungeon;
        isVisible = new SimpleBooleanProperty(true);
    }

    /**
     * @return coordinates of the entity
     */
    public Coordinate getCoordinate() {
        return position;
    }

    /**
     * @return dungeon the entity belongs to
     */
    public Dungeon dungeon() {
        return dungeon;
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
        return position.x();
    }

    /**
     * @return an IntegerProperty, representing y coordinate of the entity
     */
    public IntegerProperty y() {
        return position.y();
    }

    /**
     * @return an integer, representing y coordinate of the entity
     */
    public int getY() {
        return position.getY();
    }

    /**
     * @return an integer, representing x coordinate of the entity
     */
    public int getX() {
        return position.getX();
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

}
