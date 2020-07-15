package unsw.dungeon;

public abstract class MoveableEntity extends Entity {

    public MoveableEntity(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
    }

    public void moveUp() {
        if (dungeon().interact(this, getX(), getY() - 1)) {
            if (getY() > 0)
                y().set(getY() - 1);
        }
    }

    public void moveDown() {
        if (dungeon().interact(this, getX(), getY() + 1)) {
            if (getY() < dungeon().getHeight() - 1)
                y().set(getY() + 1);
        }
    }

    public void moveLeft() {
        if (dungeon().interact(this, getX() - 1, getY())) {
            if (getX() > 0)
                x().set(getX() - 1);
        }
    }

    public void moveRight() {
        if (dungeon().interact(this, getX() + 1, getY())) {
            if (getX() < dungeon().getWidth() - 1)
                x().set(getX() + 1);
        }
    }

}