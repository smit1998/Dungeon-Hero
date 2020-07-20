package unsw.dungeon;

import java.util.List;

/**
 * A goal strategy interface which determines if a complex goal is complete
 */
public interface GoalStrategy {

    /**
     * Determine of a list of goals completes the requirement of this goal strategy.
     * 
     * @param goals a list of goals
     * @return whether the list of goals meet the requirements of this strategy
     */
    public boolean isComplete(List<ComponentGoal> goals);

}