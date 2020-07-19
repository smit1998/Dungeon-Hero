package unsw.dungeon;

public class Boulder extends Entity {

    public Boulder(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
    }

    public boolean interact(Entity caller) {
        if (caller instanceof Player) {
            int oldX = getX();
            int oldY = getY();

            int newX = getX() * 2 - caller.getX();
            int newY = getY() * 2 - caller.getY();

            if (dungeon().interact(this, newX, newY)) {
                if (newX >= 0 && newX < dungeon().getWidth()) {
                    setX(newX);
                } else {
                    return false;
                }
                if (newY >= 0 && newY < dungeon().getHeight()) {
                    setY(newY);
                } else {
                    return false;
                }
                return true;
            } else if (getX() != oldX || getY() != oldY) {
                return true;
            }
        }
        return false;
    }

}
