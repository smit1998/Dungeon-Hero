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

public class PauseScreenController implements Controller {

    @FXML
    private Button play_button;

    @FXML
    private Button quit_button;

    @FXML
    private void handleResume(ActionEvent event) throws IOException {
        System.out.println("Resuming game");
        Stage stage = Controller.getStage(event);
        DungeonMenuController controller = new DungeonMenuController();
        stage.setScene(controller.getScene());
        stage.show();
    }

    @FXML
    private void handleExit(ActionEvent event) throws IOException {
        System.out.println("Exiting level");
        MainMenuController controller = new MainMenuController();
        Stage stage = Controller.getStage(event);
        stage.setScene(controller.getScene());
        stage.show();
    }

    public Scene getScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PauseScreenView.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        return scene;
    }

}