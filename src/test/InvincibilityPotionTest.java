package test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.json.JSONArray;
import org.json.JSONObject;

import unsw.dungeon.*;

class InvincibilityPotionTest {

    // basic test with just potion and player
    @Test
    void testPotionPickup() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject potionJSON = new JSONObject();
        potionJSON.put("x", 1);
        potionJSON.put("y", 0);
        potionJSON.put("type", "invincibility");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(potionJSON);

        JSONObject goalJSON = new JSONObject();
        goalJSON.put("goal", "enemies");

        JSONObject json = new JSONObject();
        json.put("width", 2);
        json.put("height", 1);
        json.put("entities", entitiesJSON);
        json.put("goal-condition", goalJSON);

        DungeonLoader loader = new DungeonMockLoader(json);
        Dungeon dungeon = loader.load();
        Player player = dungeon.getPlayer();

        assertTrue(dungeon.isComplete() == true);

        // player should pick up the potion and step on that grid
        player.moveRight();

        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 0);

    }

    // potion with enemies
    @Test
    void testPotionKillsEnemy() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 1);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject potionJSON = new JSONObject();
        potionJSON.put("x", 2);
        potionJSON.put("y", 0);
        potionJSON.put("type", "invincibility");

        JSONObject enemyJSON = new JSONObject();
        enemyJSON.put("x", 0);
        enemyJSON.put("y", 0);
        enemyJSON.put("type", "enemy");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(potionJSON);
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

        // enemy is alive
        assertTrue(dungeon.isComplete() == false);

        // picks up the potion
        player.moveRight();

        // kills the enemy
        player.moveLeft();
        player.moveLeft();

        assertTrue(dungeon.isComplete() == true);

    }

    // potion with multiple enemies
    @Test
    void testPotionKillsMultipleEnemies() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 1);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject potionJSON = new JSONObject();
        potionJSON.put("x", 2);
        potionJSON.put("y", 0);
        potionJSON.put("type", "invincibility");

        JSONObject enemyJSON = new JSONObject();
        enemyJSON.put("x", 0);
        enemyJSON.put("y", 0);
        enemyJSON.put("type", "enemy");

        JSONObject enemyJSON2 = new JSONObject();
        enemyJSON2.put("x", 3);
        enemyJSON2.put("y", 0);
        enemyJSON2.put("type", "enemy");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(potionJSON);
        entitiesJSON.put(enemyJSON);
        entitiesJSON.put(enemyJSON2);

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

        // enemy is alive
        assertTrue(dungeon.isComplete() == false);

        // picks up the potion
        player.moveRight();
        // kills the first enemy
        player.moveRight();
        // kills the enemy
        player.moveLeft();
        player.moveLeft();
        player.moveLeft();

        assertTrue(dungeon.isComplete() == true);
    }

    // Test that player can carry both sword and potion at the same time
    @Test
    void testPotionAndSwordPickup() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject potionJSON = new JSONObject();
        potionJSON.put("x", 1);
        potionJSON.put("y", 0);
        potionJSON.put("type", "invincibility");

        JSONObject enemyJSON = new JSONObject();
        enemyJSON.put("x", 3);
        enemyJSON.put("y", 0);
        enemyJSON.put("type", "enemy");

        JSONObject swordJSON = new JSONObject();
        swordJSON.put("x", 2);
        swordJSON.put("y", 0);
        swordJSON.put("type", "sword");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(potionJSON);
        entitiesJSON.put(enemyJSON);
        entitiesJSON.put(swordJSON);

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

        // enemy is alive
        assertFalse(dungeon.isComplete());

        // player picks up potion
        player.moveRight();

        // player picks up sword
        player.moveRight();

        // player kills enemy
        player.moveRight();

        assertTrue(dungeon.isComplete());

    }

    @Test
    void testPotionWearOff() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject potionJSON = new JSONObject();
        potionJSON.put("x", 1);
        potionJSON.put("y", 0);
        potionJSON.put("type", "invincibility");

        JSONObject enemy1JSON = new JSONObject();
        enemy1JSON.put("x", 2);
        enemy1JSON.put("y", 0);
        enemy1JSON.put("type", "enemy");

        JSONObject enemy2JSON = new JSONObject();
        enemy2JSON.put("x", 3);
        enemy2JSON.put("y", 1);
        enemy2JSON.put("type", "enemy");

        JSONObject boulderJSON = new JSONObject();
        boulderJSON.put("x", 3);
        boulderJSON.put("y", 0);
        boulderJSON.put("type", "boulder");

        JSONObject wall1JSON = new JSONObject();
        wall1JSON.put("x", 2);
        wall1JSON.put("y", 1);
        wall1JSON.put("type", "wall");

        JSONObject wall2JSON = new JSONObject();
        wall2JSON.put("x", 4);
        wall2JSON.put("y", 1);
        wall2JSON.put("type", "wall");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(potionJSON);
        entitiesJSON.put(enemy1JSON);
        entitiesJSON.put(enemy2JSON);
        entitiesJSON.put(boulderJSON);
        entitiesJSON.put(wall1JSON);
        entitiesJSON.put(wall2JSON);

        JSONObject goalJSON = new JSONObject();
        goalJSON.put("goal", "enemies");

        JSONObject json = new JSONObject();
        json.put("width", 5);
        json.put("height", 2);
        json.put("entities", entitiesJSON);
        json.put("goal-condition", goalJSON);

        DungeonLoader loader = new DungeonMockLoader(json);
        Dungeon dungeon = loader.load();
        Player player = dungeon.getPlayer();

        // Move right to pick up potion
        player.moveRight();
        assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 0);

        // Move right to kill enemy
        player.moveRight();
        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 0);

        // Wait till potion wears off
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            fail();
        }

        // Move right to push boulder
        player.moveRight();
        assertEquals(player.getX(), 3);
        assertEquals(player.getY(), 0);

        // Move down to be killed by enemy
        player.moveDown();

        assertFalse(dungeon.isComplete());

    }
}