
package unsw.dungeon;

public class SpeedUpEffect implements EffectBehaviour {

    LifeEntity subject; 

    public SpeedUpEffect(Dungeon dungeon) {
        subject = dungeon.getPlayer(); 
    }

    @Override
    public void startEffect() {
    }

    @Override
    public void stopEffect() {
    }
    
}