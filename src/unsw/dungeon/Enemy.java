package unsw.dungeon;

public class Enemy extends Entity {

    private Dungeon dungeon; 
    private Boolean isAlive; 

    public Enemy(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        this.isAlive = true;
    }

    public void attack(Entity entity) {
    }

    public void updateLifeStatus() {
        // todo
    }

    public boolean getIsAlive() {
        return this.isAlive; 
    }
}