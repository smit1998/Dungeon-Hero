package unsw.dungeon;

import java.util.ArrayList;

/**
 * effect that causes enemies to be feared
 */
public class FearEffect implements EffectBehaviour {

    private ArrayList<Enemy> enemies; 

    /**
     * constructor that sets the dungeon
     */
    public FearEffect(Dungeon dungeon) {
        enemies = dungeon.getEnemies(); 
    }

    /**
     * start fearing the enemies in the dungeon
     */
    @Override
    public void startEffect() {
        for (Enemy e : enemies) {
            e.updateFear(true); 
        }
    }

    /**
     * enemies of the dungeon no longer feared
     */
    @Override
    public void stopEffect() {
        for (Enemy e : enemies) {
            e.updateFear(false); 
        }
    }
    
}