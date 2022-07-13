package dungeonmania.entities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.battle.Battle;
import dungeonmania.entities.goal.ComplexGoal;
import dungeonmania.entities.goal.Goal;
import dungeonmania.entities.goal.GoalFactory;
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
    private List<Battle> battles = new ArrayList<>();
    private String dungeonId = UUID.randomUUID().toString();
    private String dungeonName = "";
    private String config = "";
    private Goal goals = null;
    private FactoryChooser factoryChooser = new FactoryChooser();
    private String[] buildableItems = { "bow", "shield" };
    private int tickCounter = 0;

    public String initDungeon(String dungeonName, String configName) {
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
                dungeonObjectFactory.create(new Position(x, y), type, portalColour, key);
            }

            this.goals = GoalFactory.parseJsonToGoals(resource.getJSONObject("goal-condition"));
            return this.dungeonId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public DungeonObject getDungeonObject(String uniqueId) {
        return this.dungeonObjects.get(uniqueId);
    }

    public void removeDungeonObject(String uniqueId) {
        this.dungeonObjects.remove(uniqueId);
    }

    public void addDungeonObject(String uniqueId, DungeonObject dungeonObject) {
        this.dungeonObjects.put(uniqueId, dungeonObject);
    }

    public List<DungeonObject> getDungeonObjects() {
        return new ArrayList<>(this.dungeonObjects.values());
    }

    public List<Item> getItems() {
        return this.dungeonObjects.values().stream()
                .filter(dungeonObject -> dungeonObject instanceof Item)
                .map(item -> (Item) item)
                .collect(Collectors.toList());
    }

    public List<NonPlayableActor> getNonPlayableActors() {
        return this.dungeonObjects.values().stream()
                .filter(dungeonObject -> dungeonObject instanceof NonPlayableActor)
                .map(enemy -> (NonPlayableActor) enemy)
                .collect(Collectors.toList());
    }

    public List<StaticObject> getStaticObjects() {
        return this.dungeonObjects.values().stream()
                .filter(dungeonObject -> dungeonObject instanceof StaticObject)
                .map(staticObject -> (StaticObject) staticObject)
                .collect(Collectors.toList());
    }

    public Player getPlayer() {
        return this.dungeonObjects.values().stream()
                .filter(dungeonObject -> dungeonObject instanceof Player)
                .map(player -> (Player) player).findFirst().get();
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

    public Boolean getGoals() {
        return this.goals.hasAchieved();
    }

    public List<StaticObject> getStaticObjectsAtPosition(Position position) {
        return getStaticObjects().stream()
                .filter(staticObject -> staticObject.getPosition().equals(position))
                .collect(Collectors.toList());
    }

    public List<NonPlayableActor> getNonPlayableActorsAtPosition(Position position) {
        return getNonPlayableActors().stream()
                .filter(enemy -> enemy.getPosition().equals(position))
                .collect(Collectors.toList());
    }

    public List<DungeonObject> getObjectsAtPosition(Position position) {
        return this.dungeonObjects.values().stream()
                .filter(dungeonObject -> dungeonObject.getPosition().equals(position))
                .collect(Collectors.toList());
    }

    private boolean hasObjectAtPosition(Position position) {
        return getDungeonObjects().stream()
                .anyMatch(dungeonObject -> dungeonObject.getPosition().equals(position));
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
