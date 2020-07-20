package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONArray;
import org.json.JSONObject;

import unsw.dungeon.*;

class enemyTest {

    @Test
    void basicEnemyTest() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject enemy = new JSONObject();
        enemy.put("x", 2);
        enemy.put("y", 0);
        enemy.put("type", "enemy");
        
        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
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

        assertFalse(dungeon.isComplete());

        //player moves to enemy and enemy kills player
        player.moveRight();
        player.moveRight();

        assertFalse(dungeon.isComplete());
    }

    @Test
    void enemyWithSword() {
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

        player.moveRight();

        // Kill enemy
        player.moveRight();
        assertTrue(player.getX() == 2);
        assertTrue(player.getY() == 0);

        assertTrue(dungeon.isComplete());
    }

    @Test
    void enemyWithPotion() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject potionJSON = new JSONObject();
        potionJSON.put("x", 1);
        potionJSON.put("y", 0);
        potionJSON.put("type", "invincibility");
        
        JSONObject enemy = new JSONObject();
        enemy.put("x", 2);
        enemy.put("y", 0);
        enemy.put("type", "enemy");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(potionJSON);
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

        // player picks up potion
        player.moveRight();

        // Kill enemy
        player.moveRight();

        // dungeon is complete
        assertTrue(dungeon.isComplete());
    }

    @Test
    void multipleEnemies() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject swordJSON = new JSONObject();
        swordJSON.put("x", 1);
        swordJSON.put("y", 0);
        swordJSON.put("type", "sword");

        JSONObject enemy1 = new JSONObject();
        enemy1.put("x", 2);
        enemy1.put("y", 0);
        enemy1.put("type", "enemy");

        JSONObject enemy2 = new JSONObject();
        enemy2.put("x", 3);
        enemy2.put("y", 0);
        enemy2.put("type", "enemy");
        
        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(swordJSON);
        entitiesJSON.put(enemy1);
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

        assertFalse(dungeon.isComplete());

        // Pickup sword
        player.moveRight();

        // Kill enemies
        player.moveRight();
        player.moveRight();

        assertTrue(dungeon.isComplete());
    }

    @Test
    void enemyMoveTest() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject enemy = new JSONObject();
        enemy.put("x", 2);
        enemy.put("y", 1);
        enemy.put("type", "enemy");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
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

        assertFalse(dungeon.isComplete());

        // lets the enemy move towards the player
        try{
            Thread.sleep(5000);
        }
        catch (InterruptedException e){
            fail();
        }
        //enemy kills the player
        assertFalse(player.isVisible() == true);

    }
}
