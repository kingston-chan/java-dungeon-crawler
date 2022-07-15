package dungeonmania.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.potions.Potion;

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
            System.out.println(player.getPotionConsumed());
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
            System.out.println(player.getPotionConsumed());
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
}
