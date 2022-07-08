package dungeonmania.entities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import dungeonmania.entities.actor.enemy.Enemy;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.battle.Battle;
import dungeonmania.entities.goal.GoalFactory;
import dungeonmania.entities.goal.GoalTreeNode;
import dungeonmania.entities.item.Item;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.factory.DungeonObjectFactory;
import dungeonmania.factory.FactoryChooser;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.FileLoader;
import dungeonmania.util.Position;

public class Dungeon {
    private Map<String, DungeonObject> dungeonObjects = new HashMap<>();
    private Map<String, Item> itemsInDungeon = new HashMap<>();
    private Map<String, Enemy> activeEnemies = new HashMap<>();
    private Map<String, StaticObject> staticObjects = new HashMap<>();
    private Player player = null;
    private String config = "";
    private GoalTreeNode goals = null;
    private List<Battle> battles = new ArrayList<>();
    private FactoryChooser factoryChooser = new FactoryChooser();
    private GoalFactory goalFactory = new GoalFactory();

    private GoalTreeNode addGoals(JSONObject goal) {
        GoalTreeNode goalTreeNode = new GoalTreeNode();

        if (goal.getString("goal").equals("AND")) {
            JSONArray subgoals = goal.getJSONArray("subgoals");
            goalTreeNode.setSubGoalType("AND");
            goalTreeNode.setLeftChild(addGoals(subgoals.getJSONObject(0)));
            goalTreeNode.setRightChild(addGoals(subgoals.getJSONObject(1)));
        } else if (goal.getString("goal").equals("OR")) {
            JSONArray iterate = goal.getJSONArray("subgoals");
            JSONArray subgoals = goal.getJSONArray("subgoals");
            goalTreeNode.setSubGoalType("OR");
            goalTreeNode.setLeftChild(addGoals(subgoals.getJSONObject(0)));
            goalTreeNode.setRightChild(addGoals(subgoals.getJSONObject(1)));
        } else {
            goalTreeNode.setSubGoalType("");
            goalTreeNode.setGoal(goalFactory.createGoal(goal.getString("goal")));
        }

        return goalTreeNode;
    }

    private boolean getAllGoals(Dungeon dungeon, StringBuilder allGoals, GoalTreeNode goalTreeNode) {
        if (goalTreeNode.getGoal() == null) {
            StringBuilder leftString = new StringBuilder("");
            StringBuilder rightString = new StringBuilder("");
            Boolean leftGoalAchieved = getAllGoals(dungeon, leftString, goalTreeNode.getLeftChild());
            Boolean rightGoalAchieved = getAllGoals(dungeon, rightString, goalTreeNode.getRightChild());
            if (!(leftGoalAchieved && rightGoalAchieved)) {
                allGoals.append("(" + leftString.toString() + " " + goalTreeNode.getSubGoalType() + " "
                        + rightString.toString() + ")");
                return false;
            }

            if (goalTreeNode.getSubGoalType().equals("OR")) {
                return true;
            } else {
                allGoals.append(leftString.toString() + rightString.toString());
                return false;
            }
        }
        return goalTreeNode.getGoal().hasAchieved(dungeon, allGoals);
    }

    public void initDungeon(String dungeonName, String configName) {
        this.config = configName;
        try {
            JSONObject resource = new JSONObject(FileLoader.loadResourceFile("/dungeons/" + dungeonName + ".json"));
            JSONArray array = resource.getJSONArray("entities");

            for (int i = 0; i < array.length(); i++) {
                JSONObject a = array.getJSONObject(i);
                int x = a.getInt("x");
                int y = a.getInt("y");
                String type = a.getString("type");
                DungeonObjectFactory dungeonObjectFactory = this.factoryChooser.getFactory(type);
                dungeonObjectFactory.create(new Position(x, y), type, this);
            }

            this.goals = addGoals(resource.getJSONObject("goal-condition"));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public DungeonObject getDungeonObject(String uniqueId) {
        return this.dungeonObjects.get(uniqueId);
    }

    public List<DungeonObject> getDungeonObjects() {
        return new ArrayList<>(this.dungeonObjects.values());
    }

    public Item getItemInDungeon(String uniqueId) {
        return this.itemsInDungeon.get(uniqueId);
    }

    public List<Item> getItemsInDungeon() {
        return new ArrayList<>(this.itemsInDungeon.values());
    }

    public void addItemToDungeon(Item item) {
        this.itemsInDungeon.put(item.getUniqueId(), item);
        this.dungeonObjects.put(item.getUniqueId(), item);
    }

    public void removeItemFromDungeon(Item item) {
        this.dungeonObjects.remove(item.getUniqueId());
        this.itemsInDungeon.remove(item.getUniqueId());
    }

    public Enemy getActiveEnemy(String uniqueId) {
        return this.activeEnemies.get(uniqueId);
    }

    public List<Enemy> getActiveEnemies() {
        return new ArrayList<>(this.activeEnemies.values());
    }

    public void addToActiveEnemies(Enemy enemy) {
        this.activeEnemies.put(enemy.getUniqueId(), enemy);
        this.dungeonObjects.put(enemy.getUniqueId(), enemy);
    }

    public void removeFromActiveEnemies(Enemy enemy) {
        this.dungeonObjects.remove(enemy.getUniqueId());
        this.activeEnemies.remove(enemy.getUniqueId());
    }

    public StaticObject getStaticObject(String uniqueId) {
        return this.staticObjects.get(uniqueId);
    }

    public List<StaticObject> getStaticObjects() {
        return new ArrayList<>(this.staticObjects.values());
    }

    public void addStaticObject(StaticObject staticObject) {
        this.staticObjects.put(staticObject.getUniqueId(), staticObject);
        this.dungeonObjects.put(staticObject.getUniqueId(), staticObject);
    }

    public void removeFromStaticObjects(StaticObject staticObject) {
        this.dungeonObjects.remove(staticObject.getUniqueId());
        this.staticObjects.remove(staticObject.getUniqueId());
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getConfig(String configKey) {
        try {
            JSONObject resource = new JSONObject(FileLoader.loadResourceFile("/configs/" + this.config + ".json"));
            return resource.getInt(configKey);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String getGoals() {
        if (goals.getLeftChild() == null && goals.getRightChild() == null) {
            StringBuilder allGoals = new StringBuilder("");
            goals.getGoal().hasAchieved(this, allGoals);
            return allGoals.toString();
        }

        StringBuilder leftString = new StringBuilder("");
        StringBuilder rightString = new StringBuilder("");
        Boolean leftGoalAchieved = getAllGoals(this, leftString, goals.getLeftChild());
        Boolean rightGoalAchieved = getAllGoals(this, rightString, goals.getRightChild());

        if (!(leftGoalAchieved && rightGoalAchieved)) {
            return leftString + " " + goals.getSubGoalType() + " " + rightString;
        }

        if (goals.getSubGoalType().equals("OR")) {
            return "";
        }

        String allGoals = leftString.toString() + rightString.toString();

        if (leftGoalAchieved && rightGoalAchieved && allGoals.contains(":exit")) {
            return "";
        }

        return allGoals;
    }

    public List<Battle> getBattles() {
        return null;
    }

    public DungeonResponse getDungeonResponse() {
        return null;
    }

    public List<StaticObject> getStaticObjectsAtPosition(int x, int y) {
        List<StaticObject> staticObjectsAtPosition = new ArrayList<>();
        for (StaticObject so : this.staticObjects.values()) {
            if (so.getPosition().getX() == x && so.getPosition().getY() == y) {
                staticObjectsAtPosition.add(so);
            }
        }
        return staticObjectsAtPosition;
    }

    public List<Enemy> getEnemiesAtPosition(int x, int y) {
        List<Enemy> enemiesAtPosition = new ArrayList<>();
        for (Enemy e : this.activeEnemies.values()) {
            if (e.getPosition().getX() == x && e.getPosition().getY() == y) {
                enemiesAtPosition.add(e);
            }
        }
        return enemiesAtPosition;
    }
}
