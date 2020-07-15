package unsw.dungeon;

import java.util.ArrayList;

public abstract class ComplexGoal implements ComponentGoal {
    private ArrayList<ComponentGoal> goals;

    public ComplexGoal() {
        this.goals = new ArrayList<ComponentGoal>();
    }

    public abstract boolean isComplete();
}