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
}