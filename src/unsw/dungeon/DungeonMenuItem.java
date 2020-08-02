package unsw.dungeon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class DungeonMenuItem {

    private String dungeonName;
    private File dungeonFile;
    private DungeonDifficulty difficulty;

    public DungeonMenuItem(File dungeonFile) throws FileNotFoundException {
        this.dungeonFile = dungeonFile;

        JSONObject json = new JSONObject(new JSONTokener(new FileReader(dungeonFile)));
        try {
            dungeonName = json.getString("name");
        } catch (JSONException e) {
            dungeonName = formatDungeonName(dungeonFile.getName());
        }

        try {
            difficulty = DungeonDifficulty.get(json.getString("difficulty"));
        } catch (JSONException e) {
            difficulty = DungeonDifficulty.EASY;
        }
    }

    private static String formatDungeonName(String filename) {
        int extensionIndex = filename.lastIndexOf('.');
        String basename = filename.substring(0, extensionIndex);
        return capitalizeWords(basename.replaceAll("[^a-zA-Z0-9]+", " "));
    }

    private static String capitalizeWords(String str) {
        String[] words = str.split("\\s+");
        String[] capitalizedWords = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            capitalizedWords[i] = capitalize(words[i]);
        }
        String capitalized = String.join(" ", capitalizedWords);
        return capitalized;

    }

    private static String capitalize(String str) {
        // https://attacomsian.com/blog/capitalize-first-letter-of-string-java
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public File getDungeonFile() {
        return dungeonFile;
    }

    public String getDungeonName() {
        return dungeonName;
    }

    public String getDungeonFilename() {
        return dungeonFile.getName();
    }

    @Override
    public String toString() {
        return dungeonName;
    }

    public DungeonDifficulty getDifficulty() {
        return difficulty;
    }

}