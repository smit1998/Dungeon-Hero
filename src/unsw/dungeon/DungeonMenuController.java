package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.collections.FXCollections;

public class DungeonMenuController implements Controller {

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
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                listview.requestFocus();
            }
        });

    }

    @FXML
    public void handlePlay(ActionEvent event) throws IOException {
        play(event);
    }

    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        System.out.println("Going back to main menu");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MainMenuController controller = new MainMenuController();
        stage.setScene(controller.getScene());
        stage.show();
    }

    @FXML
    public void handleListKeyPress(KeyEvent event) throws IOException {
        switch (event.getCode()) {
            case ENTER:
                play(event);
                break;

            default:
                break;
        }
    }

    @FXML
    public void handlePlayKeyPress(KeyEvent event) throws IOException {
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
                play(event);
                break;
            default:
                break;
        }
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        listview.requestFocus();

    }

    private void play(Event event) throws IOException {
        DungeonMenuItem selection = listview.getSelectionModel().getSelectedItem();
        System.out.println("Loading map: " + selection + "...");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(selection.getDungeonFile());
        DungeonController controller = dungeonLoader.loadController();
        stage.setScene(controller.getScene());
        stage.show();
    }

    public Scene getScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonMenuView.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        return scene;
    }

}