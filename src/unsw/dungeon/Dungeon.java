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
public class Dungeon {

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

    public void setGoal(ComponentGoal goal) {
        this.goal = goal;
    }

    public List<Entity> getEntities() {
        return new ArrayList<Entity>(entities);
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
}
