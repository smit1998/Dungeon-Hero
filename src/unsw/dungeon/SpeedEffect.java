package unsw.dungeon;

public class SpeedEffect implements EffectBehaviour {

    LifeEntity subject; 

    public SpeedEffect(Dungeon dungeon) {
        subject = dungeon.getPlayer(); 
    }

    @Override
    public void startEffect() {
    }

    @Override
    public void stopEffect() {
    }
    
}