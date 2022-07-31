package dungeonmania.logicalentities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static dungeonmania.TestUtils.getPlayer;
import static dungeonmania.TestUtils.countEntityOfType;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class WiresTest {

    @Test
    public void wiresCircuit() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse currentDungeon = controller.newGame("d_wirestest", "simple");

        // there is one off lightbulb
        assertEquals(countEntityOfType(currentDungeon, "light_bulb_off"), 1);

        // push a bolder onto a switch
        currentDungeon = controller.tick(Direction.RIGHT);

        // wires form a circut
        // now lightswitch on
        assertEquals(countEntityOfType(currentDungeon, "light_bulb_on"), 1);
    }

    @Test
    public void wiresDoor() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse currentDungeon = controller.newGame("d_wirestestdoor", "simple");

        // push a bolder onto a switch
        currentDungeon = controller.tick(Direction.RIGHT);
        currentDungeon = controller.tick(Direction.UP);

        EntityResponse player = getPlayer(currentDungeon).get();

        assertEquals(new Position(3, 1), player.getPosition());
        // switch door should be open so we can move to 3,1
    }

}
