package unsw.dungeon;

/**
 * A basic goal which observes a goal requirement
 */
public abstract class BasicGoal implements ComponentGoal, Observer {
    /**
     * @return whether the goal is complete
     */
    public abstract boolean isComplete();

    /**
     * Updates this goal according to the given subject
     * 
     * @param obj subject being observed
     */
    public abstract void update(Subject obj);
}