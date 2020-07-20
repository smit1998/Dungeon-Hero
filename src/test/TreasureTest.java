package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONArray;
import org.json.JSONObject;

import unsw.dungeon.*;

class treasureTest {
    @Test
    void BasicTreasureTest () {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject treasureJSON = new JSONObject();
        treasureJSON.put("x", 1);
        treasureJSON.put("y", 0);
        treasureJSON.put("type", "treasure");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(treasureJSON);

        JSONObject goalJSON = new JSONObject();
        goalJSON.put("goal", "treasure");

        JSONObject json = new JSONObject();
        json.put("width", 2);
        json.put("height", 1);
        json.put("entities", entitiesJSON);
        json.put("goal-condition", goalJSON);

        DungeonLoader loader = new DungeonMockLoader(json);
        Dungeon dungeon = loader.load();
        Player player = dungeon.getPlayer();

        assertFalse(dungeon.isComplete());
        // player picks up the treasure
        player.moveRight();

        assertTrue(dungeon.isComplete());

    }

    @Test
    void MultipleTreasure() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject treasureJSON1 = new JSONObject();
        treasureJSON1.put("x", 1);
        treasureJSON1.put("y", 0);
        treasureJSON1.put("type", "treasure");

        JSONObject treasureJSON2 = new JSONObject();
        treasureJSON2.put("x", 2);
        treasureJSON2.put("y", 0);
        treasureJSON2.put("type", "treasure");

        JSONObject treasureJSON3 = new JSONObject();
        treasureJSON3.put("x", 3);
        treasureJSON3.put("y", 0);
        treasureJSON3.put("type", "treasure");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(treasureJSON1);
        entitiesJSON.put(treasureJSON2);
        entitiesJSON.put(treasureJSON3);

        JSONObject goalJSON = new JSONObject();
        goalJSON.put("goal", "treasure");

        JSONObject json = new JSONObject();
        json.put("width", 4);
        json.put("height", 1);
        json.put("entities", entitiesJSON);
        json.put("goal-condition", goalJSON);

        DungeonLoader loader = new DungeonMockLoader(json);
        Dungeon dungeon = loader.load();
        Player player = dungeon.getPlayer();

        assertFalse(dungeon.isComplete());

        // player picks up the treasure
        player.moveRight();

        assertFalse(dungeon.isComplete());
        // player collects all the treasure.
        player.moveRight();
        player.moveRight();
        // goal is completed.
        assertTrue(dungeon.isComplete());
    }

}