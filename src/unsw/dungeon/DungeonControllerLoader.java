package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.File;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * 
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<EntityView> entities = new ArrayList<>();

    // Images
    private Image playerImage = new Image((new File("images/human_new.png")).toURI().toString());
    private Image wallImage = new Image((new File("images/brick_brown_0.png")).toURI().toString());
    private Image exitImage = new Image((new File("images/exit.png")).toURI().toString());
    private Image treasureImage = new Image((new File("images/gold_pile.png")).toURI().toString());
    private Image openDoorImage = new Image((new File("images/open_door.png")).toURI().toString());
    private Image closedDoorImage = new Image((new File("images/closed_door.png")).toURI().toString());
    private Image keyImage = new Image((new File("images/key.png")).toURI().toString());
    private Image boulderImage = new Image((new File("images/boulder.png")).toURI().toString());
    private Image switchImage = new Image((new File("images/pressure_plate.png")).toURI().toString());
    private Image portalImage = new Image((new File("images/portal.png")).toURI().toString());
    private Image enemyImage = new Image((new File("images/deep_elf_master_archer.png")).toURI().toString());
    private Image swordImage = new Image((new File("images/greatsword_1_new.png")).toURI().toString());
    private Image invincibilityImage = new Image((new File("images/brilliant_blue_new.png")).toURI().toString());
    private Image speedBootsImage = new Image((new File("images/speedBoots.png")).toURI().toString());
    private Image daggerImage = new Image((new File("images/Dagger.png")).toURI().toString());
    private Image checkpointImage = new Image((new File("images/checkpoint.png")).toURI().toString());

    public DungeonControllerLoader(String filename) throws FileNotFoundException {
        super(filename);
    }

    public DungeonControllerLoader(File file) throws FileNotFoundException {
        super(file);
    }

    @Override
    public void onLoad(Player player) {
        ImageView view = new ImageView(playerImage);
        view.setViewOrder(RenderLayer.PLAYER);
        addEntity(new EntityView(player, view));
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        view.setViewOrder(RenderLayer.PLAYER);
        addEntity(new EntityView(wall, view));
    }

    @Override
    public void onLoad(Exit exit) {
        ImageView view = new ImageView(exitImage);
        view.setViewOrder(RenderLayer.CEILING);
        addEntity(new EntityView(exit, view));
    }

    @Override
    public void onLoad(Treasure treasure) {
        ImageView view = new ImageView(treasureImage);
        view.setViewOrder(RenderLayer.ITEM);
        addEntity(new EntityView(treasure, view));
    }

    @Override
    public void onLoad(Door door) {
        ImageView view = new ImageView(closedDoorImage);
        view.setViewOrder(RenderLayer.PLAYER);
        trackOpen(door, view);
        addEntity(new EntityView(door, view));
    }

    @Override
    public void onLoad(Key key) {
        ImageView view = new ImageView(keyImage);
        view.setViewOrder(RenderLayer.ITEM);
        addEntity(new EntityView(key, view));
    }

    @Override
    public void onLoad(Boulder boulder) {
        ImageView view = new ImageView(boulderImage);
        view.setViewOrder(RenderLayer.PLAYER);
        addEntity(new EntityView(boulder, view));
    }

    @Override
    public void onLoad(FloorSwitch floorSwitch) {
        ImageView view = new ImageView(switchImage);
        view.setViewOrder(RenderLayer.SWITCH);
        addEntity(new EntityView(floorSwitch, view));
    }

    @Override
    public void onLoad(Portal portal) {
        ImageView view = new ImageView(portalImage);
        view.setViewOrder(RenderLayer.PLAYER);
        addEntity(new EntityView(portal, view));
    }

    @Override
    public void onLoad(Enemy enemy) {
        ImageView view = new ImageView(enemyImage);
        view.setViewOrder(RenderLayer.PLAYER);
        addEntity(new EntityView(enemy, view));
    }

    @Override
    public void onLoad(Checkpoint checkpoint) {
        ImageView view = new ImageView(checkpointImage);
        view.setViewOrder(RenderLayer.ITEM);
        addEntity(new EntityView(checkpoint, view));
    }
    /*
     * @Override public void onLoad(Weapon sword) { ImageView view = new
     * ImageView(swordImage); addEntity(sword, view); }
     */

    @Override
    public void onLoad(Weapon sword, String type) {
        ImageView view = null;
        switch (type) {
            case "sword":
                view = new ImageView(swordImage);
                break;
            case "dagger":
                view = new ImageView(daggerImage);
                break;
            default:
                throw new Error(String.format("Invalid weapon type '%s'", type));
        }
        view.setViewOrder(RenderLayer.ITEM);
        addEntity(new EntityView(sword, view));
    }

    @Override
    public void onLoad(Potion potion, String type) {
        ImageView view = null;
        switch (type) {
            case "invincibility":
                view = new ImageView(invincibilityImage);
                break;
            case "speedBoots":
                view = new ImageView(speedBootsImage);
                break;
            default:
                throw new Error(String.format("Invalid potion type '%s'", type));
        }
        view.setViewOrder(RenderLayer.ITEM);
        addEntity(new EntityView(potion, view));
    }

    private void addEntity(EntityView entityView) {
        trackPosition(entityView);
        trackVisibility(entityView);
        entities.add(entityView);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an entity
     * in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the model
     * will automatically be reflected in the view.
     * 
     * @param entity
     * @param node
     */
    private void trackPosition(EntityView entityView) {
        Node node = entityView.getView();
        Entity entity = entityView.getEntity();
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
    }

    public void trackVisibility(EntityView entityView) {
        Node node = entityView.getView();
        Entity entity = entityView.getEntity();
        entity.isVisible().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                node.setVisible(newValue);
            }
        });
    }

    public void trackOpen(Door door, ImageView view) {
        door.isOpen().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    view.setImage(openDoorImage);

                } else {
                    view.setImage(closedDoorImage);
                }
            }
        });
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * 
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities, getFile());
    }

}
