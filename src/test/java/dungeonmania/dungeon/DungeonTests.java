package dungeonmania.dungeon;

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

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.Item;
import dungeonmania.response.models.BattleResponse;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.RoundResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

/**
 * Dungeon tests
 * 
 * @author Kingston Chan
 */
public class DungeonTests {
    @Nested
    public class DungeonUnitWhiteBoxTests {
        @Test
        public void testInitialiseDungeon() {
            Dungeon testDungeon = new Dungeon();
            testDungeon.initDungeon("d_battleTest_basicMercenary", "c_battleTests_basicMercenaryMercenaryDies");
            List<DungeonObject> dungeonObjects = testDungeon.getDungeonObjects();

            assertTrue(dungeonObjects.size() == 11);
            assertEquals(dungeonObjects.get(0).getPosition(), new Position(0, 1));
            assertEquals(dungeonObjects.get(0).getType(), "player");
            assertFalse(dungeonObjects.get(0).isInteractable());
            assertEquals(dungeonObjects.get(0).getUniqueId(), testDungeon.getPlayer().getUniqueId());
            assertEquals(testDungeon.getPlayer(), dungeonObjects.get(0));

            assertEquals(dungeonObjects.get(1).getPosition(), new Position(2, 1));
            assertEquals(dungeonObjects.get(1).getType(), "mercenary");
            assertTrue(dungeonObjects.get(1).isInteractable());
            assertEquals(testDungeon.getActiveEnemy(dungeonObjects.get(1).getUniqueId()), dungeonObjects.get(1));

            assertEquals(dungeonObjects.get(6).getPosition(), new Position(3, 1));
            assertEquals(dungeonObjects.get(1).getType(), "wall");
            assertFalse(dungeonObjects.get(6).isInteractable());
            assertEquals(testDungeon.getStaticObject(dungeonObjects.get(6).getUniqueId()), dungeonObjects.get(6));

        }

        @Test
        public void testGetConfigFile() {
            Dungeon testDungeon = new Dungeon();
            testDungeon.initDungeon("d_battleTest_basicMercenary", "c_battleTests_basicMercenaryMercenaryDies");
            assertEquals(testDungeon.getConfig("ally_attack"), 3);
            assertEquals(testDungeon.getConfig("ally_defence"), 3);
            assertEquals(testDungeon.getConfig("bomb_radius"), 1);
            assertEquals(testDungeon.getConfig("bow_durability"), 1);
            assertEquals(testDungeon.getConfig("bribe_amount"), 1);
            assertEquals(testDungeon.getConfig("bribe_radius"), 1);
            assertEquals(testDungeon.getConfig("enemy_goal"), 1);
            assertEquals(testDungeon.getConfig("invincibility_potion_duration"), 1);
            assertEquals(testDungeon.getConfig("invisibility_potion_duration"), 1);
            assertEquals(testDungeon.getConfig("mercenary_attack"), 5);
            assertEquals(testDungeon.getConfig("mercenary_health"), 5);
            assertEquals(testDungeon.getConfig("player_attack"), 10);
            assertEquals(testDungeon.getConfig("player_health"), 10);
            assertEquals(testDungeon.getConfig("shield_defence"), 1);
            assertEquals(testDungeon.getConfig("shield_durability"), 1);
            assertEquals(testDungeon.getConfig("spider_attack"), 5);
            assertEquals(testDungeon.getConfig("spider_spawn_rate"), 0);
            assertEquals(testDungeon.getConfig("sword_attack"), 2);
            assertEquals(testDungeon.getConfig("sword_durability"), 1);
            assertEquals(testDungeon.getConfig("treasure_goal"), 1);
            assertEquals(testDungeon.getConfig("zombie_attack"), 5);
            assertEquals(testDungeon.getConfig("zombie_health"), 5);
            assertEquals(testDungeon.getConfig("zombie_spawn_rate"), 0);
        }

        @Test
        public void testGetGoals() {
            Dungeon testDungeon = new Dungeon();
            testDungeon.initDungeon("d_complexGoalsTest_andAll", "c_battleTests_basicMercenaryMercenaryDies");
            assertEquals(testDungeon.getGoals(), "(:exit AND :treasure) AND (:boulders AND :enemies)");
            Item treasure = testDungeon.getItemInDungeon(testDungeon.getDungeonObjects().get(4).getUniqueId());
            testDungeon.removeItemFromDungeon(treasure);
            assertEquals(testDungeon.getGoals(), ":exit AND (:boulders AND :enemies)");
        }

        @Test
        public void testGetBattles() {
            Dungeon testDungeon = new Dungeon();
            testDungeon.initDungeon("d_complexGoalsTest_andAll", "c_battleTests_basicMercenaryMercenaryDies");
            Player player = testDungeon.getPlayer();
            testDungeon.getDungeonObjects().get(1).accept(testDungeon, player,
                    testDungeon.getDungeonObjects().get(1).getUniqueId());

            assertTrue(testDungeon.getBattles().size() == 1);
            assertEquals(testDungeon.getBattles().get(0).getEnemyType(), "spider");
            assertEquals(testDungeon.getBattles().get(0).getInitialEnemyHealth(), (double) 5);
            assertEquals(testDungeon.getBattles().get(0).getInitialPlayerHealth(), (double) 10);

            assertTrue(testDungeon.getBattles().get(0).getRounds().size() == 3);
            assertEquals(testDungeon.getBattles().get(0).getRounds().get(0).getPlayerHealthChange(), 0.5);
            assertEquals(testDungeon.getBattles().get(0).getRounds().get(0).getEnemyHealthChange(), 2.0);
            assertTrue(testDungeon.getBattles().get(0).getRounds().get(0).getPlayerWeaponsUsed().size() == 0);
        }
    }

    @Nested
    public class DungeonUnitBlackBoxTests {
        @Test
        public void testGetDungeonResponse() {
            DungeonManiaController dmc = new DungeonManiaController();
            DungeonResponse dres = dmc.newGame("d_complexGoalsTest_andAll",
                    "c_battleTests_basicMercenaryMercenaryDies");

            EntityResponse initPlayer = getPlayer(dres).get();

            EntityResponse expectedPlayer = new EntityResponse(initPlayer.getId(),
                    "player", new Position(0, 1), false);

            assertEquals(initPlayer, expectedPlayer);

            List<EntityResponse> entities = getEntities(dres, "wall");
            assertEquals(entities.get(0).getType(), "wall");
            assertEquals(entities.get(0).getPosition(), new Position(1, 0));
            assertEquals(entities.get(2).getType(), "wall");
            assertEquals(entities.get(1).getPosition(), new Position(3, 0));
            assertEquals(entities.get(6).getType(), "wall");
            assertEquals(entities.get(6).getPosition(), new Position(1, 2));

            assertEquals(dres.getGoals(), ":exit");
        }
    }
}
