package dungeonmania.entities.actor.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.Actor;
import dungeonmania.entities.actor.nonplayableactor.Ally;
import dungeonmania.entities.item.Item;
import dungeonmania.entities.item.potions.Potion;

public class Player extends Actor {
    private Map<String, Item> inventory = new HashMap<>();
    private Item key;
    private Queue<Potion> potionEffect;
    private Potion potionConsumed;
    private List<Ally> allies = new ArrayList<>();

    public void addToInventory(Item item) {

    }

    public void removeFromInventory(Item item) {

    }

    public List<Item> getInventory() {
        return null;
    }

    public Item getItem(String itemId) {
        return null;
    }

    public boolean use(Item item) {
        return false;
    }

    public boolean removeTreasures(int numTreasures) {
        return false;
    }

    public Item getKey() {
        return null;
    }

    public void setKey(Item item) {

    }

    public void notifyEnemies(Dungeon dungeon) {

    }

    public void notifyStaticObjects(Dungeon dungeon) {

    }

    public void addPotion(Potion potion) {

    }

    public void interact(Dungeon dungeon, String uniqueId) {

    }

    public void build(Dungeon dungeon, String itemType) {

    }

    public Potion getPotionConsumed() {
        return null;
    }

    public void addAlly(Ally ally) {

    }

    @Override
    public boolean isInteractable() {
        return false;
    }
}
