package main.java.unsw.dungeon;

import javafx.scene.image.ImageView;

public class EntityView {
    private Entity entity;
    private ImageView view;

    public EntityView(Entity entity, ImageView view) {
        this.entity = entity;
        this.view = view;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public ImageView getView() {
        return view;
    }

    public void setView(ImageView view) {
        this.view = view;
    }

}