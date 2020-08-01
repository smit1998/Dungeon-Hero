package main.java.unsw.dungeon;

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
    private Button play_button, back_button;

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
    public void handlePlay(Event event) throws IOException {
        DungeonMenuItem selection = listview.getSelectionModel().getSelectedItem();
        System.out.println("Loading map: " + selection + "...");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(selection.getDungeonFile());
        DungeonController controller = dungeonLoader.loadController();
        stage.setScene(controller.getScene());
        stage.show();
    }

    @FXML
    private void handleBack(Event event) throws IOException {
        System.out.println("Going back to main menu");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MainMenuController controller = new MainMenuController();
        stage.setScene(controller.getScene());
        stage.show();
    }

    @FXML
    public void handleListKeyPress(KeyEvent event) throws IOException {
        switch (event.getCode()) {
            case W:
                selectPrevious();
                break;

            case S:
                selectNext();
                break;

            case LEFT:
            case A:
                back_button.requestFocus();
                break;

            case RIGHT:
            case D:
                play_button.requestFocus();
                break;

            case ENTER:
                handlePlay(event);
                break;

            case ESCAPE:
                handleBack(event);
                break;

            default:
                break;
        }
    }

    @FXML
    public void handlePlayKeyPress(KeyEvent event) throws IOException {
        switch (event.getCode()) {
            case W:
            case UP:
                listview.requestFocus();
                selectPrevious();
                break;

            case S:
            case DOWN:
                listview.requestFocus();
                selectNext();
                break;

            case LEFT:
            case A:
                back_button.requestFocus();
                break;

            case ENTER:
                handlePlay(event);
                break;

            case ESCAPE:
                handleBack(event);
                break;

            default:
                break;
        }
    }

    @FXML
    public void handleBackKeyPress(KeyEvent event) throws IOException {
        switch (event.getCode()) {
            case W:
            case UP:
                listview.requestFocus();
                selectPrevious();
                break;

            case S:
            case DOWN:
                listview.requestFocus();
                selectNext();
                break;

            case RIGHT:
            case D:
                play_button.requestFocus();
                break;

            case ESCAPE:
            case ENTER:
                handleBack(event);
                break;

            default:
                break;
        }
    }

    public Scene getScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../resources/fxml/DungeonMenuView.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        return scene;
    }

    private void selectPrevious() {
        int selectionIndex = listview.getSelectionModel().getSelectedIndex();
        if (selectionIndex > 0) {
            listview.getSelectionModel().selectPrevious();
        }
    }

    private void selectNext() {
        int selectionIndex = listview.getSelectionModel().getSelectedIndex();
        if (selectionIndex < menu.size() - 1) {
            listview.getSelectionModel().selectNext();
        }
    }

}