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

    public boolean addPair(Portal pair) {
        if (this == pair) {
            return false;
        }
        if (getID() != pair.getID()) {
            return false;
        }
        this.pair = pair;
        return true;
    }

    public boolean interact(Entity caller) {
        if (pair == null) {
            return false;
        }

        if (caller instanceof Player || caller instanceof Boulder) {
            int oldX = caller.getX();
            int oldY = caller.getY();

            int newX = getX() - caller.getX() + pair.getX();
            int newY = getY() - caller.getY() + pair.getY();

            if (newX < 0 || newX >= dungeon().getWidth() || newY < 0 || newY >= dungeon().getHeight()) {
                return false;
            }

            caller.setX(pair.getX());
            caller.setY(pair.getY());
            if (dungeon().interact(caller, newX, newY)) {
                caller.setX(newX);
                caller.setY(newY);
            } else {
                caller.setX(oldX);
                caller.setY(oldY);
            }
        }
        return false;
    }

}