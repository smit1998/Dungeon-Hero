package unsw.dungeon;

import java.util.ArrayList;

/**
 * Inventory class 
 * - stores items for the player
 */
public class Inventory implements Observer {

    private ArrayList<Item> items;

    /**
     * creates a new inventory object
     */
    public Inventory() {
        items = new ArrayList<Item>();
    }

    /**
     * Adds an item to the inventory
     * @param item to be added
     * @return the Item added if it can be added, otherwise null
     */
    public Item addItem(Item item) {
        if (countItem(item) < item.getMaxPickup()) {
            items.add(item);
            if (item instanceof Subject) {
                Subject s = (Subject) item;
                s.attach(this);
            }
            return item;
        }
        return null;
    }

    /**
     * Removes an item from the inventory
     * @param item to be removed 
     * no item is removed if item does not exist in inventory
     */
    public void removeItem(Item item) {
        items.remove(item);
    }

    /**
     * Counts the number of items in the inventory, by item class
     * @param item to be counted
     * @return an integer >= 0, representing the number of items of the same class
     * already inside the inventory
     */
    private int countItem(Item item) {
        int count = 0;
        for (Item inventoryItem : items) {
            if (item.getClass() == inventoryItem.getClass()) {
                count += 1;
            }
        }
        return count;
    }

    /**
     * Attempt to retrieve a weapon from the inventory
     * @return a weapon object if a weapon exists in the inventory, with the highest priority
     * if no weapon exists, then null is returned. 
     */
    public Weapon getWeapon() {
        Weapon chosenWeapon = null; 
        for (Item inventoryItem : items) {
            if (inventoryItem instanceof Weapon) {
                Weapon inventoryWeapon = (Weapon) inventoryItem; 
                if (chosenWeapon == null || chosenWeapon.getPriority() < inventoryWeapon.getPriority())  {
                    chosenWeapon = (Weapon) inventoryItem;
                }
            }
        }
        return chosenWeapon;
    }

    /**
     * Removes an object given from the inventory, when notifyObserver is called by the subjects
     * @param obj to be removed
     */
    @Override
    public void update(Subject obj) {
        if (obj instanceof Item) {
            Item i = (Item) obj;
            removeItem(i);
        }
    }

    /**
     * Attempt to retrieve a key from inventory
     * @return null if no key in inventory, otherwise the key object
     */
    public Key getKey() {
        for (Item item : items) {
            if (item instanceof Key) {
                return (Key) item;
            }
        }
        return null;
    }
}