package unsw.dungeon;

import java.util.HashMap;
import java.util.Map;

public enum DungeonDifficulty {
    EASY("easy"), MEDIUM("medium"), HARD("hard");

    private String string;

    private DungeonDifficulty(String difficulty) {
        this.string = difficulty;
    }

    // ****** Reverse Lookup Implementation************//
    // https://howtodoinjava.com/java/enum/java-enum-string-example/

    // Lookup table
    private static final Map<String, DungeonDifficulty> lookup = new HashMap<>();

    // Populate the lookup table on loading time
    static {
        for (DungeonDifficulty difficulty : DungeonDifficulty.values()) {
            lookup.put(difficulty.toString(), difficulty);
        }
    }

    // This method can be used for reverse lookup purpose
    public static DungeonDifficulty get(String difficulty) {
        return lookup.get(difficulty);
    }

    @Override
    public String toString() {
        return string;
    }

}