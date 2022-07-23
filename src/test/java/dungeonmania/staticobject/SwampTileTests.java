package dungeonmania.staticobject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.TestUtils;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class SwampTileTests {
    @Test
    public void testSwampTileMercenary() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("swamptile/d_swampTileMercenary", "c_noSpawns");

        DungeonResponse dres = dmc.tick(Direction.UP);

        // mercenary should have moved onto swamp tile because it can only move left
        Position mercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();

        dres = dmc.tick(Direction.UP);
        // mercenary is stuck for 5 more ticks
        assertEquals(mercPos, TestUtils.getEntities(dres, "mercenary").get(0).getPosition());

        dres = dmc.tick(Direction.UP);
        // mercenary is stuck for 4 more ticks
        assertEquals(mercPos, TestUtils.getEntities(dres, "mercenary").get(0).getPosition());

        dres = dmc.tick(Direction.UP);
        // mercenary is stuck for 3 more ticks
        assertEquals(mercPos, TestUtils.getEntities(dres, "mercenary").get(0).getPosition());

        dres = dmc.tick(Direction.UP);
        // mercenary is stuck for 2 more ticks
        assertEquals(mercPos, TestUtils.getEntities(dres, "mercenary").get(0).getPosition());

        dres = dmc.tick(Direction.UP);
        // mercenary is stuck for 1 more tick
        assertEquals(mercPos, TestUtils.getEntities(dres, "mercenary").get(0).getPosition());

        dres = dmc.tick(Direction.UP);
        // mercenary is stuck for 0 more ticks
        assertEquals(mercPos, TestUtils.getEntities(dres, "mercenary").get(0).getPosition());

        dres = dmc.tick(Direction.UP);
        // mercenary is free from swamp tile
        assertNotEquals(mercPos, TestUtils.getEntities(dres, "mercenary").get(0).getPosition());
    }

    @Test
    public void testSwampTileZombie() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("swamptile/d_swampTileZombie", "c_noSpawns");

        DungeonResponse dres = dmc.tick(Direction.UP);

        // zombie should have moved onto swamp tile because it can only move left
        Position zombiePos = TestUtils.getEntities(dres, "zombie_toast").get(0).getPosition();

        dres = dmc.tick(Direction.UP);
        // zombie is stuck for 5 more ticks
        assertEquals(zombiePos, TestUtils.getEntities(dres, "zombie_toast").get(0).getPosition());

        dres = dmc.tick(Direction.UP);
        // zombie is stuck for 4 more ticks
        assertEquals(zombiePos, TestUtils.getEntities(dres, "zombie_toast").get(0).getPosition());

        dres = dmc.tick(Direction.UP);
        // zombie is stuck for 3 more ticks
        assertEquals(zombiePos, TestUtils.getEntities(dres, "zombie_toast").get(0).getPosition());

        dres = dmc.tick(Direction.UP);
        // zombie is stuck for 2 more ticks
        assertEquals(zombiePos, TestUtils.getEntities(dres, "zombie_toast").get(0).getPosition());

        dres = dmc.tick(Direction.UP);
        // zombie is stuck for 1 more tick
        assertEquals(zombiePos, TestUtils.getEntities(dres, "zombie_toast").get(0).getPosition());

        dres = dmc.tick(Direction.UP);
        // zombie is stuck for 0 more ticks
        assertEquals(zombiePos, TestUtils.getEntities(dres, "zombie_toast").get(0).getPosition());

        dres = dmc.tick(Direction.UP);
        // zombie is free from swamp tile
        assertNotEquals(zombiePos, TestUtils.getEntities(dres, "zombie_toast").get(0).getPosition());
    }

    @Test
    public void testSwampTileSpider() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("swamptile/d_swampTileSpider", "c_noSpawns");

        DungeonResponse dres = dmc.tick(Direction.UP);

        // spider should have moved onto swamp tile because it can only move left
        Position spiderPos = TestUtils.getEntities(dres, "spider").get(0).getPosition();

        dres = dmc.tick(Direction.UP);
        // spider is stuck for 5 more ticks
        assertEquals(spiderPos, TestUtils.getEntities(dres, "spider").get(0).getPosition());

        dres = dmc.tick(Direction.UP);
        // spider is stuck for 4 more ticks
        assertEquals(spiderPos, TestUtils.getEntities(dres, "spider").get(0).getPosition());

        dres = dmc.tick(Direction.UP);
        // spider is stuck for 3 more ticks
        assertEquals(spiderPos, TestUtils.getEntities(dres, "spider").get(0).getPosition());

        dres = dmc.tick(Direction.UP);
        // spider is stuck for 2 more ticks
        assertEquals(spiderPos, TestUtils.getEntities(dres, "spider").get(0).getPosition());

        dres = dmc.tick(Direction.UP);
        // spider is stuck for 1 more tick
        assertEquals(spiderPos, TestUtils.getEntities(dres, "spider").get(0).getPosition());

        dres = dmc.tick(Direction.UP);
        // spider is stuck for 0 more ticks
        assertEquals(spiderPos, TestUtils.getEntities(dres, "spider").get(0).getPosition());

        dres = dmc.tick(Direction.UP);
        // spider is free from swamp tile
        assertNotEquals(spiderPos, TestUtils.getEntities(dres, "spider").get(0).getPosition());
    }

    @Test
    public void testSwampTileHydra() {
        // DungeonManiaController dmc = new DungeonManiaController();
        // dmc.newGame("swamptile/d_swampTileHydra", "c_noSpawns");

        // DungeonResponse dres = dmc.tick(Direction.UP);

        // // hydra should have moved onto swamp tile because it can only move left
        // Position hydraPos = TestUtils.getEntities(dres,
        // "hydra").get(0).getPosition();

        // dres = dmc.tick(Direction.UP);
        // // hydra is stuck for 5 more ticks
        // assertEquals(hydraPos, TestUtils.getEntities(dres,
        // "hydra").get(0).getPosition());

        // dres = dmc.tick(Direction.UP);
        // // hydra is stuck for 4 more ticks
        // assertEquals(hydraPos, TestUtils.getEntities(dres,
        // "hydra").get(0).getPosition());

        // dres = dmc.tick(Direction.UP);
        // // hydra is stuck for 3 more ticks
        // assertEquals(hydraPos, TestUtils.getEntities(dres,
        // "hydra").get(0).getPosition());

        // dres = dmc.tick(Direction.UP);
        // // hydra is stuck for 2 more ticks
        // assertEquals(hydraPos, TestUtils.getEntities(dres,
        // "hydra").get(0).getPosition());

        // dres = dmc.tick(Direction.UP);
        // // hydra is stuck for 1 more tick
        // assertEquals(hydraPos, TestUtils.getEntities(dres,
        // "hydra").get(0).getPosition());

        // dres = dmc.tick(Direction.UP);
        // // hydra is stuck for 0 more ticks
        // assertEquals(hydraPos, TestUtils.getEntities(dres,
        // "hydra").get(0).getPosition());

        // dres = dmc.tick(Direction.UP);
        // // hydra is free from swamp tile
        // assertNotEquals(hydraPos, TestUtils.getEntities(dres,
        // "hydra").get(0).getPosition());
    }

    @Test
    public void testSwampTileAssassin() {
        // DungeonManiaController dmc = new DungeonManiaController();
        // dmc.newGame("swamptile/d_swampTileAssassin", "c_noSpawns");

        // DungeonResponse dres = dmc.tick(Direction.UP);

        // // assassin should have moved onto swamp tile because it can only move left
        // Position assassinPos = TestUtils.getEntities(dres,
        // "assassin").get(0).getPosition();

        // dres = dmc.tick(Direction.UP);
        // // assassin is stuck for 5 more ticks
        // assertEquals(assassinPos, TestUtils.getEntities(dres,
        // "assassin").get(0).getPosition());

        // dres = dmc.tick(Direction.UP);
        // // assassin is stuck for 4 more ticks
        // assertEquals(assassinPos, TestUtils.getEntities(dres,
        // "assassin").get(0).getPosition());

        // dres = dmc.tick(Direction.UP);
        // // assassin is stuck for 3 more ticks
        // assertEquals(assassinPos, TestUtils.getEntities(dres,
        // "assassin").get(0).getPosition());

        // dres = dmc.tick(Direction.UP);
        // // assassin is stuck for 2 more ticks
        // assertEquals(assassinPos, TestUtils.getEntities(dres,
        // "assassin").get(0).getPosition());

        // dres = dmc.tick(Direction.UP);
        // // assassin is stuck for 1 more tick
        // assertEquals(assassinPos, TestUtils.getEntities(dres,
        // "assassin").get(0).getPosition());

        // dres = dmc.tick(Direction.UP);
        // // assassin is stuck for 0 more ticks
        // assertEquals(assassinPos, TestUtils.getEntities(dres,
        // "assassin").get(0).getPosition());

        // dres = dmc.tick(Direction.UP);
        // // assassin is free from swamp tile
        // assertNotEquals(assassinPos, TestUtils.getEntities(dres,
        // "assassin").get(0).getPosition());
    }
}
