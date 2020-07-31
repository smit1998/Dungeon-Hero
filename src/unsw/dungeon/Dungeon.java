package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    private BooleanProperty isComplete = new SimpleBooleanProperty(false);

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

    public ArrayList<Enemy> getEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        for (Entity e : entities) {
            if (e instanceof Enemy) {
                enemies.add((Enemy) e);
            }
        }
        return enemies;
    }

    /**
     * Set the player entity of the dungeon
     * 
     * @param player the player entity
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    public Inventory getInventory() {
        return player.getInventory();
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
        essentialGoals = FXCollections.observableList(goal.getEssentialGoals());
    }

    /**
     * Return whether the dungeon goal has been completed
     * 
     * @return whether the dungeon goal is completed
     */
    public boolean isComplete() {
        return isCompleted().getValue();
    }

    /**
     * Return whether the dungeon goal has been completed
     * 
     * @return whether the dungeon goal is completed
     */
    public BooleanProperty isCompleted() {
        updateCompletion();
        return isComplete;
    }

    public void updateCompletion() {
        isComplete.setValue(goal.isComplete());
    }

    /**
     * Connect the dungeon goal to its respective goal requirements
     */
    public void connectGoals() {
        for (Entity entity : entities) {
            goal.attachTo(entity);
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

    public void connectPlayerObservers() {
        for (Entity entity : entities) {
            if (entity instanceof PlayerObserver) {
                player.addPlayerObserver((PlayerObserver) entity);
            }
        }
        player.notifyObservers();
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

    /**
     * no changes made when the game state updates
     */
    public void tick() {
        for (Entity e : entities) {
            e.tick(this);
        }
        List<Entity> toRemove = new ArrayList<>();
        for (Entity e : entities) {
            if (e.isVisible().getValue() == false) {
                toRemove.add(e);
            }
        }
        entities.removeAll(toRemove);
        updateEssentialGoals();
        updateCompletion();
    }

    /**
     * no changes made when the game state updates
     */
    public void tick(long ticks) {
        for (int i = 0; i < ticks; i++) {
            tick();
        }
    }

    private ObservableList<ComponentGoal> essentialGoals;

    private void updateEssentialGoals() {
        List<ComponentGoal> newGoals = goal.getEssentialGoals();
        List<ComponentGoal> toAdd = new ArrayList<>();
        List<ComponentGoal> toRemove = new ArrayList<>();
        for (ComponentGoal eGoal : newGoals) {
            if (!essentialGoals.contains(eGoal)) {
                toAdd.add(eGoal);
            }
        }
        for (ComponentGoal oldGoal : essentialGoals) {
            if (!newGoals.contains(oldGoal)) {
                toRemove.add(oldGoal);
            }
        }
        essentialGoals.removeAll(toRemove);
        essentialGoals.addAll(toAdd);
    }

    public ObservableList<ComponentGoal> getEssentialGoals() {
        return essentialGoals;
    }

    public String getGoalString() {
        return goal.toString();
    }

    public GoalType getGoalType() {
        return goal.getType();
    }
}
