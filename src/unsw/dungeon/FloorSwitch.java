package unsw.dungeon;

/**
 * A floor switch entity that can be activated by a boulder
 */
public class FloorSwitch extends Entity {

    private boolean isPressed;

    /**
     * Constructs a new floor switch entity at (x,y) in the dungeon
     * 
     * @param x       the horizontal position
     * @param y       the vertical position
     * @param dungeon the dungeon the switch belongs to
     */
    public FloorSwitch(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        this.isPressed = false;
    }

    /**
     * Return whether this switch is activated by an boulder
     * 
     * @return whether this switch is pressed down
     */
    public boolean isPressed() {
        return this.isPressed;
    }

    /**
     * Update the isPressed property of this switch
     * 
     * @param newIsPressed whether the switch will be activated
     */
    public void updateIsPressed(boolean newIsPressed) {
        this.isPressed = newIsPressed;
    }

    /**
     * Interact with this entity. If the caller is a boulder, this switch will be
     * pressed down.
     * 
     * @param caller the calling entity
     * @return whether the interaction was successful
     */
    public boolean interact(Entity caller) {
        if (caller instanceof Boulder) {
            updateIsPressed(true);
        } else {
            updateIsPressed(false);
        }
        return true;
    }

    /**
     * no changes when game state updates
     */
    @Override
    public void tick(Dungeon dungeon) {
    }
}