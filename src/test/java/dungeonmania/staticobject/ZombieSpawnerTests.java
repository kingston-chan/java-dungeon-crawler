package dungeonmania.staticobject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.TestUtils;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class ZombieSpawnerTests {
    @Test
    public void testZombieSpawnerSpawnsZombies() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse dres = controller.newGame("d_zombieSpawnerInteract", "c_zombieSpawnRateMid");
        assertEquals(0, TestUtils.countEntityOfType(dres, "zombie_toast"));

        dres = controller.tick(Direction.LEFT);
        assertEquals(0, TestUtils.countEntityOfType(dres, "zombie_toast"));

        dres = controller.tick(Direction.LEFT);
        assertEquals(0, TestUtils.countEntityOfType(dres, "zombie_toast"));

        dres = controller.tick(Direction.LEFT);
        assertEquals(1, TestUtils.countEntityOfType(dres, "zombie_toast"));

        dres = controller.tick(Direction.LEFT);
        assertEquals(1, TestUtils.countEntityOfType(dres, "zombie_toast"));

        dres = controller.tick(Direction.LEFT);
        assertEquals(1, TestUtils.countEntityOfType(dres, "zombie_toast"));

        dres = controller.tick(Direction.LEFT);
        assertEquals(2, TestUtils.countEntityOfType(dres, "zombie_toast"));
    }

    @Test
    public void testZombieSpawnerCannotSpawnWall() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse dres = controller.newGame("d_cannotSpawnZombiesWall", "c_zombieSpawnRate");
        assertEquals(0, TestUtils.countEntityOfType(dres, "zombie_toast"));

        // spawn rate is 1 so should spawn every tick, but blocked by walls so should
        // not spawn
        dres = controller.tick(Direction.LEFT);
        assertEquals(0, TestUtils.countEntityOfType(dres, "zombie_toast"));

        dres = controller.tick(Direction.LEFT);
        assertEquals(0, TestUtils.countEntityOfType(dres, "zombie_toast"));

        dres = controller.tick(Direction.LEFT);
        assertEquals(0, TestUtils.countEntityOfType(dres, "zombie_toast"));

        dres = controller.tick(Direction.LEFT);
        assertEquals(0, TestUtils.countEntityOfType(dres, "zombie_toast"));

        dres = controller.tick(Direction.LEFT);
        assertEquals(0, TestUtils.countEntityOfType(dres, "zombie_toast"));
    }

    @Test
    public void testZombieSpawnerCanSpawnOnePosition() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse dres = controller.newGame("d_zombieSpawnerOnePosition", "c_zombieSpawnRate");
        assertEquals(0, TestUtils.countEntityOfType(dres, "zombie_toast"));

        // spawn rate is 1 so should spawn every tick but only one available position
        dres = controller.tick(Direction.LEFT);
        assertEquals(1, TestUtils.countEntityOfType(dres, "zombie_toast"));
        assertEquals(new Position(2, 1), TestUtils.getEntities(dres, "zombie_toast").get(0).getPosition());

        dres = controller.tick(Direction.LEFT);
        assertEquals(2, TestUtils.countEntityOfType(dres, "zombie_toast"));

        dres = controller.tick(Direction.LEFT);
        assertEquals(3, TestUtils.countEntityOfType(dres, "zombie_toast"));

        dres = controller.tick(Direction.LEFT);
        assertEquals(4, TestUtils.countEntityOfType(dres, "zombie_toast"));

        dres = controller.tick(Direction.LEFT);
        assertEquals(5, TestUtils.countEntityOfType(dres, "zombie_toast"));
    }

    @Test
    public void testZombieSpawnerCannotSpawnBoulders() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse dres = controller.newGame("d_cannotSpawnZombiesBoulder", "c_zombieSpawnRate");
        assertEquals(0, TestUtils.countEntityOfType(dres, "zombie_toast"));

        // spawn rate is 1 so should spawn every tick, but blocked by boulders so should
        // not spawn
        dres = controller.tick(Direction.LEFT);
        assertEquals(0, TestUtils.countEntityOfType(dres, "zombie_toast"));

        dres = controller.tick(Direction.LEFT);
        assertEquals(0, TestUtils.countEntityOfType(dres, "zombie_toast"));

        dres = controller.tick(Direction.LEFT);
        assertEquals(0, TestUtils.countEntityOfType(dres, "zombie_toast"));

        dres = controller.tick(Direction.LEFT);
        assertEquals(0, TestUtils.countEntityOfType(dres, "zombie_toast"));

        dres = controller.tick(Direction.LEFT);
        assertEquals(0, TestUtils.countEntityOfType(dres, "zombie_toast"));
    }

    @Test
    public void testZombieSpawnsOnPlayerBattle() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse dres = controller.newGame("d_zombieSpawnerOnePosition", "c_zombieSpawnRate");
        assertEquals(0, TestUtils.countEntityOfType(dres, "zombie_toast"));

        // Player moves on to spawn tile and battle should occur
        dres = controller.tick(Direction.RIGHT);

        assertFalse(dres.getBattles().isEmpty());
    }
}
