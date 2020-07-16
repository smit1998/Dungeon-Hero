package unsw.dungeon;

import java.util.ArrayList;

public class OrCompositeGoal extends ComplexGoal{

    private ArrayList<ComponentGoal> goals; 
   
    public OrCompositeGoal() {
        super();
    }

    // method forwarding 
    @Override 
    public void addGoal(ComponentGoal goal) {
        super.addGoal(goal);
    } 

    public boolean isComplete() {
        for (ComponentGoal goal : goals) {
            if (goal.isComplete() == true) { 
                return true; 
            }
        }
        return false; 
    }
}