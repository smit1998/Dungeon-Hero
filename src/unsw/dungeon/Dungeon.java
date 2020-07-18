/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon implements Observer {

    private int width, height;
    private List<Entity> entities;
    private Player player;
    private ComponentGoal goal;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public void setGoal(ComponentGoal goal) {
        this.goal = goal;
    }

    public boolean interact(Entity caller, int x, int y) {
        for (Entity entity : entities) {
            if (entity == caller || entity == null)
                continue;
            if (entity.getX() == x && entity.getY() == y) {
                if (!entity.interact(caller)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isComplete() {
        return goal.isComplete();
    }

    public void connectGoals() {
        for (Entity entity : entities) {
            if (entity instanceof Subject) {
                Subject s = (Subject) entity;
                goal.attachTo(s);
            }
        }
    }

    @Override
    public void update(Subject obj) {
        if (obj instanceof Entity) {
            Entity e = (Entity) obj; 
            System.out.println("Concurrent lolz");
            removeEntity(e); 
        }
    }
}
