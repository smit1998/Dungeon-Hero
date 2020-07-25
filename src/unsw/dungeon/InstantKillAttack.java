package unsw.dungeon; 

public class InstantKillAttack implements AttackBehaviour {
    public void attack(LifeEntity lifeEntity) {
        lifeEntity.kill(); 
    }     
}