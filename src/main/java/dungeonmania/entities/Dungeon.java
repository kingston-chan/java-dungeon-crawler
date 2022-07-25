package dungeonmania.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
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
import dungeonmania.entities.goal.ExitGoal;
import dungeonmania.entities.goal.Goal;
import dungeonmania.entities.goal.GoalFactory;
import dungeonmania.entities.item.Item;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.boulder.Boulder;
import dungeonmania.factory.DungeonObjectFactory;
import dungeonmania.factory.FactoryChooser;
import dungeonmania.factory.FactoryHelpers;
import dungeonmania.factory.actorfactory.PlayerBuilder;
import dungeonmania.factory.staticobjectfactory.ExitBuilder;
import dungeonmania.factory.staticobjectfactory.WallBuilder;
import dungeonmania.response.models.BattleResponse;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.response.models.RoundResponse;
import dungeonmania.util.FileLoader;
import dungeonmania.util.Position;

public class Dungeon {
    private final int MAX_SPIDER_SPAWN = 15;
    private final int MIN_SPIDER_SPAWN = -15;

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
        this.dungeonName = dungeonName;
        try {
            this.config = FileLoader.loadResourceFile("/configs/" + configName + ".json");
            JSONObject resource = new JSONObject(FileLoader.loadResourceFile("/dungeons/" + dungeonName + ".json"));
            JSONArray array = resource.getJSONArray("entities");

            for (int i = 0; i < array.length(); i++) {
                JSONObject a = array.getJSONObject(i);
                DungeonObjectFactory dungeonObjectFactory = this.factoryChooser
                        .getFactory(FactoryHelpers.extractType(a));
                dungeonObjectFactory.create(a);
            }

            this.goals = GoalFactory.parseJsonToGoals(resource.getJSONObject("goal-condition"));
            initialiseAnySwitches();
            return this.dungeonId;
        } catch (Exception e) {
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

    public int getIntConfig(String configKey) {
        try {
            JSONObject resource = new JSONObject(this.config);
            return resource.getInt(configKey);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public double getDoubleConfig(String configKey) {
        try {
            JSONObject resource = new JSONObject(this.config);
            return resource.getDouble(configKey);
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
        tickCounter++;

        int spiderSpawnRate = getIntConfig("spider_spawn_rate");

        if (spiderSpawnRate == 0) {
            return;
        }

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
        newSpider.setAttackPoints(getIntConfig("spider_attack"));
        newSpider.setHealthPoints(getIntConfig("spider_health"));
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

        getObjectsAtPosition(spiderPosition).forEach(o -> o.doAccept(newSpider));
    }

    private List<Position> getCardinalAdjacentDistanceTwo(Position currPos) {
        List<Position> adjacentCardinalPositionsDistTwo = new ArrayList<>();
        int x = currPos.getX();
        int y = currPos.getY();
        adjacentCardinalPositionsDistTwo.add(new Position(x, y - 2));
        adjacentCardinalPositionsDistTwo.add(new Position(x + 2, y));
        adjacentCardinalPositionsDistTwo.add(new Position(x, y + 2));
        adjacentCardinalPositionsDistTwo.add(new Position(x - 2, y));
        return adjacentCardinalPositionsDistTwo;
    }

    private boolean isWithinBoundary(int minX, int maxX, int minY, int maxY, Position currPos) {
        return (currPos.getX() > minX && currPos.getX() < maxX) && (currPos.getY() > minY && currPos.getY() < maxY);
    }

    private List<Position> getWallNeighbours(int minX, int maxX, int minY, int maxY, List<Position> allPositions,
            Position currPos) {
        List<Position> adjPosDistTwo = getCardinalAdjacentDistanceTwo(currPos);
        return adjPosDistTwo.stream()
                .filter(p1 -> allPositions.contains(p1))
                .filter(p1 -> isWithinBoundary(minX, maxX, minY, maxY, p1))
                .collect(Collectors.toList());

    }

    private List<Position> getEmptyNeighbours(int minX, int maxX, int minY, int maxY, List<Position> allPositions,
            Position currPos) {
        List<Position> adjPosDistTwo = getCardinalAdjacentDistanceTwo(currPos);
        return adjPosDistTwo.stream()
                .filter(p1 -> !allPositions.contains(p1))
                .filter(p1 -> isWithinBoundary(minX, maxX, minY, maxY, p1))
                .collect(Collectors.toList());
    }

    private Boolean isBetween(int bound1, int bound2, int toBeChecked) {
        return (bound1 < toBeChecked && toBeChecked < bound2) || (bound1 > toBeChecked && toBeChecked > bound2);
    }

    private Position getPositionBetween(Position p1, Position p2) {
        if ((p1.getX() - p2.getX()) == 0) {
            return p1.getAdjacentCardinalPositions().stream()
                    .filter(p -> p.getX() == p1.getX())
                    .filter(p -> isBetween(p1.getY(), p2.getY(), p.getY()))
                    .findFirst().get();
        } else {
            return p1.getAdjacentCardinalPositions().stream()
                    .filter(p -> p.getY() == p1.getY())
                    .filter(p -> isBetween(p1.getX(), p2.getX(), p.getX()))
                    .findFirst().get();
        }
    }

    public String initMazeDungeon(int xStart, int yStart, int xEnd, int yEnd, String configName) {
        try {
            this.config = FileLoader.loadResourceFile("/configs/" + configName + ".json");
        } catch (Exception e) {
            return null;
        }

        this.dungeonName = "maze-" + this.dungeonId;

        int mazeStartX = xStart - 1;
        int mazeStartY = yStart - 1;
        int mazeEndX = xEnd + 1;
        int mazeEndY = yEnd + 1;

        List<Position> allPositionsInMaze = new ArrayList<>();

        for (int y = mazeStartY; y <= mazeEndY; y++) {
            for (int x = mazeStartX; x <= mazeEndX; x++) {
                allPositionsInMaze.add(new Position(x, y));
            }
        }

        Position startPos = new Position(xStart, yStart);

        allPositionsInMaze.remove(startPos);

        Queue<Position> options = new LinkedList<>(
                getWallNeighbours(mazeStartX, mazeEndX, mazeStartY, mazeEndY, allPositionsInMaze, startPos));

        while (!options.isEmpty()) {
            Position next = options.poll();

            List<Position> emtpyNeighbours = getEmptyNeighbours(mazeStartX, mazeEndX, mazeStartY, mazeEndY,
                    allPositionsInMaze, next);

            if (!emtpyNeighbours.isEmpty()) {
                Random rng = new Random();
                Position distTwoNeighbour = emtpyNeighbours.get(rng.nextInt(emtpyNeighbours.size()));
                Position positionBetween = getPositionBetween(next, distTwoNeighbour);
                allPositionsInMaze.remove(distTwoNeighbour);
                allPositionsInMaze.remove(positionBetween);
                allPositionsInMaze.remove(next);
            }

            getWallNeighbours(mazeStartX, mazeEndX, mazeStartY, mazeEndY,
                    allPositionsInMaze, next).forEach(p -> {
                        if (!options.contains(p)) {
                            options.add(p);
                        }
                    });
        }

        Position exit = new Position(xEnd, yEnd);

        if (allPositionsInMaze.contains(exit)) {
            allPositionsInMaze.remove(exit);

            List<Position> neighbourDistOne = exit.getAdjacentCardinalPositions().stream()
                    .filter(p -> isWithinBoundary(mazeStartX, mazeEndX, mazeStartY, mazeEndY, p))
                    .collect(Collectors.toList());

            if (neighbourDistOne.stream().allMatch(p -> allPositionsInMaze.contains(p))) {
                Random rng = new Random();
                Position neighbour = neighbourDistOne.get(rng.nextInt(neighbourDistOne.size()));
                allPositionsInMaze.remove(neighbour);
            }
        }

        WallBuilder wallBuilder = new WallBuilder();

        allPositionsInMaze.forEach(p -> {
            JSONObject wall = new JSONObject();
            wall.put("type", "wall");
            wall.put("x", p.getX());
            wall.put("y", p.getY());
            wallBuilder.buildStaticObject(wall);
        });

        PlayerBuilder playerBuilder = new PlayerBuilder();
        JSONObject player = new JSONObject();
        player.put("type", "player");
        player.put("x", xStart);
        player.put("y", yStart);
        playerBuilder.buildActor(player);

        ExitBuilder exitBuilder = new ExitBuilder();
        JSONObject exitJObject = new JSONObject();
        exitJObject.put("type", "exit");
        exitJObject.put("x", xEnd);
        exitJObject.put("y", yEnd);
        exitBuilder.buildStaticObject(exitJObject);

        this.goals = new ExitGoal();

        return this.dungeonId;
    }

}
