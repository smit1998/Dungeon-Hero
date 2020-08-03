package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * A complex goal which is made up of one or many subgoals.
 */
public class ComplexGoal implements ComponentGoal {

    private List<ComponentGoal> goals;
    private GoalStrategy strategy;
    private BooleanProperty isComplete = new SimpleBooleanProperty(false);

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
    public void attachTo(Entity e) {
        for (ComponentGoal goal : goals) {
            goal.attachTo(e);
        }
    }

    /**
     * @return whether this goal is complete
     */
    public boolean isComplete() {
        isComplete.setValue(strategy.isComplete(goals.iterator()));
        return isComplete.getValue();
    }

    @Override
    public GoalType getType() {
        return strategy.getType();
    }

    @Override
    public String toString() {
        return strategy.toString(goals.iterator());
    }

    public String getRequirement() {
        return strategy.getRequirement();
    }

    @Override
    public BooleanProperty isCompleteProperty() {
        return isComplete;
    }

    public List<ComponentGoal> getSubgoals() {
        return new ArrayList<>(goals);
    }
}