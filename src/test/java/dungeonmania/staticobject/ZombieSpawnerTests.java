package dungeonmania.staticobject;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void testZombieSpawnerCannotSpawner() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse dres = controller.newGame("d_cannotSpawnZombies", "c_zombieSpawnRate");
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

        // spawn rate is 1 so should spawn every tick, but blocked by walls so should
        // not spawn
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
}
