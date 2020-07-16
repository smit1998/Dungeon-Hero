package unsw.dungeon;

import java.util.ArrayList;

public class ComplexGoal implements ComponentGoal {

    private ArrayList<ComponentGoal> goals; 

    public ComplexGoal() {
        this.goals = new ArrayList<ComponentGoal>();
    }
    public void addGoal(ComponentGoal goal) {
        goals.add(goal); 
    }

    public ArrayList<ComponentGoal> getGoals() {
        return goals;
    }

    @Override 
    public boolean isComplete() {
        return false; 
    }
}