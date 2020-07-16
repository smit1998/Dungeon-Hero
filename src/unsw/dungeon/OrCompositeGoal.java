package unsw.dungeon;

import java.util.ArrayList;

public class OrCompositeGoal extends ComplexGoal{

    public OrCompositeGoal() {
        super();
    }

    @Override 
    public void addGoal(ComponentGoal goal) {
        super.addGoal(goal);
    } 

    public boolean isComplete() {
        for (ComponentGoal goal : getGoals()) {
            if (goal.isComplete() == true) { 
                return true; 
            }
        }
        return false; 
    }

    @Override
    public ArrayList<ComponentGoal> getGoals() {
        return super.getGoals(); 
    }
}