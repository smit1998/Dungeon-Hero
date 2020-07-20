package unsw.dungeon;

/**
 * Observer interface
 * To be binded to subjects, and observes changes to subject
 */
public interface Observer {
    /**
     * method to update the current class based off the object being parsed through (push from subject)
     * @param obj object to be used to make updates
     */
    public void update(Subject obj);
}