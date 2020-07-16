package unsw.dungeon;

import java.util.List;

public class AndCompositeGoal extends ComplexGoal {

    public AndCompositeGoal() {
        super();
    }

    public AndCompositeGoal(List<ComponentGoal> goals) {
        super(goals);
    }

    public boolean isComplete() {
        for (ComponentGoal goal : getGoals()) {
            if (goal.isComplete() == false) {
                return false;
            }
        }
        return true;
    }

}