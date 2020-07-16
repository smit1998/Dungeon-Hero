package unsw.dungeon;

public class SwitchesGoal extends BasicGoal {

    private int switchesSpawned;
    private int switchesTriggered;

    public SwitchesGoal(int switchesSpawned) {
        this.switchesSpawned = switchesSpawned;
        this.switchesTriggered = 0;
    }

    @Override
<<<<<<< HEAD
    public boolean isCompleted() {
=======
    public boolean isComplete() {
>>>>>>> UML_implementation
        // TODO Auto-generated method stub
        return super.isComplete();
    }
}
