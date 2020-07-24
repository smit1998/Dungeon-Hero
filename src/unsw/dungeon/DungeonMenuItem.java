package unsw.dungeon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;

public class DungeonMenuItem {

    private String dungeonName;
    private File dungeonFile;

    public DungeonMenuItem(File dungeonFile) throws FileNotFoundException {
        // FileReader file = new FileReader(dungeonFile);
        this.dungeonFile = dungeonFile;

        JSONObject json = new JSONObject(new JSONTokener(new FileReader(dungeonFile)));
        dungeonName = json.getString("name");

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

}