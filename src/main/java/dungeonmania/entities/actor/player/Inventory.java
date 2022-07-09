package dungeonmania.entities.actor.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import dungeonmania.entities.item.Item;
import dungeonmania.entities.item.collectables.Arrows;
import dungeonmania.entities.item.collectables.Key;
import dungeonmania.entities.item.collectables.Treasure;
import dungeonmania.entities.item.collectables.Wood;
import dungeonmania.entities.item.equiments.Equipment;

public class Inventory {
    private Map<String, Item> allItems = new HashMap<>();
    private Stack<String> woods = new Stack<>();
    private Stack<String> arrows = new Stack<>();
    private Stack<String> treasures = new Stack<>();
    private Key key = null;

    public boolean hasItem(String itemId) {
        return this.allItems.get(itemId) != null;
    }

    public void addItem(Item item) {
        this.allItems.put(item.getUniqueId(), item);
    }

    public List<Item> getInventory() {
        return new ArrayList<>(this.allItems.values());
    }

    public void addKey(Key key) {
        this.key = key;
        this.allItems.put(key.getUniqueId(), key);
    }

    public Key getKey() {
        return this.key;
    }

    public void removeItem(String itemId) {
        this.allItems.remove(itemId);
    }

    public void addWood(Wood wood) {
        this.allItems.put(wood.getUniqueId(), wood);
        this.woods.push(wood.getUniqueId());
    }

    public boolean removeWoods(int numWoods) {
        if (getNumWood() < numWoods) {
            return false;
        }
        for (int i = 0; i < numWoods; i++) {
            String woodId = this.woods.pop();
            this.allItems.remove(woodId);
        }
        return true;
    }

    public int getNumWood() {
        return this.woods.size();
    }

    public void addArrow(Arrows arrow) {
        this.allItems.put(arrow.getUniqueId(), arrow);
        this.arrows.push(arrow.getUniqueId());
    }

    public boolean removeArrows(int numArrows) {
        if (getNumArrows() < numArrows) {
            return false;
        }
        for (int i = 0; i < numArrows; i++) {
            String arrowId = this.arrows.pop();
            this.allItems.remove(arrowId);
        }
        return true;
    }

    public int getNumArrows() {
        return this.arrows.size();
    }

    public void addTreasure(Treasure treasure) {
        this.allItems.put(treasure.getUniqueId(), treasure);
        this.treasures.push(treasure.getUniqueId());
    }

    public boolean removeTreasures(int numTreasures) {
        if (getNumTreasures() < numTreasures) {
            return false;
        }
        for (int i = 0; i < numTreasures; i++) {
            String treasureId = this.treasures.pop();
            this.allItems.remove(treasureId);
        }
        return true;
    }

    public int getNumTreasures() {
        return this.treasures.size();
    }
}
