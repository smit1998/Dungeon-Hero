package unsw.dungeon;

public class Sword extends Entity {
    public final static int MAX_PICKUP = 1;
    public final static int STARTING_DURABILITY = 5;

    private boolean isPickedUp;
    private int remainingHits;

    public Sword(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        remainingHits = STARTING_DURABILITY;
    }

    public boolean getStatus() {
        return this.isPickedUp;
    }

    public void updateStatus(boolean newIsPickedUp) {
        this.isPickedUp = newIsPickedUp;
    }

    public void updateHitsRemaining() {

    }

}