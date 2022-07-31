package dungeonmania.player;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.TestUtils;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.ItemResponse;
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
    public void testItemUsedIdNotInInventory() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse dres = dmc.newGame("d_usableItemsTest",
                "c_zombieSpawnRate");

        EntityResponse bomb = TestUtils.getEntities(dres, "bomb").get(0);
        EntityResponse invincibilityPotion = TestUtils.getEntities(dres, "invincibility_potion").get(0);
        EntityResponse invisibilityPotion = TestUtils.getEntities(dres, "invisibility_potion").get(0);

        assertThrows(InvalidActionException.class, () -> dmc.tick(bomb.getId()));
        assertThrows(InvalidActionException.class, () -> dmc.tick(invincibilityPotion.getId()));
        assertThrows(InvalidActionException.class, () -> dmc.tick(invisibilityPotion.getId()));

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

        EntityResponse merc = TestUtils.getEntities(dres, "mercenary").get(0);
        Position playerPrevPos = TestUtils.getPlayer(dres).get().getPosition();

        assertDoesNotThrow(() -> dmc.interact(merc.getId()));

        // should follow player (move to player's prev pos) if ally and not interactable
        dres = dmc.tick(Direction.RIGHT);

        EntityResponse ally = TestUtils.getEntities(dres, "mercenary").get(0);
        assertEquals(playerPrevPos, ally.getPosition());
        assertFalse(ally.isInteractable());
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
    public void testMercenaryInteractExceptions() {
        DungeonManiaController dmc = new DungeonManiaController();

        // not in range
        assertThrows(InvalidActionException.class, () -> {
            DungeonResponse dres = dmc.newGame("d_mercenaryInteract",
                    "c_battleTests_basicMercenaryMercenaryDies");
            EntityResponse merc = TestUtils.getEntities(dres, "mercenary").get(0);
            dmc.interact(merc.getId());
        });

        dmc.tick(Direction.DOWN);
        DungeonResponse dres = dmc.tick(Direction.RIGHT);

        EntityResponse merc = TestUtils.getEntities(dres, "mercenary").get(0);
        // not enough treasure to bribe
        assertThrows(InvalidActionException.class, () -> dmc.interact(merc.getId()));

    }

    @Test
    public void testZombieSpawnInteractExceptions() {
        DungeonManiaController dmc = new DungeonManiaController();

        // not in range
        assertThrows(InvalidActionException.class, () -> {
            DungeonResponse dres = dmc.newGame("d_zombieSpawnerInteract",
                    "c_battleTests_basicMercenaryMercenaryDies");

            EntityResponse zombieSpawner = TestUtils.getEntities(dres, "zombie_toast_spawner").get(0);
            dmc.interact(zombieSpawner.getId());
        });

        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.RIGHT);
        DungeonResponse dres = dmc.tick(Direction.RIGHT);

        EntityResponse zombieSpawner = TestUtils.getEntities(dres, "zombie_toast_spawner").get(0);
        // no weapon
        assertThrows(InvalidActionException.class, () -> dmc.interact(zombieSpawner.getId()));
    }

    @Test
    public void testTickStillOccursWhenUsingItemNotInInventory() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse dres = dmc.newGame("d_invalidItemIdTick",
                "c_spider_spawn_rate_0");
        dres = dmc.tick(Direction.DOWN);
        ItemResponse potion = dres.getInventory().get(0);
        assertDoesNotThrow(() -> dmc.tick(potion.getId()));
        // tick should have occured, therefore battle should have occured
        assertThrows(InvalidActionException.class, () -> dmc.tick(potion.getId()));
        // moving out of spider movement path so should not encounter it, if tick didn't
        // happen
        dres = dmc.tick(Direction.RIGHT);
        assertFalse(dres.getBattles().isEmpty());

        double expectedInitialPlayerHealth = Double
                .parseDouble(TestUtils.getValueFromConfigFile("player_health", "c_spider_spawn_rate_0"));
        double expectedInitialSpiderHealth = Double
                .parseDouble(TestUtils.getValueFromConfigFile("spider_health", "c_spider_spawn_rate_0"));

        assertEquals(expectedInitialPlayerHealth, dres.getBattles().get(0).getInitialPlayerHealth());
        assertEquals(expectedInitialSpiderHealth, dres.getBattles().get(0).getInitialEnemyHealth());
        assertEquals("spider", dres.getBattles().get(0).getEnemy());
        assertEquals(1, dres.getBattles().get(0).getRounds().size());

        assertEquals(0.0, dres.getBattles().get(0).getRounds().get(0).getDeltaCharacterHealth());
        assertEquals(-expectedInitialSpiderHealth, dres.getBattles().get(0).getRounds().get(0).getDeltaEnemyHealth());
        assertEquals(potion.getId(), dres.getBattles().get(0).getRounds().get(0).getWeaponryUsed().get(0).getId());

    }

    @Test
    public void testTickStillOccursWhenUsingUnusableItem() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse dres = dmc.newGame("d_unusableItemTick",
                "c_spider_spawn_rate_0");
        dres = dmc.tick(Direction.RIGHT);
        ItemResponse sword = dres.getInventory().get(0);
        // tick should have occured, therefore battle should have occured
        assertThrows(IllegalArgumentException.class, () -> dmc.tick(sword.getId()));
        // moving out of spider movement path so should not encounter it, if tick didn't
        // happen
        dres = dmc.tick(Direction.RIGHT);
        assertFalse(dres.getBattles().isEmpty());
    }

}
