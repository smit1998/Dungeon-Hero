package unsw.dungeon;

public abstract class LifeEntity extends Entity {

    private boolean isAlive; 

    public LifeEntity(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        this.isAlive = true;
    }

    public void moveUp() {
        if (dungeon().interact(this, getX(), getY() - 1)) {
            if (getY() > 0)
                setY(getY() - 1);
        }
    }

    public void moveDown() {
        if (dungeon().interact(this, getX(), getY() + 1)) {
            if (getY() < dungeon().getHeight() - 1)
                setY(getY() + 1);
        }
    }

    public void moveLeft() {
        if (dungeon().interact(this, getX() - 1, getY())) {
            if (getX() > 0)
                setX(getX() - 1);
        }
    }

    public void moveRight() {
        if (dungeon().interact(this, getX() + 1, getY())) {
            if (getX() < dungeon().getWidth() - 1)
                setX(getX() + 1);
        }
    }

    /**
     * Changes the life status of the player - whether the player is alive or dead
     * 
     * @param newLifeStatus - a boolean, representing the new life status of the
     *                      player
     */
    public void updateLifeStatus(boolean newLifeStatus) {
        this.isAlive = newLifeStatus;
        if (isAlive == false) {
            setVisibility(false);
        }
    }

    public boolean attack(LifeEntity e) {
        e.updateLifeStatus(false); 
        return true;
    }

    /**
     * @return the life status of the player true - the player is alive false - the
     *         player is dead
     */
    public boolean getLifeStatus() {
        return this.isAlive;
    }

}