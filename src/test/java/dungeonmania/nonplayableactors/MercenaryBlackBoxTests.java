package dungeonmania.nonplayableactors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.TestUtils;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class MercenaryBlackBoxTests {
    @Test
    public void testMercenaryWall() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse dres = controller.newGame("mercenarytests/d_wallMercenary", "simple");
        Position currMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        dres = controller.tick(Direction.UP);
        // merc should not move because surrounded by walls
        assertEquals(currMercPos,
                TestUtils.getEntities(dres, "mercenary").get(0).getPosition());
    }

    @Test
    public void testMercenaryDoor() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("mercenarytests/d_doorMercenary", "simple");
        controller.tick(Direction.DOWN);
        controller.tick(Direction.DOWN);
        controller.tick(Direction.UP);
        DungeonResponse res = controller.tick(Direction.UP);
        Position doorPos = TestUtils.getEntities(res, "door").get(0).getPosition();
        // merc should be able to move to door because unlocked
        assertEquals(doorPos, TestUtils.getEntities(res, "mercenary").get(0).getPosition());
    }

    @Test
    public void testMercenarySwitch() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("mercenarytests/d_switchMercenary", "simple");
        DungeonResponse dres = controller.tick(Direction.UP);
        // merc should move down since they can move on switches
        assertEquals(TestUtils.getEntities(dres, "switch").get(0).getPosition(),
                TestUtils.getEntities(dres, "mercenary").get(0).getPosition());
    }

    @Test
    public void testMercenaryExit() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("mercenarytests/d_exitMercenary", "simple");
        DungeonResponse dres = controller.tick(Direction.UP);
        // merc should move down since they can move on exit
        assertEquals(TestUtils.getEntities(dres, "exit").get(0).getPosition(),
                TestUtils.getEntities(dres, "mercenary").get(0).getPosition());
    }

    @Test
    public void testMercenaryBoulder() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse dres = controller.newGame("mercenarytests/d_boulderMercenary", "simple");
        Position currMercPos = TestUtils.getEntities(dres, "mercenary").get(0).getPosition();
        dres = controller.tick(Direction.UP);
        // merc should move down since they cannot move onto boulders
        int downX = Direction.DOWN.getOffset().getX();
        int downY = Direction.DOWN.getOffset().getY();
        assertEquals(new Position(currMercPos.getX() + downX, currMercPos.getY() + downY),
                TestUtils.getEntities(dres, "mercenary").get(0).getPosition());
    }

    @Test
    public void testMercenarySpawner() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("mercenarytests/d_spawnerMercenary", "simple");
        DungeonResponse dres = controller.tick(Direction.UP);
        // merc should move down since they can move on spawners
        assertEquals(TestUtils.getEntities(dres, "zombie_toast_spawner").get(0).getPosition(),
                TestUtils.getEntities(dres, "mercenary").get(0).getPosition());
    }
}
