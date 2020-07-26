package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;

import java.io.File;
import java.io.IOException;

/**
 * A JavaFX controller for the dungeon.
 * 
 * @author Robert Clifton-Everest
 *
 */

public class DungeonController implements Runnable, Controller {

    @FXML
    private StackPane stack;

    @FXML
    private GridPane squares;

    @FXML
    private HBox pause_menu;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;

    private boolean running = false;
    private Thread thread;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        trackCompletion(dungeon);
        trackIsAlive(player);
    }

    @FXML
    public void initialize() {

        Image ground = new Image((new File("images/dirt_0_new.png")).toURI().toString());

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);

        squares.requestFocus();

        // double windowHeight = squares.getHeight();
        // double windowWidth = squares.getWidth();

        // pane.setPrefHeight(windowHeight);
        // pane.setPrefWidth(windowWidth);

        start();
    }

    private Set<String> input = new TreeSet<String>();

    @FXML
    public void handleKeyPress(KeyEvent e) {
        String code = e.getCode().toString();
        input.add(code);

        switch (e.getCode()) {
            case ESCAPE:
                System.out.println("ESCAPE");
                if (running) {
                    stopGameLoop();
                    setIsPaused(true);
                    // pause_menu.requestFocus();
                } else {
                    setIsPaused(false);
                    start();
                }
                break;

            default:
                break;
        }
    }

    @FXML
    public void handleKeyRelease(KeyEvent e) {
        String code = e.getCode().toString();
        input.remove(code);
    }

    public void run() {
        // https://youtu.be/w1aB5gc38C8
        int fps = 30;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            lastTime = now;
            if (delta >= 1) {
                player.tick(input);
                dungeon.tick();
                delta -= 1;
            }
        }
        stop();
    }

    public Scene getScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        return scene;
    }

    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void trackCompletion(Dungeon dungeon) {
        dungeon.isCompleted().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                stopGameLoop();
                System.out.println("Dungeon complete");
            }
        });
    }

    public void trackIsAlive(Player player) {
        player.isAlive().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                stopGameLoop();
                System.out.println("Player has died");
            }
        });
    }

    @FXML
    public void handleQuit(ActionEvent event) throws IOException {
        System.out.println("Going back to main menu");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MainMenuController controller = new MainMenuController();
        stage.setScene(controller.getScene());
        stage.show();
    }

    @FXML
    public void handleResume(ActionEvent event) {
        setIsPaused(false);
        stack.requestFocus();
        start();
    }

    public void handlePause(ActionEvent event) {
        setIsPaused(true);
        stopGameLoop();
    }

    private void stopGameLoop() {
        running = false;
    }

    private void setIsPaused(boolean isPaused) {
        pause_menu.setVisible(isPaused);
        pause_menu.setDisable(!isPaused);
    }

    @FXML
    public void handlePauseKeyPress(KeyEvent e) {
        switch (e.getCode()) {
            case ESCAPE:
                System.out.println("Unpause game");
                setIsPaused(false);
                start();
                break;

            default:
                break;
        }
    }
}
