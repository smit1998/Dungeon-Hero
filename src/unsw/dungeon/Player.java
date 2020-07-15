package unsw.dungeon;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
    private Inventory inventory; 
    private Boolean isAlive; 

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.isAlive = true;
        this.inventory = new Inventory(); 
    }

    public Boolean canPickupItem(Item i) {
        //todo
    } 

    public void pickupItem(Item i) {
        //todo
    }

    public void removeItem(Item i) {
        //todo
    }

    public void attack(Entity e) {
        //todo
    }

    public void updateLifeStatus() {
        //todo
    }

    public void moveUp() {
        if (getY() > 0)
            y().set(getY() - 1);
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1)
            y().set(getY() + 1);
    }

    public void moveLeft() {
        if (getX() > 0)
            x().set(getX() - 1);
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1)
            x().set(getX() + 1);
    }
}
