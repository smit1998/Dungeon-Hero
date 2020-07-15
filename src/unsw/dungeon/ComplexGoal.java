package unsw.dungeon;

public class ComplexGoal implements ComponentGoal {
    private ArrayList<ComponentGoal> goals; 

    public ComplexGoal() {
        this.goals = new ArrayList<ComponentGoal>(); 
    }
}