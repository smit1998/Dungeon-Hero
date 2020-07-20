package unsw.dungeon;

/**
 * A goal where the player must reach the exit of the dungeon
 */
public class ExitGoal extends BasicGoal {

    private boolean isComplete;

    /**
     * Constructor for an exit goal
     */
    public ExitGoal() {
        isComplete = false;
    }

    /**
     * @return whether this goal is complete
     */
    public boolean isComplete() {
        return isComplete;
    }

    /**
     * Update this goal with the given subject if this goal observes it
     * 
     * @param s a subject being observed
     */
    public void update(Subject s) {
        if (s instanceof Exit) {
            Exit exit = (Exit) s;
            isComplete = exit.getExitStatus();
        }
    }

    /**
     * Attach the given subject to this goal
     * 
     * @param s the subject to be observed
     */
    public void attachTo(Subject s) {
        if (s instanceof Exit) {
            s.attach(this);
        }
    }
}