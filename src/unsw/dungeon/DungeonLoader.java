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
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
            case "player": {
                Player player = new Player(dungeon, x, y);
                dungeon.setPlayer(player);
                onLoad(player);
                entity = player;
                break;
            }
            case "wall": {
                Wall wall = new Wall(x, y, dungeon);
                onLoad(wall);
                entity = wall;
                break;
            }
            case "exit": {
                Exit exit = new Exit(x, y, dungeon);
                onLoad(exit);
                entity = exit;
                break;
            }
            case "treasure": {
                Treasure treasure = new Treasure(x, y, dungeon);
                onLoad(treasure);
                entity = treasure;
                treasureSpawned++;
                break;
            }
            case "door": {
                int id = json.getInt("id");
                Door door = new Door(x, y, dungeon, id);
                onLoad(door);
                entity = door;
                break;
            }
            case "key": {
                int id = json.getInt("id");
                Key key = new Key(x, y, dungeon, id);
                onLoad(key);
                entity = key;
                break;
            }
            case "boulder": {
                Boulder boulder = new Boulder(x, y, dungeon);
                onLoad(boulder);
                entity = boulder;
                break;
            }
            case "switch": {
                FloorSwitch floorSwitch = new FloorSwitch(x, y, dungeon);
                onLoad(floorSwitch);
                entity = floorSwitch;
                break;
            }
            case "portal": {
                int id = json.getInt("id");
                Portal portal = new Portal(x, y, dungeon, id);
                onLoad(portal);
                entity = portal;
                break;
            }
            case "enemy": {
                Enemy enemy = new Enemy(x, y, dungeon);
                onLoad(enemy);
                entity = enemy;
                enemiesSpawned++;
                break;
            }
            case "sword": {
                Weapon sword = new Weapon(x, y, dungeon, new InstantKillAttack(), 5);
                onLoad(sword, "sword");
                entity = sword;
                break;
            }
            case "invincibility": {
                ArrayList<EffectBehaviour> effects = new ArrayList<EffectBehaviour>();
                effects.add(new InvincibilityEffect(dungeon));
                effects.add(new FearEffect(dungeon));
                effects.add(new MeleeKillEffect(dungeon));
                Potion potion = new Potion(x, y, dungeon, effects);
                onLoad(potion, "invincibility");
                entity = potion;
                break;
            }
            case "speed": {
                ArrayList<EffectBehaviour> effects = new ArrayList<EffectBehaviour>();
                effects.add(new SpeedUpEffect(dungeon));
                Potion potion = new Potion(x, y, dungeon, effects);
                onLoad(potion, "speedBoots");
                entity = potion;
                break;
            }
            case "dagger": {
                Weapon dagger = new Weapon(x, y, dungeon, new InstantKillAttack(), 1);
                onLoad(dagger, "dagger");
                entity = dagger;
                break;
            }
            case "checkpoint": {
                Checkpoint checkpoint = new Checkpoint(x, y, dungeon);
                onLoad(checkpoint);
                entity = checkpoint;
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

    /**
     * Load a goal
     * 
     * @param json a JSONObject describing a goal
     * @return the Component goal representing the goal described
     * @throws Error if goal in "goal" key is invalid
     */
    private ComponentGoal loadGoal(JSONObject json) {
        String goal = json.getString("goal");
        switch (goal) {
            case "exit":
                return new ExitGoal();
            case "enemies":
                return new EnemiesGoal(enemiesSpawned);
            case "boulders":
                return new SwitchesGoal();
            case "treasure":
                return new TreasureGoal(treasureSpawned);
            case "AND": {
                JSONArray jsonSubgoals = json.getJSONArray("subgoals");
                List<ComponentGoal> subgoals = loadGoals(jsonSubgoals);
                return new ComplexGoal(new AndGoalStrategy(), subgoals);
            }
            case "OR": {
                JSONArray jsonSubgoals = json.getJSONArray("subgoals");
                List<ComponentGoal> subgoals = loadGoals(jsonSubgoals);
                return new ComplexGoal(new OrGoalStrategy(), subgoals);
            }
            default: {
                throw new Error(String.format("Invalid goal type: \"%s\"", goal));
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
