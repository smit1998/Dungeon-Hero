package unsw.dungeon;

public class Wall extends Entity {

    public Wall(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
    }

    public boolean interact(Entity caller) {
        return false;
    }

}
