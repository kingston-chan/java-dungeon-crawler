package dungeonmania.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static dungeonmania.TestUtils.getPlayer;
import static dungeonmania.TestUtils.countEntityOfType;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.TestUtils;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class KeyBlackBoxTests {
    @Test
    public void testPickUpKey() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse currentDungeon = controller.newGame("d_2door_test", "c_bombTest_placeBombRadius2");

        assertTrue(TestUtils.getInventory(currentDungeon, "key").isEmpty());
        // player position: 2,2
        currentDungeon = controller.tick(Direction.DOWN);
        // player position: 2,3
        assertFalse(TestUtils.getInventory(currentDungeon, "key").isEmpty());

        // check only exist 1 key in inventory
        assertTrue(countEntityOfType(currentDungeon, "key") == 1);
    }

    @Test
    public void testKeyOpenNotMatchedDoor() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse currentDungeon = controller.newGame("d_2door_test", "c_bombTest_placeBombRadius2");

        // player position: 2,2
        controller.tick(Direction.DOWN);
        // player position: 2,3 get key1
        // Door1 position: 3,4
        // Door2 position: 4,3

        controller.tick(Direction.RIGHT);
        // player position: 3,3

        controller.tick(Direction.RIGHT);
        currentDungeon = controller.tick(Direction.RIGHT);
        // player position: 3,3 becasue key1 not matched door2

        EntityResponse player = getPlayer(currentDungeon).get();

        assertEquals(new Position(3, 3), player.getPosition());
    }

    @Test
    public void testKeyOpenMatchedDoor() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse currentDungeon = controller.newGame("d_2door_test", "c_bombTest_placeBombRadius2");

        // player position: 2,2
        // player position: 2,3 get key1
        controller.tick(Direction.DOWN);

        // Door1 position: 3,4
        // Door2 position: 4,3

        controller.tick(Direction.RIGHT);
        currentDungeon = controller.tick(Direction.DOWN);
        // player position: 3,4 open door1 with key1

        EntityResponse player = getPlayer(currentDungeon).get();

        assertEquals(new Position(3, 4), player.getPosition());
    }
}
