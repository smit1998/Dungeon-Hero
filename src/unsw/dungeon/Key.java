package unsw.dungeon;

public class Key extends ItemEntity {

    public final static int MAX_PICKUP = 1;

    private int id;

    public Key(int x, int y, Dungeon dungeon, int id) {
        super(x, y, dungeon);
        this.id = id;
    }

    public int getID() {
        return id;
    }

    @Override
    public boolean interact(Entity caller) {
        // makes the player pickup the sword
        if (caller instanceof Player) {
            Player player = (Player) caller;
            player.pickupItem(this);
        }
        if (caller instanceof Boulder) {
            return false;
        }
        return true;
    }

    @Override
    public int getMaxPickup() {
        return MAX_PICKUP;
    }

}