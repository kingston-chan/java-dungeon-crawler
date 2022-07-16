package dungeonmania.nonplayableactors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.TestUtils;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;

public class SpiderBlackBoxTests {
    @Test
    public void testSpiderWall() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("spidertests/d_wallSpider", "c_noSpawns");
        DungeonResponse dres = controller.tick(Direction.UP);
        // spider should move down since they can move on walls
        assertEquals(TestUtils.getEntities(dres, "wall").get(0).getPosition(),
                TestUtils.getEntities(dres, "spider").get(0).getPosition());
    }

    @Test
    public void testSpiderDoor() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("spidertests/d_doorSpider", "c_noSpawns");
        DungeonResponse dres = controller.tick(Direction.UP);
        // spider should move down since they can move on walls
        assertEquals(TestUtils.getEntities(dres, "door").get(0).getPosition(),
                TestUtils.getEntities(dres, "spider").get(0).getPosition());
    }

    @Test
    public void testSpiderPortal() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("spidertests/d_portalSpider", "c_noSpawns");
        DungeonResponse dres = controller.tick(Direction.UP);
        // spider should move down since they can move on walls
        assertEquals(TestUtils.getEntities(dres, "portal").get(0).getPosition(),
                TestUtils.getEntities(dres, "spider").get(0).getPosition());
    }

    @Test
    public void testSpiderSwitch() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("spidertests/d_switchSpider", "c_noSpawns");
        DungeonResponse dres = controller.tick(Direction.UP);
        // spider should move down since they can move on walls
        assertEquals(TestUtils.getEntities(dres, "switch").get(0).getPosition(),
                TestUtils.getEntities(dres, "spider").get(0).getPosition());
    }

    @Test
    public void testSpiderExit() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("spidertests/d_exitSpider", "c_noSpawns");
        DungeonResponse dres = controller.tick(Direction.UP);
        // spider should move down since they can move on walls
        assertEquals(TestUtils.getEntities(dres, "exit").get(0).getPosition(),
                TestUtils.getEntities(dres, "spider").get(0).getPosition());
    }

    @Test
    public void testSpiderSpawner() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("spidertests/d_spawnerSpider", "c_noSpawns");
        DungeonResponse dres = controller.tick(Direction.UP);
        // spider should move down since they can move on walls
        assertEquals(TestUtils.getEntities(dres, "zombie_toast_spawner").get(0).getPosition(),
                TestUtils.getEntities(dres, "spider").get(0).getPosition());
    }
}
