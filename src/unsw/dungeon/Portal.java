package unsw.dungeon;

public class Portal extends Entity {

    private int id;
    private Portal pair;

    public Portal(int x, int y, Dungeon dungeon, int id) {
        super(x, y, dungeon);
        this.id = id;
    }

    public boolean addPair(Portal pair) {
        // TODO Check not itself
        // TODO Check no pair
        this.pair = pair;
        return false;
    }

    public Portal getPair() {
        return pair;
    }

    public int getID() {
        return id;
    }

}