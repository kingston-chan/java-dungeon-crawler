package dungeonmania.System;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.TestUtils;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.util.Direction;

public class SystemTest {
    @Test
    public void testMovementBuildingBattleSystem() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse dres = dmc.newGame("d_bowbattle",
                "simple");

        //testing moving and building and battle
        assertThrows(InvalidActionException.class, () -> dmc.build("bow"));
        
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dres = dmc.tick(Direction.RIGHT);

        assertDoesNotThrow(() -> dmc.build("bow"));

        dmc.tick(Direction.RIGHT);
        // should be fighting now
    // player should be alive
    assertEquals(1, TestUtils.countEntityOfType(dres, "player"));

    // zombie dies
    assertEquals(0, TestUtils.countEntityOfType(dres, "mercenary"));
    }
}
