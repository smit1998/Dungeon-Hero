package unsw.dungeon;

import java.util.Iterator;

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
    public boolean isComplete(Iterator<ComponentGoal> goals) {
        while (goals.hasNext()) {
            ComponentGoal goal = goals.next();
            if (goal.isComplete()) {
                return true;
            }
        }
        return false;
    }

    public String toString(Iterator<ComponentGoal> goals) {
        String str = "Complete any of the following goals:";
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
        return "Complete one of the following goals";
    }

    @Override
    public GoalType getType() {
        return GoalType.OR_GOAL;
    }

}