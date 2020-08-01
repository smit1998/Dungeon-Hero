package test;

import org.json.JSONObject;
import unsw.dungeon.*;

public class DungeonMockLoader extends DungeonLoader {

    public DungeonMockLoader(JSONObject json) {
        super(json);
    }

    @Override
    public void onLoad(Player player) {
    }

    @Override
    public void onLoad(Wall wall) {
    }

    @Override
    public void onLoad(Exit exit) {
    }

    @Override
    public void onLoad(Treasure treasure) {
    }

    @Override
    public void onLoad(Door door) {
    }

    @Override
    public void onLoad(Key key) {
    }

    @Override
    public void onLoad(Boulder boulder) {
    }

    @Override
    public void onLoad(FloorSwitch floorSwitch) {
    }

    @Override
    public void onLoad(Portal portal) {
    }

    @Override
    public void onLoad(Enemy enemy) {
    }

    @Override
    public void onLoad(Weapon sword, String type) {
    }

    @Override
    public void onLoad(Potion invincibilityPotion, String type) {
    }

    @Override
    public void onLoad(Checkpoint checkpoint) {
    }
}
