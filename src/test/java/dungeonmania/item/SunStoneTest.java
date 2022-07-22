package dungeonmania.item;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static dungeonmania.TestUtils.getPlayer;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.TestUtils;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class SunStoneTest {

        @Test
        public void testPickupSunStone() {
            DungeonManiaController controller = new DungeonManiaController();
            controller.newGame("d_sunstonedoor", "c_treasureGoal");
    
            // player current position: 2,2
            DungeonResponse current_state = controller.tick(Direction.RIGHT);
            //now at sunstone
            assertTrue(current_state.getInventory().stream().anyMatch(item -> item.getType().equals("sun_stone")));
        }

        @Test
        public void testSunStoneOpenDoor() {
            DungeonManiaController controller = new DungeonManiaController();
            controller.newGame("d_sunstonedoor", "c_treasureGoal");
    
            // player current position: 2,2, sunstone at 3,2, door at 4,2
            DungeonResponse current_state = controller.tick(Direction.RIGHT);   
            //picked up sunstone
            //moving right again is a door
            
            current_state = controller.tick(Direction.RIGHT);
            //suntone should still be in inventory
            assertTrue(current_state.getInventory().stream().anyMatch(item -> item.getType().equals("sun_stone")));
            
            EntityResponse player = getPlayer(current_state).get();

            assertEquals(new Position(4, 2), player.getPosition());
        }

        @Test
        public void testSunStoneCompletesTreasureGoal() {
            DungeonManiaController controller = new DungeonManiaController();
            controller.newGame("d_sunstonedoor", "c_treasureGoal");
    
            // player current position: 2,2, sunstone at 3,2, door at 4,2, sunstone at 5,2
            DungeonResponse current_state = controller.tick(Direction.RIGHT);
            
            //goal not yet completed
            assertEquals(":treasure", current_state.getGoals());   
            current_state = controller.tick(Direction.RIGHT);
            current_state = controller.tick(Direction.RIGHT);
            
           //should have both sunstones, thus completing the goal
           assertEquals("", current_state.getGoals()); 
        }

        @Test
        public void testMixedTreasureCompletesTreasureGoal() {
            DungeonManiaController controller = new DungeonManiaController();
            controller.newGame("d_sunstoneandtreasure", "c_treasureGoal");
    
            // player current position: 2,2, sunstone at 3,2, treasure at 4,2
            DungeonResponse current_state = controller.tick(Direction.RIGHT);
            
            //goal not yet completed
            assertEquals(":treasure", current_state.getGoals());   
            current_state = controller.tick(Direction.RIGHT);
            
           //should have both treasures, thus completing the goal
           assertEquals("", current_state.getGoals()); 
        }

        @Test
        public void testCantBribeSunStone() {
            DungeonManiaController controller = new DungeonManiaController();
            controller.newGame("d_sunstoneandtreasure", "c_treasureGoal");
    
            // Move to pick up SunStone
            DungeonResponse current_state = controller.tick(Direction.RIGHT);

            //bribe radius is 10 so merc is in range, but sunstone can't be used.
            EntityResponse merc = TestUtils.getEntities(current_state, "mercenary").get(0);
            assertThrows(InvalidActionException.class, () -> controller.interact(merc.getId()));
            
            //now pick up treasure
            current_state = controller.tick(Direction.RIGHT);
            assertDoesNotThrow(() -> controller.interact(merc.getId()));

            //sunstone should still be in inventory.
            assertTrue(current_state.getInventory().stream().anyMatch(item -> item.getType().equals("sun_stone")));

        }
}
