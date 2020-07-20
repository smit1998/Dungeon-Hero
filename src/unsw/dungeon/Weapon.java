package unsw.dungeon;

/**
 * Interface for a weapon, used for items that can be used to attack enemies
 */
public interface Weapon {
    /**
     * @return the priority of a weapon, a number from 0 - Integer.MAX_VALUE the
     *         higher the number the higher the priority of the weapon
     */
    public int getPriority();

    /**
     * attacks an enemy, with unique interaction depending on the weapon used
     * 
     * @param the enemy to be attacked
     */
    public void attack(LifeEntity e);
}