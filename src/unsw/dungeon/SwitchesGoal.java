package unsw.dungeon;

public class SwitchesGoal extends BasicGoal {

    private int switchesSpawned;
    private int switchesTriggered;

    public SwitchesGoal(int switchesSpawned) {
        this.switchesSpawned = switchesSpawned;
        this.switchesTriggered = 0;
    }

    @Override
    public boolean isComplete() {
        return (switchesSpawned == switchesTriggered);
    }

    @Override
    public void update(Subject obj) {
        if (obj instanceof FloorSwitch) {
            FloorSwitch s = (FloorSwitch) obj;
            if (s.isPressed() == true) {
                switchesTriggered += 1;
            } else if (switchesTriggered > 0) {
                switchesTriggered -= 1;
            }
        }
    }
}
