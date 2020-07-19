package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import unsw.dungeon.*;

class FloorSwitchTest {

    @Test
    void testBoulderPress() {
        Dungeon dungeon = new Dungeon(3, 1);
        Player player = new Player(dungeon, 0, 0);
        Boulder boulder = new Boulder(1, 0, dungeon);
        FloorSwitch floorSwitch = new FloorSwitch(2, 0, dungeon);
        dungeon.addEntity(player);
        dungeon.addEntity(boulder);
        dungeon.addEntity(floorSwitch);

        // Push boulder onto switch
        player.moveRight();

        // Test that boulder was pushed
        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 0);

        assertTrue(boulder.getX() == 2);
        assertTrue(boulder.getY() == 0);

        // Test that floor switch is pressed
        assertTrue(floorSwitch.isPressed());

    }

    @Test
    void testBoulderDeactivation() {
        Dungeon dungeon = new Dungeon(4, 1);
        Player player = new Player(dungeon, 0, 0);
        Boulder boulder = new Boulder(1, 0, dungeon);
        FloorSwitch floorSwitch = new FloorSwitch(2, 0, dungeon);
        dungeon.addEntity(player);
        dungeon.addEntity(boulder);
        dungeon.addEntity(floorSwitch);

        // Push boulder onto switch
        player.moveRight();

        // Test that boulder was pushed
        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 0);

        assertTrue(boulder.getX() == 2);
        assertTrue(boulder.getY() == 0);

        // Test that floor switch is pressed
        assertTrue(floorSwitch.isPressed());

        // Push boulder off of switch
        player.moveRight();

        // Test that boulder was pushed
        assertTrue(player.getX() == 2);
        assertTrue(player.getY() == 0);

        assertTrue(boulder.getX() == 3);
        assertTrue(boulder.getY() == 0);

        // Test that floor switch is not pressed
        assertFalse(floorSwitch.isPressed());

    }

    @Test
    void testPlayerStanding() {
        Dungeon dungeon = new Dungeon(2, 1);
        Player player = new Player(dungeon, 0, 0);
        FloorSwitch floorSwitch = new FloorSwitch(1, 0, dungeon);
        dungeon.addEntity(player);
        dungeon.addEntity(floorSwitch);

        // Move player onto switch
        player.moveRight();

        // Test that player is standing on switch
        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 0);

        // Test that floor switch is not pressed
        assertFalse(floorSwitch.isPressed());
    }

    @Test
    void testEnemyStanding() {
        Dungeon dungeon = new Dungeon(3, 1);
        Player player = new Player(dungeon, 0, 0);
        FloorSwitch floorSwitch = new FloorSwitch(1, 0, dungeon);
        Enemy enemy = new Enemy(2, 0, dungeon);
        dungeon.addEntity(player);
        dungeon.addEntity(floorSwitch);
        dungeon.addEntity(enemy);

        // Move enemy onto switch
        enemy.moveLeft();

        // Test that enemy is standing on the switch
        assertTrue(enemy.getX() == 1);
        assertTrue(enemy.getY() == 0);

        // Test that floor switch is not pressed
        assertFalse(floorSwitch.isPressed());
    }

}