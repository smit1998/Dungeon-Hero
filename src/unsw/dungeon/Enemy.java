package unsw.dungeon;

import java.util.HashSet;
import java.util.Set;

/**
 * An enemy entity which moves towards/away from the player in hopes of
 * defeating them
 */
public class Enemy extends LifeEntity implements Subject {

    private Set<Observer> observers = new HashSet<Observer>();

    /**
     * Creates an enemy entity in the square(x, y)
     * 
     * @param x coordinate where the enemy is initially placed
     * @param y coordinate where the enemy is initially placed
     */
    public Enemy(int x, int y) {
        super(x, y);
    }

    /**
     * @return the player entity in the dungeon
     */
    private Player getPlayer(Dungeon dungeon) {
        return dungeon.getPlayer();
    }

    /**
     * Enemy entity moves towards the player entity
     */
    private void gotoPlayer(Dungeon dungeon) {
        Player player = getPlayer(dungeon);
        int fearModifier = player.hasPotion() ? -1 : 1;

        int diffX = (player.getX() - getX()) * fearModifier;
        int diffY = (player.getY() - getY()) * fearModifier;

        int xDirection = diffX > 0 ? 1 : -1;
        if (diffY == 0) {
            if (dungeon.interact(this, getX() + xDirection, getY())) {
                if (diffX > 0) {
                    moveRight(dungeon);
                } else {
                    moveLeft(dungeon);
                }
            } else {
                moveUp(dungeon);
            }
        }

        int yDirection = diffY > 0 ? 1 : -1;
        if (diffX == 0) {
            if (dungeon.interact(this, getX(), getY() + yDirection)) {
                if (diffY > 0) {
                    moveDown(dungeon);
                } else {
                    moveUp(dungeon);
                }
            } else {
                moveLeft(dungeon);
            }
        }

        if (diffX != 0 && diffY != 0) {
            if (diffX > 0) {
                moveRight(dungeon);
            } else {
                moveLeft(dungeon);
            }
            if (diffY > 0) {
                moveDown(dungeon);
            } else {
                moveUp(dungeon);
            }
        }
    }

    /**
     * Attacks the player when in contact
     * 
     * @param e is the entity that is to be attacked
     */
    public boolean attack(LifeEntity e) {
        e.kill();
        return true;
    }

    /**
     * Interacts with the player entity when player is on the same grid as enemy
     * 
     * @param caller is the player entity that is to be attacked
     */
    public boolean interact(Entity caller) {
        if (caller instanceof Player) {
            Player player = (Player) caller;
            if (player.attack(this) == true) {
                return true;
            } else {
                this.attack(player);
                return false;
            }
        }
        return false;
    }

    @Override
    public void kill() {
        super.kill();
        notifyObservers();
    }

    /**
     * attach the observer to the subject
     * 
     * @param o observer to be attached
     */
    public void attach(Observer o) {
        observers.add(o);
    }

    /**
     * removes an observer from the observer list
     * 
     * @param 0 the observer to be removed
     */
    public void detach(Observer o) {
        observers.remove(o);
    }

    /**
     * invokes the update method of all observers in the observer list
     */
    public void notifyObservers() {
        for (Observer obs : observers) {
            obs.update(this);
        }
    }

    @Override
    public void tick(Dungeon dungeon) {
        // TODO Auto-generated method stub
        gotoPlayer(dungeon);
    }

    /**
     * Move upwards in the dungeon if possible
     */
    public void moveUp(Dungeon dungeon) {
        if (dungeon.interact(this, getX(), getY() - 1)) {
            if (getY() > 0)
                setY(getY() - 1);
        }
    }

    /**
     * Move downwards in the dungeon if possible
     */
    public void moveDown(Dungeon dungeon) {
        if (dungeon.interact(this, getX(), getY() + 1)) {
            if (getY() < dungeon.getHeight() - 1)
                setY(getY() + 1);
        }
    }

    /**
     * Move leftwards in the dungeon if possible
     */
    public void moveLeft(Dungeon dungeon) {
        if (dungeon.interact(this, getX() - 1, getY())) {
            if (getX() > 0)
                setX(getX() - 1);
        }
    }

    /**
     * Move rightwards in the dungeon if possible
     */
    public void moveRight(Dungeon dungeon) {
        if (dungeon.interact(this, getX() + 1, getY())) {
            if (getX() < dungeon.getWidth() - 1)
                setX(getX() + 1);
        }
    }

}