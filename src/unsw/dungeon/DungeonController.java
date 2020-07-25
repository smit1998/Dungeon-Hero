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
import javafx.scene.Parent;
import javafx.scene.Scene;

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
    private GridPane squares;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;

    private boolean running = false;
    private Thread thread;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
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

        start();
    }

    private Set<String> input = new TreeSet<String>();

    @FXML
    public void handleKeyPress(KeyEvent e) {
        String code = e.getCode().toString();
        input.add(code);
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

}
