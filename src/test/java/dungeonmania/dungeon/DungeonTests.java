package dungeonmania.dungeon;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.exit.Exit;
import dungeonmania.entities.staticobject.wall.Wall;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
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
            DungeonManiaController dmc = new DungeonManiaController();
            dmc.newGame("d_battleTest_basicMercenary",
                    "c_battleTests_basicMercenaryMercenaryDies");
            Dungeon testDungeon = DungeonManiaController.getDungeon();
            List<DungeonObject> dungeonObjects = testDungeon.getDungeonObjects();
            Player player = testDungeon.getPlayer();
            assertEquals(11, dungeonObjects.size());
            assertEquals(new Position(0, 1), player.getPosition());
            assertEquals("player", player.getType());
            assertEquals(1, testDungeon.getNonPlayableActors().size());
            NonPlayableActor merc = testDungeon.getNonPlayableActors().get(0);
            assertEquals(new Position(2, 1), merc.getPosition());
            assertEquals("mercenary", merc.getType());
            assertEquals(9, testDungeon.getStaticObjects().size());
            List<Wall> walls = testDungeon.getDungeonObjects().stream().filter(o -> o instanceof Wall)
                    .map(o -> (Wall) o).collect(Collectors.toList());
            assertEquals(8, walls.size());
            for (Wall w : walls) {
                assertEquals("wall", w.getType());
            }
            Exit exit = testDungeon.getDungeonObjects().stream().filter(o -> o instanceof Exit)
                    .map(o -> (Exit) o).findFirst().get();
            assertEquals("exit", exit.getType());
            assertEquals(new Position(0, 0), exit.getPosition());
        }

        @Test
        public void testCreateActors() {
            DungeonManiaController dmc = new DungeonManiaController();
            dmc.newGame("d_simpleActors", "c_differentEnemyHealthAttack");
            Dungeon testDungeon = DungeonManiaController.getDungeon();
            List<NonPlayableActor> nonPlayableActors = testDungeon.getNonPlayableActors();
            Player player = testDungeon.getPlayer();

            assertEquals(player.getPosition(), new Position(0, 1));
            assertEquals(player.getType(), "player");
            assertEquals(player.getAttackPoints(), 10);
            assertEquals(player.getHealthPoints(), 10);

            assertTrue(nonPlayableActors.size() == 3);

            int testedAllThree = 0;

            for (NonPlayableActor e : nonPlayableActors) {
                if (e.getType().equals("mercenary")) {
                    // mercenary
                    assertEquals(e.getPosition(), new Position(2, 1));
                    assertEquals(e.getAttackPoints(), 7);
                    assertEquals(e.getHealthPoints(), 3);
                    testedAllThree++;
                }

                if (e.getType().equals("spider")) {
                    // spider
                    assertEquals(e.getPosition(), new Position(4, 5));
                    assertEquals(e.getAttackPoints(), 2);
                    assertEquals(e.getHealthPoints(), 8);
                    testedAllThree++;
                }

                if (e.getType().equals("zombie_toast")) {
                    // zombie toast
                    assertEquals(e.getPosition(), new Position(3, 6));
                    assertEquals(e.getAttackPoints(), 9);
                    assertEquals(e.getHealthPoints(), 5);
                    testedAllThree++;
                }
            }

            assertEquals(testedAllThree, 3);
        }

        @Test
        public void testGetConfigFile() {
            DungeonManiaController dmc = new DungeonManiaController();
            dmc.newGame("d_battleTest_basicMercenary", "c_battleTests_basicMercenaryMercenaryDies");
            Dungeon testDungeon = DungeonManiaController.getDungeon();
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
        public void testGetGoalsAnd() {
            DungeonManiaController dmc = new DungeonManiaController();
            dmc.newGame("d_complexGoalsTest_andAll",
                    "c_battleTests_basicMercenaryMercenaryDies");
            Dungeon testDungeon = DungeonManiaController.getDungeon();
            assertEquals("(:exit AND :treasure) AND (:boulders AND :enemies)",
                    testDungeon.getDungeonResponse().getGoals());

            dmc.newGame("d_halfComplexAndGoals", "c_battleTests_basicMercenaryMercenaryDies");
            Dungeon testDungeon2 = DungeonManiaController.getDungeon();
            assertEquals("(:exit AND :treasure) AND :boulders", testDungeon2.getDungeonResponse().getGoals());

            dmc.newGame("d_twoAndGoals", "c_battleTests_basicMercenaryMercenaryDies");
            Dungeon testDungeon3 = DungeonManiaController.getDungeon();
            assertEquals(":exit AND :boulders", testDungeon3.getDungeonResponse().getGoals());

            dmc.newGame("d_unusableItems", "c_battleTests_basicMercenaryMercenaryDies");
            Dungeon testDungeon4 = DungeonManiaController.getDungeon();
            assertEquals(":enemies", testDungeon4.getDungeonResponse().getGoals());
        }

        @Test
        public void testGetGoalsOr() {
            DungeonManiaController dmc = new DungeonManiaController();
            dmc.newGame("d_complexOrGoals_andAll", "c_battleTests_basicMercenaryMercenaryDies");
            Dungeon testDungeon = DungeonManiaController.getDungeon();
            assertEquals("(:exit OR :treasure) OR (:boulders AND :enemies)",
                    testDungeon.getDungeonResponse().getGoals());

            dmc.newGame("d_halfComplexOrGoals", "c_battleTests_basicMercenaryMercenaryDies");
            Dungeon testDungeon2 = DungeonManiaController.getDungeon();
            assertEquals("(:exit OR :treasure) OR :enemies", testDungeon2.getDungeonResponse().getGoals());

            dmc.newGame("d_twoOrGoals", "c_battleTests_basicMercenaryMercenaryDies");
            Dungeon testDungeon3 = DungeonManiaController.getDungeon();
            assertEquals(":exit OR :boulders", testDungeon3.getDungeonResponse().getGoals());
        }

        @Test
        public void checkSimpleExitGoal() {
            DungeonManiaController dmc = new DungeonManiaController();
            dmc.newGame("d_simpleExitGoalAchieved", "c_battleTests_basicMercenaryMercenaryDies");
            Dungeon testDungeon = DungeonManiaController.getDungeon();
            assertEquals("", testDungeon.getDungeonResponse().getGoals());
        }

    }

    @Nested
    public class DungeonUnitBlackBoxTests {
        @Test
        public void testGetDungeonResponse() {
            DungeonManiaController dmc = new DungeonManiaController();
            DungeonResponse dres = dmc.newGame("d_battleTest_basicMercenary",
                    "c_battleTests_basicMercenaryMercenaryDies");

            EntityResponse initPlayer = getPlayer(dres).get();

            EntityResponse expectedPlayer = new EntityResponse(initPlayer.getId(),
                    "player", new Position(0, 1), false);

            assertEquals(initPlayer, expectedPlayer);

            List<EntityResponse> walls = getEntities(dres, "wall");
            assertEquals(8, walls.size());

            List<EntityResponse> mercenary = getEntities(dres, "mercenary");
            assertEquals(1, mercenary.size());

            assertEquals(dres.getGoals(), ":exit");
        }

        @Test
        public void testPlayerGoalsAnd() {
            DungeonManiaController dmc = new DungeonManiaController();
            DungeonResponse dres = dmc.newGame("d_complexGoalsTest_andAll",
                    "c_complexGoalsTest_andAll");

            assertEquals("(:exit AND :treasure) AND (:boulders AND :enemies)", dres.getGoals());

            dres = dmc.tick(Direction.RIGHT);
            assertEquals("(:exit AND :treasure) AND :boulders", dres.getGoals());

            dres = dmc.tick(Direction.RIGHT);

            assertEquals(":exit AND :treasure", dres.getGoals());

            dres = dmc.tick(Direction.DOWN);

            assertEquals(dres.getGoals(), ":exit");

            dres = dmc.tick(Direction.DOWN);

            assertEquals(dres.getGoals(), "");
        }

        @Test
        public void testPlayerGoalsOr() {
            DungeonManiaController dmc = new DungeonManiaController();
            DungeonResponse dres = dmc.newGame("d_complexOrGoals_andAll",
                    "c_complexGoalsTest_andAll");

            assertEquals(dres.getGoals(), "(:exit OR :treasure) OR (:boulders AND :enemies)");

            dmc.tick(Direction.RIGHT);
            dmc.tick(Direction.DOWN);
            dres = dmc.tick(Direction.RIGHT);

            assertEquals(dres.getGoals(), "");
        }

        @Test
        public void checkExitGoalLast() {
            DungeonManiaController dmc = new DungeonManiaController();
            DungeonResponse dres = dmc.newGame("d_exitLast", "simple");

            assertEquals(":exit AND :boulders", dres.getGoals());
        
            // now on exit, but exit must be done last
            dres = dmc.tick(Direction.DOWN);
            assertEquals(":exit AND :boulders", dres.getGoals());

            dmc.tick(Direction.UP);
            dres = dmc.tick(Direction.RIGHT);
            //boulder in positon 
            assertEquals(":exit", dres.getGoals());
            dmc.tick(Direction.LEFT);
            dres = dmc.tick(Direction.DOWN);
            assertEquals("", dres.getGoals());
        }

        @Test
        public void tickSpawnSpiders() {
            DungeonManiaController dmc = new DungeonManiaController();
            DungeonResponse dres = dmc.newGame("d_complexGoalsTest_andAll",
                    "c_spiderSpawnRateTest");

            assertTrue(dres.getEntities().size() == 6);

            dres = dmc.tick(Direction.LEFT);
            assertTrue(dres.getEntities().size() == 7);

            dres = dmc.tick(Direction.LEFT);
            assertTrue(dres.getEntities().size() == 8);

            dres = dmc.tick(Direction.LEFT);
            assertTrue(dres.getEntities().size() == 9);

            dres = dmc.tick(Direction.LEFT);
            assertTrue(dres.getEntities().size() == 10);

            dres = dmc.tick(Direction.LEFT);
            assertTrue(dres.getEntities().size() == 11);
        }
    }
}
