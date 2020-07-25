package unsw.dungeon;

/**
 * Interface for a weapon, used for items that can be used to attack enemies
 */
public class Weapon extends ItemEntity {

    private AttackBehaviour attackBehaviour; 
    private int hitsRemaining;  

    public Weapon(int x, int y, Dungeon dungeon, AttackBehaviour attackBehaviour, int hitsRemaining) {
        super(x, y, dungeon); 
        this.attackBehavior = attackBehaviour;  
    }

    /**
     * attacks an enemy, with unique interaction depending on the weapon used
     * 
     * @param the enemy to be attacked
     */
    public void attack(LifeEntity e) {
        attackBehaviour.attack(e); 
    }
}