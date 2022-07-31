package dungeonmania.logicalentities;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.TestUtils;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;

public class LogicBombTests {
    @Test
    public void testNonLogicBombDoesNotExplodeWhenCircuitActivates() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("logicBombTests/d_nonLogicBomb", "c_noSpawnsM3");
        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        // pick up bomb
        DungeonResponse dres = dmc.tick(Direction.UP);
        assertDoesNotThrow(() -> dmc.tick(TestUtils.getInventory(dres, "bomb").get(0).getId()));

        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.UP);
        // acitvate switch
        DungeonResponse res = dmc.tick(Direction.RIGHT);
        assertEquals(2, TestUtils.countEntityOfType(res, "switch"));
        assertEquals(1, TestUtils.countEntityOfType(res, "boulder"));
        assertEquals(1, TestUtils.countEntityOfType(res, "bomb"));
        assertEquals(1, TestUtils.countEntityOfType(res, "wire"));
    }

    @Test
    public void testNonLogicBombDoesNotExplodeOnUse() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("logicBombTests/d_nonLogicBomb", "c_noSpawnsM3");
        // acitvate switch
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        // pick up bomb
        DungeonResponse dres = dmc.tick(Direction.UP);
        assertDoesNotThrow(() -> {
            DungeonResponse res = dmc.tick(TestUtils.getInventory(dres, "bomb").get(0).getId());
            assertEquals(2, TestUtils.countEntityOfType(res, "switch"));
            assertEquals(1, TestUtils.countEntityOfType(res, "boulder"));
            assertEquals(1, TestUtils.countEntityOfType(res, "bomb"));
            assertEquals(1, TestUtils.countEntityOfType(res, "wire"));
        });
    }

    @Test
    public void testXorLogicBombExplodesWhenCircuitActivates() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("logicBombTests/d_xorLogicBomb", "c_noSpawnsM3");
        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        // pick up bomb
        DungeonResponse dres = dmc.tick(Direction.UP);
        assertDoesNotThrow(() -> dmc.tick(TestUtils.getInventory(dres, "bomb").get(0).getId()));

        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.UP);
        // acitvate switch
        DungeonResponse res = dmc.tick(Direction.RIGHT);
        assertEquals(0, TestUtils.countEntityOfType(res, "switch"));
        assertEquals(0, TestUtils.countEntityOfType(res, "boulder"));
        assertEquals(0, TestUtils.countEntityOfType(res, "bomb"));
        assertEquals(0, TestUtils.countEntityOfType(res, "wire"));
    }

    @Test
    public void testXorLogicBombExplodesOnUse() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("logicBombTests/d_xorLogicBomb", "c_noSpawnsM3");
        // acitvate switch
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        // pick up bomb
        DungeonResponse dres = dmc.tick(Direction.UP);
        assertDoesNotThrow(() -> {
            DungeonResponse res = dmc.tick(TestUtils.getInventory(dres, "bomb").get(0).getId());
            assertEquals(0, TestUtils.countEntityOfType(res, "switch"));
            assertEquals(0, TestUtils.countEntityOfType(res, "boulder"));
            assertEquals(0, TestUtils.countEntityOfType(res, "bomb"));
            assertEquals(0, TestUtils.countEntityOfType(res, "wire"));
        });
    }

    @Test
    public void testOrLogicBombExplodesWhenCircuitActivates() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("logicBombTests/d_orLogicBomb", "c_noSpawnsM3");
        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        // pick up bomb
        DungeonResponse dres = dmc.tick(Direction.UP);
        assertDoesNotThrow(() -> dmc.tick(TestUtils.getInventory(dres, "bomb").get(0).getId()));

        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.UP);
        // acitvate switch
        DungeonResponse res = dmc.tick(Direction.RIGHT);
        assertEquals(0, TestUtils.countEntityOfType(res, "switch"));
        assertEquals(0, TestUtils.countEntityOfType(res, "boulder"));
        assertEquals(0, TestUtils.countEntityOfType(res, "bomb"));
        assertEquals(0, TestUtils.countEntityOfType(res, "wire"));
    }

    @Test
    public void testOrLogicBombExplodesOnUse() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("logicBombTests/d_orLogicBomb", "c_noSpawnsM3");
        // acitvate switch
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        // pick up bomb
        DungeonResponse dres = dmc.tick(Direction.UP);
        assertDoesNotThrow(() -> {
            DungeonResponse res = dmc.tick(TestUtils.getInventory(dres, "bomb").get(0).getId());
            assertEquals(0, TestUtils.countEntityOfType(res, "switch"));
            assertEquals(0, TestUtils.countEntityOfType(res, "boulder"));
            assertEquals(0, TestUtils.countEntityOfType(res, "bomb"));
            assertEquals(0, TestUtils.countEntityOfType(res, "wire"));
        });
    }

    @Test
    public void testAndLogicBombExplodesOnUse() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("logicBombTests/d_andLogicBomb", "c_noSpawnsM3");
        // activate switch
        dmc.tick(Direction.UP);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.UP);
        dmc.tick(Direction.UP);
        DungeonResponse dres = dmc.tick(Direction.LEFT);
        // pick up bomb
        assertDoesNotThrow(() -> {
            DungeonResponse res = dmc.tick(TestUtils.getInventory(dres, "bomb").get(0).getId());
            assertEquals(0, TestUtils.countEntityOfType(res, "switch"));
            assertEquals(0, TestUtils.countEntityOfType(res, "boulder"));
            assertEquals(0, TestUtils.countEntityOfType(res, "bomb"));
            assertEquals(0, TestUtils.countEntityOfType(res, "wire"));
        });
    }

    @Test
    public void testAndLogicBombExplodesWhenCircuitActivates() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("logicBombTests/d_andLogicBomb", "c_noSpawnsM3");
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.UP);
        dmc.tick(Direction.UP);
        dmc.tick(Direction.UP);
        DungeonResponse dres = dmc.tick(Direction.LEFT);
        // pick up bomb
        assertDoesNotThrow(() -> dmc.tick(TestUtils.getInventory(dres, "bomb").get(0).getId()));

        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.LEFT);
        // acitvate switch
        DungeonResponse res = dmc.tick(Direction.UP);
        assertEquals(0, TestUtils.countEntityOfType(res, "switch"));
        assertEquals(0, TestUtils.countEntityOfType(res, "boulder"));
        assertEquals(0, TestUtils.countEntityOfType(res, "bomb"));
        assertEquals(0, TestUtils.countEntityOfType(res, "wire"));
    }

    @Test
    public void testCoAndlogicBombExplodesOnUse() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("logicBombTests/d_coAndLogicBomb", "c_noSpawnsM3");
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.UP);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        DungeonResponse dres = dmc.tick(Direction.RIGHT);
        // pick up bomb
        assertDoesNotThrow(() -> {
            DungeonResponse res = dmc.tick(TestUtils.getInventory(dres, "bomb").get(0).getId());
            assertEquals(0, TestUtils.countEntityOfType(res, "switch"));
            assertEquals(0, TestUtils.countEntityOfType(res, "boulder"));
            assertEquals(0, TestUtils.countEntityOfType(res, "bomb"));
            assertEquals(0, TestUtils.countEntityOfType(res, "wire"));
        });
    }

    @Test
    public void testCoAndlogicBombExplodesWhenCircuitActivates() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("logicBombTests/d_coAndLogicBomb", "c_noSpawnsM3");
        dmc.tick(Direction.UP);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        DungeonResponse dres = dmc.tick(Direction.RIGHT);
        // pick up bomb
        assertDoesNotThrow(() -> dmc.tick(TestUtils.getInventory(dres, "bomb").get(0).getId()));

        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.DOWN);

        DungeonResponse res = dmc.tick(Direction.RIGHT);
        assertEquals(0, TestUtils.countEntityOfType(res, "switch"));
        assertEquals(0, TestUtils.countEntityOfType(res, "boulder"));
        assertEquals(0, TestUtils.countEntityOfType(res, "bomb"));
        assertEquals(0, TestUtils.countEntityOfType(res, "wire"));
    }

}
