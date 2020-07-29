package unsw.dungeon;

import java.util.List;

/**
 * A component goal interface
 */
public interface ComponentGoal {
    /**
     * @return whether this goal is complete
     */
    public boolean isComplete();

    public List<ComponentGoal> getEssentialGoals();

    public GoalType getType();

    /**
     * Attach a subject to be observed by this goal
     * 
     * @param s the subject to be observed
     */
    public void attachTo(Subject s);
}