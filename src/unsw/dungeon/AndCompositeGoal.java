package unsw.dungeon;

import java.util.ArrayList;

public class AndCompositeGoal implements ComponentGoal {
   
    private ArrayList<ComponentGoal> goals; 

    public AndCompositeGoal() {
        super(); 
    }
    
    public boolean isComplete() {

    }
}