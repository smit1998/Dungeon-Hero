package unsw.dungeon;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EndScreenController implements Controller {

    private final static Image VICTORY_IMG = new Image(
            (new File("src/unsw/dungeon/victory_text.png")).toURI().toString());
    private final static Image GAMEOVER_IMG = new Image(
            (new File("src/unsw/dungeon/game_over_text.png")).toURI().toString());

    @FXML
    private VBox end_screen;

    @FXML
    private ImageView end_screen_text;

    @FXML
    private StackPane replay_button, quit_button;
    private List<StackPane> buttons = new ArrayList<>();

    private boolean victory;
    private File dungeon;

    public EndScreenController(boolean victory, File dungeon) {
        this.victory = victory;
        this.dungeon = dungeon;
    }

    @FXML
    public void initialize() {
        end_screen_text.setImage(victory ? VICTORY_IMG : GAMEOVER_IMG);

        buttons.add(replay_button);
        buttons.add(quit_button);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (victory) {
                    focusButton(quit_button);
                } else {
                    focusButton(replay_button);
                }
                end_screen.requestFocus();
            }
        });
    }

    @FXML
    private void handleReplay(Event event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(dungeon);
        DungeonController controller = dungeonLoader.loadController();
        stage.setScene(controller.getScene());
        stage.show();
    }

    @FXML
    private void handleQuit(Event event) throws IOException {
        System.out.println("Going back to main menu");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MainMenuController controller = new MainMenuController();
        stage.setScene(controller.getScene());
        stage.show();
    }

    public Parent getParent() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EndScreenView.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        return root;
    }

    public Scene getScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EndScreenView.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        return scene;
    }

    @FXML
    public void handleEndScreenKeyPress(KeyEvent event) throws IOException {
        switch (event.getCode()) {
            case ENTER:
                if (inFocus(replay_button)) {
                    handleReplay(event);
                } else if (inFocus(quit_button)) {
                    handleQuit(event);
                }
                break;
            case LEFT:
            case A:
                focusButton(quit_button);
                break;
            case RIGHT:
            case D:
                focusButton(replay_button);
            default:
                break;
        }
    }

    private boolean inFocus(StackPane button) {
        return button.getStyleClass().contains("button-selected");
    }

    public void handleReplayMouseEnter() {
        focusButton(replay_button);
    }

    public void handleReplayMouseExit() {
        unfocusButton(replay_button);
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