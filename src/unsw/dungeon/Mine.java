package unsw.dungeon;

public class Mine extends Entity{

    private boolean isActive = true;

    public Mine(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
    }

    @Override
    public boolean interact(Entity caller) {
        if(caller.getClass() == Player.class ) {
            return interact((Player) caller);
        }
        else if (caller.getClass() == Enemy.class){
            return interact((Enemy) caller);
        }
        return false;
    }

    private boolean interact(Player player) {
        if(this.isActive == true) {
            player.kill();
            return true;
        }
        return false;
    }

    private boolean interact(Enemy enemy) {
        if(this.isActive == true) {
            enemy.kill();
            return true;
        }
        return false;
    }

    @Override
    public void tick(Dungeon dungeon) {
    }
    
}