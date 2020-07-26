
package unsw.dungeon;

public class SpeedUpEffect implements EffectBehaviour {

    Player subject; 
    int originalSpeed; 

    public SpeedUpEffect(Dungeon dungeon) {
        subject = dungeon.getPlayer(); 
        originalSpeed = subject.getSpeed(); 
    }

    @Override
    public void startEffect() {
        subject.setSpeed(2);
    }

    @Override
    public void stopEffect() {
        subject.setSpeed(originalSpeed); 
    }
    
}