package unsw.dungeon;

import java.util.ArrayList;

public class FearEffect implements EffectBehaviour {

    private ArrayList<Enemy> enemies; 

    public FearEffect(Dungeon dungeon) {
        enemies = dungeon.getEnemies(); 
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