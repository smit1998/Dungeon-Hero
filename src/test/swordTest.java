package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONArray;
import org.json.JSONObject;

import unsw.dungeon.*;

class swordTest{
    // basic test
    void swordtest1() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject swordJSON = new JSONObject();
        swordJSON.put("x", 1);
        swordJSON.put("y", 0);
        swordJSON.put("type", "sword");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(swordJSON);

        JSONObject json = new JSONObject();
        json.put("width", 2);
        json.put("height", 1);
        json.put("entities", entitiesJSON);

        DungeonLoader loader = new DungeonMockLoader(json);
        Dungeon dungeon = loader.load();
        Player player = dungeon.getPlayer();

        // player steps on the same gird as sword to collect it
        player.moveRight();
        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 0);
    }

    // test with 1 enemy
    void swordtest2() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject swordJSON = new JSONObject();
        swordJSON.put("x", 1);
        swordJSON.put("y", 0);
        swordJSON.put("type", "sword");

        JSONObject enemyJSON = new JSONObject();
        enemyJSON.put("x", 2);
        enemyJSON.put("y", 0);
        enemyJSON.put("type", ":enemy");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(swordJSON);
        entitiesJSON.put(enemyJSON);

        JSONObject goalJSON = new JSONObject();
        goalJSON.put("goal", "enemies");

        JSONObject json = new JSONObject();
        json.put("width", 3);
        json.put("height", 1);
        json.put("entities", entitiesJSON);
        json.put("goal-condition", goalJSON);

        DungeonLoader loader = new DungeonMockLoader(json);
        Dungeon dungeon = loader.load();
        Player player = dungeon.getPlayer();

        assertFalse(dungeon.isComplete());
        // player picks up sword
        player.moveRight();
        // player kills enemy
        player.moveRight();

        assertTrue(dungeon.isComplete());

    }

    // sword with 6 enemies
    void swordtest3() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject swordJSON = new JSONObject();
        swordJSON.put("x", 1);
        swordJSON.put("y", 0);
        swordJSON.put("type", "sword");

        JSONObject enemyJSON1 = new JSONObject();
        enemyJSON1.put("x", 2);
        enemyJSON1.put("y", 0);
        enemyJSON1.put("type", "enemy");

        JSONObject enemyJSON2 = new JSONObject();
        enemyJSON.put2("x", 3);
        enemyJSON.put2("y", 0);
        enemyJSON.put2("type", "enemy");

        JSONObject enemyJSON3 = new JSONObject();
        enemyJSON3.put("x", 4);
        enemyJSON3.put("y", 0);
        enemyJSON3.put("type", "enemy");

        JSONObject enemyJSON4 = new JSONObject();
        enemyJSON4.put("x", 5);
        enemyJSON4.put("y", 0);
        enemyJSON4.put("type", "enemy");

        JSONObject enemyJSON5 = new JSONObject();
        enemyJSON5.put("x", 6);
        enemyJSON5.put("y", 0);
        enemyJSON5.put("type", "enemy");

        JSONObject enemyJSON6 = new JSONObject();
        enemyJSON6.put("x", 7);
        enemyJSON6.put("y", 0);
        enemyJSON6.put("type", "enemy");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(swordJSON);
        entitiesJSON.put(enemyJSON1);
        entitiesJSON.put(enemyJSON2);
        entitiesJSON.put(enemyJSON3);
        entitiesJSON.put(enemyJSON4);
        entitiesJSON.put(enemyJSON5);
        entitiesJSON.put(enemyJSON6);

        JSONObject goalJSON = new JSONObject();
        goalJSON.put("goal", "enemies");

        JSONObject json = new JSONObject();
        json.put("width", 8);
        json.put("height", 1);
        json.put("entities", entitiesJSON);
        json.put("goal-condition", goalJSON);

        DungeonLoader loader = new DungeonMockLoader(json);
        Dungeon dungeon = loader.load();
        Player player = dungeon.getPlayer();

        assertFalse(dungeon.isComplete());
        // player picks up sword
        player.moveRight();
        // player kills enemy1
        player.moveRight();
        // player kills enemy2
        player.moveRight();
        // player kills enemy3
        player.moveRight();
        // player kills enemy4
        player.moveRight();
        // player kills enemy5
        player.moveRight();
        // player dies
        player.moveRight();
        assertFalse(dungeon.isComplete());
    }
}