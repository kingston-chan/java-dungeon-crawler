package dungeonmania.player;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static dungeonmania.TestUtils.getPlayer;
import static dungeonmania.TestUtils.getEntities;
import static dungeonmania.TestUtils.getInventory;
import static dungeonmania.TestUtils.getGoals;
import static dungeonmania.TestUtils.countEntityOfType;
import static dungeonmania.TestUtils.getValueFromConfigFile;
import static dungeonmania.TestUtils.assertListAreEqualIgnoringOrder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.Item;
import dungeonmania.entities.item.collectables.Arrows;
import dungeonmania.entities.item.collectables.Key;
import dungeonmania.entities.item.collectables.Treasure;
import dungeonmania.entities.item.collectables.Wood;
import dungeonmania.entities.item.equipment.Sword;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.BattleResponse;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.response.models.RoundResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

/**
 * Player tests
 * 
 * @author Kingston Chan
 */
public class PlayerUnitWhiteBoxTests {
    @Test
    public void testAddItemToInventory() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_unusableItems", "c_battleTests_basicMercenaryMercenaryDies");
        Dungeon testDungeon = DungeonManiaController.getDungeon();
        Player player = testDungeon.getPlayer();
        List<Item> items = testDungeon.getItems();

        // Items that are not picked up
        testDungeon.getItems().stream().forEach(i -> player.visit(i));

        assertTrue(player.getInventory().size() == 5);
        assertTrue(testDungeon.getItems().size() == 0);
        assertListAreEqualIgnoringOrder(player.getInventory(), items);
    }

    @Test
    public void testRemoveItemFromInventory() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_unusableItems", "c_battleTests_basicMercenaryMercenaryDies");
        Dungeon testDungeon = DungeonManiaController.getDungeon();
        Player player = testDungeon.getPlayer();
        List<Item> items = testDungeon.getItems();

        // Items that are not picked up
        testDungeon.getItems().stream().forEach(i -> player.visit(i));

        Item itemToBeRemoved = items.get(3);
        items.remove(itemToBeRemoved);

        player.removeFromInventory(itemToBeRemoved);

        assertTrue(player.getInventory().size() == 4);
        assertListAreEqualIgnoringOrder(player.getInventory(), items);
    }

    @Test
    public void testNoItemWithIdInInventory() {
        Player player = new Player();
        assertFalse(player.hasInInventory("notanid"));
    }

    @Test
    public void testRemoveTreasures() {
        // Player player = new Player();
        // Item sword = new Sword();
        // Item treasure1 = new Treasure();
        // Item treasure2 = new Treasure();
        // treasure1.collectedBy(player);
        // treasure2.collectedBy(player);
        // assertTrue(player.removeTreasures(2));
        // assertListAreEqualIgnoringOrder(player.getInventory(), Arrays.asList(sword));
    }

    @Test
    public void testGetKeyPlayerHasKey() {
        // Player player = new Player();
        // Item key = new Key();
        // key.collectedBy(player);
        // assertEquals(player.getKey(), key);
    }

    @Test
    public void testGetKeyPlayerHasNoKey() {
        Player player = new Player();
        assertEquals(player.getKey(), null);
    }

    @Test
    public void testMercenaryInteractOutOfRange() {
        // Dungeon testDungeon = new Dungeon();
        // testDungeon.initDungeon("d_simpleMercenaryInteract",
        // "c_battleTests_basicMercenaryMercenaryDies");
        // String mercId = testDungeon.getActiveEnemies().get(0).getUniqueId();
        // Player player = testDungeon.getPlayer();
        // Item treasure = new Treasure();
        // treasure.collectedBy(player);
        // assertFalse(player.interact(testDungeon, mercId));
    }

    @Test
    public void testBribeMercenary() {
        // Dungeon testDungeon = new Dungeon();
        // testDungeon.initDungeon("d_simpleMercenaryInteract",
        // "c_battleTests_basicMercenaryMercenaryDies");
        // String mercId = testDungeon.getActiveEnemies().get(0).getUniqueId();
        // Player player = testDungeon.getPlayer();
        // Item treasure = new Treasure();
        // treasure.collectedBy(player);
        // player.setPosition(new Position(3, 1));
        // assertTrue(player.interact(testDungeon, mercId));
    }

    @Test
    public void testZombieToastSpawnerInteractNotAdjacent() {
        // Dungeon testDungeon = new Dungeon();
        // testDungeon.initDungeon("d_simpleZombieToastSpawner",
        // "c_battleTests_basicMercenaryMercenaryDies");
        // String ztsId = testDungeon.getDungeonObjects().get(0).getUniqueId();
        // Player player = testDungeon.getPlayer();
        // Item sword = new Sword();
        // sword.collectedBy(player);
        // assertFalse(player.interact(testDungeon, ztsId));
    }

    @Test
    public void testZombieToastSpawnerInteract() {
        // Dungeon testDungeon = new Dungeon();
        // testDungeon.initDungeon("d_simpleZombieToastSpawner",
        // "c_battleTests_basicMercenaryMercenaryDies");
        // String ztsId = testDungeon.getDungeonObjects().get(0).getUniqueId();
        // Player player = testDungeon.getPlayer();
        // Item sword = new Sword();
        // sword.collectedBy(player);
        // player.setPosition(new Position(3, 1));
        // assertFalse(player.interact(testDungeon, ztsId));
    }

    @Test
    public void testMercenaryInteractNotEnoughTreasures() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_simpleMercenaryInteract", "c_battleTests_basicMercenaryMercenaryDies");
        Dungeon testDungeon = DungeonManiaController.getDungeon();
        String mercId = testDungeon.getNonPlayableActors().get(0).getUniqueId();
        Player player = testDungeon.getPlayer();
        player.setPosition(new Position(3, 1));
        assertFalse(player.interact(testDungeon, mercId));
    }

    @Test
    public void testZombieToastSpawnerInteractNoWeapon() {
        // Dungeon testDungeon = new Dungeon();
        // testDungeon.initDungeon("d_simpleZombieToastSpawner",
        // "c_battleTests_basicMercenaryMercenaryDies");
        // String ztsId = testDungeon.getDungeonObjects().get(0).getUniqueId();
        // Player player = testDungeon.getPlayer();
        // player.setPosition(new Position(3, 1));
        // assertFalse(player.interact(testDungeon, ztsId));
    }

    @Test
    public void testSuccessfullyBuildsBow() {
        // Dungeon testDungeon = new Dungeon();
        // testDungeon.initDungeon("d_simpleZombieToastSpawner",
        // "c_battleTests_basicMercenaryMercenaryDies");
        // Player player = testDungeon.getPlayer();
        // Item wood = new Wood();
        // Item arrow1 = new Arrows();
        // Item arrow2 = new Arrows();
        // Item arrow3 = new Arrows();
        // wood.collectedBy(player);
        // arrow1.collectedBy(player);
        // arrow2.collectedBy(player);
        // arrow3.collectedBy(player);
        // assertDoesNotThrow(() -> player.build(testDungeon, "bow"));
    }

    @Test
    public void testSuccessfullyBuildsShieldWithKey() {
        // Dungeon testDungeon = new Dungeon();
        // testDungeon.initDungeon("d_simpleZombieToastSpawner",
        // "c_battleTests_basicMercenaryMercenaryDies");
        // Player player = testDungeon.getPlayer();
        // Item wood1 = new Wood();
        // Item wood2 = new Wood();
        // Item key = new Key();
        // key.collectedBy(player);
        // wood1.collectedBy(player);
        // wood2.collectedBy(player);
        // assertDoesNotThrow(() -> player.build(testDungeon, "shield"));
    }

    @Test
    public void testSuccessfullyBuildsShieldWithTreasure() {
        // Dungeon testDungeon = new Dungeon();
        // testDungeon.initDungeon("d_simpleZombieToastSpawner",
        // "c_battleTests_basicMercenaryMercenaryDie");
        // Player player = testDungeon.getPlayer();
        // Item wood1 = new Wood();
        // Item wood2 = new Wood();
        // Item treasure = new Treasure();
        // treasure.collectedBy(player);
        // wood1.collectedBy(player);
        // wood2.collectedBy(player);
        // assertDoesNotThrow(() -> player.build(testDungeon, "shield"));
    }

    @Test
    public void testBuildNotBowShield() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_simpleZombieToastSpawner",
                "c_battleTests_basicMercenaryMercenaryDies");
        Dungeon testDungeon = DungeonManiaController.getDungeon();
        Player player = testDungeon.getPlayer();
        assertFalse(player.isValidBuildable("notaboworshield"));
    }

    @Test
    public void testBuildBowNotEnoughItems() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_simpleZombieToastSpawner",
                "c_battleTests_basicMercenaryMercenaryDies");
        Dungeon testDungeon = DungeonManiaController.getDungeon();
        Player player = testDungeon.getPlayer();
        assertFalse(player.checkBuildables("bow"));
    }

    @Test
    public void testBuildShieldNotEnoughItems() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_simpleZombieToastSpawner",
                "c_battleTests_basicMercenaryMercenaryDies");
        Dungeon testDungeon = DungeonManiaController.getDungeon();
        Player player = testDungeon.getPlayer();
        assertFalse(player.checkBuildables("shield"));
    }

    @Test
    public void testGetConsumedPotion() {
        // Dungeon testDungeon = new Dungeon();
        // testDungeon.initDungeon("d_simplePotionTest",
        // "c_battleTests_basicMercenaryMercenaryDies");
        // Item potion = testDungeon.getItemsInDungeon().get(0);
        // Player player = testDungeon.getPlayer();
        // potion.collectedBy(player);
        // player.use(potion);
        // player.notifyEnemies(testDungeon);
        // assertEquals(player.getPotionConsumed(), potion);
    }

    @Test
    public void testGetPotionButPotionExpired() {
        // Dungeon testDungeon = new Dungeon();
        // testDungeon.initDungeon("d_simplePotionTest",
        // "c_battleTests_basicMercenaryMercenaryDies");
        // Item potion = testDungeon.getItemsInDungeon().get(0);
        // Player player = testDungeon.getPlayer();
        // potion.collectedBy(player);
        // player.use(potion);
        // player.notifyEnemies(testDungeon);
        // assertEquals(player.getPotionConsumed(), potion);
        // player.notifyEnemies(testDungeon);
        // assertEquals(player.getPotionConsumed(), null);
    }
}
