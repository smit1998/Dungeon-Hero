package main.java.unsw.dungeon;

import main.java.unsw.dungeon.controllers.MainMenuController;
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class DungeonApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Dungeon Hero");

        MainMenuController controller = new MainMenuController();
        primaryStage.setScene(controller.getScene());
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
