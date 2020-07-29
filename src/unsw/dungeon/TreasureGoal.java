package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * A goal where all treasure must be collected.
 */
public class TreasureGoal extends BasicGoal {

    private int treasureSpawned;
    private int treasureCollected;

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
        return (treasureSpawned == treasureCollected);
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
    public void attachTo(Subject s) {
        if (s instanceof Treasure) {
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
        return GoalType.TREASURE_GOAL;
    }

}