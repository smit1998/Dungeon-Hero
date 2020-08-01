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

    public String toString(List<ComponentGoal> goals) {
        String str = "Complete all of the following goals:";
        for (ComponentGoal subgoal : goals) {
            String subgoalString = subgoal.toString();
            subgoalString = subgoalString.replaceAll("(^|\n)", "$1  ");
            str += "\n" + subgoalString;
        }
        return str;
    }

}