package unsw.dungeon;

public class FloorSwitch extends Entity {
    private boolean isPressed;
    private int id;

    public FloorSwitch(int x, int y, Dungeon dungeon, int id) {
        super(x, y, dungeon);
        this.id = id;
        this.isPressed = false;
    }

    public int getID() {
        return this.id;
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
}