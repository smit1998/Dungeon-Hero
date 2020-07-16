package unsw.dungeon;

import java.util.ArrayList;

public class AndCompositeGoal extends ComplexGoal {

    public AndCompositeGoal() {
        super();
    }
    
    @Override 
    public void addGoal(ComponentGoal goal) {
        super.addGoal(goal);
    } 

    public boolean isComplete() {
        for (ComponentGoal goal : getGoals()) {
            if (goal.isComplete() == false) { 
                return false; 
            }
        }
        return true;
    }

    @Override
    public ArrayList<ComponentGoal> getGoals() {
        return super.getGoals(); 
    }
}