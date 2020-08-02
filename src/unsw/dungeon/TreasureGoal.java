package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * A goal where all treasure must be collected.
 */
public class TreasureGoal extends BasicGoal implements Observer {

    private int treasureSpawned;
    private int treasureCollected;
    private BooleanProperty isComplete = new SimpleBooleanProperty(false);

    /**
     * Constructor for a treasure goal
     * 
     * @param treasureSpawned
     */
    public TreasureGoal(int treasureSpawned) {
        this.treasureSpawned = treasureSpawned;
        this.treasureCollected = 0;
    }

    /**
     * @return whether this goal is complete
     */
    public boolean isComplete() {
        isComplete.setValue(treasureSpawned == treasureCollected);
        return isComplete.getValue();
    }

    /**
     * Update this goal with the given subject if this goal observes it
     * 
     * @param obj a subject being observed
     */
    public void update(Subject obj) {
        treasureCollected += 1;
    }

    /**
     * Attach the given subject to this goal
     * 
     * @param s the subject to be observed
     */
    public void attachTo(Entity e) {
        if (e instanceof Treasure) {
            Treasure t = (Treasure) e;
            t.attach(this);
        }
    }

    @Override
    public GoalType getType() {
        return GoalType.TREASURE_GOAL;
    }

    @Override
    public String toString() {
        return "Collect all treasure";
    }

    @Override
    public BooleanProperty isCompleteProperty() {
        return isComplete;
    }

}