package unsw.dungeon;

public class ExitGoal extends BasicGoal {

    private boolean isComplete;

    public ExitGoal() {
        isComplete = false;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void update(Subject s) {
        if (s instanceof Exit) {
            Exit exit = (Exit) s;
            isComplete = exit.getExitStatus();
        }
    }
}