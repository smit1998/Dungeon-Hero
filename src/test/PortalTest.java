package test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONArray;
import org.json.JSONObject;

import main.java.unsw.dungeon.*;
import main.java.unsw.dungeon.entities.*;

class PortalTest {

    @Test
    void testBasicPortal() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 1);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject portalJSON = new JSONObject();
        portalJSON.put("x", 0);
        portalJSON.put("y", 0);
        portalJSON.put("id", 1);
        portalJSON.put("type", "portal");

        JSONObject portalPairJSON = new JSONObject();
        portalPairJSON.put("x", 2);
        portalPairJSON.put("y", 0);
        portalPairJSON.put("id", 1);
        portalPairJSON.put("type", "portal");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(portalJSON);
        entitiesJSON.put(portalPairJSON);

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

        // Move right to portal at position x = 2 and comes out are position x = 1.
        player.moveRight();

        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 0);

    }

    // test with the boulder
    @Test
    void testPortalWithBoulder() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject portalJSON = new JSONObject();
        portalJSON.put("x", 2);
        portalJSON.put("y", 0);
        portalJSON.put("id", 1);
        portalJSON.put("type", "portal");

        JSONObject portalPairJSON = new JSONObject();
        portalPairJSON.put("x", 2);
        portalPairJSON.put("y", 1);
        portalPairJSON.put("id", 1);
        portalPairJSON.put("type", "portal");

        JSONObject boulderJSON = new JSONObject();
        boulderJSON.put("x", 1);
        boulderJSON.put("y", 0);
        boulderJSON.put("type", "boulder");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(portalJSON);
        entitiesJSON.put(portalPairJSON);
        entitiesJSON.put(boulderJSON);

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

        // player will push the boulder into the portal
        player.moveRight();

        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 0);

        // player should move into the portal
        player.moveRight();

        // should not have any effect as boulder is present on x = 0, y = 1;
        player.moveLeft();

        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 0);

    }

    // test on player holding the sword and with enemy in the dungeon.
    @Test
    void testPortalWithSwordAndEnemy() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 1);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject portalJSON = new JSONObject();
        portalJSON.put("x", 3);
        portalJSON.put("y", 0);
        portalJSON.put("id", 1);
        portalJSON.put("type", "portal");

        JSONObject portalPairJSON = new JSONObject();
        portalPairJSON.put("x", 3);
        portalPairJSON.put("y", 1);
        portalPairJSON.put("id", 1);
        portalPairJSON.put("type", "portal");

        JSONObject swordJSON = new JSONObject();
        swordJSON.put("x", 2);
        swordJSON.put("y", 0);
        swordJSON.put("type", "sword");

        JSONObject enemy = new JSONObject();
        enemy.put("x", 0);
        enemy.put("y", 0);
        enemy.put("type", "enemy");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(swordJSON);
        entitiesJSON.put(portalJSON);
        entitiesJSON.put(portalPairJSON);
        entitiesJSON.put(enemy);

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

        // Test that goal isn't complete yet
        assertFalse(dungeon.isComplete());

        // Player picks up the sword
        player.moveRight();

        // kills the enemy
        player.moveLeft();
        player.moveLeft();

        // Player goes into the portal
        player.moveRight();
        player.moveRight();
        player.moveRight();

        assertTrue(player.getX() == 4);
        assertTrue(player.getY() == 1);

        // goal is achieved.
        assertTrue(dungeon.isComplete());

    }

    @Test
    void testDifferentPortalID() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 1);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject portalJSON = new JSONObject();
        portalJSON.put("x", 0);
        portalJSON.put("y", 0);
        portalJSON.put("id", 1);
        portalJSON.put("type", "portal");

        JSONObject portal2JSON = new JSONObject();
        portal2JSON.put("x", 3);
        portal2JSON.put("y", 0);
        portal2JSON.put("id", 2);
        portal2JSON.put("type", "portal");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(portalJSON);
        entitiesJSON.put(portal2JSON);

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

        assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 0);

        // Move left into dud portal
        player.moveLeft();

        // Test that player did not move
        assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 0);

    }

    // Test that enemy will not go through portal
    @Test
    void testEnemyPortal() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject portalJSON = new JSONObject();
        portalJSON.put("x", 1);
        portalJSON.put("y", 0);
        portalJSON.put("id", 1);
        portalJSON.put("type", "portal");

        JSONObject portal2JSON = new JSONObject();
        portal2JSON.put("x", 3);
        portal2JSON.put("y", 0);
        portal2JSON.put("id", 1);
        portal2JSON.put("type", "portal");

        JSONObject enemyJSON = new JSONObject();
        enemyJSON.put("x", 4);
        enemyJSON.put("y", 0);
        enemyJSON.put("type", "enemy");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(portalJSON);
        entitiesJSON.put(portal2JSON);
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

        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);

        // Wait for enemy to move towards player
        dungeon.tick(30);

        // Test that player is still alive
        assertTrue(player.getLifeStatus());

    }

    @Test
    void testPortalAnd2Boulders() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject portalJSON = new JSONObject();
        portalJSON.put("x", 2);
        portalJSON.put("y", 0);
        portalJSON.put("id", 1);
        portalJSON.put("type", "portal");

        JSONObject portalPairJSON = new JSONObject();
        portalPairJSON.put("x", 4);
        portalPairJSON.put("y", 0);
        portalPairJSON.put("id", 1);
        portalPairJSON.put("type", "portal");

        JSONObject boulderJSON = new JSONObject();
        boulderJSON.put("x", 1);
        boulderJSON.put("y", 0);
        boulderJSON.put("type", "boulder");

        JSONObject boulderJSON2 = new JSONObject();
        boulderJSON2.put("x", 5);
        boulderJSON2.put("y", 0);
        boulderJSON2.put("type", "boulder");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(portalJSON);
        entitiesJSON.put(portalPairJSON);
        entitiesJSON.put(boulderJSON);
        entitiesJSON.put(boulderJSON2);

        JSONObject goalJSON = new JSONObject();
        goalJSON.put("goal", "enemies");

        JSONObject json = new JSONObject();
        json.put("width", 7);
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
    void testPortalOutOfBound() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject portalJSON = new JSONObject();
        portalJSON.put("x", 1);
        portalJSON.put("y", 0);
        portalJSON.put("id", 1);
        portalJSON.put("type", "portal");

        JSONObject portalPairJSON = new JSONObject();
        portalPairJSON.put("x", 3);
        portalPairJSON.put("y", 0);
        portalPairJSON.put("id", 1);
        portalPairJSON.put("type", "portal");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(portalJSON);
        entitiesJSON.put(portalPairJSON);

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

        // player can not move in to portal
        player.moveRight();

        assertTrue(player.getX() == 0);
        assertTrue(player.getY() == 0);
    }

}