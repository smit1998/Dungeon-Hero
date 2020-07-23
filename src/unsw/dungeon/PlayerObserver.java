package unsw.dungeon;

public interface PlayerObserver {

    public void update(int x, int y);

    public void updateFear(boolean fear);

}