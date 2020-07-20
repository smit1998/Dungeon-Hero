package unsw.dungeon;

/**
 * interface for an item
 */
public abstract class ItemEntity extends Entity {

    /**
     * Constructor for item entity
     * 
     * @param x       coordinate position of entity
     * @param y       coordinate position of entity
     * @param dungeon entity belongs to
     */
    public ItemEntity(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
    }

    /**
     * @return an integer >= 1 representing the maximum amount of the item type that
     *         a player can pick up
     */
    public abstract int getMaxPickup();
}