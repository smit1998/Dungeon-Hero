package unsw.dungeon;

import java.util.List;

/**
 * A complex goal which is made up of one or many subgoals.
 */
public class ComplexGoal implements ComponentGoal {

    private List<ComponentGoal> goals;
    private GoalStrategy strategy;

    /**
     * Constructor for ComplexGoal
     * 
     * @param strategy the strategy the goal will enforce
     * @param goals    a list of subgoals
     */
    public ComplexGoal(GoalStrategy strategy, List<ComponentGoal> goals) {
        this.goals = goals;
        this.strategy = strategy;
    }

    /**
     * Attach the given subject to this goal and its subgoals
     * 
     * @param s the subject to be observed
     */
    public void attachTo(Subject s) {
        for (ComponentGoal goal : goals) {
            goal.attachTo(s);
        }
    }

    /**
     * @return whether this goal is complete
     */
    public boolean isComplete() {
        return strategy.isComplete(goals);
    }

    public List<ComponentGoal> getEssentialGoals() {
        return strategy.getEssentialGoals(goals);
    }

    @Override
    public GoalType getType() {
        return GoalType.COMPLEX_GOAL;
    }

}