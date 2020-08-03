package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * A goal whether all floor switches must be pressed down by boulders
 */
public class SwitchesGoal implements ComponentGoal {

    private List<FloorSwitch> switches = new ArrayList<>();

    private BooleanProperty isComplete = new SimpleBooleanProperty(false);

    /**
     * @return whether this goal is complete
     */
    public boolean isComplete() {
        isComplete.setValue(switches.stream().allMatch(s -> s.isPressed()));
        return isComplete.getValue();
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

    @Override
    public BooleanProperty isCompleteProperty() {
        return isComplete;
    }
}
