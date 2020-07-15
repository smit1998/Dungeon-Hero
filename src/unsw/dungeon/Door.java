package unsw.dungeon;

public class Door extends Entity {

    private String id;
    private boolean isOpen;

    public Door(int x, int y, Dungeon dungeon, String id) {
        super(x, y, dungeon);
        this.id = id;
    }

    public String getID() {
        return id;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public boolean open(Key key) {
        return false;
    }

}