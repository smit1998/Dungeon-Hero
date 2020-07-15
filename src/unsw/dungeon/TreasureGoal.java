package unsw.dungeon;

public class TreasureGoal extends BasicGoal {

    private int treasureSpawned; 
    private int treasureCollected; 

    public TreasureGoal(int treasureSpawned) {
        this.treasureSpawned = treasureSpawned; 
        this.treasureCollected = 0; 
    }

    @Override @Override
    public boolean isCompleted() {
        // TODO Auto-generated method stub
        return super.isCompleted();
    }
}