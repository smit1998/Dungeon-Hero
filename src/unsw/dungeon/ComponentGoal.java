package unsw.dungeon;

public interface ComponentGoal {
    public boolean isComplete();

    public void attachTo(Subject s);
}