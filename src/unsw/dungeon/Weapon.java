package unsw.dungeon;

/**
 * Interface for a weapon, used for items that can be used to attack enemies
 */
public class Weapon extends ItemEntity {

    private AttackBehaviour attackBehaviour; 
    private int hits;  
    private Inventory inventory; 

    public Weapon(int x, int y, Dungeon dungeon, AttackBehaviour attackBehaviour, int hits) {
        super(x, y, dungeon); 
        this.attackBehaviour = attackBehaviour;  
        this.hits= hits; 
    }

    /**
     * attacks an enemy, with unique interaction depending on the weapon used
     * 
     * @param the enemy to be attacked
     */
    public void attack(LifeEntity e) {
        attackBehaviour.attack(e); 
        decrementHits(); 
    }

    public void attachInventory(Inventory inventory) {
        this.inventory = inventory; 
    }

    private void decrementHits() {
        hits--; 
        if (hits == 0) {
            deleteWeapon(); 
        }
    }

    private void deleteWeapon() {
        inventory.removeItem(this); 
    }


}