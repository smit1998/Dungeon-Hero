package unsw.dungeon;

import java.util.HashSet;
import java.util.Set;

/**
 * A floor switch entity that can be activated by a boulder
 */
public class FloorSwitch extends Entity implements Subject {

    private boolean isPressed;
    private Set<Observer> observers = new HashSet<Observer>();

    /**
     * Constructs a new floor switch entity at (x,y) in the dungeon
     * 
     * @param x       the horizontal position
     * @param y       the vertical position
     * @param dungeon the dungeon the switch belongs to
     */
    public FloorSwitch(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        this.isPressed = false;
    }

    /**
     * Return whether this switch is activated by an boulder
     * 
     * @return whether this switch is pressed down
     */
    public boolean isPressed() {
        return this.isPressed;
    }

    /**
     * Update the isPressed property of this switch
     * 
     * @param newIsPressed whether the switch will be activated
     */
    public void updateIsPressed(boolean newIsPressed) {
        if (newIsPressed != isPressed) {
            isPressed = newIsPressed;
            notifyObservers();
        }
    }

    /**
     * Interact with this entity. If the caller is a boulder, this switch will be
     * pressed down.
     * 
     * @param caller the calling entity
     * @return whether the interaction was successful
     */
    public boolean interact(Entity caller) {
        if (caller.getClass() == Boulder.class) {
            return interact((Boulder) caller);
        } else {
            updateIsPressed(false);
        }
        return true;
    }

    private boolean interact(Boulder boulder) {
        updateIsPressed(true);
        return true;
    }

    /**
     * no changes when game state updates
     */
    @Override
    public void tick(Dungeon dungeon) {
    }

    @Override
    public boolean canCollide(Entity entity) {
        return true;
    }

    /**
     * Attach the observer to the subject
     * 
     * @param o observer to be attached
     */
    public void attach(Observer o) {
        observers.add(o);
    }

    /**
     * Detach an observer from the subject
     * 
     * @param o observer to be removed
     */
    public void detach(Observer o) {
        observers.remove(o);
    }

    /**
     * Notifies all observers of a change to the subject
     */
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}