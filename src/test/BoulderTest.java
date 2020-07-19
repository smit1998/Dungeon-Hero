package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import unsw.dungeon.*;

class BoulderTest {

    // Test that boulders will not move if the player does not move towards it
    @Test
    void testAdjacentMovement() {

        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, 0, 0);
        Boulder boulder = new Boulder(1, 1, dungeon);

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
        assertTrue(player.getX() == 0);
        assertTrue(player.getY() == 0);

        // Test that boulder did not move
        assertTrue(boulder.getX() == 1);
        assertTrue(boulder.getY() == 1);

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
}