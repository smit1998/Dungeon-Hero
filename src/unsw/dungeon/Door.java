package unsw.dungeon;

<<<<<<< HEAD
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Door extends Entity{
    private String ID;
    private boolean isOpen;

    public Door(String ID) {
        this.ID = ID;
        this.isOpen = false;
    }

    public String getID(){
        return this.ID;
    }

    public boolean getIsOpen() {
        return this.isOpen;
    }

    public void updateIsOpen(boolean newIsOpen) {
        this.isOpen = newIsOpen;
    }
=======
public class Door extends Entity {

    private String id;
    private boolean isOpen;

    public Door(int x, int y, Dungeon dungeon, String id) {
        super(x, y, dungeon);
        this.id = id;
    }

    public String getID() {
        return id;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public boolean open(Key key) {
        return false;
    }

>>>>>>> 6dcb0b82110ae0e77b9419f584f621145e491b80
}