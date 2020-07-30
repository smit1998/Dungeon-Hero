package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

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
        Exit exit = (Exit) s;
        isComplete = exit.getExitStatus();
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

    @Override
    public List<ComponentGoal> getEssentialGoals() {
        List<ComponentGoal> essentials = new ArrayList<>();
        if (!isComplete())
            essentials.add(this);
        return essentials;
    }

    @Override
    public GoalType getType() {
        return GoalType.EXIT_GOAL;
    }

    @Override
    public String toString() {
        return "Exit the dungeon";
    }
}