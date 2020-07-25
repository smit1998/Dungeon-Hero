
package unsw.dungeon;

import java.util.ArrayList;

public class MeleeKillEffect implements EffectBehaviour {

    private Dungeon(); 

    public MeleeKillEffect(Dungeon dungeon) {
        Weapon weapon = new Weapon(); 
    }

    @Override
    public void startEffect() {
        for (Enemy e : enemies) {
            e.updateFear(true); 
        }
    }

    @Override
    public void stopEffect() {
        for (Enemy e : enemies) {
            e.updateFear(false); 
        }
    }
    
}