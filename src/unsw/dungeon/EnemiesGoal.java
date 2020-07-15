package unsw.dungeon;

public class EnemiesGoal extends BasicGoal {

    private int enemiesSpawned;
    private int enemiesKilled;

    public EnemiesGoal(int enemiesSpawned) {
        this.enemiesSpawned = enemiesSpawned;
        this.enemiesKilled = 0;
    }

    @Override
    public boolean isCompleted() {
        // TODO Auto-generated method stub
        return super.isCompleted();
    }
}