package unsw.dungeon;

public abstract class BasicGoal implements ComponentGoal, Observer {
    @Override
    public abstract boolean isComplete(); 
    @Override
    public void update(Subject obj){};  
}