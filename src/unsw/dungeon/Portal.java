package unsw.dungeon;

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
}