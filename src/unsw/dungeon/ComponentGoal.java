package unsw.dungeon;

import javafx.beans.property.BooleanProperty;

/**
 * A component goal interface
 */
public interface ComponentGoal {
    /**
     * @return whether this goal is complete
     */
    public boolean isComplete();

    public GoalType getType();

    /**
     * Attach a subject to be observed by this goal
     * 
     * @param s the subject to be observed
     */
    public void attachTo(Entity e);

    public BooleanProperty isCompleteProperty();
}