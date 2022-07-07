package dungeonmania.player;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.enemy.Enemy;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.Item;
import dungeonmania.entities.item.bomb.Bomb;
import dungeonmania.entities.item.collectables.Treasure;
import dungeonmania.entities.item.key.Key;
import dungeonmania.entities.item.potion.InvincibilityPotion;
import dungeonmania.entities.item.potion.Potion;
import dungeonmania.entities.item.weapon.Weapon;
import dungeonmania.response.models.BattleResponse;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
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
            Player player = new Player();
            Item sword = new Weapon();
            Item bomb = new Bomb();
            player.addToInventory(sword);
            player.addToInventory(bomb);
            assertListAreEqualIgnoringOrder(player.getInventory(), Arrays.asList(sword, bomb));
        }

        @Test
        public void testRemoveItemFromInventory() {
            Player player = new Player();
            Item sword = new Weapon();
            Item bomb = new Bomb();
            player.addToInventory(sword);
            player.addToInventory(bomb);
            player.removeFromInventory(sword);
            assertListAreEqualIgnoringOrder(player.getInventory(), Arrays.asList(bomb));
        }

        @Test
        public void testGetCorrectItem() {
            Player player = new Player();
            Item sword = new Weapon();
            sword.setUniqueId("swordId");
            player.addToInventory(sword);
            assertEquals(player.getItem("swordId"), sword);
        }

        @Test
        public void testNoItemWithIdInInventory() {
            Player player = new Player();
            assertEquals(player.getItem("notanid"), null);
        }

        @Test
        public void testRemoveTreasures() {
            Player player = new Player();
            Item sword = new Weapon();
            Item treasure1 = new Treasure();
            Item treasure2 = new Treasure();
            assertTrue(player.removeTreasures(2));
            assertListAreEqualIgnoringOrder(player.getInventory(), Arrays.asList(sword));
        }

        @Test
        public void testRemoveTreasurePlayerNoTreasure() {
            Player player = new Player();
            assertFalse(player.removeTreasures(3));
        }

        @Test
        public void testGetKeyPlayerHasKey() {
            Player player = new Player();
            Item key = new Key();
            player.setKey(key);
            assertEquals(player.getKey(), key);
        }

        @Test
        public void testGetKeyPlayerHasNoKey() {
            Player player = new Player();
            assertEquals(player.getKey(), null);
        }

        @Test
        public void testMercenaryInteractOutOfRange() {

        }

        @Test
        public void testZombieToastSpawnerInteractNotAdjacent() {

        }

        @Test
        public void testMercenaryInteractNotEnoughTreasures() {

        }

        @Test
        public void testZombieToastSpawnerInteractNoWeapon() {

        }

        @Test
        public void testSuccessfullyBuildsBow() {

        }

        @Test
        public void testSuccessfullyBuildsShield() {

        }

        @Test
        public void testBuildNotBowShield() {

        }

        @Test
        public void testBuildBowNotEnoughItems() {

        }

        @Test
        public void testBuildShieldNotEnoughItems() {

        }

        @Test
        public void testGetConsumedPotion() {

        }

        @Test
        public void testConsumedNoPotion() {

        }

        @Test
        public void testGetPotionButPotionExpired() {

        }
    }

    @Nested
    public class PlayerIntegrationWhiteBoxTests {
        @Test
        public void testUseUsableItem() {

        }

        @Test
        public void testUseUnusableItem() {

        }

        @Test
        public void testSuccessfullyNotifiesEnemiesToMove() {

        }

        @Test
        public void testSuccessfullyNotifiesEnemiesWithInvincibilityPotion() {

        }

        @Test
        public void testSuccessfullyNotifiesEnemiesWithInvisibilityPotion() {

        }

        @Test
        public void testSuccessfullyNotifiesEnemiesWhenInvincibilityPotionExpired() {

        }

        @Test
        public void testSuccessfullyNotifiesEnemiesWhenInvisibilityPotionExpired() {

        }

        @Test
        public void testSuccessfullyNotifiesZombieSpawners() {

        }

        @Test
        public void testSuccessfullyInteractsWithMercenary() {

        }

        @Test
        public void testSuccessfullyInteractsWithZombieToastSpawner() {

        }

        @Test
        public void testInvalidIdInteracts() {

        }

        @Test
        public void testValidIdButNonInteractableEntity() {

        }
    }

    @Nested
    public class PlayerUnitBlackBoxTests {
        @Test
        public void testSuccessfullyPicksUpItem() {

        }

        @Test
        public void testUseUsableItem() {

        }

        @Test
        public void testUseUnusableItem() {

        }

        @Test
        public void testSuccessfullyBuildsBow() {

        }

        @Test
        public void testSuccessfullyBuildsShield() {

        }

        @Test
        public void testBuildNotBowShield() {

        }

        @Test
        public void testNotEnoughToBuild() {

        }

        @Test
        public void testInteractWithInvalidId() {

        }

        @Test
        public void testSuccessfullyInteractsWithMercenary() {

        }

        @Test
        public void testSuccessfullyInteractsWithZombieToastSpawner() {

        }

        @Test
        public void testInteractThrowsCorrectExceptions() {

        }
    }

    @Nested
    public class PlayerIntegrationBlackBoxTests {
        @Test
        public void testSuccessfullyNotifiesEnemiesToMove() {

        }

        @Test
        public void testSuccessfullyNotifiesZombieSpawners() {

        }

        @Test
        public void testSuccessfullyNotifiesEnemiesWithInvincibilityPotion() {

        }

        @Test
        public void testSuccessfullyNotifiesEnemiesWithInvisibilityPotion() {

        }

        @Test
        public void testSuccessfullyNotifiesEnemiesWhenInvincibilityPotionExpired() {

        }

        @Test
        public void testSuccessfullyNotifiesEnemiesWhenInvisibilityPotionExpired() {

        }
    }
}
