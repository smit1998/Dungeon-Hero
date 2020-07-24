package unsw.dungeon;

import java.io.FileNotFoundException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;

public class DungeonMenuController {

    @FXML
    private ListView<DungeonMenuItem> listview;
    private DungeonMenu menu;
    private ObservableList<DungeonMenuItem> observableList;
    // ObservableList<String> names = FXCollections.observableArrayList("Mary",
    // "Bob", "sam");

    public DungeonMenuController() throws FileNotFoundException {
        menu = new DungeonMenu();
        observableList = FXCollections.observableList(menu.getMenu());
    }

    @FXML
    public void initialize() {
        listview.setItems(observableList);
    }
}