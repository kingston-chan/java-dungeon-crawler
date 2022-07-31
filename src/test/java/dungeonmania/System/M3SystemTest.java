package dungeonmania.System;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;


import dungeonmania.DungeonManiaController;
import dungeonmania.TestUtils;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;

public class M3SystemTest {
    
    @Test
    public void testSystemM3() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse dr = dmc.newGame("d_m3SystemTest", "c_m3SystemTestConfig");
        String mercId = TestUtils.getEntities(dr, "mercenary").get(0).getId();
        // try to build sceptre but fail
        assertThrows(InvalidActionException.class, () -> dmc.build("sceptre"));

        // check goals
        assertEquals(":exit AND :enemies", dr.getGoals()); 

        // pick up items
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);

        // build sceptre
        assertDoesNotThrow(() -> dmc.build("sceptre"));

        // mind control merc
        assertDoesNotThrow(() -> dmc.interact(mercId));

        // fight assassin
        dmc.tick(Direction.RIGHT);
        dr = dmc.tick(Direction.RIGHT);

        // player should be alive, assassin dead, merc still ally
        assertEquals(1, TestUtils.countEntityOfType(dr, "player"));
        assertEquals(1, TestUtils.countEntityOfType(dr, "mercenary"));
        assertEquals(0, TestUtils.countEntityOfType(dr, "assassin"));

        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.DOWN);

        // mind control runs out, kill merc
        dr = dmc.tick(Direction.UP);
        assertEquals(1, TestUtils.countEntityOfType(dr, "player"));
        assertEquals(0, TestUtils.countEntityOfType(dr, "mercenary"));
        assertEquals(":exit", dr.getGoals()); 

        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.DOWN);
        
        // push boulder into switch, activating the circuit and opening the door
        dmc.tick(Direction.DOWN);

        // go through the door, showing that the circuit works properly
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.RIGHT);
        dr = dmc.tick(Direction.RIGHT);
        
        assertEquals("", dr.getGoals()); 

    }

    



}
