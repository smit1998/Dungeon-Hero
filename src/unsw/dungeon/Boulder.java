package unsw.dungeon;

/**
 * A boulder entity that can be pushed by the player
 */
public class Boulder extends Entity {

    private Dungeon dungeon;

    /**
     * Constructs a Boulder positioned at square(x, y)
     * 
     * @param x coordinate where the boulder is placed
     * @param y coordinate where the boulder is placed
     */
    public Boulder(int x, int y, Dungeon dungeon) {
        super(x, y);
        this.dungeon = dungeon;
    }

    /**
     * Interacts with player entity and moves to the next position if possible
     */
    public boolean interact(Entity caller) {
        if (caller instanceof Player) {
            int oldX = getX();
            int oldY = getY();

            int newX = getX() * 2 - caller.getX();
            int newY = getY() * 2 - caller.getY();

            if (dungeon.interact(this, newX, newY)) {
                setX(newX);
                setY(newY);
            } else if (getX() != oldX || getY() != oldY) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void tick(Dungeon dungeon) {
        // TODO Auto-generated method stub

    }

}
