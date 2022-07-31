package dungeonmania.logicalentities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static dungeonmania.TestUtils.countEntityOfType;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;

public class SwitchTest {

    @Test
    public void logicSwitchDoesntCompleteGoal() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse currentDungeon = controller.newGame("d_orswitch", "simple");

        // so techincally there is already an activated entity, but since we don't have
        // to worry about them spawning in while on this is fine
        currentDungeon = controller.tick(Direction.RIGHT);

        // goals can only be completed by switches with boulders
        assertEquals(":boulders", currentDungeon.getGoals());
    }

    @Test
    public void orSwitch() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse currentDungeon = controller.newGame("d_orswitch", "simple");

        currentDungeon = controller.tick(Direction.RIGHT);

        assertEquals(countEntityOfType(currentDungeon, "light_bulb_on"), 1);

        currentDungeon = controller.tick(Direction.DOWN);
        currentDungeon = controller.tick(Direction.RIGHT);
        currentDungeon = controller.tick(Direction.UP);
        currentDungeon = controller.tick(Direction.UP);

        // bulb should still be on
        assertEquals(countEntityOfType(currentDungeon, "light_bulb_on"), 1);

        currentDungeon = controller.tick(Direction.RIGHT);
        currentDungeon = controller.tick(Direction.RIGHT);
        currentDungeon = controller.tick(Direction.DOWN);

        // circuit should be deactivated after boulder is pushed away from switch
        assertEquals(countEntityOfType(currentDungeon, "light_bulb_on"), 0);
    }

    @Test
    public void andSwitch() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse currentDungeon = controller.newGame("d_andswitch", "simple");

        assertEquals(countEntityOfType(currentDungeon, "light_bulb_on"), 0);

        // now two entities
        currentDungeon = controller.tick(Direction.RIGHT);

        assertEquals(countEntityOfType(currentDungeon, "light_bulb_on"), 1);
        currentDungeon = controller.tick(Direction.DOWN);
        currentDungeon = controller.tick(Direction.RIGHT);
        currentDungeon = controller.tick(Direction.UP);
        currentDungeon = controller.tick(Direction.UP);

        // circuit should be deactivated after boulder is pushed away from switch
        assertEquals(countEntityOfType(currentDungeon, "light_bulb_on"), 0);
    }

    @Test
    public void xorSwitch() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse currentDungeon = controller.newGame("d_xorswitch", "simple");

        currentDungeon = controller.tick(Direction.RIGHT);
        // one entity nearby
        assertEquals(countEntityOfType(currentDungeon, "light_bulb_on"), 1);

        currentDungeon = controller.tick(Direction.DOWN);
        currentDungeon = controller.tick(Direction.RIGHT);
        // two entities nearby, which turns it off

        assertEquals(countEntityOfType(currentDungeon, "light_bulb_on"), 0);
    }

    @Test
    public void co_andSwitchfails() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse currentDungeon = controller.newGame("d_co_andswitchfails", "simple");

        currentDungeon = controller.tick(Direction.RIGHT);
        // one entity nearby
        assertEquals(countEntityOfType(currentDungeon, "light_bulb_on"), 0);

        currentDungeon = controller.tick(Direction.DOWN);
        currentDungeon = controller.tick(Direction.RIGHT);

        // two entities nearby but not on same tick

        assertEquals(countEntityOfType(currentDungeon, "light_bulb_on"), 0);
    }

    @Test
    public void co_andSwitchsucceeds() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse currentDungeon = controller.newGame("d_co_andswitch", "simple");

        currentDungeon = controller.tick(Direction.RIGHT);

        assertEquals(countEntityOfType(currentDungeon, "light_bulb_on"), 1);

    }

}
