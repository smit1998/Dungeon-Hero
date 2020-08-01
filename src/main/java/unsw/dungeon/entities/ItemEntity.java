package main.java.unsw.dungeon.entities;

import main.java.unsw.dungeon.*;

import javafx.beans.property.BooleanProperty;

/**
 * An abstract class for an ItemEntity
 */
public abstract class ItemEntity extends Entity {

    /**
     * Constructor for item entity
     * 
     * @param x       coordinate position of entity
     * @param y       coordinate position of entity
     * @param dungeon entity belongs to
     */
    public ItemEntity(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
    }

    public abstract BooleanProperty isPickedUp();

}