package unsw.dungeon;

/**
 * A goal where all enemies must be defeated
 */
public class EnemiesGoal extends BasicGoal {

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
        if (obj instanceof Enemy) {
            Enemy e = (Enemy) obj;
            if (e.getLifeStatus() == false && enemiesKilled < enemiesSpawned) {
                enemiesKilled += 1;
            }
        }
    }

    /**
     * Attach the given subject to this goal
     * 
     * @param s the subject to be observed
     */
    public void attachTo(Subject s) {
        if (s instanceof Enemy) {
            s.attach(this);
        }
    }
}