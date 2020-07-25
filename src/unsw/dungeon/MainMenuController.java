package unsw.dungeon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController implements Controller {

    @FXML
    private Button play_button;

    @FXML
    private Button quit_button;

    public MainMenuController() {
    }

    @FXML
    private void handlePlay(ActionEvent event) throws IOException {
        System.out.println("Pressed play");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        DungeonMenuController controller = new DungeonMenuController();
        stage.setScene(controller.getScene());
        stage.show();
    }

    @FXML
    private void handleQuit(ActionEvent event) throws IOException {
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

}