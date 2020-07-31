package unsw.dungeon; 

/**
 * attack behaviour that instantly kills a life entity
 */
public class InstantKillAttack implements AttackBehaviour {
    /**
     * kills a life entity
     */
    public void attack(LifeEntity lifeEntity) {
        lifeEntity.kill(); 
    }     
}