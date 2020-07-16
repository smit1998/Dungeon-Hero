package unsw.dungeon;

import java.util.ArrayList;

public class AndCompositeGoal extends ComplexGoal {

    private ArrayList<ComponentGoal> goals; 

    public AndCompositeGoal() {
        super(); 
    }
    
    // method forwarding 
    @Override 
    public void addGoal(ComponentGoal goal) {
        super.addGoal(goal);
    } 

    public boolean isComplete() {
        for (ComponentGoal goal : goals) {
            if (goal.isComplete() == false) { 
                return false; 
            }
        }
        return true;
    }
}