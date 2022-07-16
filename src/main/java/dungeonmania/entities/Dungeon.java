package dungeonmania.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import dungeonmania.behaviours.movement.SpiderMovement;
import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.actor.nonplayableactor.Spider;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.battle.Battle;
import dungeonmania.entities.goal.Goal;
import dungeonmania.entities.goal.GoalFactory;
import dungeonmania.entities.item.Item;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.boulder.Boulder;
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
    private Player player = null;

    private String getGoals() {
        return this.goals.hasAchieved() ? "" : this.goals.toString().replaceAll("^\\(|\\)$", "");
    }

    private void initialiseAnySwitches() {
        getStaticObjects().stream().forEach(o1 -> {
            getStaticObjectsAtPosition(o1.getPosition()).stream()
                    .filter(o2 -> o2 instanceof Boulder)
                    .forEach(o2 -> o1.doAccept((Boulder) o2));
        });
    }

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
                if (a.has("colour")) {
                    portalColour = a.getString("colour");
                }
                if (a.has("key")) {
                    key = a.getInt("key");
                }
                DungeonObjectFactory dungeonObjectFactory = this.factoryChooser.getFactory(type);
                dungeonObjectFactory.create(new Position(x, y), type, portalColour, key);
            }

            this.goals = GoalFactory.parseJsonToGoals(resource.getJSONObject("goal-condition"));
            initialiseAnySwitches();
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

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
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
        List<String> builables = new ArrayList<>();
        List<BattleResponse> battleResponses = new ArrayList<>();
        List<ItemResponse> inventory = new ArrayList<>();
        List<EntityResponse> entities = new ArrayList<>();

        getDungeonObjects().forEach(d -> entities.add(new EntityResponse(
                d.getUniqueId(),
                d.getType(),
                d.getPosition(),
                d.isInteractable())));

        getPlayer().getInventory().forEach(i -> inventory.add(new ItemResponse(i.getUniqueId(), i.getType())));

        this.battles.forEach(b -> {
            List<RoundResponse> rounds = new ArrayList<>();
            b.getRounds().forEach(r -> {
                List<ItemResponse> itemsUsed = new ArrayList<>();
                r.getPlayerWeaponsUsed().forEach(i -> {
                    itemsUsed.add(new ItemResponse(i.getUniqueId(), i.getType()));
                });
                rounds.add(new RoundResponse(r.getPlayerHealthChange(),
                        r.getEnemyHealthChange(), itemsUsed));
            });
            battleResponses.add(new BattleResponse(b.getEnemyType(), rounds,
                    b.getInitialPlayerHealth(),
                    b.getInitialEnemyHealth()));
        });

        for (String s : buildableItems) {
            if (getPlayer().checkBuildables(s)) {
                builables.add(s);
            }
        }

        return new DungeonResponse(this.dungeonId, this.dungeonName, entities,
                inventory, battleResponses, builables,
                getGoals());
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

    public void updateSpawnSpider() {
        int spiderSpawnRate = getConfig("spider_spawn_rate");

        if (spiderSpawnRate == 0) {
            return;
        }

        tickCounter++;

        if (tickCounter % spiderSpawnRate != 0) {
            return;
        }

        Random rng = new Random();
        int spider_x = rng.nextInt(MAX_SPIDER_SPAWN - MIN_SPIDER_SPAWN + 1) +
                MIN_SPIDER_SPAWN;
        int spider_y = rng.nextInt(MAX_SPIDER_SPAWN - MIN_SPIDER_SPAWN + 1) +
                MIN_SPIDER_SPAWN;

        Position spiderPosition = new Position(spider_x, spider_y);

        Spider newSpider = new Spider();
        newSpider.setAttackPoints(getConfig("spider_attack"));
        newSpider.setHealthPoints(getConfig("spider_health"));
        newSpider.setType("spider");
        newSpider.setUniqueId(UUID.randomUUID().toString());

        while (!getObjectsAtPosition(spiderPosition).stream()
                .allMatch(o -> o.canAccept(newSpider))) {
            spider_x = rng.nextInt(MAX_SPIDER_SPAWN - MIN_SPIDER_SPAWN + 1) +
                    MIN_SPIDER_SPAWN;
            spider_y = rng.nextInt(MAX_SPIDER_SPAWN - MIN_SPIDER_SPAWN + 1) +
                    MIN_SPIDER_SPAWN;
            spiderPosition = new Position(spider_x, spider_y);
        }

        newSpider.setDefaultMovement(new SpiderMovement(spiderPosition));
        newSpider.setCurrentMovement(new SpiderMovement(spiderPosition));
        newSpider.setPosition(spiderPosition);

        addDungeonObject(newSpider.getUniqueId(), newSpider);
    }
}
