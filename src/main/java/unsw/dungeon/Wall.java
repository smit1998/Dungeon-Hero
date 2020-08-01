package main.java.unsw.dungeon;

;

/**
 * A wall entity with obstructs the movement of all other entities
 */
public class Wall extends Entity {

    /**
     * Constructs a wall at (x,y) in the dungeon
     * 
     * @param x       the horizontal position
     * @param y       the vertical position
     * @param dungeon the dungeon this entity belongs to
     */
    public Wall(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
    }

    /**
     * Interact with this entity
     * 
     * @return whether the interaction was successful
     */
    public boolean interact(Entity caller) {
        return false;
    }

    @Override
    public void tick(Dungeon dungeon) {
        // TODO Auto-generated method stub

    }
}
