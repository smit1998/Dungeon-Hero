package unsw.dungeon;

public class FloorSwitch extends Entity {
    private boolean isPressed;

    public FloorSwitch(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        this.isPressed = false;
    }

    public boolean isPressed() {
        return this.isPressed;
    }

    public void updateIsPressed(boolean newIsPressed) {
        this.isPressed = newIsPressed;
    }

    public void updateObservers() {
        // TODO
    }

    public boolean interact(Entity caller) {
        if (caller instanceof Boulder) {
            // TODO press
        }
        return true;
    }
}