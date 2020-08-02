package unsw.dungeon;

import java.util.HashMap;
import java.util.Map;

public enum GoalType {
    ENEMIES_GOAL("enemies"), SWITCHES_GOAL("boulders"), TREASURE_GOAL("treasure"), EXIT_GOAL("exit"), AND_GOAL("AND"),
    OR_GOAL("OR");

    private String type;

    private GoalType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    // ****** Reverse Lookup Implementation************//
    // https://howtodoinjava.com/java/enum/java-enum-string-example/

    // Lookup table
    private static final Map<String, GoalType> lookup = new HashMap<>();

    // Populate the lookup table on loading time
    static {
        for (GoalType type : GoalType.values()) {
            lookup.put(type.getType(), type);
        }
    }

    // This method can be used for reverse lookup purpose
    public static GoalType get(String type) {
        return lookup.get(type);
    }

    public boolean isComplex() {
        return this == AND_GOAL || this == OR_GOAL;
    }
}