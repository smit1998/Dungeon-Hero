package main.java.unsw.dungeon.goals;

import main.java.unsw.dungeon.*;
import main.java.unsw.dungeon.entities.*;

/**
 * A goal where the player must reach the exit of the dungeon
 */
public class ExitGoal extends BasicGoal implements Observer {

    private boolean isComplete;

    /**
     * Constructor for an exit goal
     */
    public ExitGoal() {
        isComplete = false;
    }

    /**
     * @return whether this goal is complete
     */
    public boolean isComplete() {
        return isComplete;
    }

    /**
     * Update this goal with the given subject if this goal observes it
     * 
     * @param s a subject being observed
     */
    public void update(Subject s) {
        Exit exit = (Exit) s;
        isComplete = exit.getExitStatus();
    }

    /**
     * Attach the given subject to this goal
     * 
     * @param s the subject to be observed
     */
    public void attachTo(Entity e) {
        if (e instanceof Exit) {
            Exit exit = (Exit) e;
            exit.attach(this);
        }
    }

    @Override
    public GoalType getType() {
        return GoalType.EXIT_GOAL;
    }

    @Override
    public String toString() {
        return "Exit the dungeon";
    }
}