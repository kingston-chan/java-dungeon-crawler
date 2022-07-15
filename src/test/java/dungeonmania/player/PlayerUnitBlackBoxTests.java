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
import dungeonmania.TestUtils;
import dungeonmania.entities.Dungeon;
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

public class PlayerUnitBlackBoxTests {
    @Test
    public void testInvalidIdInteracts() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_movementTest_testMovementDown",
                "c_zombieSpawnRate");

        assertThrows(IllegalArgumentException.class, () -> dmc.interact("notanid"));
    }

    @Test
    public void testSuccessfullyPicksUpItem() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_unusableItems",
                "c_zombieSpawnRate");

        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        DungeonResponse dres = dmc.tick(Direction.RIGHT);

        assertTrue(dres.getEntities().size() == 1);
        assertTrue(dres.getInventory().size() == 5);
    }

    @Test
    public void testUseUsableItem() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_usableItemsTest",
                "c_zombieSpawnRate");

        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        DungeonResponse dres = dmc.tick(Direction.RIGHT);

        ItemResponse item1 = dres.getInventory().get(0);
        ItemResponse item2 = dres.getInventory().get(1);
        ItemResponse item3 = dres.getInventory().get(2);

        assertDoesNotThrow(() -> dmc.tick(item1.getId()));
        assertDoesNotThrow(() -> dmc.tick(item2.getId()));
        assertDoesNotThrow(() -> dmc.tick(item3.getId()));
    }

    @Test
    public void testUseUnusableItem() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_unusableItems",
                "c_zombieSpawnRate");

        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        DungeonResponse dres = dmc.tick(Direction.RIGHT);

        ItemResponse item1 = dres.getInventory().get(0);
        ItemResponse item2 = dres.getInventory().get(1);
        ItemResponse item3 = dres.getInventory().get(2);
        ItemResponse item4 = dres.getInventory().get(3);
        ItemResponse item5 = dres.getInventory().get(4);

        assertThrows(IllegalArgumentException.class, () -> dmc.tick(item1.getId()));
        assertThrows(IllegalArgumentException.class, () -> dmc.tick(item2.getId()));
        assertThrows(IllegalArgumentException.class, () -> dmc.tick(item3.getId()));
        assertThrows(IllegalArgumentException.class, () -> dmc.tick(item4.getId()));
        assertThrows(IllegalArgumentException.class, () -> dmc.tick(item5.getId()));
    }

    @Test
    public void testSuccessfullyBuildsBow() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_bow",
                "c_zombieSpawnRate");
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);

        assertDoesNotThrow(() -> dmc.build("bow"));
    }

    @Test
    public void testSuccessfullyBuildsShieldKey() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_shield_key",
                "c_zombieSpawnRate");

        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);

        assertDoesNotThrow(() -> dmc.build("shield"));
    }

    @Test
    public void testSuccessfullyBuildsShieldTreasure() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_shield_treasure",
                "c_zombieSpawnRate");

        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);

        assertDoesNotThrow(() -> dmc.build("shield"));
    }

    @Test
    public void testBuildNotBowShield() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_shield_treasure",
                "c_zombieSpawnRate");

        assertThrows(IllegalArgumentException.class, () -> dmc.build("notashilednorbow"));
    }

    @Test
    public void testNotEnoughToBuild() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_shield_key",
                "c_zombieSpawnRate");

        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);

        assertThrows(InvalidActionException.class, () -> dmc.build("shield"));

        DungeonManiaController dmc2 = new DungeonManiaController();
        dmc2.newGame("d_shield_treasure",
                "c_zombieSpawnRate");

        dmc2.tick(Direction.RIGHT);
        dmc2.tick(Direction.RIGHT);

        assertThrows(InvalidActionException.class, () -> dmc2.build("shield"));

        DungeonManiaController dmc3 = new DungeonManiaController();
        dmc3.newGame("d_bow",
                "c_zombieSpawnRate");
        dmc3.tick(Direction.RIGHT);
        dmc3.tick(Direction.RIGHT);
        dmc3.tick(Direction.RIGHT);

        assertThrows(InvalidActionException.class, () -> dmc2.build("bow"));
    }

    @Test
    public void testInteractWithInvalidId() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_shield_key",
                "c_spiderSpawnRateTest");

        assertThrows(IllegalArgumentException.class, () -> dmc.interact("invalidId"));
    }

    @Test
    public void testSuccessfullyInteractsWithMercenary() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_mercenaryInteract",
                "c_battleTests_basicMercenaryMercenaryDies");

        DungeonResponse dres = dmc.tick(Direction.RIGHT);

        for (EntityResponse e : dres.getEntities()) {
            if (e.getType().equals("mercenary")) {
                assertDoesNotThrow(() -> dmc.interact(e.getId()));
            }
        }
    }

    @Test
    public void testSuccessfullyInteractsWithZombieToastSpawner() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_zombieSpawnerInteract",
                "c_battleTests_basicMercenaryMercenaryDies");

        DungeonResponse dres = dmc.tick(Direction.RIGHT);

        EntityResponse zombieSpawner = TestUtils.getEntities(dres, "zombie_toast_spawner").get(0);

        assertDoesNotThrow(() -> {
            DungeonResponse res = dmc.interact(zombieSpawner.getId());
            assertEquals(0, TestUtils.countEntityOfType(res, "zombie_toast_spawner"));
            assertEquals("", res.getGoals());
        });
    }

    @Test
    public void testInteractThrowsCorrectExceptions() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse dres = dmc.newGame("d_mercenaryInteract",
                "c_battleTests_basicMercenaryMercenaryDies");

        for (EntityResponse e : dres.getEntities()) {
            if (e.getType().equals("mercenary")) {
                assertThrows(InvalidActionException.class, () -> dmc.interact(e.getId()));
            }
        }

        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.RIGHT);

        for (EntityResponse e : dres.getEntities()) {
            if (e.getType().equals("mercenary")) {
                assertThrows(InvalidActionException.class, () -> dmc.interact(e.getId()));
            }
        }

        DungeonManiaController dmc2 = new DungeonManiaController();
        DungeonResponse dres2 = dmc2.newGame("d_zombieSpawnerInteract",
                "c_battleTests_basicMercenaryMercenaryDies");

        for (EntityResponse e : dres2.getEntities()) {
            if (e.getType().equals("zombie_toast_spawner")) {
                assertThrows(InvalidActionException.class, () -> dmc.interact(e.getId()));
            }
        }

        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);

        for (EntityResponse e : dres2.getEntities()) {
            if (e.getType().equals("zombie_toast_spawner")) {
                assertThrows(InvalidActionException.class, () -> dmc.interact(e.getId()));
            }
        }
    }

    @Test
    public void playerLosesToZombie() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_playerLosesToZombie",
                "c_playerLosesToZombie");

        DungeonResponse res = dmc.tick(Direction.RIGHT);

        assertFalse(res.getBattles().isEmpty());

        double expectedInitialPlayerHealth = Double
                .parseDouble(getValueFromConfigFile("player_health", "c_playerLosesToZombie"));
        double expectedInitialZombieHealth = Double
                .parseDouble(getValueFromConfigFile("zombie_health", "c_playerLosesToZombie"));

        int expectedPlayerAttack = Integer
                .parseInt(getValueFromConfigFile("player_attack", "c_playerLosesToZombie"));
        int expectedZombieAttack = Integer
                .parseInt(getValueFromConfigFile("zombie_attack", "c_playerLosesToZombie"));

        assertEquals(expectedInitialPlayerHealth, res.getBattles().get(0).getInitialPlayerHealth());
        assertEquals(expectedInitialZombieHealth, res.getBattles().get(0).getInitialEnemyHealth());
        assertEquals("zombie_toast", res.getBattles().get(0).getEnemy());
        assertEquals(1, res.getBattles().get(0).getRounds().size());

        RoundResponse playerLostRound = res.getBattles().get(0).getRounds().get(0);

        assertEquals(-(expectedPlayerAttack / 5.0), playerLostRound.getDeltaEnemyHealth());
        assertEquals(-(expectedZombieAttack / 10.0), playerLostRound.getDeltaCharacterHealth());

        // player should be removed from entity list
        assertEquals(0, TestUtils.countEntityOfType(res, "player"));

        // zombie should live
        assertEquals(1, TestUtils.countEntityOfType(res, "zombie"));
    }

    @Test
    public void playerLosesToSpider() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_playerLosesToSpider",
                "c_playerLosesToSpider");

        res = dmc.tick(Direction.LEFT);

        assertFalse(res.getBattles().isEmpty());

        double expectedInitialPlayerHealth = Double
                .parseDouble(getValueFromConfigFile("player_health", "c_playerLosesToSpider"));
        double expectedInitialSpiderHealth = Double
                .parseDouble(getValueFromConfigFile("spider_health", "c_playerLosesToSpider"));

        int expectedPlayerAttack = Integer
                .parseInt(getValueFromConfigFile("player_attack", "c_playerLosesToSpider"));
        int expectedSpiderAttack = Integer
                .parseInt(getValueFromConfigFile("spider_attack", "c_playerLosesToSpider"));

        assertEquals(expectedInitialPlayerHealth, res.getBattles().get(0).getInitialPlayerHealth());
        assertEquals(expectedInitialSpiderHealth, res.getBattles().get(0).getInitialEnemyHealth());
        assertEquals("spider", res.getBattles().get(0).getEnemy());
        assertEquals(1, res.getBattles().get(0).getRounds().size());

        RoundResponse playerLostRound = res.getBattles().get(0).getRounds().get(0);

        assertEquals(-(expectedPlayerAttack / 5.0), playerLostRound.getDeltaEnemyHealth());
        assertEquals(-(expectedSpiderAttack / 10.0), playerLostRound.getDeltaCharacterHealth());

        // player should be removed from entity list
        assertEquals(0, TestUtils.countEntityOfType(res, "player"));

        // spider should live
        assertEquals(1, TestUtils.countEntityOfType(res, "spider"));
    }
}
