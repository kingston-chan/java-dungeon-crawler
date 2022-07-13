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
