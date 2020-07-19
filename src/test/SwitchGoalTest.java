package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONArray;
import org.json.JSONObject;

import unsw.dungeon.*;

class SwitchGoalTest {

    @Test
    void testNoSwitchesSpawned() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);

        JSONObject goalJSON = new JSONObject();
        goalJSON.put("goal", "boulders");

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
    void testOneSwitch() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject boulderJSON = new JSONObject();
        boulderJSON.put("x", 1);
        boulderJSON.put("y", 0);
        boulderJSON.put("type", "boulder");

        JSONObject switchJSON = new JSONObject();
        switchJSON.put("x", 2);
        switchJSON.put("y", 0);
        switchJSON.put("type", "switch");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(boulderJSON);
        entitiesJSON.put(switchJSON);

        JSONObject switchGoalJSON = new JSONObject();
        switchGoalJSON.put("goal", "boulders");

        JSONObject json = new JSONObject();
        json.put("width", 3);
        json.put("height", 1);
        json.put("entities", entitiesJSON);
        json.put("goal-condition", switchGoalJSON);

        DungeonLoader loader = new DungeonMockLoader(json);
        Dungeon dungeon = loader.load();
        Player player = dungeon.getPlayer();

        // Test that goal is not met
        assertFalse(dungeon.isComplete());

        // Push boulder onto switch
        player.moveRight();
        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 0);

        // Test that dungeon is complete
        assertTrue(dungeon.isComplete());

    }

    @Test
    void testManySwitch() {

        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject boulderJSON = new JSONObject();
        boulderJSON.put("x", 1);
        boulderJSON.put("y", 0);
        boulderJSON.put("type", "boulder");

        JSONObject boulder2JSON = new JSONObject();
        boulder2JSON.put("x", 1);
        boulder2JSON.put("y", 1);
        boulder2JSON.put("type", "boulder");

        JSONObject switchJSON = new JSONObject();
        switchJSON.put("x", 2);
        switchJSON.put("y", 0);
        switchJSON.put("type", "switch");

        JSONObject switch2JSON = new JSONObject();
        switch2JSON.put("x", 2);
        switch2JSON.put("y", 1);
        switch2JSON.put("type", "switch");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(boulderJSON);
        entitiesJSON.put(boulder2JSON);
        entitiesJSON.put(switchJSON);
        entitiesJSON.put(switch2JSON);

        JSONObject switchGoalJSON = new JSONObject();
        switchGoalJSON.put("goal", "boulders");

        JSONObject json = new JSONObject();
        json.put("width", 3);
        json.put("height", 2);
        json.put("entities", entitiesJSON);
        json.put("goal-condition", switchGoalJSON);

        DungeonLoader loader = new DungeonMockLoader(json);
        Dungeon dungeon = loader.load();
        Player player = dungeon.getPlayer();

        // Test that goal is not met
        assertFalse(dungeon.isComplete());

        // Push boulder onto switch
        player.moveRight();
        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 0);

        // Test that dungeon is incomplete
        assertFalse(dungeon.isComplete());

        // Push second boulder onto switch
        player.moveLeft();
        player.moveDown();
        player.moveRight();
        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 1);

        // Test that goal has been met
        assertTrue(dungeon.isComplete());

    }

    @Test
    void testUnpressedSwitch() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject boulderJSON = new JSONObject();
        boulderJSON.put("x", 1);
        boulderJSON.put("y", 0);
        boulderJSON.put("type", "boulder");

        JSONObject switchJSON = new JSONObject();
        switchJSON.put("x", 2);
        switchJSON.put("y", 0);
        switchJSON.put("type", "switch");

        JSONObject switch2JSON = new JSONObject();
        switch2JSON.put("x", 3);
        switch2JSON.put("y", 0);
        switch2JSON.put("type", "switch");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(boulderJSON);
        entitiesJSON.put(switchJSON);
        entitiesJSON.put(switch2JSON);

        JSONObject switchGoalJSON = new JSONObject();
        switchGoalJSON.put("goal", "boulders");

        JSONObject json = new JSONObject();
        json.put("width", 4);
        json.put("height", 1);
        json.put("entities", entitiesJSON);
        json.put("goal-condition", switchGoalJSON);

        DungeonLoader loader = new DungeonMockLoader(json);
        Dungeon dungeon = loader.load();
        Player player = dungeon.getPlayer();

        // Test that goal is not met
        assertFalse(dungeon.isComplete());

        // Push boulder onto switch
        player.moveRight();
        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 0);

        // Test that dungeon is incomplete
        assertFalse(dungeon.isComplete());

        // Push boulder onto second switch
        player.moveRight();
        assertTrue(player.getX() == 2);
        assertTrue(player.getY() == 0);

        // Test that goal has not been met
        assertFalse(dungeon.isComplete());
    }

}