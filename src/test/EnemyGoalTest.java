package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONArray;
import org.json.JSONObject;

import unsw.dungeon.*;

class EnemyGoalTest {

    @Test
    void testNoEnemies() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);

        JSONObject goalJSON = new JSONObject();
        goalJSON.put("goal", "enemies");

        JSONObject json = new JSONObject();
        json.put("width", 1);
        json.put("height", 1);
        json.put("entities", entitiesJSON);
        json.put("goal-condition", goalJSON);

        DungeonLoader loader = new DungeonMockLoader(json);
        Dungeon dungeon = loader.load();

        // Test that goal is complete
        assertTrue(dungeon.isComplete());
    }

    @Test
    void testOneEnemyGoal() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject swordJSON = new JSONObject();
        swordJSON.put("x", 1);
        swordJSON.put("y", 0);
        swordJSON.put("type", "sword");

        JSONObject enemy = new JSONObject();
        enemy.put("x", 2);
        enemy.put("y", 0);
        enemy.put("type", "enemy");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(swordJSON);
        entitiesJSON.put(enemy);

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

        // Test that goal isn't complete yet
        assertFalse(dungeon.isComplete());

        // Pickup sword
        player.moveRight();
        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 0);

        // Kill enemy
        player.moveRight();
        assertTrue(player.getX() == 2);
        assertTrue(player.getY() == 0);

        // Test that dungeon has been completed
        assertTrue(dungeon.isComplete());
    }

    @Test
    void testManyEnemyGoal() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject swordJSON = new JSONObject();
        swordJSON.put("x", 1);
        swordJSON.put("y", 0);
        swordJSON.put("type", "sword");

        JSONObject enemy = new JSONObject();
        enemy.put("x", 2);
        enemy.put("y", 0);
        enemy.put("type", "enemy");

        JSONObject enemy2 = new JSONObject();
        enemy2.put("x", 3);
        enemy2.put("y", 0);
        enemy2.put("type", "enemy");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(swordJSON);
        entitiesJSON.put(enemy);
        entitiesJSON.put(enemy2);

        JSONObject goalJSON = new JSONObject();
        goalJSON.put("goal", "enemies");

        JSONObject json = new JSONObject();
        json.put("width", 4);
        json.put("height", 1);
        json.put("entities", entitiesJSON);
        json.put("goal-condition", goalJSON);

        DungeonLoader loader = new DungeonMockLoader(json);
        Dungeon dungeon = loader.load();
        Player player = dungeon.getPlayer();

        // Test that goal isn't complete yet
        assertFalse(dungeon.isComplete());

        // Pickup sword
        player.moveRight();
        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 0);

        // Kill first enemy
        player.moveRight();
        assertTrue(player.getX() == 2);
        assertTrue(player.getY() == 0);

        // Test that goal isn't complete yet
        assertFalse(dungeon.isComplete());

        // Kill second enemy
        player.moveRight();
        assertTrue(player.getX() == 2);
        assertTrue(player.getY() == 0);

        // Test that dungeon has been completed
        assertTrue(dungeon.isComplete());
    }

}