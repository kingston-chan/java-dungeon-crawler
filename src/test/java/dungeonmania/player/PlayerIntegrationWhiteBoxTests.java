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
import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.Item;
import dungeonmania.entities.item.Key;
import dungeonmania.entities.item.collectables.Arrows;
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

public class PlayerIntegrationWhiteBoxTests {
    @Test
    public void testUseUsableItem() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_usableItemsTest",
                "c_battleTests_basicMercenaryMercenaryDies");
        Dungeon testDungeon = DungeonManiaController.getDungeon();

        Item item1 = testDungeon.getItems().get(0);
        Item item2 = testDungeon.getItems().get(1);
        Item item3 = testDungeon.getItems().get(2);
        Player player = testDungeon.getPlayer();

        item1.doAccept(player);
        item2.doAccept(player);
        item3.doAccept(player);

        assertTrue(player.use(item1.getUniqueId()));
        assertTrue(player.use(item2.getUniqueId()));
        assertTrue(player.use(item3.getUniqueId()));
    }

    @Test
    public void testUseUnusableItem() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_unusableItems",
                "c_battleTests_basicMercenaryMercenaryDies");
        Dungeon testDungeon = DungeonManiaController.getDungeon();

        Item item1 = testDungeon.getItems().get(0);
        Item item2 = testDungeon.getItems().get(1);
        Item item3 = testDungeon.getItems().get(2);
        Item item4 = testDungeon.getItems().get(3);
        Item item5 = testDungeon.getItems().get(4);

        Player player = testDungeon.getPlayer();

        item1.doAccept(player);
        item2.doAccept(player);
        item3.doAccept(player);
        item4.doAccept(player);
        item5.doAccept(player);

        assertFalse(player.use(item1.getUniqueId()));
        assertFalse(player.use(item2.getUniqueId()));
        assertFalse(player.use(item3.getUniqueId()));
        assertFalse(player.use(item4.getUniqueId()));
        assertFalse(player.use(item5.getUniqueId()));
    }

    @Test
    public void testSuccessfullyNotifiesEnemiesToMove() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_simpleActors",
                "c_battleTests_basicMercenaryMercenaryDies");
        Dungeon testDungeon = DungeonManiaController.getDungeon();

        Player player = testDungeon.getPlayer();

        NonPlayableActor enemy1 = testDungeon.getNonPlayableActors().get(0);
        NonPlayableActor enemy2 = testDungeon.getNonPlayableActors().get(1);
        NonPlayableActor enemy3 = testDungeon.getNonPlayableActors().get(2);

        Position enemyPosition1 = enemy1.getPosition();
        Position enemyPosition2 = enemy2.getPosition();
        Position enemyPosition3 = enemy3.getPosition();

        player.notifyAllObservers();

        assertNotEquals(enemyPosition1, enemy1.getPosition());
        assertNotEquals(enemyPosition2, enemy2.getPosition());
        assertNotEquals(enemyPosition3, enemy3.getPosition());
    }

    @Test
    public void testSuccessfullyNotifiesEnemiesWithInvincibilityPotion() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_invincibilityPotionActors",
                "c_battleTests_basicMercenaryMercenaryDies");
        Dungeon testDungeon = DungeonManiaController.getDungeon();

        Player player = testDungeon.getPlayer();

        NonPlayableActor enemy1 = testDungeon.getNonPlayableActors().get(0);
        NonPlayableActor enemy2 = testDungeon.getNonPlayableActors().get(1);
        NonPlayableActor enemy3 = testDungeon.getNonPlayableActors().get(2);

        Item invincibilityPotion = testDungeon.getItems().get(0);
        invincibilityPotion.doAccept(player);

        player.use(invincibilityPotion.getUniqueId());
        player.consumeQueuedPotionEffect();

        Position enemyPosition1 = enemy1.getPosition();
        Position enemyPosition2 = enemy2.getPosition();
        Position enemyPosition3 = enemy3.getPosition();

        player.notifyAllObservers();

        assertNotEquals(enemyPosition1, enemy1.getPosition());
        assertNotEquals(enemyPosition2, enemy2.getPosition());
        assertNotEquals(enemyPosition3, enemy3.getPosition());
    }

    @Test
    public void testSuccessfullyNotifiesEnemiesWithInvisibilityPotion() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_invisibilityPotionActors",
                "c_battleTests_basicMercenaryMercenaryDies");
        Dungeon testDungeon = DungeonManiaController.getDungeon();

        Player player = testDungeon.getPlayer();

        NonPlayableActor enemy1 = testDungeon.getNonPlayableActors().get(0);
        NonPlayableActor enemy2 = testDungeon.getNonPlayableActors().get(1);
        NonPlayableActor enemy3 = testDungeon.getNonPlayableActors().get(2);

        Item invisibilityPotion = testDungeon.getItems().get(0);
        invisibilityPotion.doAccept(player);

        player.use(invisibilityPotion.getUniqueId());
        player.consumeQueuedPotionEffect();

        Position enemyPosition1 = enemy1.getPosition();
        Position enemyPosition2 = enemy2.getPosition();
        Position enemyPosition3 = enemy3.getPosition();

        player.notifyAllObservers();

        assertNotEquals(enemyPosition1, enemy1.getPosition());
        assertNotEquals(enemyPosition2, enemy2.getPosition());
        assertNotEquals(enemyPosition3, enemy3.getPosition());
    }

    @Test
    public void testSuccessfullyNotifiesZombieSpawners() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_simpleZombieToastSpawner",
                "c_zombieSpawnRate");
        Dungeon testDungeon = DungeonManiaController.getDungeon();

        assertEquals(3, testDungeon.getDungeonObjects().size());

        Player player = testDungeon.getPlayer();

        player.notifyAllObservers();

        assertEquals(4, testDungeon.getDungeonObjects().size());
    }

    @Test
    public void testPlayerNormalBattleMercenary() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_mercenaryInteract",
                "c_battleTestsMultipleRounds");
        Dungeon testDungeon = DungeonManiaController.getDungeon();
        Player player = testDungeon.getPlayer();

        testDungeon.getNonPlayableActors().forEach(o -> o.doAccept(player));

        assertEquals(1, testDungeon.getDungeonResponse().getBattles().size());
        BattleResponse br = testDungeon.getDungeonResponse().getBattles().get(0);
        assertEquals((double) Integer.parseInt(getValueFromConfigFile("player_health",
                "c_battleTestsMultipleRounds")),
                br.getInitialPlayerHealth());
        assertEquals((double) Integer.parseInt(getValueFromConfigFile("mercenary_health",
                "c_battleTestsMultipleRounds")),
                br.getInitialEnemyHealth());
        assertEquals("mercenary", br.getEnemy());

        int enemyAttack = Integer.parseInt(getValueFromConfigFile("mercenary_attack",
                "c_battleTestsMultipleRounds"));
        int playerAttack = Integer.parseInt(getValueFromConfigFile("player_attack",
                "c_battleTestsMultipleRounds"));

        for (RoundResponse r : br.getRounds()) {
            assertEquals(-(enemyAttack / 10.0), r.getDeltaCharacterHealth());
            assertEquals(-(playerAttack / 5.0), r.getDeltaEnemyHealth());
            assertEquals(0, r.getWeaponryUsed().size());
        }

        assertEquals(0, testDungeon.getNonPlayableActors().size());
    }

    @Test
    public void testPlayerNormalBattleSpider() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_spiderTest_basicMovement",
                "c_battleTestsMultipleRounds");
        Dungeon testDungeon = DungeonManiaController.getDungeon();
        Player player = testDungeon.getPlayer();

        testDungeon.getNonPlayableActors().forEach(o -> o.doAccept(player));

        assertEquals(1, testDungeon.getDungeonResponse().getBattles().size());
        BattleResponse br = testDungeon.getDungeonResponse().getBattles().get(0);
        assertEquals((double) Integer.parseInt(getValueFromConfigFile("player_health",
                "c_battleTestsMultipleRounds")),
                br.getInitialPlayerHealth());
        assertEquals((double) Integer.parseInt(getValueFromConfigFile("spider_health",
                "c_battleTestsMultipleRounds")),
                br.getInitialEnemyHealth());
        assertEquals("spider", br.getEnemy());

        int enemyAttack = Integer.parseInt(getValueFromConfigFile("spider_attack",
                "c_battleTestsMultipleRounds"));
        int playerAttack = Integer.parseInt(getValueFromConfigFile("player_attack",
                "c_battleTestsMultipleRounds"));

        for (RoundResponse r : br.getRounds()) {
            assertEquals(-(enemyAttack / 10.0), r.getDeltaCharacterHealth());
            assertEquals(-(playerAttack / 5.0), r.getDeltaEnemyHealth());
            assertEquals(0, r.getWeaponryUsed().size());
        }

        assertEquals(0, testDungeon.getNonPlayableActors().size());
    }

    @Test
    public void testPlayerNormalBattleZombieToast() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_zombieBattle",
                "c_battleTestsMultipleRounds");
        Dungeon testDungeon = DungeonManiaController.getDungeon();
        Player player = testDungeon.getPlayer();

        testDungeon.getNonPlayableActors().forEach(o -> o.doAccept(player));

        assertEquals(1, testDungeon.getDungeonResponse().getBattles().size());
        BattleResponse br = testDungeon.getDungeonResponse().getBattles().get(0);
        assertEquals((double) Integer.parseInt(getValueFromConfigFile("player_health",
                "c_battleTestsMultipleRounds")),
                br.getInitialPlayerHealth());
        assertEquals((double) Integer.parseInt(getValueFromConfigFile("zombie_health",
                "c_battleTestsMultipleRounds")),
                br.getInitialEnemyHealth());
        assertEquals("zombie_toast", br.getEnemy());

        int enemyAttack = Integer.parseInt(getValueFromConfigFile("zombie_attack",
                "c_battleTestsMultipleRounds"));
        int playerAttack = Integer.parseInt(getValueFromConfigFile("player_attack",
                "c_battleTestsMultipleRounds"));

        for (RoundResponse r : br.getRounds()) {
            assertEquals(-(enemyAttack / 10.0), r.getDeltaCharacterHealth());
            assertEquals(-(playerAttack / 5.0), r.getDeltaEnemyHealth());
            assertEquals(0, r.getWeaponryUsed().size());
        }

        assertEquals(0, testDungeon.getNonPlayableActors().size());
    }

    @Test
    public void testPlayerInvincibleBattle() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_invincibilityPotionActors",
                "c_battleTestsMultipleRounds");
        Dungeon testDungeon = DungeonManiaController.getDungeon();
        Player player = testDungeon.getPlayer();

        Item invincibilityPotion = testDungeon.getItems().get(0);

        invincibilityPotion.doAccept(player);

        player.use(invincibilityPotion.getUniqueId());

        player.consumeQueuedPotionEffect();

        testDungeon.getNonPlayableActors().forEach(o -> o.doAccept(player));

        assertEquals(3, testDungeon.getDungeonResponse().getBattles().size());
        for (BattleResponse br : testDungeon.getDungeonResponse().getBattles()) {
            assertEquals((double) Integer.parseInt(getValueFromConfigFile("player_health",
                    "c_battleTestsMultipleRounds")), br.getInitialPlayerHealth());
            assertEquals(1, br.getRounds().size());
            assertEquals(1, br.getRounds().get(0).getWeaponryUsed().size());
            assertEquals(invincibilityPotion.getUniqueId(), br.getRounds().get(0).getWeaponryUsed().get(0).getId());
        }

        assertEquals(0, testDungeon.getNonPlayableActors().size());
    }

    @Test
    public void testPlayerInvisibleNoBattle() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_invisibilityPotionActors",
                "c_battleTestsMultipleRounds");
        Dungeon testDungeon = DungeonManiaController.getDungeon();
        Player player = testDungeon.getPlayer();

        Item invisibilityPotion = testDungeon.getItems().get(0);

        invisibilityPotion.doAccept(player);

        player.use(invisibilityPotion.getUniqueId());

        player.consumeQueuedPotionEffect();

        testDungeon.getNonPlayableActors().forEach(o -> o.doAccept(player));

        assertEquals(0, testDungeon.getDungeonResponse().getBattles().size());

        assertEquals(3, testDungeon.getNonPlayableActors().size());
    }

    @Test
    public void testPlayerSwordBattle() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_enemyPlayerBattleSword",
                "c_battleTestsMultipleRounds");
        Dungeon testDungeon = DungeonManiaController.getDungeon();
        Player player = testDungeon.getPlayer();

        Item sword = testDungeon.getItems().get(0);

        sword.doAccept(player);

        player.use(sword.getUniqueId());

        testDungeon.getNonPlayableActors().forEach(o -> o.doAccept(player));

        assertEquals(1, testDungeon.getDungeonResponse().getBattles().size());

        BattleResponse br = testDungeon.getDungeonResponse().getBattles().get(0);

        assertEquals((double) Integer.parseInt(getValueFromConfigFile("player_health",
                "c_battleTestsMultipleRounds")), br.getInitialPlayerHealth());
        assertEquals((double) Integer.parseInt(getValueFromConfigFile("mercenary_health",
                "c_battleTestsMultipleRounds")), br.getInitialEnemyHealth());

        int enemyAttack = Integer.parseInt(getValueFromConfigFile("zombie_attack",
                "c_battleTestsMultipleRounds"));
        int playerAttack = Integer.parseInt(getValueFromConfigFile("player_attack",
                "c_battleTestsMultipleRounds")) + Integer.parseInt(
                        getValueFromConfigFile("sword_attack",
                                "c_battleTestsMultipleRounds"));

        for (RoundResponse r : br.getRounds()) {
            assertEquals(-(enemyAttack / 10.0), r.getDeltaCharacterHealth());
            assertEquals(-(playerAttack / 5.0), r.getDeltaEnemyHealth());
            assertEquals(1, r.getWeaponryUsed().size());
            assertEquals(sword.getUniqueId(), r.getWeaponryUsed().get(0).getId());
            assertEquals(sword.getType(), r.getWeaponryUsed().get(0).getType());
        }

        assertEquals(0, testDungeon.getNonPlayableActors().size());
        // 1 durability
        assertEquals(0, player.getInventory().size());
    }

    @Test
    public void testPlayerBowBattle() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_bowBattleEnemy",
                "c_battleTestsMultipleRounds");
        Dungeon testDungeon = DungeonManiaController.getDungeon();
        Player player = testDungeon.getPlayer();

        testDungeon.getItems().forEach(i -> i.doAccept(player));

        assertTrue(player.checkBuildables("bow"));

        player.build("bow");

        assertEquals(1, player.getInventory().size());

        Item bow = player.getInventory().get(0);

        testDungeon.getNonPlayableActors().forEach(o -> o.doAccept(player));

        assertEquals(1, testDungeon.getDungeonResponse().getBattles().size());

        BattleResponse br = testDungeon.getDungeonResponse().getBattles().get(0);

        assertEquals((double) Integer.parseInt(getValueFromConfigFile("player_health",
                "c_battleTestsMultipleRounds")), br.getInitialPlayerHealth());
        assertEquals((double) Integer.parseInt(getValueFromConfigFile("mercenary_health",
                "c_battleTestsMultipleRounds")), br.getInitialEnemyHealth());

        int enemyAttack = Integer.parseInt(getValueFromConfigFile("zombie_attack",
                "c_battleTestsMultipleRounds"));
        int playerAttack = Integer.parseInt(getValueFromConfigFile("player_attack",
                "c_battleTestsMultipleRounds")) * 2;

        for (RoundResponse r : br.getRounds()) {
            assertEquals(-(enemyAttack / 10.0), r.getDeltaCharacterHealth());
            assertEquals(-(playerAttack / 5.0), r.getDeltaEnemyHealth());
            assertEquals(1, r.getWeaponryUsed().size());
            assertEquals(bow.getUniqueId(), r.getWeaponryUsed().get(0).getId());
            assertEquals(bow.getType(), r.getWeaponryUsed().get(0).getType());
        }

        assertEquals(0, testDungeon.getNonPlayableActors().size());
        // 2 durability
        assertEquals(1, player.getInventory().size());
    }

    @Test
    public void testPlayerShieldBattle() {

    }

    @Test
    public void testPlayerAllEquipmentBattle() {

    }
}
