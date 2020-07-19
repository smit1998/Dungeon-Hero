package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONArray;
import org.json.JSONObject;

import unsw.dungeon.*;

class potionTest{

    // basic test with just potion and player
    void potiontest1() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject potionJSON = new JSONObject();
        potionJSON.put("x", 1);
        potionJSON.put("y", 0);
        potionJSON.put("type", "potion");

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
    void potiontest2() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 1);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject potionJSON = new JSONObject();
        potionJSON.put("x", 2);
        potionJSON.put("y", 0);
        potionJSON.put("type", "potion");

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

        //enemy is alive
        assertTrue(dungeon.isComplete() == false);

        // picks up the potion
        player.moveRight();

        // kills the enemy
        player.moveLeft();
        player.moveLeft();

        assertTrue(dungeon.isComplete() == true);

    }

    // potion with multiple enemies
    void potiontest3(){
        void potiontest2() {
            JSONObject playerJSON = new JSONObject();
            playerJSON.put("x", 1);
            playerJSON.put("y", 0);
            playerJSON.put("type", "player");
    
            JSONObject potionJSON = new JSONObject();
            potionJSON.put("x", 2);
            potionJSON.put("y", 0);
            potionJSON.put("type", "potion");
    
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
    
            //enemy is alive
            assertTrue(dungeon.isComplete() == false);
    
            // picks up the potion
            player.moveRight();
            //kills the first enemy
            player.moveRight();
            // kills the enemy
            player.moveLeft();
            player.moveLeft();
            player.moveLeft();
    
            assertTrue(dungeon.isComplete() == true);
    }

    // sword and potion.
    
    void potiontest4() {
        JSONObject playerJSON = new JSONObject();
        playerJSON.put("x", 0);
        playerJSON.put("y", 0);
        playerJSON.put("type", "player");

        JSONObject potionJSON = new JSONObject();
        potionJSON.put("x", 1);
        potionJSON.put("y", 0);
        potionJSON.put("type", "potion");

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

        //enemy is alive
        assertFalse(dungeon.isComplete());

        // player picks up potion
        player.moveRight();
        // player does not pick up sword
        player.moveRight();

        // player kills enemy
        player.moveRight();

        assertTrue(dungeon.isComplete());

    }
} 