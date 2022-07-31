package dungeonmania.movementtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.TestUtils;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

import static dungeonmania.TestUtils.getPlayer;
import static dungeonmania.TestUtils.getEntities;

public class MovementTowardPlayer {
    @Test
    public void testMercenaryPortal() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("d_enemy_portal_test", "simple");

        // protal: 2,1
        // portal position: 3,3
        // mercenary position: 6,5
        controller.tick(Direction.UP);
        controller.tick(Direction.UP);
        controller.tick(Direction.UP);
        DungeonResponse current_dungeon = controller.tick(Direction.UP);

        EntityResponse player = getPlayer(current_dungeon).get();
        assertEquals(player.getPosition(), new Position(1, -3));

        // mercenary notify portal and it will get closer to player if use portal
        EntityResponse mercenary = getEntities(current_dungeon, "mercenary").get(0);
        assertEquals(mercenary.getPosition(), new Position(4, 3));

        // mercenary teleported by portal
        current_dungeon = controller.tick(Direction.UP);
        mercenary = getEntities(current_dungeon, "mercenary").get(0);
        // since it moved left into entry portal it should move out left of destination
        // portal
        assertEquals(new Position(2, 0), mercenary.getPosition());
    }

    @Test
    public void test_no_player_path_in_map() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("d_player_push_boulder_to_stuck_mercenary", "simple");
        controller.tick(Direction.RIGHT);
        controller.tick(Direction.LEFT);
        // push boulder to block mercenary's path

        controller.tick(Direction.LEFT);
        controller.tick(Direction.LEFT);
        DungeonResponse current_dungeon = controller.tick(Direction.LEFT);
        // player can't push if there is a mercenary next to the boulder

        EntityResponse player = getPlayer(current_dungeon).get();
        assertEquals(player.getPosition(), new Position(6, 1));

        EntityResponse mercenary = getEntities(current_dungeon, "mercenary").get(0);
        assertEquals(mercenary.getPosition(), new Position(4, 1));
    }

    @Test
    public void testMercenaryMovesIntoPortalPlayerAtExit() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("d_portalMercenaryOnePath", "simple");
        DungeonResponse dres = controller.tick(Direction.RIGHT);

        // Should move towards the portal
        assertEquals(new Position(2, 1), getEntities(dres, "mercenary").get(0).getPosition());

        dres = controller.tick(Direction.LEFT);

        // next to portal
        assertEquals(new Position(3, 1), getEntities(dres, "mercenary").get(0).getPosition());

        dres = controller.tick(Direction.LEFT);

        // merc should have teleported onto player and player wins
        assertEquals(0, TestUtils.countEntityOfType(dres, "mercenary"));
        assertFalse(dres.getBattles().isEmpty());
        assertEquals("", dres.getGoals());
    }

    @Test
    public void testNoPathButPlayerUnlocksDoorForPath() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse dres = controller.newGame("d_mercenaryNoPathUntilPlayerUnlocksDoor", "simple");
        Position mercStartPos = getEntities(dres, "mercenary").get(0).getPosition();

        dres = controller.tick(Direction.RIGHT);

        // should not move because no path to player, blocked by door
        assertEquals(mercStartPos, getEntities(dres, "mercenary").get(0).getPosition());

        dres = controller.tick(Direction.RIGHT);

        // should move because door is now unlocked
        assertNotEquals(mercStartPos, getEntities(dres, "mercenary").get(0).getPosition());

        controller.tick(Direction.RIGHT);
        controller.tick(Direction.RIGHT);
        dres = controller.tick(Direction.UP);

        // battle should have occured and player wins
        assertEquals("", dres.getGoals());
        assertEquals(1, dres.getBattles().size());
        assertEquals(0, getEntities(dres, "mercenary").size());
    }
}
