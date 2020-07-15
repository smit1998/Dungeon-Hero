package unsw.dungeon;

import javafx.beans.property.IntegerProperty;

/**
 * An entity in the dungeon.
 * 
 * @author Robert Clifton-Everest
 *
 */
public abstract class Entity {

    private Coordinate position;
    private Dungeon dungeon;

    public Entity(int x, int y, Dungeon dungeon) {
        position = new Coordinate(x, y);
        this.dungeon = dungeon;
    }

    public Coordinate getCoordinate() {
        return position;
    }

    public Dungeon getDungeon() {
        return dungeon;
    }

    public Entity interact(Entity caller) {
        return this;
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

}
