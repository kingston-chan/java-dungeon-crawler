package dungeonmania;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.FileLoader;
import dungeonmania.util.Position;
import dungeonmania.util.MapStoring;

import java.util.List;

public class DungeonManiaController {
    private static Dungeon currentDungeonInstance = null;

    public static Dungeon getDungeon() {
        return currentDungeonInstance;
    }

    public String getSkin() {
        return "default";
    }

    public String getLocalisation() {
        return "en_US";
    }

    /**
     * /dungeons
     */
    public static List<String> dungeons() {
        return FileLoader.listFileNamesInResourceDirectory("dungeons");
    }

    /**
     * /configs
     */
    public static List<String> configs() {
        return FileLoader.listFileNamesInResourceDirectory("configs");
    }

    /**
     * /game/new
     */
    public DungeonResponse newGame(String dungeonName, String configName) throws IllegalArgumentException {
        Dungeon newDungeon = new Dungeon();
        currentDungeonInstance = newDungeon;
        String newDungeonId = newDungeon.initDungeon(dungeonName, configName);
        if (newDungeonId == null) {
            currentDungeonInstance = null;
            throw new IllegalArgumentException();
        }
        return newDungeon.getDungeonResponse();
    }

    /**
     * /game/dungeonResponseModel
     */
    public DungeonResponse getDungeonResponseModel() {
        return currentDungeonInstance.getDungeonResponse();
    }

    /**
     * /game/tick/item
     */
    public DungeonResponse tick(String itemUsedId) throws IllegalArgumentException, InvalidActionException {
        Player player = currentDungeonInstance.getPlayer();

        if (!player.hasInInventory(itemUsedId)) {
            player.consumeQueuedPotionEffect();
            player.notifyAllObservers();
            throw new InvalidActionException(itemUsedId);
        }

        if (!player.use(itemUsedId)) {
            player.consumeQueuedPotionEffect();
            player.notifyAllObservers();
            throw new IllegalArgumentException();
        }

        player.consumeQueuedPotionEffect();
        player.notifyAllObservers();

        return currentDungeonInstance.getDungeonResponse();
    }

    /**
     * /game/tick/movement
     */
    public DungeonResponse tick(Direction movementDirection) {
        Player player = currentDungeonInstance.getPlayer();

        player.consumeQueuedPotionEffect();

        Position moveTo = new Position(player.getPosition().getX() + movementDirection.getOffset().getX(),
                player.getPosition().getY() + movementDirection.getOffset().getY());

        if (currentDungeonInstance.getStaticObjectsAtPosition(moveTo).stream().allMatch(o -> o.canAccept(player))) {
            Position playerOldPosition = player.getPosition();
            currentDungeonInstance.getStaticObjectsAtPosition(moveTo).stream().forEach(o -> o.doAccept(player));
            if (playerOldPosition == player.getPosition()) {
                currentDungeonInstance.getNonPlayableActorsAtPosition(moveTo).stream().forEach(o -> o.doAccept(player));
                currentDungeonInstance.getItems().stream().filter(i -> i.getPosition().equals(moveTo))
                        .forEach(o -> o.doAccept(player));
                player.setPreviousPosition(playerOldPosition);
                player.setPosition(moveTo);
            }
        }

        player.notifyAllObservers();

        return currentDungeonInstance.getDungeonResponse();
    }

    /**
     * /game/build
     */
    public DungeonResponse build(String buildable) throws IllegalArgumentException, InvalidActionException {
        Player player = currentDungeonInstance.getPlayer();

        if (!player.canBuild(buildable)) {
            throw new IllegalArgumentException();
        }

        if (!player.checkBuildables(buildable)) {
            throw new InvalidActionException(buildable);
        }

        player.build(buildable);

        return currentDungeonInstance.getDungeonResponse();
    }

    /**
     * /game/interact
     */
    public DungeonResponse interact(String entityId) throws IllegalArgumentException, InvalidActionException {
        Player player = currentDungeonInstance.getPlayer();

        if (currentDungeonInstance.getDungeonObject(entityId) == null) {
            throw new IllegalArgumentException();
        }

        if (!player.interact(entityId)) {
            throw new InvalidActionException(entityId);
        }

        return currentDungeonInstance.getDungeonResponse();
    }

    /**
     * /game/save
     */
    public DungeonResponse saveGame(String name) throws IllegalArgumentException {
        MapStoring.saveDungeon(name, getDungeon());
        return currentDungeonInstance.getDungeonResponse();
    }

    /**
     * /game/load
     */
    public DungeonResponse loadGame(String name) throws IllegalArgumentException {
        currentDungeonInstance = MapStoring.loadDungeon(name);
        return currentDungeonInstance.getDungeonResponse();
    }

    /**
     * /games/all
     */
    public List<String> allGames() {
        return MapStoring.getAllGames();
    }

    public DungeonResponse generateDungeon(int xStart, int yStart, int xEnd, int yEnd, String configName) {
        Dungeon newDungeon = new Dungeon();
        currentDungeonInstance = newDungeon;
        String newDungeonId = newDungeon.initMazeDungeon(xStart, yStart, xEnd, yEnd, configName);
        if (newDungeonId == null) {
            currentDungeonInstance = null;
            throw new IllegalArgumentException();
        }
        return newDungeon.getDungeonResponse();
    }
}
