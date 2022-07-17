package dungeonmania.item;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.Item;
import dungeonmania.entities.item.equipment.Sword;
import dungeonmania.entities.item.potions.Potion;
import dungeonmania.util.Direction;

public class PotionWhiteBoxTests {
    @Test
    public void testInvincibilityPotionDuration() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_invincibilityPotionOnly", "c_potionDurationHigh");
        Dungeon testDungeon = DungeonManiaController.getDungeon();
        Player player = testDungeon.getPlayer();
        Potion invincibilityPotion = (Potion) testDungeon.getItems().get(0);

        invincibilityPotion.doAccept(player);
        // potion has 10 duration
        assertTrue(player.use(invincibilityPotion.getUniqueId()));

        for (int i = 0; i < 5; i++) {
            player.consumeQueuedPotionEffect();
        }

        assertEquals(player.getPotionConsumed(), invincibilityPotion);

        for (int i = 0; i < 5; i++) {
            player.consumeQueuedPotionEffect();
        }

        // should still have a potion consumed (10th duration tick)
        assertEquals(player.getPotionConsumed(), invincibilityPotion);

        player.consumeQueuedPotionEffect();

        // should have no potion consumed because duration expired (11th tick)
        assertEquals(player.getPotionConsumed(), null);
    }

    @Test
    public void testInvisibilityPotionDuration() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_invisibilityPotionOnly", "c_potionDurationHigh");
        Dungeon testDungeon = DungeonManiaController.getDungeon();
        Player player = testDungeon.getPlayer();
        Potion invisibilityPotion = (Potion) testDungeon.getItems().get(0);

        invisibilityPotion.doAccept(player);
        // potion has 10 duration
        assertTrue(player.use(invisibilityPotion.getUniqueId()));

        for (int i = 0; i < 5; i++) {
            player.consumeQueuedPotionEffect();
        }

        assertEquals(player.getPotionConsumed(), invisibilityPotion);

        for (int i = 0; i < 5; i++) {
            player.consumeQueuedPotionEffect();
        }

        // should still have a potion consumed (10th duration tick)
        assertEquals(player.getPotionConsumed(), invisibilityPotion);

        player.consumeQueuedPotionEffect();

        // should have no potion consumed because duration expired (11th tick)
        assertEquals(player.getPotionConsumed(), null);

    }

    @Test
    public void testQueuedPotion() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_potionSword", "c_potionTwoDuration");
        Dungeon testDungeon = DungeonManiaController.getDungeon();
        Player player = testDungeon.getPlayer();

        Potion invincibilityPotion = (Potion) testDungeon.getItems().stream()
                .filter(o -> o.getType().equals("invincibility_potion")).findFirst().get();
        Potion invisibilityPotion = (Potion) testDungeon.getItems().stream()
                .filter(o -> o.getType().equals("invisibility_potion")).findFirst().get();
        Sword sword = (Sword) testDungeon.getItems().stream()
                .filter(o -> o.getType().equals("sword")).findFirst().get();

        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);

        assertDoesNotThrow(() -> {
            dmc.tick(invincibilityPotion.getUniqueId());
            // 1st invincibility potion duration is used
            assertEquals(invincibilityPotion, player.getPotionConsumed());
        });
        assertDoesNotThrow(() -> {
            dmc.tick(invisibilityPotion.getUniqueId());
            // 2nd invincibility potion duration is used
            assertEquals(invincibilityPotion, player.getPotionConsumed());
        });

        dmc.tick(Direction.RIGHT);
        // 3rd invincibility potion duration is used
        assertEquals(invincibilityPotion, player.getPotionConsumed());

        assertThrows(IllegalArgumentException.class, () -> {
            dmc.tick(sword.getUniqueId());
            // player attempts to use counts as tick
            // 3 invincibility potion duration is used, so get should be invisibility
            // potion next
            assertEquals(invisibilityPotion, player.getPotionConsumed());
        });

        dmc.tick(Direction.RIGHT);
        // 2nd invincibility potion duration is used
        assertEquals(invisibilityPotion, player.getPotionConsumed());

        dmc.tick(Direction.RIGHT);
        // all potion duration used should have consumed potion
        assertEquals(null, player.getPotionConsumed());
    }
}
