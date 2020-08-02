package unsw.dungeon;

import java.util.Iterator;
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
    public boolean isComplete(Iterator<ComponentGoal> goals) {
        while (goals.hasNext()) {
            ComponentGoal goal = goals.next(); 
            if (!goal.isComplete()) { return false; }
        }
        return true; 
    }

    public String toString(Iterator<ComponentGoal> goals) {
        String str = "Complete all of the following goals:";
        while (goals.hasNext()) {
            ComponentGoal subgoal = goals.next(); 
            String subgoalString = subgoal.toString();
            subgoalString = subgoalString.replaceAll("(^|\n)", "$1  ");
            str += "\n" + subgoalString;
        }
        return str; 
    }

}