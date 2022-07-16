package dungeonmania.movementtest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.TestUtils;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class FollowPlayerTests {
    @Test
    public void testAllyFollowPlayerSimple() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("d_allyFollowPlayer", "c_mercenaryBribeRadiusTwo");

        DungeonResponse dres = controller.tick(Direction.RIGHT);

        EntityResponse merc = TestUtils.getEntities(dres, "mercenary").get(0);
        assertDoesNotThrow(() -> controller.interact(merc.getId()));
        Position currPlayerPos = TestUtils.getPlayer(dres).get().getPosition();

        dres = controller.tick(Direction.RIGHT);
        assertEquals(currPlayerPos, TestUtils.getEntities(dres, "mercenary").get(0).getPosition());
        currPlayerPos = TestUtils.getPlayer(dres).get().getPosition();

        dres = controller.tick(Direction.RIGHT);
        assertEquals(currPlayerPos, TestUtils.getEntities(dres, "mercenary").get(0).getPosition());
        currPlayerPos = TestUtils.getPlayer(dres).get().getPosition();
    }

    @Test
    public void testAllyFollowPlayerPortal() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("d_allyFollowPlayerThroughPortal", "c_mercenaryBribeRadiusTwo");

        DungeonResponse dres = controller.tick(Direction.RIGHT);

        EntityResponse merc = TestUtils.getEntities(dres, "mercenary").get(0);
        assertDoesNotThrow(() -> controller.interact(merc.getId()));
        Position currPlayerPos = TestUtils.getPlayer(dres).get().getPosition();

        dres = controller.tick(Direction.RIGHT);
        assertEquals(currPlayerPos, TestUtils.getEntities(dres, "mercenary").get(0).getPosition());
        currPlayerPos = TestUtils.getPlayer(dres).get().getPosition();

        dres = controller.tick(Direction.RIGHT);
        assertEquals(currPlayerPos, TestUtils.getEntities(dres, "mercenary").get(0).getPosition());
        currPlayerPos = TestUtils.getPlayer(dres).get().getPosition();
    }

}
