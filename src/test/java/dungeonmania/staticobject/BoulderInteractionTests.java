package dungeonmania.staticobject;

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

public class BoulderInteractionTests {
  @Test
  public void blouderVisitsFloorSwitchTest() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse currentDungeon = controller.newGame("d_boulder_test", "simple");

        //player at 2, 2
        assertEquals(":boulders", currentDungeon.getGoals());

        currentDungeon = controller.tick(Direction.DOWN);
        // boulder at 2, 3 and switch at 2, 4
        // boulder should be pushed into switch. 

        // boulders goal has been completed
        assertEquals("", currentDungeon.getGoals());

        //this should do nothing as there is a boulder blocking
        currentDungeon = controller.tick(Direction.DOWN);
        assertEquals("", currentDungeon.getGoals());

        currentDungeon = controller.tick(Direction.RIGHT);
        currentDungeon = controller.tick(Direction.DOWN);
        currentDungeon = controller.tick(Direction.LEFT);

        // but now the bolder should be knocked off
        assertEquals(":boulders", currentDungeon.getGoals());
        
  }

  @Test
  public void blouderVisitsWallAndDoorTest() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse currentDungeon = controller.newGame("d_boulder_advanced_test", "simple");

        //player at 2, 2
        // boulder at 2, 3, switch at 2, 3 and wall at 2, 5
        // boulder should be pushed into switch. 
        // boulders goal has been completed
        //Wall blocking path
        currentDungeon = controller.tick(Direction.DOWN);
        currentDungeon = controller.tick(Direction.DOWN);
        assertEquals("", currentDungeon.getGoals());

        //pick up key
        currentDungeon = controller.tick(Direction.LEFT);
        currentDungeon = controller.tick(Direction.DOWN);
        //on left of boulder.
        
        currentDungeon = controller.tick(Direction.RIGHT);
        //boulder blocked by closed door
        assertEquals("", currentDungeon.getGoals());

        // now boulder should go through open door
        currentDungeon = controller.tick(Direction.UP);
        currentDungeon = controller.tick(Direction.RIGHT);
        currentDungeon = controller.tick(Direction.RIGHT);
        currentDungeon = controller.tick(Direction.DOWN);
        currentDungeon = controller.tick(Direction.UP);
        currentDungeon = controller.tick(Direction.LEFT);
        currentDungeon = controller.tick(Direction.LEFT);
        currentDungeon = controller.tick(Direction.DOWN);

        //back where we started, this push should move boulder
        currentDungeon = controller.tick(Direction.RIGHT);
        assertEquals(":boulders", currentDungeon.getGoals());
  }

  @Test
  public void blouderVisitsAllPassableTest() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse currentDungeon = controller.newGame("d_boulder_everything_test", "simple");

        //player at 2, 2, go down to 2,9 to push the boulder to the switch
        assertEquals(":boulders", currentDungeon.getGoals());

        currentDungeon = controller.tick(Direction.DOWN);
        currentDungeon = controller.tick(Direction.DOWN);
        currentDungeon = controller.tick(Direction.DOWN);
        currentDungeon = controller.tick(Direction.DOWN);
        currentDungeon = controller.tick(Direction.DOWN);
        currentDungeon = controller.tick(Direction.DOWN);
        currentDungeon = controller.tick(Direction.DOWN);

        assertEquals("", currentDungeon.getGoals());


        
  }
}