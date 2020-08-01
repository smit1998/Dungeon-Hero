package main.java.unsw.dungeon.entities;

;

public interface PlayerObserver {

    public void update(int x, int y);

    public void updateFear(boolean fear);

}