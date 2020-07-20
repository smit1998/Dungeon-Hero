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
     * @param x coordinate position of entity
     * @param y coordinate position of entity
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
     * Abstract method to implemented in the entity
     * @return a boolean to whether the calling entity is able to interact with the current one
     */
    public boolean interact(Entity caller) {
        return false;
    }

    public IntegerProperty x() {
        return position.x();
    }

    public IntegerProperty y() {
        return position.y();
    }

    public int getY() {
        return position.getY();
    }

    public int getX() {
        return position.getX();
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
