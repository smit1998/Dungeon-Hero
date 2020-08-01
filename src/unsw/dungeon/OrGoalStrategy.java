package unsw.dungeon;

import java.util.List;

/**
 * A goal strategy where the complex goal will be completed if any of its
 * subgoals are completed.
 */
public class OrGoalStrategy implements GoalStrategy {

    /**
     * Determine of a list of goals completes the requirement of this goal strategy.
     * 
     * @param goals a list of goals
     * @return whether the list of goals meet the requirements of this strategy
     */
    public boolean isComplete(List<ComponentGoal> goals) {
        for (ComponentGoal goal : goals) {
            if (goal.isComplete() == true) {
                return true;
            }
        }
        return false;
    }

    public String toString(List<ComponentGoal> goals) {
        String str = "Complete any of the following goals:";
        for (ComponentGoal subgoal : goals) {
            String subgoalString = subgoal.toString();
            subgoalString = subgoalString.replaceAll("(^|\n)", "$1  ");
            str += "\n" + subgoalString;
        }
        return str;
    }

}