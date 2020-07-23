package unsw.dungeon;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/**
 * An enemy entity which moves towards/away from the player in hopes of
 * defeating them
 */
public class Enemy extends LifeEntity implements Subject {

    private Set<Observer> observers = new HashSet<Observer>();

    private Timer timer = new Timer();
    private TimerTask task = new TimerTask() {
        public void run() {
            gotoPlayer();
        }
    };

    /**
     * Creates an enemy entity in the square(x, y)
     * 
     * @param x coordinate where the enemy is initially placed
     * @param y coordinate where the enemy is initially placed
     */
    public Enemy(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        start();
    }

    /**
     * Starts the timer for the enemy movement
     */
    public void start() {
        timer.scheduleAtFixedRate(task, 2000, 500);
    }

    /**
     * @return the player entity in the dungeon
     */
    private Player getPlayer() {
        // return dungeon().getPlayer();
        return null;
    }

    /**
     * Enemy entity moves towards the player entity
     */
    private void gotoPlayer() {
        // Player player = getPlayer();
        // int fearModifier = player.hasPotion() ? -1 : 1;

        // int diffX = (player.getX() - getX()) * fearModifier;
        // int diffY = (player.getY() - getY()) * fearModifier;

        // int xDirection = diffX > 0 ? 1 : -1;
        // if (diffY == 0) {
        // if (dungeon().interact(this, getX() + xDirection, getY())) {
        // if (diffX > 0) {
        // moveRight();
        // } else {
        // moveLeft();
        // }
        // } else {
        // moveUp();
        // }
        // }

        // int yDirection = diffY > 0 ? 1 : -1;
        // if (diffX == 0) {
        // if (dungeon().interact(this, getX(), getY() + yDirection)) {
        // if (diffY > 0) {
        // moveDown();
        // } else {
        // moveUp();
        // }
        // } else {
        // moveLeft();
        // }
        // }

        // if (diffX != 0 && diffY != 0) {
        // if (diffX > 0) {
        // moveRight();
        // } else {
        // moveLeft();
        // }
        // if (diffY > 0) {
        // moveDown();
        // } else {
        // moveUp();
        // }
        // }
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
        task = null;
        timer.cancel();
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

    public void tick() {

    }

}