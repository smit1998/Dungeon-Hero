package unsw.dungeon;

public class Mine extends Entity {

    private boolean isActive = true;

    public Mine(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
    }

    @Override
    public boolean interact(Entity caller) {
        if (caller instanceof Player) {
            return interact((Player) caller);
        } else if (caller instanceof Enemy) {
            return interact((Enemy) caller);
        }
        return false;
    }

    public boolean interact(Player player) {
        if (this.isActive == true) {
            player.kill();
            return true;
        }
        return false;
    }

    public boolean interact(Enemy enemy) {
        if (this.isActive == true) {
            enemy.kill();
            return true;
        }
        return false;
    }

    @Override
    public void tick(Dungeon dungeon) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean canCollide(Entity entity) {
        return true;
    }

}