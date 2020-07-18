package unsw.dungeon;
public class Door extends Entity {

    private int id;
    private boolean isOpen;

    public Door(int x, int y, Dungeon dungeon, int id) {
        super(x, y, dungeon);
        this.id = id;
        this.isOpen = false;
    }

    public int getID() {
        return id;
    }
    // returns true if door is open and false otherwise.
    public boolean isOpen() {
        return this.isOpen;
    }
    // opens the door if the given key is valid
    public boolean open(Key key) {
        if(key.getID() == this.id) {
            this.isOpen = true;
            return true;
        }
        return false;
    }

    public boolean interact(Entity caller) {
        if(caller instanceof Key && ()) {
            
        }
    }
}