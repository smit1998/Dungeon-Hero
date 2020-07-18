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

    public boolean hasPair() {
        return pair != null;
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
        if (caller instanceof Player) {
            int oldX = getX();
            int oldY = getY();

            int newX = getX() - caller.getX() + pair.getX();
            int newY = getY() - caller.getY() + pair.getY();
            if (dungeon().interact(caller, newX, newY)) {
                if (newX >= 0 && newX < dungeon().getWidth()) {
                    caller.x().set(newX);
                }
                if (newY >= 0 && newY < dungeon().getHeight()) {
                    caller.y().set(newY);
                }
            } else {
                caller.x().set(pair.getX());
                caller.y().set(pair.getY());
                if (dungeon().interact(caller, newX, newY)) {
                    if (newX >= 0 && newX < dungeon().getWidth()) {
                        caller.x().set(newX);
                    }
                    if (newY >= 0 && newY < dungeon().getHeight()) {
                        caller.y().set(newY);
                    }
                }
            }
        } else if (caller instanceof Boulder) {
            int newX = getX() - caller.getX() + pair.getX();
            int newY = getY() - caller.getY() + pair.getY();
            if (dungeon().interact(caller, newX, newY)) {
                if (newX >= 0 && newX < dungeon().getWidth()) {
                    caller.x().set(newX);
                }
                if (newY >= 0 && newY < dungeon().getHeight()) {
                    caller.y().set(newY);
                }
            }
        }
        return false;
    }

}