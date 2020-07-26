package unsw.dungeon;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController implements Controller {

    @FXML
    private Button play_button, quit_button;

    @FXML
    public void initialize() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                play_button.requestFocus();
            }
        });
    }

    @FXML
    private void handlePlay(Event event) throws IOException {
        System.out.println("Pressed play");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        DungeonMenuController controller = new DungeonMenuController();
        stage.setScene(controller.getScene());
        stage.show();
    }

    @FXML
    private void handleQuit(Event event) throws IOException {
        System.out.println("Quitting game");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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

    public void handleKeyPress(KeyEvent e) {
        switch (e.getCode()) {
            case UP:
            case W:
                play_button.requestFocus();
                break;
            case DOWN:
            case S:
                quit_button.requestFocus();
                break;
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

}