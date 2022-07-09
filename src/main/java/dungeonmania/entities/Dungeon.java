package dungeonmania.entities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import dungeonmania.entities.actor.enemy.Enemy;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.battle.Battle;
import dungeonmania.entities.goal.Goal;
import dungeonmania.entities.goal.GoalFactory;
import dungeonmania.entities.goal.GoalTreeNode;
import dungeonmania.entities.item.Item;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.factory.DungeonObjectFactory;
import dungeonmania.factory.FactoryChooser;
import dungeonmania.response.models.BattleResponse;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.response.models.RoundResponse;
import dungeonmania.util.FileLoader;
import dungeonmania.util.Position;

public class Dungeon {
    private final int MAX_SPIDER_SPAWN = 15;
    private final int MIN_SPIDER_SPAWN = 0;

    private Map<String, DungeonObject> dungeonObjects = new HashMap<>();
    private Map<String, Item> itemsInDungeon = new HashMap<>();
    private Map<String, Enemy> activeEnemies = new HashMap<>();
    private Map<String, StaticObject> staticObjects = new HashMap<>();
    private String dungeonId = UUID.randomUUID().toString();
    private String dungeonName = "";
    private Player player = null;
    private String config = "";
    private GoalTreeNode goals = null;
    private List<Battle> battles = new ArrayList<>();
    private FactoryChooser factoryChooser = new FactoryChooser();
    private GoalFactory goalFactory = new GoalFactory();
    private String[] buildableItems = { "bow", "shield" };
    private int tickCounter = 0;

    private GoalTreeNode addGoals(JSONObject goal) {
        GoalTreeNode goalTreeNode = new GoalTreeNode();

        String goalStr = goal.getString("goal");

        Goal goalType = goalFactory.createGoal(goalStr);

        if (goalType == null) {
            JSONArray subgoals = goal.getJSONArray("subgoals");
            goalTreeNode.setSubGoalType(goalStr);
            goalTreeNode.setLeftChild(addGoals(subgoals.getJSONObject(0)));
            goalTreeNode.setRightChild(addGoals(subgoals.getJSONObject(1)));
        } else {
            goalTreeNode.setGoal(goalType);
        }

        return goalTreeNode;
    }

    private boolean getAllGoals(Dungeon dungeon, StringBuilder allGoals, GoalTreeNode goalTreeNode) {
        if (goalTreeNode.getGoal() == null) {
            StringBuilder leftString = new StringBuilder("");
            StringBuilder rightString = new StringBuilder("");
            Boolean leftGoalAchieved = getAllGoals(dungeon, leftString, goalTreeNode.getLeftChild());
            Boolean rightGoalAchieved = getAllGoals(dungeon, rightString, goalTreeNode.getRightChild());
            if (leftGoalAchieved == false && rightGoalAchieved == false) {
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
        this.dungeonName = dungeonName;
        try {
            JSONObject resource = new JSONObject(FileLoader.loadResourceFile("/dungeons/" + dungeonName + ".json"));
            JSONArray array = resource.getJSONArray("entities");

            for (int i = 0; i < array.length(); i++) {
                JSONObject a = array.getJSONObject(i);
                int x = a.getInt("x");
                int y = a.getInt("y");
                String type = a.getString("type");
                String portalColour = "";
                int key = -1;
                if (a.has("color")) {
                    portalColour = a.getString("colour");
                }
                if (a.has("key")) {
                    key = a.getInt("key");
                }
                DungeonObjectFactory dungeonObjectFactory = this.factoryChooser.getFactory(type);
                dungeonObjectFactory.create(new Position(x, y), type, this, portalColour, key);
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

    public void addToBattles(Battle battle) {
        this.battles.add(battle);
    }

    public DungeonResponse getDungeonResponse() {
        // List<String> builables = new ArrayList<>();
        // List<BattleResponse> battleResponses = new ArrayList<>();
        // List<ItemResponse> inventory = new ArrayList<>();
        // List<EntityResponse> entities = new ArrayList<>();

        // for (DungeonObject d : this.dungeonObjects.values()) {
        // entities.add(new EntityResponse(
        // d.getUniqueId(),
        // d.getType(),
        // d.getPosition(),
        // d.isInteractable()));
        // }

        // for (Item i : this.player.getInventory()) {
        // inventory.add(new ItemResponse(i.getUniqueId(), i.getType()));
        // }

        // for (Battle b : this.battles) {
        // List<RoundResponse> rounds = new ArrayList<>();

        // for (Round r : b.getRounds()) {
        // List<ItemResponse> itemsUsed = new ArrayList<>();

        // for (Item i : r.getWeaponsUsed()) {
        // itemsUsed.add(new ItemResponse(i.getUniqueId(), i.getType()));
        // }

        // rounds.add(new RoundResponse(r.getPlayerHealthChange(),
        // r.getEnemyHealthChange(), itemsUsed));
        // }

        // battleResponses.add(new BattleResponse(b.getEnemyType(), rounds,
        // b.getInitialPlayerHealth(),
        // b.getInitialEnemyHealth()));
        // }

        // for (String s : buildableItems) {
        // if (this.player.checkBuildables(s)) {
        // builables.add(s);
        // }
        // }

        // return new DungeonResponse(this.dungeonId, this.dungeonName, entities,
        // inventory, battleResponses, builables,
        // getGoals());

        return null;
    }

    public String getGoals() {
        if (this.goals.getLeftChild() == null && this.goals.getRightChild() == null) {
            StringBuilder allGoals = new StringBuilder("");
            goals.getGoal().hasAchieved(this, allGoals);
            return allGoals.toString();
        }

        StringBuilder leftString = new StringBuilder("");
        StringBuilder rightString = new StringBuilder("");
        Boolean leftGoalAchieved = getAllGoals(this, leftString, this.goals.getLeftChild());
        Boolean rightGoalAchieved = getAllGoals(this, rightString, this.goals.getRightChild());

        if (leftGoalAchieved == false && rightGoalAchieved == false) {
            return leftString + " " + this.goals.getSubGoalType() + " " + rightString;
        }

        if (this.goals.getSubGoalType().equals("OR")) {
            return "";
        }

        String allGoals = leftString.toString() + rightString.toString();

        if (leftGoalAchieved && rightGoalAchieved && allGoals.contains(":exit")) {
            return "";
        }

        return allGoals;
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

    public List<DungeonObject> getObjectsAtPosition(int x, int y) {
        List<DungeonObject> objects = new ArrayList<>();
        for (DungeonObject d : this.dungeonObjects.values()) {
            if (d.getPosition().getX() == x && d.getPosition().getY() == y) {
                objects.add(d);
            }
        }
        return objects;
    }

    private boolean hasObjectAtPosition(Position position) {
        for (DungeonObject d : this.dungeonObjects.values()) {
            if (d.getPosition().equals(position)) {
                return true;
            }
        }

        return false;
    }

    public void updateSpawnSpider() {
        // int spiderSpawnRate = getConfig("spider_spawn_rate");

        // if (spiderSpawnRate == 0) {
        // return;
        // }

        // tickCounter++;

        // if (tickCounter % spiderSpawnRate != 0) {
        // return;
        // }

        // DungeonObjectFactory spiderFactory =
        // this.factoryChooser.getFactory("spider");
        // Enemy newSpider = getActiveEnemy(
        // spiderFactory.create(new Position(0, 0), "spider", this, "",
        // -1).getUniqueId());

        // Random rng = new Random();
        // int spider_x = rng.nextInt(MAX_SPIDER_SPAWN - MIN_SPIDER_SPAWN + 1) +
        // MIN_SPIDER_SPAWN;
        // int spider_y = rng.nextInt(MAX_SPIDER_SPAWN - MIN_SPIDER_SPAWN + 1) +
        // MIN_SPIDER_SPAWN;

        // Position spiderPosition = new Position(spider_x, spider_y);

        // while (hasObjectAtPosition(spiderPosition)) {
        // if (getObjectsAtPosition(spider_x, spider_y).stream()
        // .allMatch(o -> o.accept(this, newSpider, o.getUniqueId()) == true)) {
        // return;
        // }
        // spider_x = rng.nextInt(MAX_SPIDER_SPAWN - MIN_SPIDER_SPAWN + 1) +
        // MIN_SPIDER_SPAWN;
        // spider_y = rng.nextInt(MAX_SPIDER_SPAWN - MIN_SPIDER_SPAWN + 1) +
        // MIN_SPIDER_SPAWN;
        // spiderPosition = new Position(spider_x, spider_y);
        // }

        // newSpider.setPosition(spiderPosition);
    }
}
