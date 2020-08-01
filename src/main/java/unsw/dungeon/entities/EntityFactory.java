package main.java.unsw.dungeon.entities;

import java.util.ArrayList;

import main.java.unsw.dungeon.*;

public class EntityFactory {

    public Entity createEntity(EntityType type, int x, int y, int id, Dungeon dungeon) {
        switch (type) {
            case PLAYER: {
                return new Player(dungeon, x, y);
            }
            case WALL: {
                return new Wall(x, y, dungeon);
            }
            case EXIT: {
                return new Exit(x, y, dungeon);
            }
            case TREASURE: {
                return new Treasure(x, y, dungeon);
            }
            case DOOR: {
                return new Door(x, y, dungeon, id);
            }
            case KEY: {
                return new Key(x, y, dungeon, id);
            }
            case BOULDER: {
                return new Boulder(x, y, dungeon);
            }
            case SWITCH: {
                return new FloorSwitch(x, y, dungeon);
            }
            case PORTAL: {
                return new Portal(x, y, dungeon, id);
            }
            case ENEMY: {
                return new Enemy(x, y, dungeon);
            }
            case SWORD: {
                return new Weapon(x, y, dungeon, new InstantKillAttack(), 5);
            }
            case INVINCIBILITY: {
                ArrayList<EffectBehaviour> effects = new ArrayList<EffectBehaviour>();
                effects.add(new InvincibilityEffect(dungeon));
                effects.add(new FearEffect(dungeon));
                effects.add(new MeleeKillEffect(dungeon));
                return new Potion(x, y, dungeon, effects);
            }
            case SPEED: {
                ArrayList<EffectBehaviour> effects = new ArrayList<EffectBehaviour>();
                effects.add(new SpeedUpEffect(dungeon));
                return new Potion(x, y, dungeon, effects);
            }
            case DAGGER: {
                return new Weapon(x, y, dungeon, new InstantKillAttack(), 1);
            }
            case CHECKPOINT: {
                return new Checkpoint(x, y, dungeon);
            }
            default:
                return null;
        }
    }
}