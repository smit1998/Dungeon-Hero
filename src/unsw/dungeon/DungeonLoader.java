package unsw.dungeon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;
    private File file;

    private int enemiesSpawned;
    private int treasureSpawned;

    /**
     * Constructor for a DungeonLoader
     * 
     * @param filename the filename of a file located in the 'dungeons' directory
     * @throws FileNotFoundException
     */
    public DungeonLoader(File file) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader(file)));
        this.file = file;
    }

    /**
     * Constructor for a DungeonLoader
     * 
     * @param filename the filename of a file located in the 'dungeons' directory
     * @throws FileNotFoundException
     */
    public DungeonLoader(String filename) throws FileNotFoundException {
        String path = "dungeons/" + filename;
        file = new File(path);
        json = new JSONObject(new JSONTokener(new FileReader(path)));
    }

    /**
     * Constructor for a DungeonLoader
     * 
     * @param json dungeon specifications
     */
    public DungeonLoader(JSONObject json) {
        this.json = json;
    }

    /**
     * Parses the JSON to create a dungeon.
     * 
     * @return a dungeon generated according to the dungeon specifications
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }

        ComponentGoal goal = loadGoal(json.getJSONObject("goal-condition"));
        dungeon.setGoal(goal);

        dungeon.connectGoals();
        dungeon.connectPortals();
        dungeon.connectPlayerObservers();

        return dungeon;
    }

    /**
     * Load an entity into the dungeon.
     * 
     * @param dungeon the dungeon the entity will be loaded into
     * @param json    JSONObject containing entity details
     */
    private void loadEntity(Dungeon dungeon, JSONObject json) {
        EntityType type = EntityType.get(json.getString("type"));
        int x = json.getInt("x");
        int y = json.getInt("y");

        int id = -1;
        try {
            id = json.getInt("id");
        } catch (Exception e) {
        }

        EntityFactory factory = new EntityFactory();
        Entity entity = factory.createEntity(type, x, y, id, dungeon);
        if (entity == null) {
            throw new Error("Invalid entity type");
        }
        switch (type) {
            case PLAYER: {
                Player player = (Player) entity;
                dungeon.setPlayer(player);
                onLoad(player);
                break;
            }
            case WALL: {
                Wall wall = (Wall) entity;
                onLoad(wall);
                break;
            }
            case EXIT: {
                Exit exit = (Exit) entity;
                onLoad(exit);
                break;
            }
            case TREASURE: {
                Treasure treasure = (Treasure) entity;
                onLoad(treasure);
                treasureSpawned++;
                break;
            }
            case DOOR: {
                Door door = (Door) entity;
                onLoad(door);
                break;
            }
            case KEY: {
                Key key = (Key) entity;
                onLoad(key);
                break;
            }
            case BOULDER: {
                Boulder boulder = (Boulder) entity;
                onLoad(boulder);
                break;
            }
            case SWITCH: {
                FloorSwitch floorSwitch = (FloorSwitch) entity;
                onLoad(floorSwitch);
                break;
            }
            case PORTAL: {
                Portal portal = (Portal) entity;
                onLoad(portal);
                break;
            }
            case ENEMY: {
                Enemy enemy = (Enemy) entity;
                onLoad(enemy);
                enemiesSpawned++;
                break;
            }
            case SWORD: {
                Weapon sword = (Weapon) entity;
                onLoad(sword, "sword");
                break;
            }
            case INVINCIBILITY: {
                Potion potion = (Potion) entity;
                onLoad(potion, "invincibility");
                break;
            }
            case SPEED: {
                Potion potion = (Potion) entity;
                onLoad(potion, "speedBoots");
                break;
            }

            case DAGGER: {
                Weapon dagger = (Weapon) entity;
                onLoad(dagger, "dagger");
                break;
            }
            case CHECKPOINT: {
                Checkpoint checkpoint = (Checkpoint) entity;
                onLoad(checkpoint);
                break;
            }
            case MINE: {
                Mine mine = (Mine) entity;
                onLoad(mine);
                break;
            }
        }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Player player);

    public abstract void onLoad(Wall wall);

    public abstract void onLoad(Exit exit);

    public abstract void onLoad(Treasure treasure);

    public abstract void onLoad(Door door);

    public abstract void onLoad(Key key);

    public abstract void onLoad(Boulder boulder);

    public abstract void onLoad(FloorSwitch floorSwitch);

    public abstract void onLoad(Portal portal);

    public abstract void onLoad(Enemy enemy);

    public abstract void onLoad(Weapon sword, String type);

    public abstract void onLoad(Potion potion, String type);

    public abstract void onLoad(Checkpoint checkpoint);

    public abstract void onLoad(Mine mine);

    /**
     * Load a goal
     * 
     * @param json a JSONObject describing a goal
     * @return the Component goal representing the goal described
     * @throws Error if goal in "goal" key is invalid
     */
    private ComponentGoal loadGoal(JSONObject json) {
        String goalString = json.getString("goal");
        GoalType type = GoalType.get(goalString);
        switch (type) {
            case EXIT_GOAL:
                return new ExitGoal();
            case ENEMIES_GOAL:
                return new EnemiesGoal(enemiesSpawned);
            case SWITCHES_GOAL:
                return new SwitchesGoal();
            case TREASURE_GOAL:
                return new TreasureGoal(treasureSpawned);
            case AND_GOAL: {
                JSONArray jsonSubgoals = json.getJSONArray("subgoals");
                List<ComponentGoal> subgoals = loadGoals(jsonSubgoals);
                return new ComplexGoal(new AndGoalStrategy(), subgoals);
            }
            case OR_GOAL: {
                JSONArray jsonSubgoals = json.getJSONArray("subgoals");
                List<ComponentGoal> subgoals = loadGoals(jsonSubgoals);
                return new ComplexGoal(new OrGoalStrategy(), subgoals);
            }
            default: {
                throw new Error(String.format("Invalid goal type: \"%s\"", goalString));
            }
        }
    }

    /**
     * Load a JSONArray of goals.
     * 
     * @param jsonSubgoals a JSONArray of goals
     * @return a list of goals which represent the JSONArray of goal
     */
    private List<ComponentGoal> loadGoals(JSONArray goalsJSON) {
        List<ComponentGoal> goals = new ArrayList<ComponentGoal>();
        for (int i = 0; i < goalsJSON.length(); i++) {
            goals.add(loadGoal(goalsJSON.getJSONObject(i)));
        }
        return goals;
    }

    public File getFile() {
        return file;
    }
}
