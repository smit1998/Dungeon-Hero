package unsw.dungeon;

import java.util.List;

public class OrGoalStrategy implements GoalStrategy {

    public boolean isComplete(List<ComponentGoal> goals) {
        for (ComponentGoal goal : goals) {
            if (goal.isComplete() == true) {
                return true;
            }
        }
        return false;
    }
}