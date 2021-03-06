package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import unsw.dungeon.*;

class BoulderTest {

    @Test
    void testValidPush() {
        Dungeon dungeon = new Dungeon(3, 1);
        Player player = new Player(dungeon, 0, 0);
        Boulder boulder = new Boulder(1, 0, dungeon);
        dungeon.addEntity(player);
        dungeon.addEntity(boulder);

        player.moveRight();

        // Test that boulder was pushed
        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 0);

        assertTrue(boulder.getX() == 2);
        assertTrue(boulder.getY() == 0);

    }

    // Test that boulders will not move if the player does not move towards it
    @Test
    void testAdjacentMovement() {

        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, 0, 0);
        Boulder boulder = new Boulder(1, 1, dungeon);

        dungeon.addEntity(player);
        dungeon.addEntity(boulder);

        assertTrue(player.getX() == 0);
        assertTrue(player.getY() == 0);

        // Move around boulder
        player.moveRight();
        player.moveRight();
        player.moveDown();
        player.moveDown();
        player.moveLeft();
        player.moveLeft();
        player.moveUp();
        player.moveUp();

        // Test that player is back in their original position
        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 1);

        // Test that boulder did not move
        assertTrue(boulder.getX() == 0);
        assertTrue(boulder.getY() == 0);

    }

    // Test that boulders cannot be pushed out of the bounds of the dungeon
    @Test
    void testPushingOutOfBounds() {

        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, 1, 1);
        Boulder bUp = new Boulder(1, 0, dungeon);
        Boulder bRight = new Boulder(2, 1, dungeon);
        Boulder bDown = new Boulder(1, 2, dungeon);
        Boulder bLeft = new Boulder(0, 1, dungeon);

        dungeon.addEntity(player);
        dungeon.addEntity(bUp);
        dungeon.addEntity(bRight);
        dungeon.addEntity(bDown);
        dungeon.addEntity(bLeft);

        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 1);

        // Try to push boulders out of bounds
        player.moveUp();
        player.moveRight();
        player.moveDown();
        player.moveLeft();

        // Test that the player has not moved
        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 1);

        // Test that none of the boulders move
        assertTrue(bUp.getX() == 1);
        assertTrue(bUp.getY() == 0);

        assertTrue(bRight.getX() == 2);
        assertTrue(bRight.getY() == 1);

        assertTrue(bDown.getX() == 1);
        assertTrue(bDown.getY() == 2);

        assertTrue(bLeft.getX() == 0);
        assertTrue(bLeft.getY() == 1);

    }

    // Test that multiple boulders cannot be pushed at once
    @Test
    void testPushingMultipleBoulders() {

        Dungeon dungeon = new Dungeon(7, 7);
        Player player = new Player(dungeon, 3, 3);
        Boulder bUp = new Boulder(3, 1, dungeon);
        Boulder bUp1 = new Boulder(3, 2, dungeon);
        Boulder bRight = new Boulder(4, 3, dungeon);
        Boulder bRight1 = new Boulder(5, 3, dungeon);
        Boulder bDown = new Boulder(3, 4, dungeon);
        Boulder bDown1 = new Boulder(3, 5, dungeon);
        Boulder bLeft = new Boulder(1, 3, dungeon);
        Boulder bLeft1 = new Boulder(2, 3, dungeon);

        dungeon.addEntity(player);
        dungeon.addEntity(bUp);
        dungeon.addEntity(bUp1);
        dungeon.addEntity(bRight);
        dungeon.addEntity(bRight1);
        dungeon.addEntity(bDown);
        dungeon.addEntity(bDown1);
        dungeon.addEntity(bLeft);
        dungeon.addEntity(bLeft1);

        assertTrue(player.getX() == 3);
        assertTrue(player.getY() == 3);

        // Try to push multiple boulders
        player.moveUp();
        player.moveRight();
        player.moveDown();
        player.moveLeft();

        // Test that the player did not move
        assertTrue(player.getX() == 3);
        assertTrue(player.getY() == 3);

        // Test that none of the boulders moved
        assertTrue(bUp.getX() == 3);
        assertTrue(bUp.getY() == 1);

        assertTrue(bUp1.getX() == 3);
        assertTrue(bUp1.getY() == 2);

        assertTrue(bRight.getX() == 4);
        assertTrue(bRight.getY() == 3);

        assertTrue(bRight1.getX() == 5);
        assertTrue(bRight1.getY() == 3);

        assertTrue(bDown.getX() == 3);
        assertTrue(bDown.getY() == 4);

        assertTrue(bDown1.getX() == 3);
        assertTrue(bDown1.getY() == 5);

        assertTrue(bLeft.getX() == 1);
        assertTrue(bLeft.getY() == 3);

        assertTrue(bLeft1.getX() == 2);
        assertTrue(bLeft1.getY() == 3);

    }

    @Test
    void testEnemyObstruction() {
        Dungeon dungeon = new Dungeon(3, 1);
        Player player = new Player(dungeon, 0, 0);
        Boulder boulder = new Boulder(1, 0, dungeon);
        Enemy enemy = new Enemy(2, 0, dungeon);
        dungeon.addEntity(player);
        dungeon.addEntity(boulder);
        dungeon.addEntity(enemy);

        player.moveRight();

        // Test that no entities have moved
        assertTrue(player.getX() == 0);
        assertTrue(player.getY() == 0);

        assertTrue(boulder.getX() == 1);
        assertTrue(boulder.getY() == 0);

        assertTrue(enemy.getX() == 2);
        assertTrue(enemy.getY() == 0);
    }

    @Test
    void testExitObstruction() {
        Dungeon dungeon = new Dungeon(3, 1);
        Player player = new Player(dungeon, 0, 0);
        Boulder boulder = new Boulder(1, 0, dungeon);
        Exit exit = new Exit(2, 0, dungeon);
        dungeon.addEntity(player);
        dungeon.addEntity(boulder);
        dungeon.addEntity(exit);

        player.moveRight();

        // Test that no entities have moved
        assertTrue(player.getX() == 0);
        assertTrue(player.getY() == 0);

        assertTrue(boulder.getX() == 1);
        assertTrue(boulder.getY() == 0);

        assertTrue(exit.getX() == 2);
        assertTrue(exit.getY() == 0);
    }

    /*
     * @Test void testInvincibilityPotionObstruction() { Dungeon dungeon = new
     * Dungeon(3, 1); Player player = new Player(dungeon, 0, 0); Boulder boulder =
     * new Boulder(1, 0, dungeon); InvincibilityPotion potion = new
     * InvincibilityPotion(2, 0, dungeon); dungeon.addEntity(player);
     * dungeon.addEntity(boulder); dungeon.addEntity(potion);
     * 
     * player.moveRight();
     * 
     * // Test that no entities have moved assertTrue(player.getX() == 0);
     * assertTrue(player.getY() == 0);
     * 
     * assertTrue(boulder.getX() == 1); assertTrue(boulder.getY() == 0);
     * 
     * assertTrue(potion.getX() == 2); assertTrue(potion.getY() == 0); }
     */

    @Test
    void testKeyObstruction() {
        Dungeon dungeon = new Dungeon(3, 1);
        Player player = new Player(dungeon, 0, 0);
        Boulder boulder = new Boulder(1, 0, dungeon);
        Key key = new Key(2, 0, dungeon, 1);
        dungeon.addEntity(player);
        dungeon.addEntity(boulder);
        dungeon.addEntity(key);

        player.moveRight();

        // Test that no entities have moved
        assertTrue(player.getX() == 0);
        assertTrue(player.getY() == 0);

        assertTrue(boulder.getX() == 1);
        assertTrue(boulder.getY() == 0);

        assertTrue(key.getX() == 2);
        assertTrue(key.getY() == 0);
    }

    /*
     * @Test void testSwordObstruction() { Dungeon dungeon = new Dungeon(3, 1);
     * Player player = new Player(dungeon, 0, 0); Boulder boulder = new Boulder(1,
     * 0, dungeon); Sword sword = new Sword(2, 0, dungeon);
     * dungeon.addEntity(player); dungeon.addEntity(boulder);
     * dungeon.addEntity(sword);
     * 
     * player.moveRight();
     * 
     * // Test that no entities have moved assertTrue(player.getX() == 0);
     * assertTrue(player.getY() == 0);
     * 
     * assertTrue(boulder.getX() == 1); assertTrue(boulder.getY() == 0);
     * 
     * assertTrue(sword.getX() == 2); assertTrue(sword.getY() == 0); }
     */

    @Test
    void testTreasureObstruction() {
        Dungeon dungeon = new Dungeon(3, 1);
        Player player = new Player(dungeon, 0, 0);
        Boulder boulder = new Boulder(1, 0, dungeon);
        Treasure treasure = new Treasure(2, 0, dungeon);
        dungeon.addEntity(player);
        dungeon.addEntity(boulder);
        dungeon.addEntity(treasure);

        player.moveRight();

        // Test that no entities have moved
        assertTrue(player.getX() == 0);
        assertTrue(player.getY() == 0);

        assertTrue(boulder.getX() == 1);
        assertTrue(boulder.getY() == 0);

        assertTrue(treasure.getX() == 2);
        assertTrue(treasure.getY() == 0);
    }

    @Test
    void testClosedDoorObstruction() {
        Dungeon dungeon = new Dungeon(3, 1);
        Player player = new Player(dungeon, 0, 0);
        Boulder boulder = new Boulder(1, 0, dungeon);
        Door door = new Door(2, 0, dungeon, 1);
        dungeon.addEntity(player);
        dungeon.addEntity(boulder);
        dungeon.addEntity(door);

        player.moveRight();

        // Test that no entities have moved
        assertTrue(player.getX() == 0);
        assertTrue(player.getY() == 0);

        assertTrue(boulder.getX() == 1);
        assertTrue(boulder.getY() == 0);

        assertTrue(door.getX() == 2);
        assertTrue(door.getY() == 0);
    }

    @Test
    void testOpenDoorObstruction() {
        Dungeon dungeon = new Dungeon(4, 3);
        Player player = new Player(dungeon, 2, 2);
        Boulder boulder = new Boulder(1, 0, dungeon);
        Door door = new Door(2, 0, dungeon, 1);
        Key key = new Key(2, 1, dungeon, 1);
        dungeon.addEntity(player);
        dungeon.addEntity(boulder);
        dungeon.addEntity(door);
        dungeon.addEntity(key);

        // Move up to pickup key
        player.moveUp();
        assertTrue(player.getX() == 2);
        assertTrue(player.getY() == 1);

        // Move up to unlock door
        player.moveUp();
        assertTrue(player.getX() == 2);
        assertTrue(player.getY() == 0);

        // Move around, to the back of the boulder (facing the door)
        player.moveDown();
        player.moveLeft();
        player.moveLeft();
        player.moveUp();
        assertTrue(player.getX() == 0);
        assertTrue(player.getY() == 0);

        // Move right to push boulder into open door
        player.moveRight();
        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 0);
        assertTrue(boulder.getX() == 2);
        assertTrue(boulder.getY() == 0);

        // Move right to push boulder out of door way
        player.moveRight();
        assertTrue(player.getX() == 2);
        assertTrue(player.getY() == 0);
        assertTrue(boulder.getX() == 3);
        assertTrue(boulder.getY() == 0);

    }

}