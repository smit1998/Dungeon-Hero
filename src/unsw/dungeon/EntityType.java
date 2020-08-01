package unsw.dungeon;

import java.util.HashMap;
import java.util.Map;

public enum EntityType {

    PLAYER("player"), WALL("wall"), EXIT("exit"), TREASURE("treasure"), DOOR("door"), KEY("key"), BOULDER("boulder"),
    SWITCH("switch"), PORTAL("portal"), ENEMY("enemy"), SWORD("sword"), INVINCIBILITY("invincibility"), SPEED("speed"),
    DAGGER("dagger");

    private String type;

    private EntityType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    // ****** Reverse Lookup Implementation************//
    // https://howtodoinjava.com/java/enum/java-enum-string-example/

    // Lookup table
    private static final Map<String, EntityType> lookup = new HashMap<>();

    // Populate the lookup table on loading time
    static {
        for (EntityType type : EntityType.values()) {
            lookup.put(type.getType(), type);
        }
    }

    // This method can be used for reverse lookup purpose
    public static EntityType get(String type) {
        return lookup.get(type);
    }
}