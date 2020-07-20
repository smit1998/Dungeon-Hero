package unsw.dungeon;

import java.util.List;

/**
 * A goal strategy where all subgoals must be completed before the complex goal
 * is considered completed.
 */
public class AndGoalStrategy implements GoalStrategy {

    /**
     * Determine of a list of goals completes the requirement of this goal strategy.
     * 
     * @param goals a list of goals
     * @return whether the list of goals meet the requirements of this strategy
     */
    public boolean isComplete(List<ComponentGoal> goals) {
        for (ComponentGoal goal : goals) {
            if (goal.isComplete() == false) {
                return false;
            }
        }
        return true;
    }
}