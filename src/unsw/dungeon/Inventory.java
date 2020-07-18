package unsw.dungeon;

import java.util.ArrayList;

public class Inventory implements Observer {

    private ArrayList<Item> items; 

    public Inventory() {
        items = new ArrayList<Item>(); 
    }

    public Item addItem(Item item) {
        if (countItem(item) < item.getMaxPickup()) {
            item.setInventory(this); 
            items.add(item);
            if (item instanceof Sword) {
                Sword s = (Sword) item; 
                s.attach(this); 
            }
            return item; 
        } 
        return null; 
    }

    public void removeItem(Item item) {
        items.remove(item); 
    }
    
    private int countItem(Item item) {
        int count = 0; 
        for (Item inventoryItem : items) {
            if (item.getClass() == inventoryItem.getClass()) {
                count+=1; 
            }
        }
        return count;
    }

    public Weapon getWeapon() {
        for (Item inventoryItem : items) {
            if (inventoryItem instanceof Weapon) {
                return (Weapon) inventoryItem;
            }
        }
        return null; 
    }

	@Override
	public void update(Subject obj) {
        // removes a sword if hits reaches 0 
        if (obj instanceof Item) {
            Item i = (Item) obj; 
            System.out.println("removed sword"); 
            removeItem(i); 
        }
	}

}