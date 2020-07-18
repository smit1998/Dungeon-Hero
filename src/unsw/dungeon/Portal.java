package unsw.dungeon;

public class Portal extends Entity {

    private int id;
    private Portal pair;

    public Portal(int x, int y, Dungeon dungeon, int id) {
        super(x, y, dungeon);
        this.id = id;
    }

    // what do we need this for //
    public boolean addPair(Portal pair) {
        // TODO Check not itself        
        if(pair.getCoordinate() == this.getCoordinate()) {
            return false;
        }

        // TODO Check no pair
        if() {
            return false;
        }

        this.pair = pair;
        return true;

    }

    public Portal getPair() {
        return this.pair;
    }

    public int getID() {
        return this.id;
    }

}