package unsw.dungeon;

public class EnemiesGoal extends BasicGoal {

    private int enemiesSpawned;
    private int enemiesKilled;

    public EnemiesGoal(int enemiesSpawned) {
        this.enemiesSpawned = enemiesSpawned;
        this.enemiesKilled = 0;
    }

    @Override
    public boolean isComplete() {
        return (enemiesSpawned == enemiesKilled);
    }

    @Override
    public void update(Subject obj) {
        if (obj instanceof Enemy) {
            Enemy e = (Enemy) obj;
            // TODO
            // remove enemiesKill < enemiesSpawned after figuring out how
            // to remove entities
            if (e.getIsAlive() == false && enemiesKilled < enemiesSpawned) {
                enemiesKilled += 1;
            }
        }
    }
}