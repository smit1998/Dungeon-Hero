package main.java.unsw.dungeon;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class EndScreenController implements Controller {

    private final static Image VICTORY_IMG = new Image(
            (new File("src/main/resources/images/victory_text.png")).toURI().toString());
    private final static Image GAMEOVER_IMG = new Image(
            (new File("src/main/resources/images/game_over_text.png")).toURI().toString());

    @FXML
    private ImageView end_screen_text;

    @FXML
    private Button replay_button, quit_button;

    private boolean victory;
    private File dungeon;

    public EndScreenController(boolean victory, File dungeon) {
        this.victory = victory;
        this.dungeon = dungeon;
    }

    @FXML
    public void initialize() {
        end_screen_text.setImage(victory ? VICTORY_IMG : GAMEOVER_IMG);

        replay_button.setOnKeyPressed(e -> handleReplayKeyPress(e));
        quit_button.setOnKeyPressed(e -> handleQuitKeyPress(e));

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (victory) {
                    quit_button.requestFocus();
                } else {
                    replay_button.requestFocus();
                }
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../resources/fxml/EndScreenView.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        return root;
    }

    public Scene getScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../resources/fxml/EndScreenView.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        return scene;
    }

    private void handleQuitKeyPress(KeyEvent event) {
        try {
            switch (event.getCode()) {
                case ENTER:
                    handleQuit(event);
                    break;
                case RIGHT:
                case D:
                    replay_button.requestFocus();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void handleReplayKeyPress(KeyEvent event) {
        try {
            switch (event.getCode()) {
                case ENTER:
                    handleReplay(event);
                    break;
                case LEFT:
                case A:
                    quit_button.requestFocus();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}