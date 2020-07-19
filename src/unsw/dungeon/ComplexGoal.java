package unsw.dungeon;

import java.util.List;
import java.util.ArrayList;

public class ComplexGoal implements ComponentGoal {

    private List<ComponentGoal> goals;
    private GoalStrategy strategy;

    public ComplexGoal(GoalStrategy strategy, List<ComponentGoal> goals) {
        this.goals = goals;
        this.strategy = strategy;
    }

    public void addGoal(ComponentGoal goal) {
        goals.add(goal);
    }

    public List<ComponentGoal> getGoals() {
        return new ArrayList<ComponentGoal>(goals);
    }

    public void attachTo(Subject s) {
        for (ComponentGoal goal : goals) {
            goal.attachTo(s);
        }
    }

    public boolean isComplete() {
        return strategy.isComplete(goals);
    }
}