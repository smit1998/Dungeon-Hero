package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONArray;
import org.json.JSONObject;

import unsw.dungeon.*;

class ExitGoalTest {

    @Test
    void testStandardExit() {
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

        JSONObject goalJSON = new JSONObject();
        goalJSON.put("goal", "exit");

        JSONObject json = new JSONObject();
        json.put("width", 2);
        json.put("height", 1);
        json.put("entities", entitiesJSON);
        json.put("goal-condition", goalJSON);

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
    void testConjuctionGoal() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject exitJSON = new JSONObject();
        exitJSON.put("x", 1);
        exitJSON.put("y", 0);
        exitJSON.put("type", "exit");

        JSONObject boulderJSON = new JSONObject();
        boulderJSON.put("x", 0);
        boulderJSON.put("y", 1);
        boulderJSON.put("type", "boulder");

        JSONObject switchJSON = new JSONObject();
        switchJSON.put("x", 0);
        switchJSON.put("y", 2);
        switchJSON.put("type", "switch");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(exitJSON);
        entitiesJSON.put(boulderJSON);
        entitiesJSON.put(switchJSON);

        JSONObject exitGoalJSON = new JSONObject();
        exitGoalJSON.put("goal", "exit");

        JSONObject switchGoalJSON = new JSONObject();
        switchGoalJSON.put("goal", "boulders");

        JSONArray subgoalsJSON = new JSONArray();
        subgoalsJSON.put(exitGoalJSON);
        subgoalsJSON.put(switchGoalJSON);

        JSONObject andGoalJSON = new JSONObject();
        andGoalJSON.put("goal", "AND");
        andGoalJSON.put("subgoals", subgoalsJSON);

        JSONObject json = new JSONObject();
        json.put("width", 2);
        json.put("height", 3);
        json.put("entities", entitiesJSON);
        json.put("goal-condition", andGoalJSON);

        System.out.println(json);

        DungeonLoader loader = new DungeonMockLoader(json);
        Dungeon dungeon = loader.load();
        Player player = dungeon.getPlayer();

        // Test that goals are not met
        assertFalse(dungeon.isComplete());

        // Test that player cannot exit
        player.moveRight();
        assertTrue(player.getX() == 0);
        assertTrue(player.getY() == 0);
        assertFalse(dungeon.isComplete());

        // Push boulder to complete switch goal
        player.moveDown();
        assertTrue(player.getX() == 0);
        assertTrue(player.getY() == 1);

        // Test that goal is still incomplete
        assertFalse(dungeon.isComplete());

        // Test that player can exit
        player.moveUp();
        player.moveRight();
        assertTrue(dungeon.isComplete());
    }

    @Test
    void testDisjunctionGoal() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject exitJSON = new JSONObject();
        exitJSON.put("x", 1);
        exitJSON.put("y", 0);
        exitJSON.put("type", "exit");

        JSONObject boulderJSON = new JSONObject();
        boulderJSON.put("x", 0);
        boulderJSON.put("y", 1);
        boulderJSON.put("type", "boulder");

        JSONObject switchJSON = new JSONObject();
        switchJSON.put("x", 0);
        switchJSON.put("y", 2);
        switchJSON.put("type", "switch");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(exitJSON);
        entitiesJSON.put(boulderJSON);
        entitiesJSON.put(switchJSON);

        JSONObject exitGoalJSON = new JSONObject();
        exitGoalJSON.put("goal", "exit");

        JSONObject switchGoalJSON = new JSONObject();
        switchGoalJSON.put("goal", "boulders");

        JSONArray subgoalsJSON = new JSONArray();
        subgoalsJSON.put(exitGoalJSON);
        subgoalsJSON.put(switchGoalJSON);

        JSONObject andGoalJSON = new JSONObject();
        andGoalJSON.put("goal", "OR");
        andGoalJSON.put("subgoals", subgoalsJSON);

        JSONObject json = new JSONObject();
        json.put("width", 2);
        json.put("height", 3);
        json.put("entities", entitiesJSON);
        json.put("goal-condition", andGoalJSON);

        System.out.println(json);

        DungeonLoader loader = new DungeonMockLoader(json);
        Dungeon dungeon = loader.load();
        Player player = dungeon.getPlayer();

        // Test that goals are not met
        assertFalse(dungeon.isComplete());

        // Test that player can exit
        player.moveRight();
        assertTrue(dungeon.isComplete());
    }
}