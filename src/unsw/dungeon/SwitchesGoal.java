package unsw.dungeon;

public class SwitchesGoal extends BasicGoal {

    private int switchesSpawned;
    private int switchesTriggered;

    public SwitchesGoal(int switchesSpawned) {
        this.switchesSpawned = switchesSpawned;
        this.switchesTriggered = 0;
    }

    @Override
    public boolean isCompleted() {
        // TODO Auto-generated method stub
        return super.isCompleted();
    }
}
