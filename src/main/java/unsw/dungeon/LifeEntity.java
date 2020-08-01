package main.java.unsw.dungeon;

;

/**
 * A living entity that be attack and be killed
 */
public abstract class LifeEntity extends Entity {

    private boolean isAlive;
    private boolean isMortal;

    /**
     * Constructs a new life entity at (x,y) in the dungeon
     * 
     * @param x       the horizontal position
     * @param y       the vertical position
     * @param dungeon the dungeon this entity belongs to
     */
    public LifeEntity(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        this.isAlive = true;
        this.isMortal = true;
    }

    public boolean isMortal() {
        return isMortal;
    }

    public void setMortality(boolean isMortal) {
        this.isMortal = isMortal;
    }

    /**
     * Move upwards in the dungeon if possible
     */
    public void moveUp() {
        if (dungeon().interact(this, getX(), getY() - 1)) {
            if (getY() > 0)
                setY(getY() - 1);
        }
    }

    /**
     * Move downwards in the dungeon if possible
     */
    public void moveDown() {
        if (dungeon().interact(this, getX(), getY() + 1)) {
            if (getY() < dungeon().getHeight() - 1)
                setY(getY() + 1);
        }
    }

    /**
     * Move leftwards in the dungeon if possible
     */
    public void moveLeft() {
        if (dungeon().interact(this, getX() - 1, getY())) {
            if (getX() > 0)
                setX(getX() - 1);
        }
    }

    /**
     * Move rightwards in the dungeon if possible
     */
    public void moveRight() {
        if (dungeon().interact(this, getX() + 1, getY())) {
            if (getX() < dungeon().getWidth() - 1)
                setX(getX() + 1);
        }
    }

    /**
     * Kill this entity
     */
    public void kill() {
        isAlive = false;
        setVisibility(false);
    }

    /**
     * Attack the given entity
     * 
     * @param e the entity to be attacked
     * @return whether the attack was successful
     */
    public abstract boolean attack(LifeEntity e);

    /**
     * Get whether the entity is alive
     * 
     * @return whether this entity is alive
     */
    public boolean getLifeStatus() {
        return this.isAlive;
    }

    public void tick() {

    }
}