package main.java.unsw.dungeon.goals;

import main.java.unsw.dungeon.*;
import main.java.unsw.dungeon.entities.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A goal whether all floor switches must be pressed down by boulders
 */
public class SwitchesGoal extends BasicGoal {

    // private Set<FloorSwitch> switches = new HashSet<>();
    private List<FloorSwitch> switches = new ArrayList<>();

    /**
     * @return whether this goal is complete
     */
    public boolean isComplete() {
        return switches.stream().allMatch(s -> s.isPressed());
    }

    /**
     * Attach the given subject to this goal
     * 
     * @param s the subject to be observed
     */
    public void attachTo(Entity e) {
        if (e instanceof FloorSwitch) {
            switches.add((FloorSwitch) e);
        }
    }

    @Override
    public GoalType getType() {
        return GoalType.SWITCHES_GOAL;
    }

    @Override
    public String toString() {
        return "Activate all switches";
    }
}