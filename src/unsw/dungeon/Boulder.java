package unsw.dungeon;

public class Boulder extends Entity {

    public Boulder(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
    }

    public boolean interact(Entity caller) {
        if (caller instanceof Player) {
            int newX = getX() * 2 - caller.getX();
            int newY = getY() * 2 - caller.getY();
            if (dungeon().interact(this, newX, newY)) {
                if (getX() < dungeon().getWidth() - 1)
                    x().set(newX);
                if (getY() < dungeon().getHeight() - 1)
                    y().set(newY);
                return true;
            }
        }
        return false;
    }

}
