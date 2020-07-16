package unsw.dungeon;

/**
 * The player entity
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Player extends MoveableEntity {

    // private Inventory inventory;
    private boolean isAlive;

    /**
     * Create a player positioned in square (x,y)
     * 
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y, dungeon);
        this.isAlive = true;
        // this.inventory = new Inventory();
    }

    // public boolean canPickupItem(Item i) {
    // // todo
    // }

    // public void pickupItem(Item i) {
    // // todo
    // }

    // public void removeItem(Item i) {
    // // todo
    // }

    public void attack(Entity e) {
        // todo
    }

    public void updateLifeStatus() {
        // todo
    }

}
