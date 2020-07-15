package unsw.dungeon;

public class Key extends Entity {

    public final static int MAX_PICKUP = 1;

    private String id;

    public Key(int x, int y, Dungeon dungeon, String id) {
        super(x, y, dungeon);
        this.id = id;
    }

    public String getID() {
        return id;
    }

}