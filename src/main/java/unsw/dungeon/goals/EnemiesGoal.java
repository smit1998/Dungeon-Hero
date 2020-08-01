package main.java.unsw.dungeon.goals;

import main.java.unsw.dungeon.*;

/**
 * A goal where all enemies must be defeated
 */
public class EnemiesGoal extends BasicGoal implements Observer {

    private int enemiesSpawned;
    private int enemiesKilled;

    /**
     * Constructor for an enemy goal
     * 
     * @param enemiesSpawned the number of enemies spawned
     */
    public EnemiesGoal(int enemiesSpawned) {
        this.enemiesSpawned = enemiesSpawned;
        this.enemiesKilled = 0;
    }

    /**
     * @return whether this goal is complete
     */
    public boolean isComplete() {
        return (enemiesSpawned == enemiesKilled);
    }

    /**
     * Update this goal with the given subject if this goal observes it
     * 
     * @param obj a subject being observed
     */
    public void update(Subject obj) {
        Enemy e = (Enemy) obj;
        if (!e.getLifeStatus()) {
            enemiesKilled += 1;
        }
    }

    /**
     * Attach the given subject to this goal
     * 
     * @param s the subject to be observed
     */
    public void attachTo(Entity e) {
        if (e instanceof Enemy) {
            Enemy enemy = (Enemy) e;
            enemy.attach(this);
        }
    }

    @Override
    public GoalType getType() {
        return GoalType.ENEMIES_GOAL;
    }

    @Override
    public String toString() {
        return "Defeat all enemies";
    }
}