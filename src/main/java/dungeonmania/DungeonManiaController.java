package dungeonmania;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.Item;
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
import java.util.Optional;

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
        currentDungeonInstance = newDungeon;
        String newDungeonId = newDungeon.initDungeon(dungeonName, configName);
        if (newDungeonId == null) {
            currentDungeonInstance = null;
            throw new IllegalArgumentException();
        }
        dungeons.put(newDungeonId, newDungeon);
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

        // throw InvalidActionException if item is not in player's inventory
        if (!currentDungeonInstance.getPlayer()
                                   .getInventory()
                                   .stream()
                                   .anyMatch(inventory_item -> inventory_item.getType().equals(itemUsedId))){
            throw new InvalidActionException("Item is not in player's inventory");
        }

        Optional<Item> tmp = currentDungeonInstance.getItems()
                                                    .stream()
                                                    .filter(item -> item.getUniqueId().equals(itemUsedId))
                                                    .findFirst();

        if(!tmp.isPresent()){
            throw new IllegalArgumentException("Item is not exist now");
        }

        Item object_item = tmp.get();
        Player player = currentDungeonInstance.getPlayer();
        if (!object_item.playerUse(player)){
            throw new IllegalArgumentException("Item cannot used by player");
        }

        return getDungeonResponseModel();
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
