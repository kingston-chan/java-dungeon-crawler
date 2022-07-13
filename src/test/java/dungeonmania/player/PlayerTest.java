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
public class PlayerTest {
    @Nested
    public class PlayerUnitWhiteBoxTests {
        @Test
        public void testAddItemToInventory() {
            // Player player = new Player();
            // Item sword = new Sword();
            // Item bomb = new Bomb();
            // sword.collectedBy(player);
            // bomb.collectedBy(player);
            // assertListAreEqualIgnoringOrder(player.getInventory(), Arrays.asList(sword,
            // bomb));
        }

        @Test
        public void testRemoveItemFromInventory() {
            // Player player = new Player();
            // Item sword = new Sword();
            // Item bomb = new Bomb();
            // sword.collectedBy(player);
            // bomb.collectedBy(player);
            // player.removeFromInventory(sword);
            // assertListAreEqualIgnoringOrder(player.getInventory(), Arrays.asList(bomb));
        }

        @Test
        public void testGetCorrectItem() {
            // Player player = new Player();
            // Item sword = new Sword();
            // sword.setUniqueId("swordId");
            // sword.collectedBy(player);
            // assertEquals(player.getItem("swordId"), sword);
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

    @Nested
    public class PlayerIntegrationWhiteBoxTests {
        @Test
        public void testUseUsableItem() {
            // Dungeon testDungeon = new Dungeon();
            // testDungeon.initDungeon("d_usableItemsTest",
            // "c_battleTests_basicMercenaryMercenaryDies");

            // Item item1 = testDungeon.getItemsInDungeon().get(0);
            // Item item2 = testDungeon.getItemsInDungeon().get(1);
            // Item item3 = testDungeon.getItemsInDungeon().get(2);
            // Player player = testDungeon.getPlayer();

            // item1.collectedBy(player);
            // item2.collectedBy(player);
            // item3.collectedBy(player);

            // assertTrue(player.use(item1));
            // assertTrue(player.use(item2));
            // assertTrue(player.use(item3));
        }

        @Test
        public void testUseUnusableItem() {
            // Dungeon testDungeon = new Dungeon();
            // testDungeon.initDungeon("d_unusableItems",
            // "c_battleTests_basicMercenaryMercenaryDies");

            // Item item1 = testDungeon.getItemsInDungeon().get(0);
            // Item item2 = testDungeon.getItemsInDungeon().get(1);
            // Item item3 = testDungeon.getItemsInDungeon().get(2);
            // Item item4 = testDungeon.getItemsInDungeon().get(3);
            // Item item5 = testDungeon.getItemsInDungeon().get(4);

            // Player player = testDungeon.getPlayer();

            // item1.collectedBy(player);
            // item2.collectedBy(player);
            // item3.collectedBy(player);
            // item4.collectedBy(player);
            // item5.collectedBy(player);

            // assertFalse(player.use(item1));
            // assertFalse(player.use(item2));
            // assertFalse(player.use(item3));
            // assertFalse(player.use(item4));
            // assertFalse(player.use(item5));
        }

        @Test
        public void testSuccessfullyNotifiesEnemiesToMove() {
            // Dungeon testDungeon = new Dungeon();
            // testDungeon.initDungeon("d_simpleActors",
            // "c_battleTests_basicMercenaryMercenaryDies");

            // Player player = testDungeon.getPlayer();

            // Enemy enemy1 = testDungeon.getActiveEnemies().get(0);
            // Enemy enemy2 = testDungeon.getActiveEnemies().get(1);
            // Enemy enemy3 = testDungeon.getActiveEnemies().get(2);

            // Position enemyPosition1 = enemy1.getPosition();
            // Position enemyPosition2 = enemy2.getPosition();
            // Position enemyPosition3 = enemy3.getPosition();

            // player.notifiyEnemies(testDungeon);

            // assertNotEquals(enemyPosition1, enemy1.getPosition());
            // assertNotEquals(enemyPosition2, enemy2.getPosition());
            // assertNotEquals(enemyPosition3, enemy3.getPosition());
        }

        @Test
        public void testSuccessfullyNotifiesEnemiesWithInvincibilityPotion() {
            // Dungeon testDungeon = new Dungeon();
            // testDungeon.initDungeon("d_invinicibilityPotionActors",
            // "c_battleTests_basicMercenaryMercenaryDies");

            // Player player = testDungeon.getPlayer();

            // Enemy enemy1 = testDungeon.getActiveEnemies().get(0);
            // Enemy enemy2 = testDungeon.getActiveEnemies().get(1);
            // Enemy enemy3 = testDungeon.getActiveEnemies().get(2);

            // Position enemyPosition1 = enemy1.getPosition();
            // Position enemyPosition2 = enemy2.getPosition();
            // Position enemyPosition3 = enemy3.getPosition();

            // Item invincibilityPotion = testDungeon.getItemsInDungeon().get(0);

            // invincibilityPotion.collectedBy(player)

            // player.use(invincibilityPotion);

            // player.notifiyEnemies(testDungeon);

            // assertNotEquals(enemyPosition1, enemy1.getPosition());
            // assertNotEquals(enemyPosition2, enemy2.getPosition());
            // assertNotEquals(enemyPosition3, enemy3.getPosition());
        }

        @Test
        public void testSuccessfullyNotifiesEnemiesWithInvisibilityPotion() {
            // Dungeon testDungeon = new Dungeon();
            // testDungeon.initDungeon("d_invisibilityPotionActors",
            // "c_battleTests_basicMercenaryMercenaryDies");

            // Player player = testDungeon.getPlayer();

            // Enemy enemy1 = testDungeon.getActiveEnemies().get(0);
            // Enemy enemy2 = testDungeon.getActiveEnemies().get(1);
            // Enemy enemy3 = testDungeon.getActiveEnemies().get(2);

            // Position enemyPosition1 = enemy1.getPosition();
            // Position enemyPosition2 = enemy2.getPosition();
            // Position enemyPosition3 = enemy3.getPosition();

            // Item invisibilityPotion = testDungeon.getItemsInDungeon().get(0);

            // invisibilityPotion.collectedBy(player);

            // player.use(invisibilityPotion);

            // player.notifiyEnemies(testDungeon);

            // assertNotEquals(enemyPosition1, enemy1.getPosition());
            // assertNotEquals(enemyPosition2, enemy2.getPosition());
            // assertNotEquals(enemyPosition3, enemy3.getPosition());
        }

        @Test
        public void testSuccessfullyNotifiesZombieSpawners() {
            // Dungeon testDungeon = new Dungeon();
            // testDungeon.initDungeon("d_simpleZombieToastSpawner",
            // "c_zombieSpawnRate");

            // Player player = testDungeon.getPlayer();

            // player.notifyZombieToastSpawners();

            // assertTrue(testDungeon.getActiveEnemies().size() > 0);
        }

    }

    @Nested
    public class PlayerUnitBlackBoxTests {

        @Test
        public void testValidIdButNonInteractableEntity() {
            // DungeonManiaController dmc = new DungeonManiaController();
            // DungeonResponse dres = dmc.newGame("d_movementTest_testMovementDown",
            // "c_spiderSpawnRateTest");

            // EntityResponse er1 = dres.getEntities().get(0);
            // EntityResponse er2 = dres.getEntities().get(1);

            // assertThrows(IllegalArgumentException.class, () ->
            // dmc.interact(er1.getId()));
            // assertThrows(IllegalArgumentException.class, () ->
            // dmc.interact(er2.getId()));
        }

        @Test
        public void testInvalidIdInteracts() {
            // DungeonManiaController dmc = new DungeonManiaController();
            // dmc.newGame("d_movementTest_testMovementDown",
            // "c_spiderSpawnRateTest");

            // assertThrows(IllegalArgumentException.class, () ->
            // dmc.interact("notanid");
        }

        @Test
        public void testSuccessfullyPicksUpItem() {
            // DungeonManiaController dmc = new DungeonManiaController();
            // dmc.newGame("d_unusableItems",
            // "c_spiderSpawnRateTest");

            // dmc.tick(Direction.RIGHT);
            // dmc.tick(Direction.RIGHT);
            // dmc.tick(Direction.RIGHT);
            // dmc.tick(Direction.RIGHT);
            // DungeonResponse dres = dmc.tick(Direction.RIGHT);

            // assertTrue(dres.getEntities().size() == 1);
            // assertTrue(dres.getInventory().size() == 5);
        }

        @Test
        public void testUseUsableItem() {
            // DungeonManiaController dmc = new DungeonManiaController();
            // dmc.newGame("d_usableItemsTest",
            // "c_spiderSpawnRateTest");

            // dmc.tick(Direction.RIGHT);
            // dmc.tick(Direction.RIGHT);
            // DungeonResponse dres = dmc.tick(Direction.RIGHT);

            // ItemResponse item1 = dres.getInventory().get(0);
            // ItemResponse item2 = dres.getInventory().get(1);
            // ItemResponse item3 = dres.getInventory().get(2);

            // assertDoesNotThrow(() -> dmc.tick(item1.getId()));
            // assertDoesNotThrow(() -> dmc.tick(item2.getId()));
            // assertDoesNotThrow(() -> dmc.tick(item3.getId()));
        }

        @Test
        public void testUseUnusableItem() {
            // DungeonManiaController dmc = new DungeonManiaController();
            // dmc.newGame("d_unusableItems",
            // "c_spiderSpawnRateTest");

            // dmc.tick(Direction.RIGHT);
            // dmc.tick(Direction.RIGHT);
            // dmc.tick(Direction.RIGHT);
            // dmc.tick(Direction.RIGHT);
            // DungeonResponse dres = dmc.tick(Direction.RIGHT);

            // ItemResponse item1 = dres.getInventory().get(0);
            // ItemResponse item2 = dres.getInventory().get(1);
            // ItemResponse item3 = dres.getInventory().get(2);
            // ItemResponse item4 = dres.getInventory().get(3);
            // ItemResponse item5 = dres.getInventory().get(4);

            // assertThrows(IllegalArgumentException.class, () -> dmc.tick(item1.getId()));
            // assertThrows(IllegalArgumentException.class, () -> dmc.tick(item2.getId()));
            // assertThrows(IllegalArgumentException.class, () -> dmc.tick(item3.getId()));
            // assertThrows(IllegalArgumentException.class, () -> dmc.tick(item4.getId()));
            // assertThrows(IllegalArgumentException.class, () -> dmc.tick(item5.getId()));
        }

        @Test
        public void testSuccessfullyBuildsBow() {
            // DungeonManiaController dmc = new DungeonManiaController();
            // dmc.newGame("d_bow",
            // "c_spiderSpawnRateTest");
            // dmc.tick(Direction.RIGHT);
            // dmc.tick(Direction.RIGHT);
            // dmc.tick(Direction.RIGHT);
            // dmc.tick(Direction.RIGHT);

            // assertDoesNotThrow(() -> dmc.build("bow"));
        }

        @Test
        public void testSuccessfullyBuildsShieldKey() {
            // DungeonManiaController dmc = new DungeonManiaController();
            // dmc.newGame("d_shield_key",
            // "c_spiderSpawnRateTest");

            // dmc.tick(Direction.RIGHT);
            // dmc.tick(Direction.RIGHT);
            // dmc.tick(Direction.RIGHT);

            // assertDoesNotThrow(() -> dmc.build("shield"));
        }

        @Test
        public void testSuccessfullyBuildsShieldTreasure() {
            // DungeonManiaController dmc = new DungeonManiaController();
            // dmc.newGame("d_shield_treasure",
            // "c_spiderSpawnRateTest");

            // dmc.tick(Direction.RIGHT);
            // dmc.tick(Direction.RIGHT);
            // dmc.tick(Direction.RIGHT);

            // assertDoesNotThrow(() -> dmc.build("shield"));
        }

        @Test
        public void testBuildNotBowShield() {
            // DungeonManiaController dmc = new DungeonManiaController();
            // dmc.newGame("d_shield_treasure",
            // "c_spiderSpawnRateTest");

            // assertThrows(IllegalArgumentException.class, () ->
            // dmc.build("notashilednorbow"));
        }

        @Test
        public void testNotEnoughToBuild() {
            // DungeonManiaController dmc = new DungeonManiaController();
            // dmc.newGame("d_shield_key",
            // "c_spiderSpawnRateTest");

            // dmc.tick(Direction.RIGHT);
            // dmc.tick(Direction.RIGHT);

            // assertThrows(InvalidActionException.class, () -> dmc.build("shield"));

            // DungeonManiaController dmc2 = new DungeonManiaController();
            // dmc2.newGame("d_shield_treasure",
            // "c_spiderSpawnRateTest");

            // dmc2.tick(Direction.RIGHT);
            // dmc2.tick(Direction.RIGHT);

            // assertThrows(InvalidActionException.class, () -> dmc2.build("shield"));

            // DungeonManiaController dmc3 = new DungeonManiaController();
            // dmc3.newGame("d_bow",
            // "c_spiderSpawnRateTest");
            // dmc3.tick(Direction.RIGHT);
            // dmc3.tick(Direction.RIGHT);
            // dmc3.tick(Direction.RIGHT);

            // assertThrows(InvalidActionException.class, () -> dmc2.build("bow"));
        }

        @Test
        public void testInteractWithInvalidId() {
            // DungeonManiaController dmc = new DungeonManiaController();
            // dmc.newGame("d_shield_key",
            // "c_spiderSpawnRateTest");

            // assertThrows(IllegalArgumentException.class, () ->
            // dmc.interact("invalidId"));
        }

        @Test
        public void testSuccessfullyInteractsWithMercenary() {
            // DungeonManiaController dmc = new DungeonManiaController();
            // dmc.newGame("d_mercenaryInteract",
            // "c_battleTests_basicMercenaryMercenaryDies");

            // DungeonResponse dres = dmc.tick(Direction.RIGHT);

            // for (EntityResponse e : dres.getEntities()) {
            // if (e.getType().equals("mercenary")) {
            // assertDoesNotThrow(() -> dmc.interact(e.getId()));
            // }
            // }
        }

        @Test
        public void testSuccessfullyInteractsWithZombieToastSpawner() {
            // DungeonManiaController dmc = new DungeonManiaController();
            // dmc.newGame("d_zombieSpawnerInteract",
            // "c_battleTests_basicMercenaryMercenaryDies");

            // DungeonResponse dres = dmc.tick(Direction.RIGHT);

            // for (EntityResponse e : dres.getEntities()) {
            // if (e.getType().equals("zombie_toast_spawner")) {
            // assertDoesNotThrow(() -> dmc.interact(e.getId()));
            // }
            // }
        }

        @Test
        public void testInteractThrowsCorrectExceptions() {
            // DungeonManiaController dmc = new DungeonManiaController();
            // DungeonResponse dres = dmc.newGame("d_mercenaryInteract",
            // "c_battleTests_basicMercenaryMercenaryDies");

            // for (EntityResponse e : dres.getEntities()) {
            // if (e.getType().equals("mercenary")) {
            // assertThrows(InvalidActionException.class, () -> dmc.interact(e.getId()));
            // }
            // }

            // dmc.tick(Direction.DOWN);
            // dmc.tick(Direction.RIGHT);

            // for (EntityResponse e : dres.getEntities()) {
            // if (e.getType().equals("mercenary")) {
            // assertThrows(InvalidActionException.class, () -> dmc.interact(e.getId()));
            // }
            // }

            // DungeonManiaController dmc2 = new DungeonManiaController();
            // DungeonResponse dres2 = dmc2.newGame("d_zombieSpawnerInteract",
            // "c_battleTests_basicMercenaryMercenaryDies");

            // for (EntityResponse e : dres2.getEntities()) {
            // if (e.getType().equals("zombie_toast_spawner")) {
            // assertThrows(InvalidActionException.class, () -> dmc.interact(e.getId()));
            // }
            // }

            // dmc.tick(Direction.DOWN);
            // dmc.tick(Direction.RIGHT);
            // dmc.tick(Direction.RIGHT);

            // for (EntityResponse e : dres2.getEntities()) {
            // if (e.getType().equals("zombie_toast_spawner")) {
            // assertThrows(InvalidActionException.class, () -> dmc.interact(e.getId()));
            // }
            // }
        }
    }

    @Nested
    public class PlayerIntegrationBlackBoxTests {

        @Test
        public void testSuccessfullyNotifiesZombieSpawners() {
            // DungeonManiaController dmc = new DungeonManiaController();
            // DungeonResponse dres = dmc.newGame("d_zombieSpawnerInteract",
            // "c_zombieSpawnRate");

            // assertTrue(dres.getEntities().size() == 3);

            // dres = dmc.tick(Direction.UP);

            // assertTrue(dres.getEntities().size() == 4);
        }
    }
}
