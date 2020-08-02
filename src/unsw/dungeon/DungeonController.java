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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;

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
    private VBox pane;

    @FXML
    private StackPane map_stack;

    @FXML
    private HBox items;

    @FXML
    private Text goal_string;

    @FXML
    private SplitPane lower_box;

    @FXML
    private ImageView background_image;

    private List<EntityView> initialEntities;

    private Player player;

    private Dungeon dungeon;
    private File file;

    private boolean running = false;
    private Thread thread;

    public DungeonController(Dungeon dungeon, List<EntityView> initialEntities, File file) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        this.file = file;
        trackCompletion(dungeon);
        trackIsAlive(player);
    }

    @FXML
    public void initialize() throws IOException {

        background_image.fitHeightProperty().bind(stack.heightProperty());
        background_image.fitWidthProperty().bind(stack.widthProperty());

        Image ground = new Image((new File("images/dirt_0_new.png")).toURI().toString());
        Image backpack = new Image((new File("images/backpack.png")).toURI().toString());

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                ImageView groundView = new ImageView(ground);
                groundView.setViewOrder(RenderLayer.FLOOR);
                squares.add(groundView, x, y);
            }
        }
        items.getChildren().add(new ImageView(backpack));

        for (EntityView entityView : initialEntities) {
            trackPickedUp(entityView);
            squares.getChildren().add(entityView.getView());
        }

        goal_string.setText(dungeon.getGoalString());
        lower_box.setMaxWidth(dungeon.getWidth() * 32);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                stack.requestFocus();
            }
        });

        start();

    }

    private Set<String> input = new TreeSet<String>();

    @FXML
    public void handleKeyPress(KeyEvent e) {
        String code = e.getCode().toString();
        input.add(code);

        switch (e.getCode()) {
            case ESCAPE:
                handlePause();
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

    private final Object lock = new Object();

    private boolean isPaused = false;

    public void run() {
        // Game loop - https://youtu.be/w1aB5gc38C8
        // Pause runnable - https://stackoverflow.com/a/37565875
        try {
            // For fade out of black
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int fps = 30;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        while (running) {
            synchronized (lock) {
                if (!running)
                    break;
                if (isPaused) {
                    try {
                        synchronized (lock) {
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                    if (!running)
                        break;
                    lastTime = System.nanoTime();
                }
            }
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
        StackPane goalTextPane = new StackPane();
        goalTextPane.setStyle("-fx-background-color: black");

        Text goalText = new Text(dungeon.getGoalType() == GoalType.COMPLEX_GOAL ? "" : dungeon.getGoalString());
        goalText.setTextAlignment(TextAlignment.CENTER);
        goalText.setFill(Color.WHITE);
        goalText.setFont(Font.font("", 20));

        goalTextPane.getChildren().add(goalText);
        stack.getChildren().add(goalTextPane);
        goalText.toFront();

        FadeTransition fadeInText = new FadeTransition(Duration.millis(750), goalText);
        fadeInText.setFromValue(0.0);
        fadeInText.setToValue(1.0);
        fadeInText.setCycleCount(1);
        fadeInText.setAutoReverse(true);

        FadeTransition fadeOutBlack = new FadeTransition(Duration.millis(4000), goalTextPane);
        fadeOutBlack.setFromValue(1.0);
        fadeOutBlack.setToValue(0.0);
        fadeOutBlack.setCycleCount(1);
        fadeOutBlack.setAutoReverse(true);

        fadeOutBlack.play();
        fadeInText.play();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                map_stack.getChildren().remove(goalTextPane);
                squares.requestFocus();
            }
        });

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
                if (newValue == true) {
                    stopGameLoop();
                    System.out.println("Dungeon complete");
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            EndScreenController controller = new EndScreenController(true, file);
                            try {
                                stack.getChildren().add((Node) controller.getParent());
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                }
            }
        });
    }

    public void trackIsAlive(Player player) {
        player.isAlive().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                stopGameLoop();
                System.out.println("Player has died");
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        EndScreenController controller = new EndScreenController(false, file);
                        try {
                            stack.getChildren().add((Node) controller.getParent());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        });
    }

    public void trackPickedUp(EntityView entityView) {
        Entity entity = entityView.getEntity();
        ImageView view = entityView.getView();

        if (entity instanceof ItemEntity == false)
            return;

        ItemEntity item = (ItemEntity) entity;
        item.isPickedUp().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (newValue) {
                            squares.getChildren().remove(view);
                            view.setVisible(true);
                            view.setStyle("-fx-effect: dropshadow(gaussian, yellow, 5, 0.0, 0, 0)");
                            items.getChildren().add(view);
                        } else {
                            view.setVisible(false);
                            items.getChildren().remove(view);
                        }
                    }
                });
            }
        });
    }

    @FXML
    public void handleQuit() throws IOException {
        System.out.println("Going back to main menu");
        Stage stage = (Stage) squares.getScene().getWindow();
        MainMenuController controller = new MainMenuController();
        stage.setScene(controller.getScene());
        stage.show();
    }

    @FXML
    public void handleResume() {
        synchronized (lock) {
            setIsPaused(false);
            lock.notifyAll();
        }
        squares.requestFocus();
    }

    @FXML
    public void handleRestart(Event event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(file);
        DungeonController controller = dungeonLoader.loadController();
        stage.setScene(controller.getScene());
        stage.show();
    }

    public void handlePause() {
        setIsPaused(true);
        PauseScreenController controller = new PauseScreenController(this);
        try {
            stack.getChildren().add((Node) controller.getParent());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void stopGameLoop() {
        running = false;
    }

    private void setIsPaused(boolean isPaused) {
        this.isPaused = isPaused;
    }

}
