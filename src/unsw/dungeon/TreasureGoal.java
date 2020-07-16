package unsw.dungeon;

public class TreasureGoal extends BasicGoal {

    private int treasureSpawned;
    private int treasureCollected;

    public TreasureGoal(int treasureSpawned) {
        this.treasureSpawned = treasureSpawned;
        this.treasureCollected = 0;
    }

    @Override
    public boolean isComplete() {
        return (treasureSpawned == treasureCollected);
    }

    @Override
    public void update(Subject obj) {
        treasureCollected+=1;
    }

    
}