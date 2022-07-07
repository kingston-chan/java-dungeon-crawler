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

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

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

        }

        @Test
        public void testRemoveItemFromInventory() {

        }

        @Test
        public void testGetCorrectItem() {

        }

        @Test
        public void testNoItemWithUniqueId() {

        }

        @Test
        public void testUseUsableItem() {

        }

        @Test
        public void testUseUnusableItem() {

        }

        @Test
        public void testRemoveTreasures() {

        }

        @Test
        public void testRemoveTreasurePlayerNoTreasure() {

        }

        @Test
        public void testGetKeyPlayerHasKey() {

        }

        @Test
        public void testGetKeyPlayerHasNoKey() {

        }

        @Test
        public void testAddPotion() {

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
