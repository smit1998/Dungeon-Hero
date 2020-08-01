package main.java.unsw.dungeon.goals;

/**
 * A basic goal which observes a goal requirement
 */
public abstract class BasicGoal implements ComponentGoal {

    /**
     * @return whether the goal is complete
     */
    public abstract boolean isComplete();

}