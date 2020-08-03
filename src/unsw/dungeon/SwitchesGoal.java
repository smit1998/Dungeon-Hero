package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * A goal whether all floor switches must be pressed down by boulders
 */
public class SwitchesGoal implements ComponentGoal, Observer {

    private int switchesSpawned;
    private int switchPressed;
    private BooleanProperty isComplete = new SimpleBooleanProperty(false);

    /**
     * @return whether this goal is complete
     */
    public boolean isComplete() {
        isComplete.setValue(switchesSpawned == switchPressed);
        return isComplete.getValue();
    }

    /**
     * Attach the given subject to this goal
     * 
     * @param s the subject to be observed
     */
    public void attachTo(Entity e) {
        if (e instanceof FloorSwitch) {
            FloorSwitch s = (FloorSwitch) e;
            s.attach(this);
            switchesSpawned++;
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

    @Override
    public void update(Subject obj) {
        if (obj.getClass() == FloorSwitch.class) {
            FloorSwitch s = (FloorSwitch) obj;
            switchPressed += s.isPressed() ? 1 : -1; // Update on change
        }
    }
}
