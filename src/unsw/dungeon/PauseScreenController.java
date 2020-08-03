package unsw.dungeon;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PauseScreenController implements Controller {

    private final static Font MAIN_FONT = Font.loadFont("file:resources/fonts/DUNGRG__.TTF", 32);

    @FXML
    private VBox pause_menu;

    @FXML
    private ImageView paused_text;

    @FXML
    private Text resume_text, restart_text, quit_text;

    @FXML
    private StackPane resume_button, restart_button, quit_button;
    private List<StackPane> buttons = new ArrayList<>();

    private DungeonController game;

    public PauseScreenController(DungeonController game) {
        this.game = game;
    }

    @FXML
    public void initialize() {

        buttons.add(resume_button);
        buttons.add(restart_button);
        buttons.add(quit_button);

        resume_text.setFont(MAIN_FONT);
        restart_text.setFont(MAIN_FONT);
        quit_text.setFont(MAIN_FONT);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                paused_text.setFitWidth(pause_menu.getScene().getWidth() * 0.6);
                pause_menu.requestFocus();
                focusButton(resume_button);
            }
        });
    }

    public Parent getParent() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PauseScreenView.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        return root;
    }

    public Scene getScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PauseScreenView.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        return scene;
    }

    public void handleResume() {
        game.handleResume();
    }

    public void handleRestart(Event e) throws IOException {
        game.handleRestart(e);
    }

    public void handleQuit() throws IOException {
        game.handleQuit();
    }

    @FXML
    public void handlePauseKeyPress(KeyEvent e) throws IOException {
        switch (e.getCode()) {
            case W:
            case UP:
                if (inFocus(quit_button)) {
                    focusButton(restart_button);
                } else if (inFocus(restart_button)) {
                    focusButton(resume_button);
                } else if (!inFocus(resume_button)) {
                    focusButton(quit_button);
                }
                break;
            case S:
            case DOWN:
                if (inFocus(resume_button)) {
                    focusButton(restart_button);
                } else if (inFocus(restart_button)) {
                    focusButton(quit_button);
                } else if (!inFocus(quit_button)) {
                    focusButton(resume_button);
                }
                break;
            case ENTER:
                if (inFocus(resume_button)) {
                    handleResume();
                } else if (inFocus(restart_button)) {
                    handleRestart(e);
                } else if (inFocus(quit_button)) {
                    handleQuit();
                }
            default:
                break;
        }
    }

    private boolean inFocus(StackPane button) {
        return button.getStyleClass().contains("button-selected");
    }

    public void handleResumeMouseEnter() {
        focusButton(resume_button);
    }

    public void handleResumeMouseExit() {
        unfocusButton(resume_button);
    }

    public void handleRestartMouseEnter() {
        focusButton(restart_button);
    }

    public void handleRestartMouseExit() {
        unfocusButton(restart_button);
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