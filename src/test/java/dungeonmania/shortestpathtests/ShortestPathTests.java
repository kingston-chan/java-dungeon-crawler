package dungeonmania.shortestpathtests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.TestUtils;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class ShortestPathTests {
    @Test
    public void testFindShortestPath() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("shortestpathtests/d_simpleShortestPath", "c_noSpawns");
        DungeonResponse dres = dmc.tick(Direction.UP);
        // merc should have moved left
        Position currentMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        assertEquals(new Position(-2, 3), currentMercPos);

        dres = dmc.tick(Direction.UP);
        // merc should have moved up
        currentMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        assertEquals(new Position(-2, 2), currentMercPos);

        dres = dmc.tick(Direction.UP);
        // merc should have moved up
        currentMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        assertEquals(new Position(-2, 1), currentMercPos);

        dres = dmc.tick(Direction.UP);
        // merc should have moved up
        currentMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        assertEquals(new Position(-2, 0), currentMercPos);

        dres = dmc.tick(Direction.UP);
        // merc should have moved right
        currentMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        assertEquals(new Position(-1, 0), currentMercPos);

        dres = dmc.tick(Direction.UP);
        // battle should have occured
        assertFalse(dres.getBattles().isEmpty());
    }

    @Test
    public void testFindShortestPathSwampTile() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("shortestpathtests/d_shortestPathSwampTile", "c_noSpawns");
        DungeonResponse dres = dmc.tick(Direction.UP);
        // merc should have moved right because going left towards to swamp tile takes
        // longer ticks to reach the player
        Position currentMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        assertEquals(new Position(0, 3), currentMercPos);

        dres = dmc.tick(Direction.UP);
        // merc should have moved right
        currentMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        assertEquals(new Position(1, 3), currentMercPos);

        dres = dmc.tick(Direction.UP);
        // merc should have moved right
        currentMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        assertEquals(new Position(2, 3), currentMercPos);

        dres = dmc.tick(Direction.UP);
        // merc should have moved up
        currentMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        assertEquals(new Position(2, 2), currentMercPos);

        dres = dmc.tick(Direction.UP);
        // merc should have moved up
        currentMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        assertEquals(new Position(2, 1), currentMercPos);

        dres = dmc.tick(Direction.UP);
        // merc should have moved up
        currentMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        assertEquals(new Position(2, 0), currentMercPos);

        dres = dmc.tick(Direction.UP);
        // merc should have moved left
        currentMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        assertEquals(new Position(1, 0), currentMercPos);

        dres = dmc.tick(Direction.UP);
        // battle should have occured
        assertFalse(dres.getBattles().isEmpty());
    }

    @Test
    public void testFindShortestPathPortal() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("shortestpathtests/d_shortestPathPortal", "c_noSpawns");
        DungeonResponse dres = dmc.tick(Direction.UP);
        // merc should have moved left towards portal because it takes less ticks to
        // player
        Position currentMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        assertEquals(new Position(0, 3), currentMercPos);

        dres = dmc.tick(Direction.UP);
        // merc should have moved left
        currentMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        assertEquals(new Position(-1, 3), currentMercPos);

        dres = dmc.tick(Direction.UP);
        // merc should have moved left into portal and teleported
        currentMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        assertEquals(new Position(-1, 0), currentMercPos);

        dres = dmc.tick(Direction.UP);
        // merc should have moved right into player and battled
        assertFalse(dres.getBattles().isEmpty());
    }

    @Test
    public void testFindShortestPathPortalSwampTile() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("shortestpathtests/d_shortestPathPortalSwampTile", "c_noSpawns");
        DungeonResponse dres = dmc.tick(Direction.UP);
        // merc should have moved right away from portal because it takes it to a swamp
        // tile which takes more ticks than going right towards the player
        Position currentMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        assertEquals(new Position(2, 3), currentMercPos);

        dres = dmc.tick(Direction.UP);
        // merc should have moved up
        currentMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        assertEquals(new Position(2, 2), currentMercPos);

        dres = dmc.tick(Direction.UP);
        // merc should have moved up
        currentMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        assertEquals(new Position(2, 1), currentMercPos);

        dres = dmc.tick(Direction.UP);
        // merc should have moved up
        currentMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        assertEquals(new Position(2, 0), currentMercPos);

        dres = dmc.tick(Direction.UP);
        // merc should have moved left
        currentMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        assertEquals(new Position(1, 0), currentMercPos);

        dres = dmc.tick(Direction.UP);
        // merc should have moved left into player and battled
        assertFalse(dres.getBattles().isEmpty());
    }

    @Test
    public void testShortestPathIncludesSwampTile() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("shortestpathtests/d_shortestPathIncludeSwampTile", "c_noSpawns");
        DungeonResponse dres = dmc.tick(Direction.UP);
        // merc should have moved up into swamp tile because it takes less ticks to
        // reach player going this way
        Position currentMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        assertEquals(new Position(-2, 2), currentMercPos);

        dres = dmc.tick(Direction.UP);
        // merc is stuck for 2 ticks
        currentMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        assertEquals(new Position(-2, 2), currentMercPos);

        dres = dmc.tick(Direction.UP);
        // merc is stuck for 1 ticks
        currentMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        assertEquals(new Position(-2, 2), currentMercPos);

        dres = dmc.tick(Direction.UP);
        // merc should have moved up
        currentMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        assertEquals(new Position(-2, 1), currentMercPos);

        dres = dmc.tick(Direction.UP);
        // merc should have moved up
        currentMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        assertEquals(new Position(-2, 0), currentMercPos);

        dres = dmc.tick(Direction.UP);
        // merc should have moved right
        currentMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        assertEquals(new Position(-1, 0), currentMercPos);

        dres = dmc.tick(Direction.UP);
        // merc should have moved left into player and battled
        assertFalse(dres.getBattles().isEmpty());
    }

    @Test
    public void testChangeDirectionWhenDoorOpened() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("shortestpathtests/d_shortestPathDoor", "c_noSpawns");
        // Player picks up key
        DungeonResponse dres = dmc.tick(Direction.RIGHT);
        // merc should have moved up right because its currently the shortest path
        // because door locked
        Position currentMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        assertEquals(new Position(2, 3), currentMercPos);

        // player unlocks door
        dres = dmc.tick(Direction.DOWN);
        // merc should recalculate path and include unlocked door, therefore should go
        // back (left)
        currentMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        assertEquals(new Position(1, 3), currentMercPos);

        dres = dmc.tick(Direction.RIGHT);
        // merc should move right
        currentMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        assertEquals(new Position(0, 3), currentMercPos);

        dres = dmc.tick(Direction.RIGHT);
        // merc should have moved up
        currentMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        assertEquals(new Position(0, 2), currentMercPos);

        dres = dmc.tick(Direction.RIGHT);
        // merc should have moved up into player and battle
        assertFalse(dres.getBattles().isEmpty());
    }

    @Test
    public void testChangeDirectionWhenBoulderPushed() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("shortestpathtests/d_shortestPathBoulderPushed", "c_noSpawns");
        DungeonResponse dres = dmc.tick(Direction.UP);
        // merc should have moved up right because its currently the shortest path
        // because boulder is blocking
        Position currentMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        assertEquals(new Position(-1, 3), currentMercPos);

        dres = dmc.tick(Direction.LEFT);
        // merc should recalculate path and include path with boulder out of the way
        currentMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        assertEquals(new Position(-2, 3), currentMercPos);

        dres = dmc.tick(Direction.UP);
        // merc should move up
        currentMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        assertEquals(new Position(-2, 2), currentMercPos);

        dres = dmc.tick(Direction.UP);
        // merc should have moved up
        currentMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        assertEquals(new Position(-2, 1), currentMercPos);

        dres = dmc.tick(Direction.UP);
        // merc should have moved up into player and battle
        assertFalse(dres.getBattles().isEmpty());
    }

    @Test
    public void testExitsPortalClosestToPlayer() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("shortestpathtests/mercenaryMovesToPortal", "c_noSpawns");
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        DungeonResponse dres = dmc.tick(Direction.RIGHT);
        // should be at right above player/below portal, not right of portal
        assertEquals(new Position(3, 4), TestUtils.getEntities(dres, "mercenary").get(0).getPosition());
    }

    @Test
    void testDoesNotTakePortal() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_mercenaryDoesNotTakePortal", "c_noSpawns");
        DungeonResponse res = dmc.tick(Direction.DOWN);
        int playerX = TestUtils.getPlayer(res).get().getPosition().getX();
        int playerY = TestUtils.getPlayer(res).get().getPosition().getY();
        assertEquals(new Position(playerX, playerY - 1), TestUtils.getEntities(res, "mercenary").get(0).getPosition());
    }
}
