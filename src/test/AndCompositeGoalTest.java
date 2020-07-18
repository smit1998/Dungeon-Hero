package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONArray;
import org.json.JSONObject;

import unsw.dungeon.*;

class AndCompositeGoalTest {

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