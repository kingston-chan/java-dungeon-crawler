package dungeonmania.player;

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

public class PlayerInteractionTestWhiteBox {

        @Test
        public void playerVisitsSinglePortalTestWhiteBox() {
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
        public void playerVisitsChainedPortalTestWhiteBox() {
                DungeonManiaController dmc = new DungeonManiaController();
                dmc.newGame("d_chainedPortals",
                                "c_battleTests_basicMercenaryMercenaryDies");
                Dungeon testDungeon = DungeonManiaController.getDungeon();
                Portal portal = testDungeon.getObjectsAtPosition(new Position(2, 3)).stream()
                                .filter(o -> o instanceof Portal)
                                .map(o -> (Portal) o).findFirst().get();
                Player player = testDungeon.getPlayer();
                assertTrue(portal != null);
                assertTrue(portal.canAccept(player));
                portal.doAccept(player);
                assertEquals(new Position(5, 14), player.getPosition());
        }

        @Test
        public void playVisitsBlockedPortalTestWhiteBox() {
                DungeonManiaController dmc = new DungeonManiaController();
                dmc.newGame("d_unreachablePortalDestination",
                                "c_battleTests_basicMercenaryMercenaryDies");

                Dungeon testDungeon = DungeonManiaController.getDungeon();
                Portal portal = testDungeon.getObjectsAtPosition(new Position(2, 3)).stream()
                                .filter(o -> o instanceof Portal)
                                .map(o -> (Portal) o).findFirst().get();

                Player player = testDungeon.getPlayer();

                assertTrue(portal != null);
                assertFalse(portal.canAccept(player));
                assertEquals(new Position(2, 2), player.getPosition());
        }

        @Test
        public void playerVisitsWallTest() {
                DungeonManiaController dmc = new DungeonManiaController();
                dmc.newGame("d_visitWall",
                                "c_battleTests_basicMercenaryMercenaryDies");

                Dungeon testDungeon = DungeonManiaController.getDungeon();

                Player player = testDungeon.getPlayer();

                DungeonObject wall = testDungeon.getStaticObjectsAtPosition(new Position(1, 0)).get(0);

                assertFalse(wall.canAccept(player));
        }

        @Test
        public void playerVisitsFloorSwitchTest() {
                DungeonManiaController dmc = new DungeonManiaController();
                dmc.newGame("d_visitFloorSwitch",
                                "c_battleTests_basicMercenaryMercenaryDies");

                Dungeon testDungeon = DungeonManiaController.getDungeon();

                Player player = testDungeon.getPlayer();

                DungeonObject floorSwitch = testDungeon.getStaticObjectsAtPosition(new Position(1, 0)).get(0);

                assertTrue(floorSwitch.canAccept(player));
        }

}
