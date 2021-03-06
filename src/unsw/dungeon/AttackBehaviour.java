package unsw.dungeon;

/** 
*   interface that represents a unique attack behaviour
*/
public interface AttackBehaviour {
    /**
     * method that can be called to attack a life entity
     */
    public void attack(LifeEntity lifeEntity);     
}