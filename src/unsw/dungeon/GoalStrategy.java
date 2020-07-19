package unsw.dungeon;

import java.util.List;

public interface GoalStrategy {
    public boolean isComplete(List<ComponentGoal> goals);
}