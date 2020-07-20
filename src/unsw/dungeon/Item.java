package unsw.dungeon;

/**
 * interface for an item
 */
public interface Item {
    /**
     * @return an integer >= 1 representing 
     * the maximum amount of the item type that a player can pick up 
     */
    public int getMaxPickup(); 
}