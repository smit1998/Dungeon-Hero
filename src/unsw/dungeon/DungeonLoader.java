package unsw.dungeon;

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

    private int enemiesSpawned;
    private int switchesSpawned;
    private int treasureSpawned;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    public DungeonLoader(JSONObject json) {
        this.json = json;
    }

    /**
     * Parses the JSON to create a dungeon.
     * 
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }

        ComponentGoal goal = loadGoal(dungeon, json.getJSONObject("goal-condition"));
        dungeon.setGoal(goal);

        return dungeon;
    }

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
                switchesSpawned++;
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
                Sword sword = new Sword(x, y, dungeon);
                onLoad(sword);
                entity = sword;
                break;
            }
            case "invincibility": {
                InvincibilityPotion potion = new InvincibilityPotion(x, y, dungeon);
                onLoad(potion);
                entity = potion;
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

    public abstract void onLoad(Sword sword);

    public abstract void onLoad(InvincibilityPotion invincibilityPotion);

    private ComponentGoal loadGoal(Dungeon dungeon, JSONObject json) {
        String goal = json.getString("goal");
        switch (goal) {
            case "exit":
                ExitGoal exitGoal = new ExitGoal();
                for (Entity entity : dungeon.getEntities()) {
                    if (entity instanceof Exit) {
                        Exit exit = (Exit) entity;
                        exit.attach(exitGoal);
                    }
                }
                return exitGoal;
            case "enemies":
                EnemiesGoal enemyGoal = new EnemiesGoal(enemiesSpawned);
                for (Entity entity : dungeon.getEntities()) {
                    if (entity instanceof Enemy) {
                        Enemy e = (Enemy) entity;
                        e.attach(enemyGoal);
                    }
                }
                return enemyGoal;
            case "boulders":
                SwitchesGoal switchGoal = new SwitchesGoal(switchesSpawned);
                for (Entity entity : dungeon.getEntities()) {
                    if (entity instanceof FloorSwitch) {
                        FloorSwitch s = (FloorSwitch) entity;
                        s.attach(switchGoal);
                    }
                }
                return switchGoal;
            case "treasure":
                TreasureGoal treasureGoal = new TreasureGoal(treasureSpawned);
                for (Entity entity : dungeon.getEntities()) {
                    if (entity instanceof Treasure) {
                        Treasure t = (Treasure) entity;
                        t.attach(treasureGoal);
                    }
                }
                return treasureGoal;
            case "AND": {
                List<ComponentGoal> subgoals = new ArrayList<ComponentGoal>();
                for (Object o : json.getJSONArray("subgoals")) {
                    if (o instanceof JSONObject) {
                        subgoals.add(loadGoal(dungeon, (JSONObject) o));
                    }
                }
                return new AndCompositeGoal(subgoals);
            }
            case "OR": {
                List<ComponentGoal> subgoals = new ArrayList<ComponentGoal>();
                for (Object o : json.getJSONArray("subgoals")) {
                    if (o instanceof JSONObject) {
                        subgoals.add(loadGoal(dungeon, (JSONObject) o));
                    }
                }
                return new OrCompositeGoal(subgoals);
            }
            default:
                // TODO: throw exception
                break;
        }
        return null;
    }
}
