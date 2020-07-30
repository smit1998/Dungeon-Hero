package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * A goal whether all floor switches must be pressed down by boulders
 */
public class SwitchesGoal extends BasicGoal {

    private int switchesSpawned;
    private int switchesTriggered;

    /**
     * Constructor for a switch goal
     * 
     * @param switchesSpawned the number of switches spawned
     */
    public SwitchesGoal(int switchesSpawned) {
        this.switchesSpawned = switchesSpawned;
        this.switchesTriggered = 0;
    }

    /**
     * @return whether this goal is complete
     */
    public boolean isComplete() {
        return (switchesSpawned == switchesTriggered);
    }

    /**
     * Update this goal with the given subject if this goal observes it
     * 
     * @param obj a subject being observed
     */
    public void update(Subject obj) {
        FloorSwitch s = (FloorSwitch) obj;
        if (s.isPressed() == true) {
            switchesTriggered += 1;
        } else if (switchesTriggered > 0) {
            switchesTriggered -= 1;
        }
    }

    /**
     * Attach the given subject to this goal
     * 
     * @param s the subject to be observed
     */
    public void attachTo(Subject s) {
        if (s instanceof FloorSwitch) {
            s.attach(this);
        }
    }

    @Override
    public List<ComponentGoal> getEssentialGoals() {
        List<ComponentGoal> essentials = new ArrayList<>();
        if (!isComplete())
            essentials.add(this);
        return essentials;
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
