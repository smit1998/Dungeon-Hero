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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener.Change;
import javafx.event.ActionEvent;
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
    private VBox pause_menu;

    @FXML
    private Button quit_button, resume_button;

    @FXML
    private HBox items;

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
        trackEssentialGoals(dungeon);
        trackIsAlive(player);
    }

    @FXML
    public void initialize() throws IOException {
        resume_button.setOnKeyPressed(e -> handleResumeKeyPress(e));
        quit_button.setOnKeyPressed(e -> handleQuitKeyPress(e));

        Image ground = new Image((new File("images/dirt_0_new.png")).toURI().toString());
        Image backpack = new Image((new File("images/backpack.png")).toURI().toString());

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }
        items.getChildren().add(new ImageView(backpack));
        items.setStyle("-fx-background-color: black;");

        for (EntityView entityView : initialEntities) {
            trackPickedUp(entityView);
            squares.getChildren().add(entityView.getView());
        }

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                squares.requestFocus();
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

    public void trackEssentialGoals(Dungeon dungeon) {
        dungeon.getEssentialGoals().addListener(new ListChangeListener<ComponentGoal>() {
            @Override
            public void onChanged(Change<? extends ComponentGoal> c) {
                c.next();
                for (ComponentGoal goal : c.getAddedSubList()) {
                    switch (goal.getType()) {
                        case EXIT_GOAL:
                            System.out.println("Add exit goal");
                            break;
                        case ENEMIES_GOAL:
                            System.out.println("Add enemies goal");
                            break;
                        case TREASURE_GOAL:
                            System.out.println("Add treaure goal");
                            break;
                        case SWITCHES_GOAL:
                            System.out.println("Add switch goal");
                            break;
                        default:
                            break;
                    }
                }
                for (ComponentGoal goal : c.getRemoved()) {
                    switch (goal.getType()) {
                        case EXIT_GOAL:
                            System.out.println("Remove exit goal");
                            break;
                        case ENEMIES_GOAL:
                            System.out.println("Remove enemies goal");
                            break;
                        case TREASURE_GOAL:
                            System.out.println("Remove treaure goal");
                            break;
                        case SWITCHES_GOAL:
                            System.out.println("Remove switch goal");
                            break;
                        default:
                            break;
                    }
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
        setIsPaused(false);
        start();
        squares.requestFocus();
    }

    public void handlePause() {
        setIsPaused(true);
        stopGameLoop();
        resume_button.requestFocus();
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
                handleResume();
                break;
            case LEFT:
                quit_button.requestFocus();
                break;

            case RIGHT:
                resume_button.requestFocus();
                break;

            default:
                break;
        }
    }

    private void handleQuitKeyPress(KeyEvent event) {
        try {
            switch (event.getCode()) {
                case ENTER:
                    handleQuit();
                    break;
                case RIGHT:
                case D:
                    resume_button.requestFocus();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void handleResumeKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case ENTER:
                handleResume();
                break;
            case LEFT:
            case A:
                quit_button.requestFocus();
            default:
                break;
        }
    }

}
