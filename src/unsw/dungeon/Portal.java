package unsw.dungeon;

public class Portal extends Entity {

    private int id;
    private Portal pair;

    public Portal(int x, int y, Dungeon dungeon, int id) {
        super(x, y, dungeon);
        this.id = id;
    }

    public int getID() {
        return this.id;
    }

    public Portal getPair() {
        return this.pair;
    }

    public boolean addPair(Portal pair) {
        // TODO Check not itself        
        if(pair.getCoordinate() == this.getCoordinate()) {
            return false;
        }
        // TODO Check no pair
        if(this.pair != null) {
            return false;
        }
        this.pair = pair;
        return true;
    }

    @Override
    public boolean interact(Entity caller) {
        if(caller instanceof Player) {
            // todo
        }
        return false;
    }


}