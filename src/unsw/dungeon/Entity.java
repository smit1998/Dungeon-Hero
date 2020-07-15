package unsw.dungeon;

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

}
