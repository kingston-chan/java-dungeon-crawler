package dungeonmania.visiting;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.TestUtils;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.portal.Portal;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;
import dungeonmania.TestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Nested;

public class PlayerInteractionTestBlackBox {

    @Test
    public void playerVisitsSinglePortalTestBlackBox() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_simplePortal",
                "c_battleTests_basicMercenaryMercenaryDies");

        DungeonResponse res = dmc.tick(Direction.DOWN);

        assertEquals(new Position(7, 7), TestUtils.getEntities(res, "player").get(0).getPosition());
        assertEquals("", TestUtils.getGoals(res));
        assertEquals(1, TestUtils.getInventory(res, "sword").size());
    }

    @Test
    public void playerVisitsChainedPortalTestBlackBox() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_chainedPortals",
                "c_battleTests_basicMercenaryMercenaryDies");

        DungeonResponse res = dmc.tick(Direction.DOWN);

        assertEquals(new Position(5, 14), TestUtils.getEntities(res, "player").get(0).getPosition());
    }

    @Test
    public void playVisitsBlockedPortalTestBlackBox() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_unreachablePortalDestination",
                "c_battleTests_basicMercenaryMercenaryDies");

        DungeonResponse res = dmc.tick(Direction.DOWN);

        assertEquals(new Position(2, 2), TestUtils.getEntities(res, "player").get(0).getPosition());
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
