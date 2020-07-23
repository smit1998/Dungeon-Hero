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

    /**
     * Constructs a dungeon of width and height
     * 
     * @param width  the number of horizontal grid squares
     * @param height the number of vertical grid squares
     */
    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
    }

    /**
     * Get the width of the dungeon
     * 
     * @return the width of the dungeon
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the height of the dungeon
     * 
     * @return the height of the dungeon
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get the player entity of the dungeon
     * 
     * @return the player entity of the dungeon
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Set the player entity of the dungeon
     * 
     * @param player the player entity
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Add an entity to the dungeon
     * 
     * @param entity the entity to be added
     */
    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    /**
     * Remove an entity from the dungeon
     * 
     * @param entity the entity to be removed
     */
    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    /**
     * Set the goal of the dungeon
     * 
     * @param goal
     */
    public void setGoal(ComponentGoal goal) {
        this.goal = goal;
    }

    /**
     * Return whether the dungeon goal has been completed
     * 
     * @return whether the dungeon goal is completed
     */
    public boolean isComplete() {
        return goal.isComplete();
    }

    /**
     * Connect the dungeon goal to its respective goal requirements
     */
    public void connectGoals() {
        for (Entity entity : entities) {
            if (entity instanceof Subject) {
                Subject s = (Subject) entity;
                goal.attachTo(s);
            }
        }
    }

    /**
     * Connect all portal entities with the same ID together
     */
    public void connectPortals() {
        List<Portal> portals = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity instanceof Portal) {
                portals.add((Portal) entity);
            }
        }
        for (Portal portalA : portals) {
            for (Portal portalB : portals) {
                portalA.addPair(portalB);
                portalB.addPair(portalA);
            }
        }
    }

    /**
     * Make the given caller entity interact with all entities at the given
     * coordinate
     * 
     * @param caller the entity starting the interaction
     * @param x      target horizontal coordinate position
     * @param y      target vertical coordinate position
     * @return whether the calling entity successfully interacted with the entities
     *         at the coordinate
     */
    public boolean interact(Entity caller, int x, int y) {
        if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
            return false;
        }

        for (Entity entity : entities) {
            if (entity == caller || entity == null)
                continue;
            if (entity.isVisible().getValue() && entity.getX() == x && entity.getY() == y) {
                if (!entity.interact(caller)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void tick() {
        // List<Entity> toRemove = new ArrayList<Entity>();
        for (Entity e : entities) {
            e.tick(this);
        }
        // for (Entity e : entities) {
        // if (!e.isVisible().getValue()) {
        // toRemove.add(e);
        // }
        // }
        // entities.removeAll(toRemove);
    }
}
