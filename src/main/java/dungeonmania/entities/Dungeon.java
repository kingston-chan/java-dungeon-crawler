package dungeonmania.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dungeonmania.entities.actor.nonplayableactor.Enemy;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.battle.Battle;
import dungeonmania.entities.goal.GoalTreeNode;
import dungeonmania.entities.item.Item;
import dungeonmania.entities.staticobject.StaticObject;

public class Dungeon {
    private Map<String, DungeonObject> dungeonObjects = new HashMap<>();
    private Map<String, Item> itemsInDungeon = new HashMap<>();
    private Map<String, Enemy> activeEnemies = new HashMap<>();
    private Map<String, StaticObject> staticObjects = new HashMap<>();
    private Player player = null;
    private String config = "";
    private GoalTreeNode goals = null;
    private List<Battle> battles = new ArrayList<>();

    public void initDungeon(String dungeonName, String configName) {

    }

    public DungeonObject getDungeonObject(String uniqueId) {
        return null;
    }

    public List<DungeonObject> getDungeonObjects() {
        return null;
    }

    public Item getItemInDungeon(String uniqueId) {
        return null;
    }

    public List<Item> getItemsInDungeon() {
        return null;
    }

    public void addItemToDungeon(Item item) {
    }

    public void removeItemFromDungeon(Item item) {
    }

    public Enemy getActiveEnemy(String uniqueId) {
        return null;
    }

    public List<Enemy> getActiveEnemies() {
        return null;
    }

    public void addToActiveEnemies(Enemy enemy) {
    }

    public void removeFromActiveEnemies(Enemy enemy) {
    }

    public StaticObject getStaticObject(String uniqueId) {
        return null;
    }

    public List<StaticObject> getStaticObjects() {
        return null;
    }

    public void addStaticObject(StaticObject staticObject) {
    }

    public void removeFromStaticObjects(StaticObject staticObject) {
    }

    public Player getPlayer() {
        return null;
    }

    public void setPlayer(Player player) {
    }

}
