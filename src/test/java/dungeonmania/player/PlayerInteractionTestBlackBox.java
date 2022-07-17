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
    public void playBlockedAlonePortalBlackBox() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_alone_portal",
                "simple");

        DungeonResponse res = dmc.tick(Direction.DOWN);

        assertEquals(new Position(2, 2), TestUtils.getEntities(res, "player").get(0).getPosition());
    }

    @Test
    public void playerVisitsWallTest() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_visitWall",
                "c_battleTests_basicMercenaryMercenaryDies");

        Position playerStartPos = TestUtils.getPlayer(res).get().getPosition();

        res = dmc.tick(Direction.UP);

        assertEquals(playerStartPos, TestUtils.getPlayer(res).get().getPosition());
    }

    @Test
    public void playerVisitsFloorSwitchTest() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_visitFloorSwitch",
                "c_battleTests_basicMercenaryMercenaryDies");

        Position switchPos = TestUtils.getEntities(res, "switch").get(0).getPosition();

        res = dmc.tick(Direction.UP);

        assertEquals(switchPos, TestUtils.getPlayer(res).get().getPosition());
    }

}
