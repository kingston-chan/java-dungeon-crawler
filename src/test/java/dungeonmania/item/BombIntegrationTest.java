package dungeonmania.item;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.TestUtils;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.util.Direction;

import static dungeonmania.TestUtils.countEntityOfType;

public class BombIntegrationTest {
    @Test
    public void testPickupBomb() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("d_bombTest_placeBombRadius2", "c_bombTest_placeBombRadius2");

        // player current position: 2,2
        controller.tick(Direction.DOWN);
        DungeonResponse current_state = controller.tick(Direction.RIGHT);
        // player current position: 3,3
        // pick up bomb
        assertTrue(current_state.getInventory().stream().anyMatch(item -> item.getType().equals("bomb")));
    }

    @Test
    public void testSetUpBombNearInactiveSwitch() throws IllegalArgumentException, InvalidActionException {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("d_bombTest_placeBombRadius2", "c_bombTest_placeBombRadius2");

        // player current position: 2,2
        controller.tick(Direction.DOWN);
        DungeonResponse current_state = controller.tick(Direction.RIGHT);
        // player current position: 3,3
        // pick up bomb
        assertTrue(current_state.getInventory().stream().anyMatch(item -> item.getType().equals("bomb")));

        Optional<ItemResponse> object_item = current_state.getInventory()
                .stream()
                .filter(item -> item.getType().equals("bomb"))
                .findFirst();
        // throw nothing if get bomb
        assertDoesNotThrow(() -> object_item.get());
        ItemResponse bomb = object_item.get();

        controller.tick(Direction.RIGHT);
        // player currecnt position: 4,3

        assertDoesNotThrow(() -> controller.tick(bomb.getId()));
        // bomb position: 4,3

        // switch position: 4,2
        // boulder position: 3,2

        // Going to active switch
        controller.tick(Direction.LEFT);
        controller.tick(Direction.LEFT);
        controller.tick(Direction.UP);
        // player current position: 2,2

        current_state = controller.tick(Direction.RIGHT);
        // player current position: 3,2 and boulder pushed onto dswitch
        // and the bomb should be exploded now

        // all things in range should be disappeared except from player
        int count = countEntityOfType(current_state, "bomb");
        assertEquals(count, 0);

        count = countEntityOfType(current_state, "switch");
        assertEquals(count, 0);

        count = countEntityOfType(current_state, "boulder");
        assertEquals(count, 0);

        count = countEntityOfType(current_state, "wall");
        assertEquals(count, 0);

        count = countEntityOfType(current_state, "treasure");
        assertEquals(count, 0);

        count = countEntityOfType(current_state, "player");
        assertEquals(count, 1);

    }

    @Test
    public void testSetUpBombNearActiveSwitchAndExplosion() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("d_bombTest_placeBombRadius2", "c_bombTest_placeBombRadius2");

        // player current position: 2,2
        DungeonResponse current_state = controller.tick(Direction.RIGHT);
        // player current position: 3,2
        // activate switch
        current_state = controller.tick(Direction.DOWN);
        // player currecnt position: 3,3
        // player pick up bomb

        Optional<ItemResponse> object_item = current_state.getInventory()
                .stream()
                .filter(item -> item.getType().equals("bomb"))
                .findFirst();
        // throw nothing if get bomb
        assertDoesNotThrow(() -> object_item.get());
        ItemResponse bomb = object_item.get();

        controller.tick(Direction.RIGHT);
        // player position: 4,3

        current_state = assertDoesNotThrow(() -> controller.tick(bomb.getId()));
        // bomb position: 4,3
        // place bomb and it should be exploded now

        // all things in range should be disappeared except from player
        int count = countEntityOfType(current_state, "bomb");
        assertEquals(count, 0);

        count = countEntityOfType(current_state, "switch");
        assertEquals(count, 0);

        count = countEntityOfType(current_state, "boulder");
        assertEquals(count, 0);

        count = countEntityOfType(current_state, "wall");
        assertEquals(count, 0);

        count = countEntityOfType(current_state, "treasure");
        assertEquals(count, 0);

        count = countEntityOfType(current_state, "player");
        assertEquals(count, 1);
    }

    @Test
    public void testExplodeTwoBombs() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse dres = controller.newGame("d_2bombs", "c_bombTest_placeBombRadius1");

        // 3 walls total, 2 should be destroyed at when switch gets activate
        // 1 is not in range of both bombs, 1 is in range of both bombs, 1 is only in
        // range of one bomb
        assertEquals(3, TestUtils.countEntityOfType(dres, "wall"));
        // 2 treasure, 1 is not in range of both bombs, 1 is only in range of one bomb
        assertEquals(2, TestUtils.countEntityOfType(dres, "treasure"));

        controller.tick(Direction.DOWN);
        // pick up bomb 1
        controller.tick(Direction.RIGHT);

        assertDoesNotThrow(() -> {
            // sets bomb below switch
            DungeonResponse res = controller.tick(Direction.RIGHT);
            res = controller.tick(TestUtils.getInventory(res, "bomb").get(0).getId());
            assertTrue(res.getInventory().isEmpty());
        });

        controller.tick(Direction.LEFT);
        controller.tick(Direction.LEFT);

        controller.tick(Direction.UP);
        controller.tick(Direction.UP);

        // pick up bomb2
        controller.tick(Direction.RIGHT);

        assertDoesNotThrow(() -> {
            // sets bomb above switch
            DungeonResponse res = controller.tick(Direction.RIGHT);
            res = controller.tick(TestUtils.getInventory(res, "bomb").get(0).getId());
            assertTrue(res.getInventory().isEmpty());
        });

        controller.tick(Direction.LEFT);
        controller.tick(Direction.LEFT);
        // adjacent to boulder
        controller.tick(Direction.DOWN);
        dres = controller.tick(Direction.RIGHT);

        // 2 bombs exploded
        assertEquals(1, TestUtils.countEntityOfType(dres, "wall"));
        assertEquals(1, TestUtils.countEntityOfType(dres, "treasure"));
        assertEquals(0, TestUtils.countEntityOfType(dres, "boulder"));
        assertEquals(0, TestUtils.countEntityOfType(dres, "switch"));
    }
}
