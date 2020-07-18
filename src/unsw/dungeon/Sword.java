package unsw.dungeon;

public class Sword extends Entity implements Item, Observer {

    // use strategy pattern here
    public final static int MAX_PICKUP = 1;
    public final static int STARTING_DURABILITY = 5;

    private boolean isPickedUp;
    private int remainingHits;

    public Sword(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        remainingHits = STARTING_DURABILITY;
    }

    // returns the current status of the sword.
    public boolean getStatus() {
        return this.isPickedUp;
    }

    // changes the stauts of the sword if picked up or not.
    public void updateStatus(boolean newIsPickedUp) {
        this.isPickedUp = newIsPickedUp;
    }

    // drceases the remaining hits by 1.
    public void updateHitsRemaining() {
        this.remainingHits--;
    }

    // returns the number of hits remaining.
    public int getRemainingHits() {
        return this.remainingHits;
    }

    @Override
    public int getMaxPickup() {
        return MAX_PICKUP; 
    }

    @Override
    public boolean interact(Entity caller) {
        if (caller instanceof Player) {
            Player player = (Player) caller; 
            // make the player interact with this object 
            if (player.interact(this) == false) {
                return false; 
            }  
            // if successful, make this observe the player
            else {
                player.attach(this); 
            }
        }
        return false;
    }

    @Override
    public void update(Subject obj) {
        // makes the sword item follow the player
        if (obj instanceof Player) {
            Player player = (Player) obj; 
            x().set(player.getX()); 
            y().set(player.getY()); 
        }
    }
}