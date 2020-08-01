package main.java.unsw.dungeon.entities;

;

/**
 * interface of a unique potion effect
 */
public interface EffectBehaviour {
    /**
     * starts the effect
     */
    public void startEffect();

    /**
     * ends the effect
     */
    public void stopEffect();
}