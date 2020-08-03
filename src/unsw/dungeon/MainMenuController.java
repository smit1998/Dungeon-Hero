package unsw.dungeon;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainMenuController implements Controller {

    private final static Font MAIN_FONT = Font.loadFont("file:resources/fonts/DUNGRG__.TTF", 35);

    @FXML
    private Text play_text, quit_text;

    @FXML
    private StackPane play_button, quit_button;

    private List<StackPane> buttons = new ArrayList<>();

    @FXML
    public void initialize() {
        buttons.add(play_button);
        buttons.add(quit_button);
        setFocus(play_button);

        play_text.setFont(MAIN_FONT);
        quit_text.setFont(MAIN_FONT);
    }

    @FXML
    private void handlePlay(Event event) throws IOException {
        System.out.println("Pressed play");
        Stage stage = Controller.getStage(event);
        DungeonMenuController controller = new DungeonMenuController();
        stage.setScene(controller.getScene());
        stage.show();
    }

    @FXML
    private void handleQuit(Event event) throws IOException {
        System.out.println("Quitting game");
        Stage stage = Controller.getStage(event);
        stage.close();
    }

    public Scene getScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenuView.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        return scene;
    }

    public void handleKeyPress(KeyEvent e) throws IOException {
        switch (e.getCode()) {
            case UP:
            case W:
                focusButton(play_button);
                break;
            case DOWN:
            case S:
                focusButton(quit_button);
                break;
            case ENTER:
                if (inFocus(play_button)) {
                    handlePlay(e);
                } else if (inFocus(quit_button)) {
                    handleQuit(e);
                }
            default:
                break;
        }
    }

    public void handlePlayKeyPress(KeyEvent e) throws IOException {
        if (e.getCode().equals(KeyCode.ENTER)) {
            handlePlay(e);
        }
    }

    public void handleQuitKeyPress(KeyEvent e) throws IOException {
        if (e.getCode().equals(KeyCode.ENTER)) {
            handleQuit(e);
        }
    }

    private void setFocus(StackPane button) {
        focusButton(button);
    }

    private boolean inFocus(StackPane button) {
        return button.getStyleClass().contains("button-selected");
    }

    public void handlePlayMouseEnter() {
        focusButton(play_button);
    }

    public void handlePlayMouseExit() {
        unfocusButton(play_button);
    }

    public void handleQuitMouseEnter() {
        focusButton(quit_button);
    }

    public void handleQuitMouseExit() {
        unfocusButton(quit_button);
    }

    public void focusButton(StackPane button) {
        for (StackPane btn : buttons)
            unfocusButton(btn);
        button.getStyleClass().add("button-selected");
    }

    public void unfocusButton(StackPane button) {
        button.getStyleClass().remove("button-selected");
    }

}