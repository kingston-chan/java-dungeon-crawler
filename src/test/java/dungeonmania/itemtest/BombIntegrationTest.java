package dungeonmania.itemtest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.eclipse.jetty.client.api.ContentResponse;
import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.item.Bomb;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.util.Direction;

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
        // player current position: 3,2 and boulder pushed onto switch
        // and the bomb should be exploded now

    }

    @Test
    public void testSetUpBombNearActiveSwitchAndExplosion(){
    }
}
