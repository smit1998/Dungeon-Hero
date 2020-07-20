package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONObject;

import unsw.dungeon.*;

class PlayerTest {

    @Test
    void testValidMovement() {

        Dungeon dungeon = new Dungeon(2, 2);
        Player player = new Player(dungeon, 0, 0);

        assertTrue(player.getX() == 0);
        assertTrue(player.getY() == 0);

        // Move right
        player.moveRight();
        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 0);

        // Move down
        player.moveDown();
        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 1);

        // Move left
        player.moveLeft();
        assertTrue(player.getX() == 0);
        assertTrue(player.getY() == 1);

        // Move up
        player.moveUp();
        assertTrue(player.getX() == 0);
        assertTrue(player.getY() == 0);

    }

    @Test
    void testInvalidMovement() {

        Dungeon dungeon = new Dungeon(2, 2);
        Player player = new Player(dungeon, 0, 0);

        assertTrue(player.getX() == 0);
        assertTrue(player.getY() == 0);

        // Move out of bounds up
        player.moveUp();
        assertTrue(player.getX() == 0);
        assertTrue(player.getY() == 0);

        // Move out of bounds left
        player.moveLeft();
        assertTrue(player.getX() == 0);
        assertTrue(player.getY() == 0);

        // Move to bottom right of dungeon
        player.moveRight();
        player.moveDown();
        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 1);

        // Move out of bounds right
        player.moveRight();
        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 1);

        // Move out of bounds down
        player.moveDown();
        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 1);
    }

    @Test
    void PlayerAttacksWithKey() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject keyJSON = new JSONObject();
        keyJSON.put("x", 1);
        keyJSON.put("y", 0);
        keyJSON.put("type", "key");

        JSONObject enemyJSON = new JSONObject();
        enemyJSON.put("x", 2);
        enemyJSON.put("y", 0);
        enemyJSON.put("type", "enemy");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(keyJSON);
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
        assertTrue(player.getLifeStatus());

    }
}