package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONArray;
import org.json.JSONObject;

import main.java.unsw.dungeon.*;

class TreasureGoalTest {

    @Test
    void testNoTreasureSpawned() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);

        JSONObject goalJSON = new JSONObject();
        goalJSON.put("goal", "treasure");

        JSONObject json = new JSONObject();
        json.put("width", 1);
        json.put("height", 1);
        json.put("entities", entitiesJSON);
        json.put("goal-condition", goalJSON);

        DungeonLoader loader = new DungeonMockLoader(json);
        Dungeon dungeon = loader.load();

        // Test that goal is completed
        assertTrue(dungeon.isComplete());
    }

    @Test
    void testOneTreasure() {
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
        json.put("width", 3);
        json.put("height", 1);
        json.put("entities", entitiesJSON);
        json.put("goal-condition", goalJSON);

        DungeonLoader loader = new DungeonMockLoader(json);
        Dungeon dungeon = loader.load();
        Player player = dungeon.getPlayer();

        // Test that goal is not met
        assertFalse(dungeon.isComplete());

        // Pickup treasure
        player.moveRight();

        // Test that dungeon is complete
        assertTrue(dungeon.isComplete());

    }

    @Test
    void testManyTreasure() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject t1 = new JSONObject();
        t1.put("x", 1);
        t1.put("y", 0);
        t1.put("type", "treasure");

        JSONObject t2 = new JSONObject();
        t2.put("x", 2);
        t2.put("y", 0);
        t2.put("type", "treasure");

        JSONObject t3 = new JSONObject();
        t3.put("x", 3);
        t3.put("y", 0);
        t3.put("type", "treasure");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(t1);
        entitiesJSON.put(t2);
        entitiesJSON.put(t3);

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

        // Test that goal is not met
        assertFalse(dungeon.isComplete());

        // Pickup first treasure
        player.moveRight();
        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 0);

        // Test that goal is not met
        assertFalse(dungeon.isComplete());

        // Pickup second treasure
        player.moveRight();
        assertTrue(player.getX() == 2);
        assertTrue(player.getY() == 0);

        // Test that goal is not met
        assertFalse(dungeon.isComplete());

        // Pickup last treasure
        player.moveRight();

        // Test that dungeon is complete
        assertTrue(dungeon.isComplete());

    }

}