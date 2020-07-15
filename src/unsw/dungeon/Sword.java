package unsw.dungeon;

public class Sword extends Entity {
    public final static int MAX_PICKUP = 1;
    public final static int STARTING_DURABILITY = 5;

    private boolean isPickedUp;
    private int remainingHits;

    // private String ID;

    public Sword(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        remainingHits = STARTING_DURABILITY;
    }

    // public Sword(String ID, int remainingHits) {
    // this.ID = ID;
    // this.remainingHits = remainingHits;
    // }

    // public String getID() {
    // return this.ID;
    // }

    public boolean getStatus() {
        return this.isPickedUp;
    }

    public void updateStatus(boolean newIsPickedUp) {

    }

    public void updateHitsRemaining() {

    }

}