package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import unsw.dungeon.*;

class WallTest {

    @Test
    void testPlayerObstruction() {

        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, 1, 0);
        Wall wall = new Wall(1, 1, dungeon);
        dungeon.addEntity(player);
        dungeon.addEntity(wall);

        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 0);

        // Try to move down into wall
        player.moveDown();
        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 0);

        // Move to the right of the wall
        player.moveRight();
        player.moveDown();
        assertTrue(player.getX() == 2);
        assertTrue(player.getY() == 1);

        // Try to move left into the wall
        player.moveLeft();
        assertTrue(player.getX() == 2);
        assertTrue(player.getY() == 1);

        // Move to the bottom of the wall
        player.moveDown();
        player.moveLeft();
        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 2);

        // Try to move up into the wall
        player.moveUp();
        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 2);

        // Move to the left of the wall
        player.moveLeft();
        player.moveUp();
        assertTrue(player.getX() == 0);
        assertTrue(player.getY() == 1);

        // Try to move right into the wall
        player.moveRight();
        assertTrue(player.getX() == 0);
        assertTrue(player.getY() == 1);

        // Test that wall has not moved
        assertTrue(wall.getX() == 1);
        assertTrue(wall.getY() == 1);
    }

    @Test
    void testBoulderObstruction() {

        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 2, 0);
        Wall wall = new Wall(2, 2, dungeon);
        Boulder bLeft = new Boulder(1, 2, dungeon);
        Boulder bUp = new Boulder(2, 1, dungeon);
        Boulder bRight = new Boulder(3, 2, dungeon);
        Boulder bDown = new Boulder(2, 3, dungeon);
        dungeon.addEntity(player);
        dungeon.addEntity(wall);
        dungeon.addEntity(bLeft);
        dungeon.addEntity(bUp);
        dungeon.addEntity(bRight);
        dungeon.addEntity(bDown);

        assertTrue(player.getX() == 2);
        assertTrue(player.getY() == 0);

        // Try to push boulder down into wall
        player.moveDown();
        assertTrue(player.getX() == 2);
        assertTrue(player.getY() == 0);
        assertTrue(bUp.getX() == 2);
        assertTrue(bUp.getY() == 1);

        // Move to the right of the right boulder
        player.moveRight();
        player.moveRight();
        player.moveDown();
        player.moveDown();
        assertTrue(player.getX() == 4);
        assertTrue(player.getY() == 2);

        // Try to push boulder left into wall
        player.moveLeft();
        assertTrue(player.getX() == 4);
        assertTrue(player.getY() == 2);
        assertTrue(bRight.getX() == 3);
        assertTrue(bRight.getY() == 2);

        // Move to the bottom of the bottom boulder
        player.moveDown();
        player.moveDown();
        player.moveLeft();
        player.moveLeft();
        assertTrue(player.getX() == 2);
        assertTrue(player.getY() == 4);

        // Try to push bottom boulder up into wall
        player.moveUp();
        assertTrue(player.getX() == 2);
        assertTrue(player.getY() == 4);
        assertTrue(bDown.getX() == 2);
        assertTrue(bDown.getY() == 3);

        // Move to the left of the left boulder
        player.moveLeft();
        player.moveLeft();
        player.moveUp();
        player.moveUp();
        assertTrue(player.getX() == 0);
        assertTrue(player.getY() == 2);

        // Try to push left boulder right into wall
        player.moveLeft();
        assertTrue(player.getX() == 0);
        assertTrue(player.getY() == 2);
        assertTrue(bLeft.getX() == 1);
        assertTrue(bLeft.getY() == 2);

        // Test that wall has not moved
        assertTrue(wall.getX() == 2);
        assertTrue(wall.getY() == 2);
    }
}