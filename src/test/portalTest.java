package test;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonLoader;
import unsw.dungeon.Player;

class portalTest {

    void testPortal1() {
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

        JSONObject json = new JSONObject();
        json.put("width", 3);
        json.put("height", 1);
        json.put("entities", entitiesJSON);

        DungeonLoader loader = new DungeonMockLoader(json);
        Dungeon dungeon = loader.load();
        Player player = dungeon.getPlayer();

        // Move right to portal at position x = 2 and comes out are position x = 1.
        player.moveRight();

        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 0);

    }
    // test with the boulder
    void testPortal2() {
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
        
        JSONObject json = new JSONObject();
        json.put("width", 3);
        json.put("height", 2);
        json.put("entities", entitiesJSON);

        DungeonLoader loader = new DungeonMockLoader(json);
        Dungeon dungeon = loader.load();
        Player player = dungeon.getPlayer();

        // player will push the boulder into the portal
        player.moveRight();

        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 0);

        //player should move into the portal
        player.moveRight();

        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 1);

    }

    // test on player holding the sword and with enemy in the dungeon.
    void testPortal3() {
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
        json.put("width", 4);
        json.put("height", 2);
        json.put("entities", entitiesJSON);
        json.put("goal-condition", goalJSON);

        DungeonLoader loader = new DungeonMockLoader(json);
        Dungeon dungeon = loader.load();
        Player player = dungeon.getPlayer();

        // Test that goal isn't complete yet
        assertFalse(dungeon.isComplete());

        // Player pickes up the sword
        player.moveRight();

        // kills the enemy
        player.moveLeft();

        // Player goes into the portal
        player.moveRight();
        player.moveRight();

        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 1);

        // goal is acheived.
        assertTrue(dungeon.isComplete());

    }

    private void assertFalse(boolean complete) {
    }

    private void assertTrue(boolean b) {
    }
}