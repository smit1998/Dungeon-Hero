package unsw.dungeon;

import java.util.List;

public class AndGoalStrategy implements GoalStrategy {

    public boolean isComplete(List<ComponentGoal> goals) {
        for (ComponentGoal goal : goals) {
            if (goal.isComplete() == false) {
                return false;
            }
        }
        return true;
    }
}