package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.json.JSONArray;
import org.json.JSONObject;

import unsw.dungeon.*;

class SwordTest {
    // basic test
    @Test
    void BasicSwordTest() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject swordJSON = new JSONObject();
        swordJSON.put("x", 1);
        swordJSON.put("y", 0);
        swordJSON.put("type", "sword");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(swordJSON);

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

        // player steps on the same gird as sword to collect it
        player.moveRight();
        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 0);
    }

    // test with 1 enemy
    @Test
    void SwordTestWithEnemy() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject swordJSON = new JSONObject();
        swordJSON.put("x", 1);
        swordJSON.put("y", 0);
        swordJSON.put("type", "sword");

        JSONObject enemyJSON = new JSONObject();
        enemyJSON.put("x", 2);
        enemyJSON.put("y", 0);
        enemyJSON.put("type", "enemy");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(swordJSON);
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

        assertTrue(dungeon.isComplete());

    }

    // sword with 6 enemies
    @Test
    void SwordTestWithMultipleEnemies() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject swordJSON = new JSONObject();
        swordJSON.put("x", 1);
        swordJSON.put("y", 0);
        swordJSON.put("type", "sword");

        JSONObject enemyJSON1 = new JSONObject();
        enemyJSON1.put("x", 2);
        enemyJSON1.put("y", 0);
        enemyJSON1.put("type", "enemy");

        JSONObject enemyJSON2 = new JSONObject();
        enemyJSON2.put("x", 3);
        enemyJSON2.put("y", 0);
        enemyJSON2.put("type", "enemy");

        JSONObject enemyJSON3 = new JSONObject();
        enemyJSON3.put("x", 4);
        enemyJSON3.put("y", 0);
        enemyJSON3.put("type", "enemy");

        JSONObject enemyJSON4 = new JSONObject();
        enemyJSON4.put("x", 5);
        enemyJSON4.put("y", 0);
        enemyJSON4.put("type", "enemy");

        JSONObject enemyJSON5 = new JSONObject();
        enemyJSON5.put("x", 6);
        enemyJSON5.put("y", 0);
        enemyJSON5.put("type", "enemy");

        JSONObject enemyJSON6 = new JSONObject();
        enemyJSON6.put("x", 7);
        enemyJSON6.put("y", 0);
        enemyJSON6.put("type", "enemy");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(swordJSON);
        entitiesJSON.put(enemyJSON1);
        entitiesJSON.put(enemyJSON2);
        entitiesJSON.put(enemyJSON3);
        entitiesJSON.put(enemyJSON4);
        entitiesJSON.put(enemyJSON5);
        entitiesJSON.put(enemyJSON6);

        JSONObject goalJSON = new JSONObject();
        goalJSON.put("goal", "enemies");

        JSONObject json = new JSONObject();
        json.put("width", 8);
        json.put("height", 1);
        json.put("entities", entitiesJSON);
        json.put("goal-condition", goalJSON);

        DungeonLoader loader = new DungeonMockLoader(json);
        Dungeon dungeon = loader.load();
        Player player = dungeon.getPlayer();

        assertFalse(dungeon.isComplete());

        // player picks up sword
        player.moveRight();

        // player kills enemy1
        player.moveRight();

        // player kills enemy2
        player.moveRight();

        // player kills enemy3
        player.moveRight();

        // player kills enemy4
        player.moveRight();

        // player kills enemy5
        player.moveRight();

        dungeon.tick();

        // player dies
        player.moveRight();

        assertFalse(player.getLifeStatus());

        assertFalse(dungeon.isComplete());
    }

    @Test
    void EnemyAttacksPlayerWithSword() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject swordJSON = new JSONObject();
        swordJSON.put("x", 1);
        swordJSON.put("y", 0);
        swordJSON.put("type", "sword");

        JSONObject enemyJSON = new JSONObject();
        enemyJSON.put("x", 3);
        enemyJSON.put("y", 0);
        enemyJSON.put("type", "enemy");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(swordJSON);
        entitiesJSON.put(enemyJSON);

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

        // player picks up sword
        player.moveRight();

        // enemy attacks player
        dungeon.tick(30 * 5);

        player.moveRight();
        player.moveRight();
        assertTrue(dungeon.isComplete());

    }

    @Test
    void MultipleSword() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject swordJSON = new JSONObject();
        swordJSON.put("x", 1);
        swordJSON.put("y", 0);
        swordJSON.put("type", "sword");

        JSONObject swordJSON2 = new JSONObject();
        swordJSON2.put("x", 2);
        swordJSON2.put("y", 0);
        swordJSON2.put("type", "sword");

        JSONArray entitiesJSON = new JSONArray();
        entitiesJSON.put(playerJSON);
        entitiesJSON.put(swordJSON);
        entitiesJSON.put(swordJSON2);

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

        // player pick up the sword
        player.moveRight();
        // player can not pick up 2nd sword
        player.moveRight();
    }
}