package dungeonmania.dungeon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.TestUtils;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Position;

public class MazeTests {
    @Test
    public void testGenerateSmallMaze() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse dres = dmc.generateDungeon(-1, -1, -7, -7, "c_noSpawns");
        assertEquals(new Position(-1, -1), TestUtils.getPlayer(dres).get().getPosition());
        assertEquals(new Position(-7, -7), TestUtils.getEntities(dres, "exit").get(0).getPosition());
        assertTrue(MazeTestHelper.hasPath(DungeonManiaController.getDungeon()));
    }

    @Test
    public void testGenerateLargeMaze() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse dres = dmc.generateDungeon(0, 0, 50, 50, "c_noSpawns");
        assertEquals(new Position(0, 0), TestUtils.getPlayer(dres).get().getPosition());
        assertEquals(new Position(50, 50), TestUtils.getEntities(dres, "exit").get(0).getPosition());
        assertTrue(MazeTestHelper.hasPath(DungeonManiaController.getDungeon()));
    }

    @Test
    public void testInvalidConfig() {
        DungeonManiaController dmc = new DungeonManiaController();
        assertThrows(IllegalArgumentException.class, () -> dmc.generateDungeon(0, 0, 50, 50, "invalid_config"));
    }
}
