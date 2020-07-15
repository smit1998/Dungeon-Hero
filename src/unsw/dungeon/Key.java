package unsw.dungeon;

<<<<<<< HEAD
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Key extends Entity{
    private String ID;
    private int maxPickedUp;
    
    public Key(String ID) {
        this.ID = ID;
        this.maxPickedUp = 1;
    }

    public String getID() {
        return this.ID;
    }
=======
public class Key extends Entity {

    public final static int MAX_PICKUP = 1;

    private String id;

    public Key(int x, int y, Dungeon dungeon, String id) {
        super(x, y, dungeon);
        this.id = id;
    }

    public String getID() {
        return id;
    }

>>>>>>> 6dcb0b82110ae0e77b9419f584f621145e491b80
}