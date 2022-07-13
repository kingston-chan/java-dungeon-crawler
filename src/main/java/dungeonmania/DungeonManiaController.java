package dungeonmania;

import dungeonmania.entities.Dungeon;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.FileLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DungeonManiaController {
    private static Map<String, Dungeon> dungeons = new HashMap<>();
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
<<<<<<< HEAD
        String newDungeonId = newDungeon.initDungeon(dungeonName, configName);
        if (newDungeonId == null) {
            throw new IllegalArgumentException();
        }
        dungeons.put(newDungeonId, newDungeon);
        currentDungeonInstance = newDungeon;
=======
        currentDungeonInstance = newDungeon;
        String newDungeonId = newDungeon.initDungeon(dungeonName, configName);
        if (newDungeonId == null) {
            currentDungeonInstance = null;
            throw new IllegalArgumentException();
        }
        dungeons.put(newDungeonId, newDungeon);
>>>>>>> 330aa486fcff1036064af87e303a4c5dbb30daaa
        return newDungeon.getDungeonResponse();
    }

    /**
     * /game/dungeonResponseModel
     */
    public DungeonResponse getDungeonResponseModel() {
        return null;
    }

    /**
     * /game/tick/item
     */
    public DungeonResponse tick(String itemUsedId) throws IllegalArgumentException, InvalidActionException {
        return null;
    }

    /**
     * /game/tick/movement
     */
    public DungeonResponse tick(Direction movementDirection) {
        return null;
    }

    /**
     * /game/build
     */
    public DungeonResponse build(String buildable) throws IllegalArgumentException, InvalidActionException {
        return null;
    }

    /**
     * /game/interact
     */
    public DungeonResponse interact(String entityId) throws IllegalArgumentException, InvalidActionException {
        return null;
    }
}
