package unsw.dungeon;

public class Door extends Entity {

    private int id;
    private boolean isOpen;

    public Door(int x, int y, Dungeon dungeon, int id) {
        super(x, y, dungeon);
        this.id = id;
        isOpen = false;
    }

    public int getID() {
        return id;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public boolean open(Key key) {
        return false;
    }

}