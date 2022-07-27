package dungeonmania.logicswitch;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;

import static dungeonmania.TestUtils.countEntityOfType;

public class WireBasicTest {
    @Test
    public void test_activitate_bomb_throught_wire(){
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("logicswitchtests/d_logicSwitch_activeBomb", "c_noSpawns");
        dmc.tick(Direction.RIGHT);
        // pick up bomb

        dmc.tick(Direction.UP);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.DOWN);
        dmc.tick(Direction.RIGHT);
        DungeonResponse resp = dmc.tick(Direction.RIGHT);
        // player position: 7,1
        // bomb position: 7,1

        String bomb_id = resp.getInventory().stream()
                                            .filter(e->e.getType().equals("bomb"))
                                            .findFirst().get().getId();
        assertDoesNotThrow(() -> dmc.tick(bomb_id));

        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.UP);
        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.LEFT);
        dmc.tick(Direction.DOWN);
        resp = dmc.tick(Direction.RIGHT);
        // activitate bomb and explode
        int count = countEntityOfType(resp, "bomb");
        // assertEquals(count, 0);

        count = countEntityOfType(resp, "wire");
        // assertEquals(count, 1);
    }

    @Test
    public void test_activitate_switch_door_throught_wire(){}

    @Test
    public void test_boulder_on_switch_and_not_affected_by_wire(){}
}
