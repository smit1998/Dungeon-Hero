package unsw.dungeon;

public interface Weapon {
    public void updateHitsRemaining(); 
    public int getRemainingHits(); 
    public int getPriority(); 
    public void attack(Enemy e); 
}