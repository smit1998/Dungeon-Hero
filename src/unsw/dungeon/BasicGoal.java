package unsw.dungeon;

public class BasicGoal implements Observer {

    private boolean isCompleted;

    public BasicGoal() {
        this.isCompleted = false;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void update(Subject s) {

    }
}