package unsw.dungeon;

public class Mine extends Entity {

    private boolean isActive = true;

    public Mine(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
    }

    @Override
    public boolean interact(Entity caller) {
<<<<<<< HEAD
        if(caller.getClass() == Player.class ) {
            return interact((Player) caller);
        }
        else if (caller.getClass() == Enemy.class){
=======
        if (caller instanceof Player) {
            return interact((Player) caller);
        } else if (caller instanceof Enemy) {
>>>>>>> Milestone3
            return interact((Enemy) caller);
        }
        return false;
    }

<<<<<<< HEAD
    private boolean interact(Player player) {
        if(this.isActive == true) {
=======
    public boolean interact(Player player) {
        if (this.isActive == true) {
>>>>>>> Milestone3
            player.kill();
            return true;
        }
        return false;
    }

<<<<<<< HEAD
    private boolean interact(Enemy enemy) {
        if(this.isActive == true) {
=======
    public boolean interact(Enemy enemy) {
        if (this.isActive == true) {
>>>>>>> Milestone3
            enemy.kill();
            return true;
        }
        return false;
    }

    @Override
    public void tick(Dungeon dungeon) {
    }

    @Override
    public boolean canCollide(Entity entity) {
        return true;
    }

}