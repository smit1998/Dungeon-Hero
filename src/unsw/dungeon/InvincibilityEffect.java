package unsw.dungeon;

public class InvincibilityEffect implements EffectBehaviour {

    LifeEntity subject; 

    @Override
    public void startEffect() {
        // set the player mortality to false - player is immortal
        subject.setMortality(false);
    }

    @Override
    public void stopEffect() {
        // player no longer immortal
        subject.setMortality(true); 
    }
    
}