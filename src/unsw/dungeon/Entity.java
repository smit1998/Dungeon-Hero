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

    public Entity(int x, int y, Dungeon dungeon) {
        position = new Coordinate(x, y);
        this.dungeon = dungeon;
        isVisible = new SimpleBooleanProperty(true);
        ;
    }

    public Coordinate getCoordinate() {
        return position;
    }

    public Dungeon dungeon() {
        return dungeon;
    }

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

    public BooleanProperty isVisible() {
        return isVisible;
    }

    public void setVisibility(boolean isVisible) {
        System.out.println("set isvisiable to " + isVisible);
        this.isVisible.setValue(isVisible);
    }

}
