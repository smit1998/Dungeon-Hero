package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import java.util.Timer;
import java.util.TimerTask;

public class Enemy extends MoveableEntity implements Subject {

    private List<Observer> observers = new ArrayList<Observer>();

    private boolean isAlive;

    private Timer timer = new Timer();
    private TimerTask task = new TimerTask() {
        public void run() {
            gotoPlayer();
        }
    };

    /**
     * Creates an enemy entity in the square(x, y)
     * @param x coordinate where the enemy is initially placed
     * @param y coordinate where the enemy is initially placed
     */
    public Enemy(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        this.isAlive = true;
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
        return dungeon().getPlayer();
    }

    /**
     * Enemy entity moves towards the player entity
     */
    private void gotoPlayer() {
        Player player = getPlayer();
        int fearModifier = player.hasPotion() ? -1 : 1;

        int diffX = (player.getX() - getX()) * fearModifier;
        int diffY = (player.getY() - getY()) * fearModifier;

        int xDirection = diffX > 0 ? 1 : -1;
        if (diffY == 0) {
            if (dungeon().interact(this, getX() + xDirection, getY())) {
                switch (xDirection) {
                    case 1:
                        moveRight();
                        break;
                    case -1:
                        moveLeft();
                        break;
                }
            } else {
                moveUp();
            }
        }

        int yDirection = diffY > 0 ? 1 : -1;
        if (diffX == 0) {
            if (dungeon().interact(this, getX(), getY() + yDirection)) {
                switch (yDirection) {
                    case 1:
                        moveDown();
                        break;
                    case -1:
                        moveUp();
                        break;
                }
            } else {
                moveLeft();
            }
        }

        if (diffX != 0 && diffY != 0) {
            if (diffX > 0) {
                moveRight();
            } else if (diffX < 0) {
                moveLeft();
            }
            if (diffY > 0) {
                moveDown();
            } else if (diffY < 0) {
                moveUp();
            }
        }
    }

    /**
     * Attacks the player when in contact
     * @param p is the player that is to be attacked
     */
    public void attack(Player p) {
        p.updateLifeStatus(false);
    }

    /**
     * Interacts with the player entity when player is on the same grid as enemy
     * @param caller is the player entity that is to be attacked
     */
    @Override
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

    public void attack(Entity entity) {
    }

    /**
     * Changes the life status and visibility of the enemy
     * @param newLifeStatus is the new boolean value of isAlive variable
     */
    public void updateLifeStatus(boolean newLifeStatus) {
        this.isAlive = newLifeStatus;
        if (newLifeStatus == false) {
            setVisibility(false);
            task = null;
            timer.cancel();
        }
        notifyObservers();
    }

    /**
     * @return the life status of the enemy entity
     */
    public boolean getIsAlive() {
        return this.isAlive;
    }

    /**
     * attaches as observer to the enemy, by storing it inside the observerlist  
     * @param o is the observer
     */
    public void attach(Observer o) {
        observers.add(o);
    }

    /**
     * removes an observer from the observer list
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

}