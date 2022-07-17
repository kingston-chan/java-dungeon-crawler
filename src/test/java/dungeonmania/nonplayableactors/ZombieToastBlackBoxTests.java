package dungeonmania.nonplayableactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import dungeonmania.util.Direction;
import dungeonmania.util.Position;
import dungeonmania.DungeonManiaController;
import dungeonmania.TestUtils;
import dungeonmania.response.models.DungeonResponse;

public class ZombieToastBlackBoxTests {
    @Test
    public void testZombieWall() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse dres = controller.newGame("zombietests/d_wallZombie", "c_noSpawns");
        Position currZombPos = TestUtils.getEntities(dres, "zombie_toast").get(0).getPosition();
        dres = controller.tick(Direction.UP);
        // zombie should not move because surrounded by walls
        assertEquals(currZombPos,
                TestUtils.getEntities(dres, "zombie_toast").get(0).getPosition());
    }

    @Test
    public void testZombieDoor() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse dres = controller.newGame("zombietests/d_doorZombie", "c_noSpawns");
        Position currZombPos = TestUtils.getEntities(dres, "zombie_toast").get(0).getPosition();
        dres = controller.tick(Direction.DOWN);
        // zombie should not move because surrounded by walls and a lock door
        assertEquals(currZombPos,
                TestUtils.getEntities(dres, "zombie_toast").get(0).getPosition());
        dres = controller.tick(Direction.DOWN);
        // zombie should move up since door is unlocked and player is on door, so a
        // battle occurs
        assertFalse(dres.getBattles().isEmpty());
        assertEquals("zombie_toast", dres.getBattles().get(0).getEnemy());
    }

    @Test
    public void testZombieSwitch() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("zombietests/d_switchZombie", "c_noSpawns");
        DungeonResponse dres = controller.tick(Direction.UP);
        // zombie should move down since they can move on switches
        assertEquals(TestUtils.getEntities(dres, "switch").get(0).getPosition(),
                TestUtils.getEntities(dres, "zombie_toast").get(0).getPosition());
    }

    @Test
    public void testZombieExit() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("zombietests/d_exitZombie", "c_noSpawns");
        DungeonResponse dres = controller.tick(Direction.UP);
        // zombie should move down since they can move on exits
        assertEquals(TestUtils.getEntities(dres, "exit").get(0).getPosition(),
                TestUtils.getEntities(dres, "zombie_toast").get(0).getPosition());
    }

    @Test
    public void testZombieBoulder() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse dres = controller.newGame("zombietests/d_boulderZombie", "c_noSpawns");
        Position currZombPos = TestUtils.getEntities(dres, "zombie_toast").get(0).getPosition();
        dres = controller.tick(Direction.UP);
        // zombie should not move because surrounded by boulders
        assertEquals(currZombPos,
                TestUtils.getEntities(dres, "zombie_toast").get(0).getPosition());
    }
}
