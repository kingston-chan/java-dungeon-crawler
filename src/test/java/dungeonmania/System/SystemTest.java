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
        
        assertEquals(":exit AND :enemies", dres.getGoals()); 

        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dres = dmc.tick(Direction.RIGHT);

        assertDoesNotThrow(() -> dmc.build("bow"));
        
        dres = dmc.tick(Direction.RIGHT);
        // should have fought now
        // player should be alive
        assertEquals(1, TestUtils.countEntityOfType(dres, "player"));

        // merc dies
        assertEquals(0, TestUtils.countEntityOfType(dres, "mercenary"));
        assertEquals(":exit", dres.getGoals()); 

        //move to exit
        dres = dmc.tick(Direction.RIGHT);
        assertEquals("", dres.getGoals()); 

    }
}
