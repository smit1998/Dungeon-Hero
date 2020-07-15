package unsw.dungeon;

public class Enemy extends Entity {
    private Boolean isAlive;

    public Enemy(int x, int y, Dungeon dungeon) {
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