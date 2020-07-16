package unsw.dungeon;

public class Enemy {

    private Dungeon dungeon; 
    private Boolean isAlive; 

    public Enemy(int x, int y, Dungeon dungeon, Boolean isAlive) {
        super(x, y, dungeon); 
        this.isAlive = true; 
    }

    public void attack(Entity entity) {
        // todo
    }

    public void updateLifeStatus() {
        // todo 
    }
}