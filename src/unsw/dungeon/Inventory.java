package unsw.dungeon;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<Item> items; 

    public Inventory() {
        items = new ArrayList<Item>(); 
    }

    public Item addItem(Item item) {
        if (countItem(item) < item.getMaxPickup()) {
            items.add(item);
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

}