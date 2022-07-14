package dungeonmania.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static dungeonmania.TestUtils.assertListAreEqualIgnoringOrder;

import java.util.List;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.nonplayableactor.Mercenary;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.Item;
import dungeonmania.entities.item.Key;
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
    public void testGetKeyPlayerHasKey() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_unusableItems", "c_battleTests_basicMercenaryMercenaryDies");
        Dungeon testDungeon = DungeonManiaController.getDungeon();
        Player player = testDungeon.getPlayer();
        List<Item> items = testDungeon.getItems();

        Key key = items.stream().filter(i -> i instanceof Key).map(i -> (Key) i).findFirst().get();

        testDungeon.getItems().stream().forEach(i -> player.visit(i));
        assertEquals(key, player.getKey());
    }

    @Test
    public void testGetKeyPlayerHasNoKey() {
        Player player = new Player();
        assertEquals(null, player.getKey());
    }

    @Test
    public void testTryPick2Keys() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_2keys", "c_battleTests_basicMercenaryMercenaryDies");
        Dungeon testDungeon = DungeonManiaController.getDungeon();
        Player player = testDungeon.getPlayer();
        List<Item> items = testDungeon.getItems();

        assertTrue(items.size() == 2);

        testDungeon.getItems().stream().forEach(i -> i.doAccept(player));
        assertEquals(1, player.getInventory().size());
    }

    @Test
    public void testMercenaryInteractOutOfRange() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_simpleNotInRangeMercenaryInteract",
                "c_battleTests_basicMercenaryMercenaryDies");
        Dungeon testDungeon = DungeonManiaController.getDungeon();
        String mercId = testDungeon.getNonPlayableActors().get(0).getUniqueId();
        Player player = testDungeon.getPlayer();

        assertFalse(player.interact(mercId));
    }

    @Test
    public void testBribeMercenary() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_simpleMercenaryInteract",
                "c_battleTests_basicMercenaryMercenaryDies");
        Dungeon testDungeon = DungeonManiaController.getDungeon();

        String mercId = testDungeon.getNonPlayableActors().get(0).getUniqueId();
        Mercenary merc = (Mercenary) testDungeon.getNonPlayableActors().get(0);
        Player player = testDungeon.getPlayer();
        // get treasure
        testDungeon.getItems().stream().forEach(i -> i.doAccept(player));
        player.setPosition(new Position(1, 1));
        assertTrue(player.interact(mercId));
        assertEquals(1, player.getNumAllies());
        assertTrue(merc.isAlly());
        assertFalse(merc.isInteractable());
    }

    @Test
    public void testZombieToastSpawnerInteractNotAdjacent() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_simpleZombieToastSpawner",
                "c_battleTests_basicMercenaryMercenaryDies");
        Dungeon testDungeon = DungeonManiaController.getDungeon();

        String ztsId = testDungeon.getStaticObjects().get(0).getUniqueId();
        Player player = testDungeon.getPlayer();
        assertFalse(player.interact(ztsId));
    }

    @Test
    public void testZombieToastSpawnerInteract() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_simpleZombieToastSpawner",
                "c_battleTests_basicMercenaryMercenaryDies");
        Dungeon testDungeon = DungeonManiaController.getDungeon();

        String ztsId = testDungeon.getStaticObjects().get(0).getUniqueId();
        List<Item> items = testDungeon.getItems();
        Player player = testDungeon.getPlayer();
        items.stream().forEach(i -> i.doAccept(player));
        player.setPosition(new Position(3, 1));
        assertTrue(player.interact(ztsId));
        assertEquals(0, testDungeon.getStaticObjects().size());
    }

    @Test
    public void testMercenaryInteractNotEnoughTreasures() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_simpleMercenaryInteract", "c_battleTests_basicMercenaryMercenaryDies");
        Dungeon testDungeon = DungeonManiaController.getDungeon();
        String mercId = testDungeon.getNonPlayableActors().get(0).getUniqueId();
        Player player = testDungeon.getPlayer();
        player.setPosition(new Position(3, 1));
        assertFalse(player.interact(mercId));
    }

    @Test
    public void testZombieToastSpawnerInteractNoWeapon() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_simpleZombieToastSpawner",
                "c_battleTests_basicMercenaryMercenaryDies");
        Dungeon testDungeon = DungeonManiaController.getDungeon();

        String ztsId = testDungeon.getStaticObjects().get(0).getUniqueId();
        Player player = testDungeon.getPlayer();
        player.setPosition(new Position(3, 1));
        assertFalse(player.interact(ztsId));
        assertEquals(1, testDungeon.getStaticObjects().size());
    }

    @Test
    public void testSuccessfullyBuildsBow() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_bow",
                "c_battleTests_basicMercenaryMercenaryDies");
        Dungeon testDungeon = DungeonManiaController.getDungeon();

        Player player = testDungeon.getPlayer();
        testDungeon.getItems().stream().forEach(i -> i.doAccept(player));
        assertEquals(4, player.getInventory().size());
        assertTrue(player.canBuild("bow"));
        assertTrue(player.checkBuildables("bow"));
        player.build("bow");
        assertEquals(1, player.getInventory().size());
        assertTrue(player.getInventory().get(0).getType().equals("bow"));
    }

    @Test
    public void testSuccessfullyBuildsShieldWithKey() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_shield_key",
                "c_battleTests_basicMercenaryMercenaryDies");
        Dungeon testDungeon = DungeonManiaController.getDungeon();

        Player player = testDungeon.getPlayer();
        testDungeon.getItems().stream().forEach(i -> i.doAccept(player));
        assertEquals(3, player.getInventory().size());
        assertTrue(player.canBuild("shield"));
        assertTrue(player.checkBuildables("shield"));
        player.build("shield");
        assertEquals(1, player.getInventory().size());
        assertTrue(player.getInventory().get(0).getType().equals("shield"));
    }

    @Test
    public void testSuccessfullyBuildsShieldWithTreasure() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_shield_treasure",
                "c_battleTests_basicMercenaryMercenaryDies");
        Dungeon testDungeon = DungeonManiaController.getDungeon();

        Player player = testDungeon.getPlayer();
        testDungeon.getItems().stream().forEach(i -> i.doAccept(player));
        assertEquals(3, player.getInventory().size());
        assertTrue(player.canBuild("shield"));
        assertTrue(player.checkBuildables("shield"));
        player.build("shield");
        assertEquals(1, player.getInventory().size());
        assertTrue(player.getInventory().get(0).getType().equals("shield"));
    }

    @Test
    public void testBuildNotBowShield() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_simpleZombieToastSpawner",
                "c_battleTests_basicMercenaryMercenaryDies");
        Dungeon testDungeon = DungeonManiaController.getDungeon();
        Player player = testDungeon.getPlayer();
        assertFalse(player.canBuild("notaboworshield"));
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
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_simplePotionTest",
                "c_battleTests_basicMercenaryMercenaryDies");
        Dungeon testDungeon = DungeonManiaController.getDungeon();

        Item potion = testDungeon.getItems().get(0);
        Player player = testDungeon.getPlayer();
        potion.doAccept(player);
        player.use(potion.getUniqueId());
        player.consumeQueuedPotionEffect();
        assertEquals(player.getPotionConsumed(), potion);
    }

    @Test
    public void testGetPotionButPotionExpired() {
        DungeonManiaController dmc = new DungeonManiaController();

        dmc.newGame("d_simplePotionTest",
                "c_battleTests_basicMercenaryMercenaryDies");
        Dungeon testDungeon = DungeonManiaController.getDungeon();

        Item potion = testDungeon.getItems().get(0);
        Player player = testDungeon.getPlayer();
        potion.doAccept(player);
        player.use(potion.getUniqueId());
        player.consumeQueuedPotionEffect();
        player.consumeQueuedPotionEffect();
        assertEquals(player.getPotionConsumed(), null);
    }
}
