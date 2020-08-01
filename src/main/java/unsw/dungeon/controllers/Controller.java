package main.java.unsw.dungeon.controllers;

import java.io.IOException;

import javafx.event.Event;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public interface Controller {

    public static Stage getStage(Event event) {
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }

    public Scene getScene() throws IOException;

}