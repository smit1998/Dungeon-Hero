package unsw.dungeon;

import java.util.List;
import java.util.ArrayList;

public class OrCompositeGoal extends ComplexGoal {

    public OrCompositeGoal() {
        super();
    }

    public OrCompositeGoal(List<ComponentGoal> goals) {
        super(goals);
    }

    public boolean isComplete() {
        for (ComponentGoal goal : getGoals()) {
            if (goal.isComplete() == true) {
                return true;
            }
        }
        return false;
    }

}