package unsw.dungeon;

public class Exit extends Entity {

    public Exit(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
    }

    public void updateGameState() {
        return;
    }

    public boolean getExitStatus() {
        return false;
    }
}