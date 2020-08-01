package main.java.unsw.dungeon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class DungeonMenu {

    private final static String DUNGEON_PATH = "dungeons/";

    private List<DungeonMenuItem> menuItems = new ArrayList<>();

    public DungeonMenu() throws FileNotFoundException {
        File[] dungeonFiles = getDungeonFiles();
        loadMenu(dungeonFiles);
    }

    private File[] getDungeonFiles() {
        // https://stackabuse.com/java-list-files-in-a-directory/

        File f = new File(DUNGEON_PATH);

        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File f, String name) {
                return name.endsWith(".json");
            }
        };

        return f.listFiles(filter);
    }

    private void loadMenu(File[] dungeonFiles) throws FileNotFoundException {
        for (File file : dungeonFiles) {
            menuItems.add(new DungeonMenuItem(file));
        }
    }

    public List<DungeonMenuItem> getMenu() {
        return new ArrayList<>(menuItems);
    }

    public int size() {
        return menuItems.size();
    }

}