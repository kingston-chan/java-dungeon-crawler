package dungeonmania.visiting;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.util.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Nested;

public class PlayerInteractionTest {

    @Test
    public void playerVisitsSinglePortalTest() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_simplePortal",
                "c_battleTests_basicMercenaryMercenaryDies");
        Dungeon testDungeon = DungeonManiaController.getDungeon();
        DungeonObject portal = testDungeon.getObjectsAtPosition(new Position(2, 3)).get(0);
        Player player = testDungeon.getPlayer();
        assertTrue(portal != null);
        portal.doAccept(player);

        assertEquals(new Position(7, 7), player.getPosition());
    }

    @Test
    public void playerVisitsChainedPortalTest() {
        // DungeonManiaController dmc = new DungeonManiaController();
        // dmc.newGame("d_chainedPortals",
        // "c_battleTests_basicMercenaryMercenaryDies");
        // Dungeon testDungeon = DungeonManiaController.getDungeon();
        // DungeonObject portal = testDungeon.getObjectsAtPosition(new Position(2,
        // 3)).get(0);
        // Player player = testDungeon.getPlayer();
        // assertTrue(portal != null);
        // assertTrue(portal.canAccept(player));
        // portal.doAccept(player);
        // assertEquals(new Position(5, 14), player.getPosition());
    }

    @Test
    public void playerVisitsDoorTest() {

    }

    @Test
    public void playerVisitsWallTest() {

    }

    @Test
    public void playerVisitsBoulderTest() {

    }

    @Test
    public void playerVisitsFloorSwitchTest() {

    }

    @Nested
    public class collectableItemTests {
        @Test
        public void playerVisitsTreasureTest() {

        }

        @Test
        public void playerVisitsWoodTest() {

        }

        @Test
        public void playerVisitsArrowTest() {

        }

        @Test
        public void playerVisitsBombTest() {

        }

        @Test
        public void playerVisitsKeyTest() {

        }

        @Test
        public void playerVisitsSwordTest() {

        }

        @Test
        public void playerVisitsInvisibilityPotionTest() {

        }

        @Test
        public void playerVisitsInvincibilityPotionTest() {

        }

    }

    @Nested
    public class hostileEntityTests {
        @Test
        public void playerZombieSpawnerSpiderTest() {

        }

        @Test
        public void playerNormalVisitsSpiderTest() {

        }

        @Test
        public void playerNormalVisitsZombieTest() {

        }

        @Test
        public void playerNormalVisitsMercenaryTest() {

        }

        @Test
        public void playerInvisibleVisitsSpiderTest() {

        }

        @Test
        public void playerInvisibleVisitsZombieTest() {

        }

        @Test
        public void playerInvisibleVisitsMercenaryTest() {

        }

        @Test
        public void playerInvincableVisitsSpiderTest() {

        }

        @Test
        public void playerInvincableVisitsZombieTest() {

        }

        @Test
        public void playerInvincableVisitsMercenaryTest() {

        }
    }

}
