package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;

public class Enemy extends MoveableEntity {
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

    public void attack(Entity entity) {
        // todo
    }

    public void updateLifeStatus() {
        // todo
    }
}