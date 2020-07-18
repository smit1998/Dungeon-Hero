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

    public Enemy(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        this.isAlive = true;
        start();
    }

    public void start() {
        timer.scheduleAtFixedRate(task, 2000, 500);
    }

    private Player getPlayer() {
        return dungeon().getPlayer();
    }

    private void gotoPlayer() {
        Player player = getPlayer();
        int diffX = player.getX() - getX();
        int diffY = player.getY() - getY();

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

    @Override
    public boolean interact(Entity caller) {
        if (caller instanceof Player) {
            // TODO Determine whether player has weapon

            // Assume player has weapon
            updateLifeStatus(false);
            setVisibility(false);
            notifyObservers();
            return true;
        }
        return false;
    }

    public void attack(Entity entity) {
    }

    public void updateLifeStatus(boolean newLifeStatus) {
        this.isAlive = newLifeStatus;
    }

    public boolean getIsAlive() {
        return this.isAlive;
    }

    public void attach(Observer o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }

    }

    public void detach(Observer o) {
        observers.remove(o);

    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}