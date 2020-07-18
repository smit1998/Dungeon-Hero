package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONArray;
import org.json.JSONObject;

import unsw.dungeon.*;

class OrCompositeGoalTest {

    @Test
    void testStandardOrGoal() {
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

        JSONObject orGoal = new JSONObject();
        orGoal.put("goal", "OR");
        orGoal.put("subgoals", subgoals);

        JSONObject json = new JSONObject();
        json.put("width", 4);
        json.put("height", 2);
        json.put("entities", entitiesJSON);
        json.put("goal-condition", orGoal);

        DungeonLoader loader = new DungeonMockLoader(json);
        Dungeon dungeon = loader.load();
        Player player = dungeon.getPlayer();

        // Test that goal isn't complete yet
        assertFalse(dungeon.isComplete());

        // Move right to collect treasure
        player.moveRight();

        // Test that goal has been completed
        assertTrue(dungeon.isComplete());
    }

    @Test
    void testStandardOrGoal2() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject treasureJSON = new JSONObject();
        treasureJSON.put("x", 0);
        treasureJSON.put("y", 1);
        treasureJSON.put("type", "treasure");

        JSONObject boulderJSON = new JSONObject();
        boulderJSON.put("x", 1);
        boulderJSON.put("y", 0);
        boulderJSON.put("type", "boulder");

        JSONObject switchJSON = new JSONObject();
        switchJSON.put("x", 2);
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

        JSONObject orGoal = new JSONObject();
        orGoal.put("goal", "OR");
        orGoal.put("subgoals", subgoals);

        JSONObject json = new JSONObject();
        json.put("width", 4);
        json.put("height", 2);
        json.put("entities", entitiesJSON);
        json.put("goal-condition", orGoal);

        DungeonLoader loader = new DungeonMockLoader(json);
        Dungeon dungeon = loader.load();
        Player player = dungeon.getPlayer();

        // Test that goal isn't complete yet
        assertFalse(dungeon.isComplete());

        // Move right to push boulder onto switch
        player.moveRight();

        // Test that goal has been completed
        assertTrue(dungeon.isComplete());
    }

    @Test
    void testStandardOrGoal3() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject treasureJSON = new JSONObject();
        treasureJSON.put("x", 0);
        treasureJSON.put("y", 1);
        treasureJSON.put("type", "treasure");

        JSONObject boulderJSON = new JSONObject();
        boulderJSON.put("x", 1);
        boulderJSON.put("y", 1);
        boulderJSON.put("type", "boulder");

        JSONObject switchJSON = new JSONObject();
        switchJSON.put("x", 2);
        switchJSON.put("y", 0);
        switchJSON.put("type", "switch");

        JSONObject exitJSON = new JSONObject();
        exitJSON.put("x", 1);
        exitJSON.put("y", 0);
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

        JSONObject orGoal = new JSONObject();
        orGoal.put("goal", "OR");
        orGoal.put("subgoals", subgoals);

        JSONObject json = new JSONObject();
        json.put("width", 4);
        json.put("height", 2);
        json.put("entities", entitiesJSON);
        json.put("goal-condition", orGoal);

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

        JSONObject orGoal0 = new JSONObject();
        orGoal0.put("goal", "OR");
        orGoal0.put("subgoals", subgoals0);

        JSONArray subgoals1 = new JSONArray();
        subgoals1.put(orGoal0);
        subgoals1.put(exitGoal);

        JSONObject orGoal1 = new JSONObject();
        orGoal1.put("goal", "OR");
        orGoal1.put("subgoals", subgoals1);

        JSONObject json = new JSONObject();
        json.put("width", 4);
        json.put("height", 2);
        json.put("entities", entitiesJSON);
        json.put("goal-condition", orGoal1);

        DungeonLoader loader = new DungeonMockLoader(json);
        Dungeon dungeon = loader.load();
        Player player = dungeon.getPlayer();

        // Test that goal isn't complete yet
        assertFalse(dungeon.isComplete());

        // Move right to collect treasure
        player.moveRight();

        // Test that goal has been completed
        assertTrue(dungeon.isComplete());
    }
}