package unsw.dungeon;

import java.util.HashSet;
import java.util.Set;

/**
 * An enemy entity which moves towards/away from the player in hopes of
 * defeating them
 */
public class Enemy extends LifeEntity implements Subject, PlayerObserver {

    private Set<Observer> observers = new HashSet<Observer>();

    private final int NO_POSITION = -1;
    private int playerX = NO_POSITION;
    private int playerY = NO_POSITION;
    private boolean feared = false;

    /**
     * Creates an enemy entity in the square(x, y)
     * 
     * @param x coordinate where the enemy is initially placed
     * @param y coordinate where the enemy is initially placed
     */
    public Enemy(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
    }

    /**
     * Enemy entity moves towards the player entity
     */
    private void gotoPlayer() {
        int fearModifier = feared ? -1 : 1;

        int diffX = (playerX - getX()) * fearModifier;
        int diffY = (playerY - getY()) * fearModifier;

        int xDirection = diffX > 0 ? 1 : -1;
        if (diffY == 0) {
            if (dungeon().interact(this, getX() + xDirection, getY())) {
                if (diffX > 0) {
                    moveRight();
                } else {
                    moveLeft();
                }
            } else {
                moveUp();
            }
        }

        int yDirection = diffY > 0 ? 1 : -1;
        if (diffX == 0) {
            if (dungeon().interact(this, getX(), getY() + yDirection)) {
                if (diffY > 0) {
                    moveDown();
                } else {
                    moveUp();
                }
            } else {
                moveLeft();
            }
        }

        if (diffX != 0 && diffY != 0) {
            if (diffX > 0) {
                moveRight();
            } else {
                moveLeft();
            }
            if (diffY > 0) {
                moveDown();
            } else {
                moveUp();
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

    public boolean interact(Entity caller) {
        if (caller.getClass() == Player.class) {
            return interact((Player) caller);
        }
        if (caller.getClass() == Boulder.class) {
            return interact((Boulder) caller);
        }
        return false;
    }

    /**
     * Interacts with the player entity when player is on the same grid as enemy
     * 
     * @param caller is the player entity that is to be attacked
     */
    public boolean interact(Player player) {
        if (player.attack(this) == true) {
            return true;
        } else {
            this.attack(player);
            return false;
        }
    }

    /**
     * Interaction with a boulder
     * 
     * @param boulder that wants to run over the enemy If a boulder interacts with
     *                this enemy, the enemy dies, and boulder can locate to the
     *                current position
     */
    public boolean interact(Boulder boulder) {
        this.kill();
        return true;
    }

    /**
     * kills the current player, as well as notifying any observers of this change
     */
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

    private int ticksSinceLastMove;
    private final static int TICKS_PER_MOVE = 10;

    /**
     * moves the enemy towards the player in the next game state
     */
    @Override
    public void tick(Dungeon dungeon) {
        if (ticksSinceLastMove < TICKS_PER_MOVE) {
            ticksSinceLastMove++;
        } else {
            gotoPlayer();
            ticksSinceLastMove = 0;
        }
    }

    /**
     * updates the current position of the enemy
     */
    public void update(int x, int y) {
        playerX = x;
        playerY = y;
    }

    /**
     * updates the state of the enemy currently being feared or not
     */
    public void updateFear(boolean fear) {
        this.feared = fear;
    }

    public boolean isAlive() {
        return super.getLifeStatus();
    }

    @Override
    public boolean canCollide(Entity entity) {
        return true;
    }
}