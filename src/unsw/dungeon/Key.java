package unsw.dungeon;

public class Key extends Entity {

    public final static int MAX_PICKUP = 1;

    private int id;

    public Key(int x, int y, Dungeon dungeon, int id) {
        super(x, y, dungeon);
        this.id = id;
    }

    public int getID() {
        return id;
    }

}