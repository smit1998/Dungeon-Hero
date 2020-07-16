package unsw.dungeon;

public class BasicGoal implements ComponentGoal, Observer {

    private boolean isCompleted;

    public BasicGoal() {
        this.isCompleted = false;
    }

    public boolean isComplete() {
        return isCompleted;
    }

    public void update(Subject s) {

    }
}