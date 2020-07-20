package unsw.dungeon;

/**
 * Observer interface To be bound to subjects, and observes changes to subject
 */
public interface Observer {
    /**
     * Update this object based off the object being parsed through (push from
     * subject)
     * 
     * @param obj object to be used to make updates
     */
    public void update(Subject obj);
}