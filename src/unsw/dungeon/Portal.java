package unsw.dungeon;

public class Portal extends Entity {

    private String id;
    private Portal pair;

    public Portal(int x, int y, Dungeon dungeon, String id) {
        super(x, y, dungeon);
    }

    public boolean addPair(Portal pair) {
        // TODO Check not itself
        // Check no pair
        this.pair = pair;
        return false;
    }

    public Portal getPair() {
        return pair;
    }

    public String getID() {
        return id;
    }

}