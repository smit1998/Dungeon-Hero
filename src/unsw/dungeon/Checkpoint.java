package unsw.dungeon;

public class Checkpoint extends Entity {

    private boolean isUsed = false;

    public Checkpoint(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
    }

    @Override
    public boolean interact(Entity caller) {
        if (caller instanceof Player) {
            return interact((Player) caller);
        }
        return true;
    }

    public boolean interact(Player player) {
        if (!isUsed) {
            isUsed = true;
            player.increaseLives();
            player.setCheckpoint(this);
        }
        return true;
    }

    @Override
    public void tick(Dungeon dungeon) {
    }

    @Override
    public boolean canCollide(Entity entity) {
        return true;
    }

}