package dungeonmania.item;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.TestUtils;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class PotionBlackBoxTests {
    @Test
    public void testWinsBattleImmediatelyInvincibilityPotionMercenary() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_invincibilityPotionSmallDungeon", "c_potionDurationHigh");

        DungeonResponse dres = dmc.tick(Direction.RIGHT);

        ItemResponse potion = TestUtils.getInventory(dres, "invincibility_potion").get(0);

        EntityResponse merc = TestUtils.getEntities(dres, "mercenary").get(0);

        // move towards player
        assertEquals(new Position(6, 3), merc.getPosition());

        assertDoesNotThrow(() -> {
            DungeonResponse res = dmc.tick(potion.getId());
            // moves away from player
            assertEquals(new Position(7, 3), TestUtils.getEntities(res, "mercenary").get(0).getPosition());
        });

        assertTrue(dres.getBattles().isEmpty());

        dres = dmc.tick(Direction.RIGHT);

        assertEquals(1, dres.getBattles().size());

        double expectedPlayerHealth = Double
                .parseDouble(TestUtils.getValueFromConfigFile("player_health", "c_potionDurationHigh"));
        assertEquals(expectedPlayerHealth,
                dres.getBattles().get(0).getInitialPlayerHealth());
        double expectedEnemyHealth = Double
                .parseDouble(TestUtils.getValueFromConfigFile("mercenary_health", "c_potionDurationHigh"));
        assertEquals(expectedEnemyHealth,
                dres.getBattles().get(0).getInitialEnemyHealth());
        assertEquals(1, dres.getBattles().get(0).getRounds().size());
        assertEquals(-(expectedEnemyHealth),
                dres.getBattles().get(0).getRounds().get(0).getDeltaEnemyHealth());
        assertEquals(0, dres.getBattles().get(0).getRounds().get(0).getDeltaCharacterHealth());
        assertEquals(1, dres.getBattles().get(0).getRounds().get(0).getWeaponryUsed().size());
        assertEquals(potion.getId(), dres.getBattles().get(0).getRounds().get(0).getWeaponryUsed().get(0).getId());
        assertEquals(potion.getType(), dres.getBattles().get(0).getRounds().get(0).getWeaponryUsed().get(0).getType());
    }

    @Test
    public void testWinsBattleImmediatelyInvincibilityPotionSpider() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_invincibilityPotionSpider", "c_potionDurationHigh");

        DungeonResponse dres = dmc.tick(Direction.RIGHT);

        ItemResponse potion = TestUtils.getInventory(dres, "invincibility_potion").get(0);

        assertDoesNotThrow(() -> dmc.tick(potion.getId()));

        assertTrue(dres.getBattles().isEmpty());

        dres = dmc.tick(Direction.LEFT);

        assertEquals(1, dres.getBattles().size());

        double expectedPlayerHealth = Double
                .parseDouble(TestUtils.getValueFromConfigFile("player_health", "c_potionDurationHigh"));
        assertEquals(expectedPlayerHealth,
                dres.getBattles().get(0).getInitialPlayerHealth());
        double expectedEnemyHealth = Double
                .parseDouble(TestUtils.getValueFromConfigFile("spider_health", "c_potionDurationHigh"));
        assertEquals(expectedEnemyHealth,
                dres.getBattles().get(0).getInitialEnemyHealth());
        assertEquals(1, dres.getBattles().get(0).getRounds().size());
        assertEquals(-(expectedEnemyHealth),
                dres.getBattles().get(0).getRounds().get(0).getDeltaEnemyHealth());
        assertEquals(0, dres.getBattles().get(0).getRounds().get(0).getDeltaCharacterHealth());
        assertEquals(1, dres.getBattles().get(0).getRounds().get(0).getWeaponryUsed().size());
        assertEquals(potion.getId(), dres.getBattles().get(0).getRounds().get(0).getWeaponryUsed().get(0).getId());
        assertEquals(potion.getType(), dres.getBattles().get(0).getRounds().get(0).getWeaponryUsed().get(0).getType());
    }

    @Test
    public void testWinsBattleImmediatelyInvincibilityPotionZombie() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_invincibilityPotionZombie", "c_potionDurationHigh");

        DungeonResponse dres = dmc.tick(Direction.RIGHT);

        ItemResponse potion = TestUtils.getInventory(dres, "invincibility_potion").get(0);

        assertDoesNotThrow(() -> {
            DungeonResponse res = dmc.tick(potion.getId());
            assertEquals(new Position(7, 3), TestUtils.getEntities(res, "zombie_toast").get(0).getPosition());
        });

        assertTrue(dres.getBattles().isEmpty());

        dres = dmc.tick(Direction.RIGHT);

        assertEquals(1, dres.getBattles().size());

        double expectedPlayerHealth = Double
                .parseDouble(TestUtils.getValueFromConfigFile("player_health", "c_potionDurationHigh"));
        assertEquals(expectedPlayerHealth,
                dres.getBattles().get(0).getInitialPlayerHealth());
        double expectedEnemyHealth = Double
                .parseDouble(TestUtils.getValueFromConfigFile("zombie_health", "c_potionDurationHigh"));
        assertEquals(expectedEnemyHealth,
                dres.getBattles().get(0).getInitialEnemyHealth());
        assertEquals(1, dres.getBattles().get(0).getRounds().size());
        assertEquals(-(expectedEnemyHealth),
                dres.getBattles().get(0).getRounds().get(0).getDeltaEnemyHealth());
        assertEquals(0, dres.getBattles().get(0).getRounds().get(0).getDeltaCharacterHealth());
        assertEquals(1, dres.getBattles().get(0).getRounds().get(0).getWeaponryUsed().size());
        assertEquals(potion.getId(), dres.getBattles().get(0).getRounds().get(0).getWeaponryUsed().get(0).getId());
        assertEquals(potion.getType(), dres.getBattles().get(0).getRounds().get(0).getWeaponryUsed().get(0).getType());
    }

    @Test
    public void testNoBattlesInvisibilityPotionSpider() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_invisibilityPotionSpider", "c_potionDurationHigh");

        DungeonResponse dres = dmc.tick(Direction.RIGHT);

        ItemResponse potion = TestUtils.getInventory(dres, "invisibility_potion").get(0);

        assertDoesNotThrow(() -> dmc.tick(potion.getId()));

        assertTrue(dres.getBattles().isEmpty());

        dres = dmc.tick(Direction.LEFT);

        assertEquals(0, dres.getBattles().size());
    }

    @Test
    public void testNoBattlesInvisibilityPotionZombie() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_invisibilityPotionZombie", "c_potionDurationHigh");

        DungeonResponse dres = dmc.tick(Direction.RIGHT);

        ItemResponse potion = TestUtils.getInventory(dres, "invisibility_potion").get(0);

        assertDoesNotThrow(() -> dmc.tick(potion.getId()));

        // wall wall wall wall wall wall
        // wall [] player zombie [] wall
        // wall wall wall wall wall wall

        // since zombies move randomly, moving left guarantees that it will encounter
        // zombie
        dres = dmc.tick(Direction.LEFT);

        assertEquals(0, dres.getBattles().size());
    }

    @Test
    public void testInvincibilityPotionDuration() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_invincibilityPotionOnly", "c_potionDurationHigh");

        // picks up potion
        DungeonResponse dres = dmc.tick(Direction.RIGHT);

        EntityResponse merc = TestUtils.getEntities(dres, "mercenary").get(0);

        int start_x_merc_before_potion = 6;

        assertEquals(new Position(start_x_merc_before_potion, 3), merc.getPosition());

        ItemResponse potion = TestUtils.getInventory(dres, "invincibility_potion").get(0);

        assertDoesNotThrow(() -> dmc.tick(potion.getId()));

        dres = dmc.tick(Direction.LEFT);

        assertTrue(TestUtils.getInventory(dres, "invincibility_potion").isEmpty());

        merc = TestUtils.getEntities(dres, "mercenary").get(0);

        // Moves away from player, 2nd tick of potion (move towards player when player
        // picks up potion and 1 for moving away)
        assertEquals(new Position(start_x_merc_before_potion + 2, 3), merc.getPosition());

        // 5 ticks
        for (int i = 0; i < 4; i++) {
            dmc.tick(Direction.LEFT);
        }
        dres = dmc.tick(Direction.LEFT);

        merc = TestUtils.getEntities(dres, "mercenary").get(0);

        // Should be 7 total ticks
        assertEquals(new Position(start_x_merc_before_potion + 7, 3), merc.getPosition());

        // 3 ticks
        for (int i = 0; i < 2; i++) {
            dmc.tick(Direction.LEFT);
        }
        dres = dmc.tick(Direction.LEFT);

        merc = TestUtils.getEntities(dres, "mercenary").get(0);
        // Last tick
        assertEquals(new Position(start_x_merc_before_potion + 10, 3), merc.getPosition());

        dmc.tick(Direction.LEFT);
        dres = dmc.tick(Direction.LEFT);

        merc = TestUtils.getEntities(dres, "mercenary").get(0);
        // start moving back to player
        assertEquals(new Position(start_x_merc_before_potion + 8, 3), merc.getPosition());
    }

    @Test
    public void testInvisibilityPotionDuration() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_invisibilityPotionOnly", "c_potionDurationHigh");

        // picks up potion
        DungeonResponse dres = dmc.tick(Direction.RIGHT);

        ItemResponse potion = TestUtils.getInventory(dres, "invisibility_potion").get(0);

        assertDoesNotThrow(() -> dmc.tick(potion.getId()));

        dres = dmc.tick(Direction.RIGHT);

        assertTrue(TestUtils.getInventory(dres, "invisibility_potion").isEmpty());

        // no battles should have occurred because either merc moved into player when
        // player used
        // potion or when player moved right and only free space for merc to move is
        // move left
        // which is where player is
        assertEquals(0, dres.getBattles().size());

        // For 9 ticks, it should not have done any battles
        for (int i = 0; i < 8; i++) {
            // into a hostile mercenary
            dres = dmc.tick(Direction.RIGHT);
            assertEquals(0, dres.getBattles().size());
        }

        dmc.tick(Direction.LEFT);
        dres = dmc.tick(Direction.LEFT);
        // Battle should have occured
        assertEquals(1, dres.getBattles().size());
    }
}
