package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.layout.StackPane;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.collections.FXCollections;

public class DungeonMenuController implements Controller {

    @FXML
    private ListView<DungeonMenuItem> dungeonList;

    @FXML
    private StackPane stack;

    private DungeonMenu menu;
    private ObservableList<DungeonMenuItem> observableList;

    public DungeonMenuController() throws FileNotFoundException {
        menu = new DungeonMenu();
        observableList = FXCollections.observableList(menu.getMenu());
    }

    @FXML
    public void initialize() {
        buttons.add(play_button);
        buttons.add(back_button);
        dungeonList.setItems(observableList);
        dungeonList.getSelectionModel().selectFirst();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                dungeonList.requestFocus();
            }
        });
    }

    @FXML
    public void handlePlay(Event event) throws IOException {
        DungeonMenuItem selection = dungeonList.getSelectionModel().getSelectedItem();
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
    public void handleMainKeyPress(KeyEvent event) throws IOException {
        switch (event.getCode()) {
            case ENTER:
                if (inFocus(play_button)) {
                    handlePlay(event);
                } else if (inFocus(back_button)) {
                    handleBack(event);
                }
                break;
            case W:
            case UP:
                for (StackPane btn : buttons)
                    unfocusButton(btn);
                dungeonList.requestFocus();
                break;
            case LEFT:
            case A:
                focusButton(back_button);
                stack.requestFocus();
                break;

            case RIGHT:
            case D:
                focusButton(play_button);
                stack.requestFocus();
                break;
            default:
                break;
        }
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
                focusButton(back_button);
                stack.requestFocus();
                break;

            case RIGHT:
            case D:
                focusButton(play_button);
                stack.requestFocus();
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

    public Scene getScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonMenuView.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        return scene;
    }

    private void selectPrevious() {
        int selectionIndex = dungeonList.getSelectionModel().getSelectedIndex();
        if (selectionIndex > 0) {
            dungeonList.getSelectionModel().selectPrevious();
        }
    }

    private void selectNext() {
        int selectionIndex = dungeonList.getSelectionModel().getSelectedIndex();
        if (selectionIndex < menu.size() - 1) {
            dungeonList.getSelectionModel().selectNext();
        }
    }

    @FXML
    private StackPane play_button, back_button;

    private List<StackPane> buttons = new ArrayList<>();

    public void handlePlayMouseEnter() {
        focusButton(play_button);
    }

    public void handlePlayMouseExit() {
        unfocusButton(play_button);
    }

    public void handleBackMouseEnter() {
        focusButton(back_button);
    }

    public void handleBackMouseExit() {
        unfocusButton(back_button);
    }

    public void focusButton(StackPane button) {
        for (StackPane btn : buttons)
            unfocusButton(btn);
        button.getStyleClass().add("button-selected");
    }

    public void unfocusButton(StackPane button) {
        button.getStyleClass().remove("button-selected");
    }

    private boolean inFocus(StackPane button) {
        return button.getStyleClass().contains("button-selected");
    }
}