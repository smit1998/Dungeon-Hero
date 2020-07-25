package test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONArray;
import org.json.JSONObject;

import unsw.dungeon.*;

class DoorKeyTest {

    // standard 1 door and 1 key
    @Test
    void testStandard1Door1key() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject keyJSON = new JSONObject();
        keyJSON.put("x", 1);
        keyJSON.put("y", 0);
        keyJSON.put("id", 1);
        keyJSON.put("type", "key");

        JSONObject doorJSON = new JSONObject();
        doorJSON.put("x", 2);
        doorJSON.put("y", 0);
        doorJSON.put("id", 1);
        doorJSON.put("type", "door");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(keyJSON);
        entitiesJSON.put(doorJSON);

        JSONObject goalJSON = new JSONObject();
        goalJSON.put("goal", "exit");

        JSONObject json = new JSONObject();
        json.put("width", 4);
        json.put("height", 1);
        json.put("entities", entitiesJSON);
        json.put("goal-condition", goalJSON);

        DungeonLoader loader = new DungeonMockLoader(json);
        Dungeon dungeon = loader.load();
        Player player = dungeon.getPlayer();

        // player picks up the key
        player.moveRight();
        // player moves into the door
        player.moveRight();
        player.moveRight();

        assertTrue(player.getX() == 3);
        assertTrue(player.getY() == 0);

    }

    // test with wrong key.
    @Test
    void testWrongKey() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject keyJSON = new JSONObject();
        keyJSON.put("x", 1);
        keyJSON.put("y", 0);
        keyJSON.put("id", 2);
        keyJSON.put("type", "key");

        JSONObject doorJSON = new JSONObject();
        doorJSON.put("x", 2);
        doorJSON.put("y", 0);
        doorJSON.put("id", 1);
        doorJSON.put("type", "door");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(keyJSON);
        entitiesJSON.put(doorJSON);

        JSONObject goalJSON = new JSONObject();
        goalJSON.put("goal", "exit");

        JSONObject json = new JSONObject();
        json.put("width", 4);
        json.put("height", 1);
        json.put("entities", entitiesJSON);
        json.put("goal-condition", goalJSON);

        DungeonLoader loader = new DungeonMockLoader(json);
        Dungeon dungeon = loader.load();
        Player player = dungeon.getPlayer();

        // player picks up the key
        player.moveRight();
        // player moves into the door
        player.moveRight();
        player.moveRight();

        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 0);
    }

    // test with the enemies.
    @Test
    void testDoorKeyWithEnemies() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 1);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject keyJSON = new JSONObject();
        keyJSON.put("x", 2);
        keyJSON.put("y", 0);
        keyJSON.put("id", 1);
        keyJSON.put("type", "key");

        JSONObject doorJSON = new JSONObject();
        doorJSON.put("x", 3);
        doorJSON.put("y", 0);
        doorJSON.put("id", 1);
        doorJSON.put("type", "door");

        JSONObject enemyJSON = new JSONObject();
        enemyJSON.put("x", 0);
        enemyJSON.put("y", 0);
        enemyJSON.put("type", "enemy");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(keyJSON);
        entitiesJSON.put(doorJSON);
        entitiesJSON.put(enemyJSON);

        JSONObject goalJSON = new JSONObject();
        goalJSON.put("goal", "exit");

        JSONObject json = new JSONObject();
        json.put("width", 5);
        json.put("height", 1);
        json.put("entities", entitiesJSON);
        json.put("goal-condition", goalJSON);

        DungeonLoader loader = new DungeonMockLoader(json);
        Dungeon dungeon = loader.load();
        Player player = dungeon.getPlayer();

        // Pickup key
        player.moveRight();

        // Unlock door
        player.moveRight();

        // Go through door
        player.moveRight();

        // Moves back into doorway
        player.moveLeft();

        assertTrue(player.getX() == 3);
        assertTrue(player.getY() == 0);

    }

    // test with multiple doors and keys
    @Test
    void testMultipleDoorKeys() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject keyJSON = new JSONObject();
        keyJSON.put("x", 1);
        keyJSON.put("y", 0);
        keyJSON.put("id", 1);
        keyJSON.put("type", "key");

        JSONObject keyJSON2 = new JSONObject();
        keyJSON2.put("x", 4);
        keyJSON2.put("y", 0);
        keyJSON2.put("id", 2);
        keyJSON2.put("type", "key");

        JSONObject doorJSON = new JSONObject();
        doorJSON.put("x", 2);
        doorJSON.put("y", 0);
        doorJSON.put("id", 1);
        doorJSON.put("type", "door");

        JSONObject doorJSON2 = new JSONObject();
        doorJSON2.put("x", 5);
        doorJSON2.put("y", 0);
        doorJSON2.put("id", 2);
        doorJSON2.put("type", "door");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(keyJSON);
        entitiesJSON.put(doorJSON);
        entitiesJSON.put(keyJSON2);
        entitiesJSON.put(doorJSON2);

        JSONObject goalJSON = new JSONObject();
        goalJSON.put("goal", "exit");

        JSONObject json = new JSONObject();
        json.put("width", 6);
        json.put("height", 1);
        json.put("entities", entitiesJSON);
        json.put("goal-condition", goalJSON);

        DungeonLoader loader = new DungeonMockLoader(json);
        Dungeon dungeon = loader.load();
        Player player = dungeon.getPlayer();

        // picks up the key1
        player.moveRight();

        // enters door1
        player.moveRight();

        dungeon.tick();

        player.moveRight();

        // picks up key2
        player.moveRight();

        // enters door2
        player.moveRight();

        dungeon.tick();

        assertTrue(player.getX() == 5);
        assertTrue(player.getY() == 0);

    }

    @Test
    void testEnterDoorWithoutKey() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject doorJSON = new JSONObject();
        doorJSON.put("x", 1);
        doorJSON.put("y", 0);
        doorJSON.put("id", 1);
        doorJSON.put("type", "door");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(doorJSON);

        JSONObject goalJSON = new JSONObject();
        goalJSON.put("goal", "exit");

        JSONObject json = new JSONObject();
        json.put("width", 3);
        json.put("height", 1);
        json.put("entities", entitiesJSON);
        json.put("goal-condition", goalJSON);

        DungeonLoader loader = new DungeonMockLoader(json);
        Dungeon dungeon = loader.load();
        Player player = dungeon.getPlayer();

        player.moveRight();

        assertTrue(player.getX() == 0);
        assertTrue(player.getY() == 0);

    }

    @Test
    void testPlayerHasSwordAndKey() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject keyJSON = new JSONObject();
        keyJSON.put("x", 2);
        keyJSON.put("y", 0);
        keyJSON.put("id", 1);
        keyJSON.put("type", "key");

        JSONObject swordJSON = new JSONObject();
        swordJSON.put("x", 1);
        swordJSON.put("y", 0);
        swordJSON.put("type", "sword");

        JSONObject doorJSON = new JSONObject();
        doorJSON.put("x", 3);
        doorJSON.put("y", 0);
        doorJSON.put("id", 1);
        doorJSON.put("type", "door");

        JSONObject enemyJSON = new JSONObject();
        enemyJSON.put("x", 4);
        enemyJSON.put("y", 0);
        enemyJSON.put("type", "enemy");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(keyJSON);
        entitiesJSON.put(doorJSON);
        entitiesJSON.put(enemyJSON);
        entitiesJSON.put(swordJSON);

        JSONObject goalJSON = new JSONObject();
        goalJSON.put("goal", "enemies");

        JSONObject json = new JSONObject();
        json.put("width", 5);
        json.put("height", 1);
        json.put("entities", entitiesJSON);
        json.put("goal-condition", goalJSON);

        DungeonLoader loader = new DungeonMockLoader(json);
        Dungeon dungeon = loader.load();
        Player player = dungeon.getPlayer();

        // Pickup sword
        player.moveRight();

        // Pickup key
        player.moveRight();

        // enters door
        player.moveRight();

        // kills enemy
        player.moveRight();

        assertTrue(dungeon.isComplete());

    }

    @Test
    void testPlayerAttacksWithKey() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject keyJSON = new JSONObject();
        keyJSON.put("x", 1);
        keyJSON.put("y", 0);
        keyJSON.put("id", 1);
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
        assertFalse(player.getLifeStatus());

    }

    // Test that dungeon can be created with 3 unique doors
    @Test
    void test3UniqueDoors() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject doorJSON = new JSONObject();
        doorJSON.put("x", 1);
        doorJSON.put("y", 0);
        doorJSON.put("id", 1);
        doorJSON.put("type", "door");

        JSONObject doorJSON2 = new JSONObject();
        doorJSON2.put("x", 2);
        doorJSON2.put("y", 0);
        doorJSON2.put("id", 2);
        doorJSON2.put("type", "door");

        JSONObject doorJSON3 = new JSONObject();
        doorJSON3.put("x", 3);
        doorJSON3.put("y", 0);
        doorJSON3.put("id", 3);
        doorJSON3.put("type", "door");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(doorJSON);
        entitiesJSON.put(doorJSON2);
        entitiesJSON.put(doorJSON3);

        JSONObject goalJSON = new JSONObject();
        goalJSON.put("goal", "exit");

        JSONObject json = new JSONObject();
        json.put("width", 4);
        json.put("height", 1);
        json.put("entities", entitiesJSON);
        json.put("goal-condition", goalJSON);

        DungeonLoader loader = new DungeonMockLoader(json);
        Dungeon dungeon = loader.load();
        Player player = dungeon.getPlayer();

        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);

        player.moveRight();

        // Test that player did not go through door
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);

    }

    // Test that dungeon can be created with more than 3 unique doors
    @Test
    void testMoreThan3UniqueDoors() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject doorJSON = new JSONObject();
        doorJSON.put("x", 1);
        doorJSON.put("y", 0);
        doorJSON.put("id", 1);
        doorJSON.put("type", "door");

        JSONObject doorJSON2 = new JSONObject();
        doorJSON2.put("x", 2);
        doorJSON2.put("y", 0);
        doorJSON2.put("id", 2);
        doorJSON2.put("type", "door");

        JSONObject doorJSON3 = new JSONObject();
        doorJSON3.put("x", 3);
        doorJSON3.put("y", 0);
        doorJSON3.put("id", 3);
        doorJSON3.put("type", "door");

        JSONObject doorJSON4 = new JSONObject();
        doorJSON4.put("x", 4);
        doorJSON4.put("y", 0);
        doorJSON4.put("id", 4);
        doorJSON4.put("type", "door");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(doorJSON);
        entitiesJSON.put(doorJSON2);
        entitiesJSON.put(doorJSON3);
        entitiesJSON.put(doorJSON4);

        JSONObject goalJSON = new JSONObject();
        goalJSON.put("goal", "exit");

        JSONObject json = new JSONObject();
        json.put("width", 4);
        json.put("height", 1);
        json.put("entities", entitiesJSON);
        json.put("goal-condition", goalJSON);

        DungeonLoader loader = new DungeonMockLoader(json);
        Dungeon dungeon = loader.load();
        Player player = dungeon.getPlayer();

        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);

        player.moveRight();

        // Test that player did not go through door
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);
    }

}