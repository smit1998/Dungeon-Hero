package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONArray;
import org.json.JSONObject;

import main.java.unsw.dungeon.*;
import main.java.unsw.dungeon.entities.Player;

class AndCompositeGoalTest {

    @Test
    void testStandardANDGoal() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject treasureJSON = new JSONObject();
        treasureJSON.put("x", 1);
        treasureJSON.put("y", 0);
        treasureJSON.put("type", "treasure");

        JSONObject boulderJSON = new JSONObject();
        boulderJSON.put("x", 2);
        boulderJSON.put("y", 0);
        boulderJSON.put("type", "boulder");

        JSONObject switchJSON = new JSONObject();
        switchJSON.put("x", 3);
        switchJSON.put("y", 0);
        switchJSON.put("type", "switch");

        JSONObject exitJSON = new JSONObject();
        exitJSON.put("x", 2);
        exitJSON.put("y", 1);
        exitJSON.put("type", "exit");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(treasureJSON);
        entitiesJSON.put(boulderJSON);
        entitiesJSON.put(switchJSON);
        entitiesJSON.put(exitJSON);

        JSONObject exitGoal = new JSONObject();
        exitGoal.put("goal", "exit");

        JSONObject treasureGoal = new JSONObject();
        treasureGoal.put("goal", "treasure");

        JSONObject switchGoal = new JSONObject();
        switchGoal.put("goal", "boulders");

        JSONArray subgoals = new JSONArray();
        subgoals.put(treasureGoal);
        subgoals.put(switchGoal);
        subgoals.put(exitGoal);

        JSONObject andGoal = new JSONObject();
        andGoal.put("goal", "AND");
        andGoal.put("subgoals", subgoals);

        JSONObject json = new JSONObject();
        json.put("width", 4);
        json.put("height", 2);
        json.put("entities", entitiesJSON);
        json.put("goal-condition", andGoal);

        DungeonLoader loader = new DungeonMockLoader(json);
        Dungeon dungeon = loader.load();
        Player player = dungeon.getPlayer();

        // Test that goal isn't complete yet
        assertFalse(dungeon.isComplete());

        // Move right to collect treasure
        player.moveRight();

        // Test that goal isn't complete yet
        assertFalse(dungeon.isComplete());

        // Move right to push boulder onto switch
        player.moveRight();

        // Test that goal isn't complete yet
        assertFalse(dungeon.isComplete());

        // Move down to exit
        player.moveDown();

        // Test that goal has been completed
        assertTrue(dungeon.isComplete());
    }

    @Test
    void testNestedGoals() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject treasureJSON = new JSONObject();
        treasureJSON.put("x", 1);
        treasureJSON.put("y", 0);
        treasureJSON.put("type", "treasure");

        JSONObject boulderJSON = new JSONObject();
        boulderJSON.put("x", 2);
        boulderJSON.put("y", 0);
        boulderJSON.put("type", "boulder");

        JSONObject switchJSON = new JSONObject();
        switchJSON.put("x", 3);
        switchJSON.put("y", 0);
        switchJSON.put("type", "switch");

        JSONObject exitJSON = new JSONObject();
        exitJSON.put("x", 2);
        exitJSON.put("y", 1);
        exitJSON.put("type", "exit");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(treasureJSON);
        entitiesJSON.put(boulderJSON);
        entitiesJSON.put(switchJSON);
        entitiesJSON.put(exitJSON);

        JSONObject exitGoal = new JSONObject();
        exitGoal.put("goal", "exit");

        JSONObject treasureGoal = new JSONObject();
        treasureGoal.put("goal", "treasure");

        JSONObject switchGoal = new JSONObject();
        switchGoal.put("goal", "boulders");

        JSONArray subgoals0 = new JSONArray();
        subgoals0.put(treasureGoal);
        subgoals0.put(switchGoal);

        JSONObject andGoal0 = new JSONObject();
        andGoal0.put("goal", "AND");
        andGoal0.put("subgoals", subgoals0);

        JSONArray subgoals1 = new JSONArray();
        subgoals1.put(andGoal0);
        subgoals1.put(exitGoal);

        JSONObject andGoal1 = new JSONObject();
        andGoal1.put("goal", "AND");
        andGoal1.put("subgoals", subgoals1);

        JSONObject json = new JSONObject();
        json.put("width", 4);
        json.put("height", 2);
        json.put("entities", entitiesJSON);
        json.put("goal-condition", andGoal1);

        DungeonLoader loader = new DungeonMockLoader(json);
        Dungeon dungeon = loader.load();
        Player player = dungeon.getPlayer();

        // Test that goal isn't complete yet
        assertFalse(dungeon.isComplete());

        // Move right to collect treasure
        player.moveRight();

        // Test that goal isn't complete yet
        assertFalse(dungeon.isComplete());

        // Move right to push boulder onto switch
        player.moveRight();

        // Test that goal isn't complete yet
        assertFalse(dungeon.isComplete());

        // Move down to exit
        player.moveDown();

        // Test that goal has been completed
        assertTrue(dungeon.isComplete());
    }

    @Test
    void testDuplicateGoal() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject exitJSON = new JSONObject();
        exitJSON.put("x", 1);
        exitJSON.put("y", 0);
        exitJSON.put("type", "exit");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(exitJSON);

        JSONObject goal0 = new JSONObject();
        goal0.put("goal", "exit");

        JSONObject goal1 = new JSONObject();
        goal1.put("goal", "exit");

        JSONArray subgoals = new JSONArray();
        subgoals.put(goal0);
        subgoals.put(goal1);

        JSONObject andGoal = new JSONObject();
        andGoal.put("goal", "AND");
        andGoal.put("subgoals", subgoals);

        JSONObject json = new JSONObject();
        json.put("width", 2);
        json.put("height", 1);
        json.put("entities", entitiesJSON);
        json.put("goal-condition", andGoal);

        DungeonLoader loader = new DungeonMockLoader(json);
        Dungeon dungeon = loader.load();
        Player player = dungeon.getPlayer();

        // Test that goal isn't complete yet
        assertFalse(dungeon.isComplete());

        // Move right to exit
        player.moveRight();

        // Test that goal has been completed
        assertTrue(dungeon.isComplete());
    }
}