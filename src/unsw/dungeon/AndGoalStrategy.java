package unsw.dungeon;

import java.util.Iterator;

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
    public boolean isComplete(Iterator<ComponentGoal> goals) {
        boolean isComplete = true;
        while (goals.hasNext()) {
            ComponentGoal goal = goals.next();
            if (!goal.isComplete()) {
                isComplete = false;
            }
        }
        return isComplete;
    }

    public String toString(Iterator<ComponentGoal> goals) {
        String str = "Complete all of the following goals:";
        while (goals.hasNext()) {
            ComponentGoal subgoal = goals.next();
            String subgoalString = subgoal.toString();
            subgoalString = subgoalString.replaceAll("(^|\n)", "$1\t");
            str += "\n" + subgoalString;
        }
        return str;
    }

    @Override
    public String getRequirement() {
        return "Complete all of the following goals";
    }

    @Override
    public GoalType getType() {
        return GoalType.AND_GOAL;
    }

}