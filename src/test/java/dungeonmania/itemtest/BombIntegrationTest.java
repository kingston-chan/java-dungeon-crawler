package dungeonmania.itemtest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.parser.Entity;

import org.eclipse.jetty.client.api.ContentResponse;
import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.item.Bomb;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.util.Direction;

import static dungeonmania.TestUtils.getEntities;
import static dungeonmania.TestUtils.countEntityOfType;


public class BombIntegrationTest {
    @Test
    public void testPickupBomb(){
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
    public void testSetUpBombNearInactiveSwitch() throws IllegalArgumentException, InvalidActionException{
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
    public void testSetUpBombNearActiveSwitchAndExplosion(){
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("d_bombTest_placeBombRadius2", "c_bombTest_placeBombRadius2");

        // player current position: 2,2
        DungeonResponse current_state = controller.tick(Direction.RIGHT);
        // player current position: 3,2
        // activate switch

        Optional<ItemResponse> object_item = current_state.getInventory()
                                    .stream()
                                    .filter(item -> item.getType().equals("bomb"))
                                    .findFirst();
        // throw nothing if get bomb
        assertDoesNotThrow(() -> object_item.get());
        ItemResponse bomb = object_item.get();

        controller.tick(Direction.DOWN);
        // player currecnt position: 3,3
        // player pick up bomb

        controller.tick(Direction.RIGHT);
        // player position: 4,3

        assertDoesNotThrow(() -> controller.tick(bomb.getId()));
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
}
