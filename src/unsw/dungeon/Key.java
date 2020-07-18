package unsw.dungeon;

public class Key extends Entity implements Item, Observer {

    public final static int MAX_PICKUP = 1;

    private int id;
    private Inventory inventory;

    public Key(int x, int y, Dungeon dungeon, int id) {
        super(x, y, dungeon);
        this.id = id;
        this.inventory = null;
    }

    public int getID() {
        return id;
    }

    @Override
    public boolean interact(Entity caller) {
        // makes the player pickup the sword
        if (caller instanceof Player) {
            Player player = (Player) caller;
            if (player.pickupItem(this) != null) {
                player.attach(this);
            }
            return true;
        }
        return false;
    }

    @Override
    public int getMaxPickup() {
        return MAX_PICKUP;
    }

    @Override
    public void setInventory(Inventory i) {
        this.inventory = i;
    }

    @Override
    public void update(Subject obj) {
        if (obj instanceof Player) {
            Player player = (Player) obj;
            x().set(player.getX());
            y().set(player.getY());
        }
    }
}