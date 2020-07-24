package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.collections.FXCollections;

public class DungeonMenuController {

    @FXML
    private ListView<DungeonMenuItem> listview;

    @FXML
    private Button play_btn;

    private DungeonMenu menu;
    private ObservableList<DungeonMenuItem> observableList;

    public DungeonMenuController() throws FileNotFoundException {
        menu = new DungeonMenu();
        observableList = FXCollections.observableList(menu.getMenu());
    }

    @FXML
    public void initialize() {
        listview.setItems(observableList);
        listview.getSelectionModel().selectFirst();
    }

    @FXML
    public void handlePlay(ActionEvent event) {
        play();
    }

    @FXML
    public void handleListKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case ENTER:
                play();
                break;

            default:
                break;
        }
    }

    @FXML
    public void handlePlayKeyPress(KeyEvent event) {
        int selectionIndex = listview.getSelectionModel().getSelectedIndex();
        switch (event.getCode()) {
            case UP:
                if (selectionIndex > 0) {
                    listview.getSelectionModel().selectPrevious();
                }
                break;
            case DOWN:
                if (selectionIndex < menu.size() - 1) {
                    listview.getSelectionModel().selectNext();
                }
                break;
            case ENTER:
                play();
                break;
            default:
                break;
        }
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        listview.requestFocus();

    }

    private void play() {
        DungeonMenuItem selection = listview.getSelectionModel().getSelectedItem();
        if (selection == null) {
            System.out.println("No dungeon selected");
            return;
            // TODO Display message
        }
        System.out.println(selection);

        try {
            DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(selection.getDungeonFile());

            DungeonController controller = dungeonLoader.loadController();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            root.requestFocus();
            Stage stage = new Stage();
            stage.setScene(scene);
            prevStage.close();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Stage prevStage;

    public void setPrevStage(Stage stage) {
        this.prevStage = stage;
    }

}