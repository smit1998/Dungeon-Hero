package unsw.dungeon;

/**
 * Subject interface - 
 */
public interface Subject {
    /**
     * attach the observer to the subject
     * @param o observer to be attached
     */
    public void attach(Observer o);

    /**
     * detach an observer from the subject
     * @param o observer to be removed
     */
    public void detach(Observer o);

    /**
     * notifies all observers of a change to the subject
     */
    public void notifyObservers();
}
