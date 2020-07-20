package unsw.dungeon;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class Enemy extends LifeEntity implements Subject {

    private Set<Observer> observers = new HashSet<Observer>();

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

    public void attack(Player p) {
        p.updateLifeStatus(false);
    }

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

    @Override
    public void updateLifeStatus(boolean newLifeStatus) {
        super.updateLifeStatus(newLifeStatus); 
        if (newLifeStatus == false) {
            task = null;
            timer.cancel();
        }
        notifyObservers();
    }

    public void attach(Observer o) {
        observers.add(o);
    }

    public void detach(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        for (Observer obs : observers) {
            obs.update(this);
        }
    }

}