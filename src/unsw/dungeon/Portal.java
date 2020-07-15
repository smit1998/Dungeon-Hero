package unsw.dungeon;

<<<<<<< HEAD
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Pair;

public class Portal extends Entity{
    private String ID;
    private Pair portalPair; // A bit doubtful

    public Portal(String ID, pair portalPair) {
        this.ID = ID;
        this.portalPair = portalPair;
    }

    public Portal getPair() {
        return null;
        // TODO
    }
    
    public String getID() {
        return this.ID;
    }

    public boolean addPair(Pair newPortalPair) {
        return false;
        // TODO
    }
=======
public class Portal extends Entity {

    private String id;
    private Portal pair;

    public Portal(int x, int y, Dungeon dungeon, String id) {
        super(x, y, dungeon);
        this.id = id;
    }

    public boolean addPair(Portal pair) {
        // TODO Check not itself
        // Check no pair
        this.pair = pair;
        return false;
    }

    public Portal getPair() {
        return pair;
    }

    public String getID() {
        return id;
    }

>>>>>>> 6dcb0b82110ae0e77b9419f584f621145e491b80
}