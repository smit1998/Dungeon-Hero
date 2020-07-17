package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import unsw.dungeon.*;

class PlayerTest {

    @Test
    void testValidMovement() {

        Dungeon dungeon = new Dungeon(2, 2);
        Player player = new Player(dungeon, 0, 0);

        assertTrue(player.getX() == 0);
        assertTrue(player.getY() == 0);

        // Move right
        player.moveRight();
        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 0);

        // Move down
        player.moveDown();
        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 1);

        // Move left
        player.moveLeft();
        assertTrue(player.getX() == 0);
        assertTrue(player.getY() == 1);

        // Move up
        player.moveUp();
        assertTrue(player.getX() == 0);
        assertTrue(player.getY() == 0);

    }

    @Test
    void testInvalidMovement() {

        Dungeon dungeon = new Dungeon(2, 2);
        Player player = new Player(dungeon, 0, 0);

        assertTrue(player.getX() == 0);
        assertTrue(player.getY() == 0);

        // Move out of bounds up
        player.moveUp();
        assertTrue(player.getX() == 0);
        assertTrue(player.getY() == 0);

        // Move out of bounds left
        player.moveLeft();
        assertTrue(player.getX() == 0);
        assertTrue(player.getY() == 0);

        // Move to bottom right of dungeon
        player.moveRight();
        player.moveDown();
        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 1);

        // Move out of bounds right
        player.moveRight();
        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 1);

        // Move out of bounds down
        player.moveDown();
        assertTrue(player.getX() == 1);
        assertTrue(player.getY() == 1);
    }
}