package unsw.dungeon;

import java.util.List;
import java.util.Set;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.ArrayList;

/**
 * The player entity
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Player extends LifeEntity {

    private BooleanProperty isAlive = new SimpleBooleanProperty(true);
    private int livesRemaining = 1;
    private Checkpoint checkpoint;

    private int ticksSinceUp = 0;
    private int ticksSinceDown = 0;
    private int ticksSinceLeft = 0;
    private int ticksSinceRight = 0;
    private int ticks_per_move = 5;

    private Inventory inventory;

    private List<PlayerObserver> playerObservers = new ArrayList<>();

    /**
     * Create a player positioned in square (x,y) in the dungeon
     * 
     * @param dungeon the dungeon this player belongs to
     * @param x       coordinate where the player spawns at
     * @param y       coordinate where the player spawns at
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y, dungeon);
        this.inventory = new Inventory();
    }

    /**
     * Pickup an item e
     * 
     * @return the item that has been picked up. if the item is not picked up, due
     *         to the player not being able to, null is returned otherwise, the item
     *         is added to the inventory
     */
    public boolean pickupItem(ItemEntity e) {
        inventory.addItem(e);
        return true;
    }

    /**
     * @return a Key item if the player is holding one
     */
    public Key getKey() {
        return inventory.getKey();
    }

    /**
     * Attack the given entity using a weapon
     * 
     * @return whether the attack was successful
     */
    public boolean attack(LifeEntity e) {
        Weapon weapon = getWeapon();
        if (weapon != null) {
            weapon.attack(e);
            return true;
        }
        return false;
    }

    /**
     * @return a Weapon with the highest priority in the players inventory
     */
    public Weapon getWeapon() {
        return inventory.getWeapon();
    }

    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Interact with this entity
     * 
     * @param caller - the entity who wants to interact with the player
     * @return - true if caller was able to interact with the player otherwise false
     */
    public boolean interact(Entity caller) {
        if (caller.getClass() == Enemy.class) {
            return interact((Enemy) caller);
        }
        return false;
    }

    private boolean interact(Enemy enemy) {
        Weapon weapon = inventory.getWeapon();
        if (weapon != null) { // player has weapon, player kills enemy
            weapon.attack(enemy);
            return false;
        } else if (this.isMortal() && enemy.isAlive()) { // player is mortal, enemy kills player
            enemy.attack(this);
            return true;
        } else { // player is immortal, enemy cannot kill player
            return false;
        }
    }

    @Override
    public void tick(Dungeon dungeon) {
        inventory.tick(dungeon);
        ticksSinceUp++;
        ticksSinceDown++;
        ticksSinceLeft++;
        ticksSinceRight++;
    }

    public void tick(Set<String> input) {
        if (input.contains("LEFT") && ticksSinceLeft > ticks_per_move) {
            ticksSinceLeft = 0;
            moveLeft();
        }
        if (input.contains("RIGHT") && ticksSinceRight > ticks_per_move) {
            ticksSinceRight = 0;
            moveRight();
        }
        if (input.contains("UP") && ticksSinceUp > ticks_per_move) {
            ticksSinceUp = 0;
            moveUp();
        }
        if (input.contains("DOWN") && ticksSinceDown > ticks_per_move) {
            ticksSinceDown = 0;
            moveDown();
        }
    }

    /**
     * Move upwards in the dungeon if possible
     */
    @Override
    public void moveUp() {
        super.moveUp();
        notifyObservers();
    }

    /**
     * Move downwards in the dungeon if possible
     */
    @Override
    public void moveDown() {
        super.moveDown();
        notifyObservers();
    }

    /**
     * Move leftwards in the dungeon if possible
     */
    @Override
    public void moveLeft() {
        super.moveLeft();
        notifyObservers();
    }

    /**
     * Move rightwards in the dungeon if possible
     */
    @Override
    public void moveRight() {
        super.moveRight();
        notifyObservers();
    }

    public void addPlayerObserver(PlayerObserver o) {
        playerObservers.add(o);
    }

    public void notifyObservers() {
        for (PlayerObserver o : playerObservers) {
            o.update(getX(), getY());
        }
    }

    @Override
    public void kill() {
        if (livesRemaining > 1 && checkpoint != null) {
            livesRemaining--;
            setX(checkpoint.getX());
            setY(checkpoint.getY());
        } else {
            super.kill();
            isAlive.setValue(false);
        }
    }

    /**
     * Get whether the entity is alive
     * 
     * @return whether this entity is alive
     */
    @Override
    public boolean getLifeStatus() {
        return isAlive.getValue();
    }

    public BooleanProperty isAlive() {
        return isAlive;
    }

    public void setSpeed(int ticksPerMove) {
        ticks_per_move = ticksPerMove;
    }

    public int getSpeed() {
        return ticks_per_move;
    }

    public void removeItem(ItemEntity item) {
        inventory.removeItem(item);
    }

    public int getLives() {
        return livesRemaining;
    }

    public void increaseLives() {
        livesRemaining++;
    }

    public void setCheckpoint(Checkpoint checkpoint) {
        this.checkpoint = checkpoint;
    }

    @Override
    public boolean canCollide(Entity entity) {
        return true;
    }
}
