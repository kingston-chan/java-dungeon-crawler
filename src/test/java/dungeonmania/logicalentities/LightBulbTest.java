package dungeonmania.logicalentities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static dungeonmania.TestUtils.countEntityOfType;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;

public class LightBulbTest {

    @Test
    public void OrLightBulbBoulder() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse currentDungeon = controller.newGame("d_lightswitch", "simple");

        // there is one off lightbulb
        assertEquals(countEntityOfType(currentDungeon, "light_bulb_off"), 1);

        // push a bolder onto a switch
        currentDungeon = controller.tick(Direction.RIGHT);

        // now lightswitch on
        assertEquals(countEntityOfType(currentDungeon, "light_bulb_off"), 0);
        assertEquals(countEntityOfType(currentDungeon, "light_bulb_on"), 1);
    }

    @Test
    public void OrLightBulbWire() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse currentDungeon = controller.newGame("d_wireslightbulb", "simple");

        // there is one off lightbulb
        assertEquals(countEntityOfType(currentDungeon, "light_bulb_off"), 1);

        // push a bolder onto a switch
        currentDungeon = controller.tick(Direction.RIGHT);

        // now lightswitch on
        assertEquals(countEntityOfType(currentDungeon, "light_bulb_off"), 0);
        assertEquals(countEntityOfType(currentDungeon, "light_bulb_on"), 1);
    }

    @Test
    public void OrLightBulbWireMulti() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse currentDungeon = controller.newGame("d_ormultilogic", "simple");

        // there is one off lightbulb
        assertEquals(countEntityOfType(currentDungeon, "light_bulb_off"), 1);

        // push a bolder onto a switch
        currentDungeon = controller.tick(Direction.RIGHT);

        // now lightswitch on
        assertEquals(countEntityOfType(currentDungeon, "light_bulb_off"), 0);
        assertEquals(countEntityOfType(currentDungeon, "light_bulb_on"), 1);
    }

    @Test
    public void AndLightBulbWireMulti() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse currentDungeon = controller.newGame("d_andmultilogic", "simple");

        // there is one off lightbulb
        assertEquals(countEntityOfType(currentDungeon, "light_bulb_off"), 1);

        // push a bolder onto a switch
        currentDungeon = controller.tick(Direction.RIGHT);

        // now lightswitch on
        assertEquals(countEntityOfType(currentDungeon, "light_bulb_on"), 1);
    }

    @Test
    public void xorLightBulb() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse currentDungeon = controller.newGame("d_xorlightbulb", "simple");

        // there is one off lightbulb
        assertEquals(countEntityOfType(currentDungeon, "light_bulb_off"), 1);

        // push a bolder onto a switch
        currentDungeon = controller.tick(Direction.RIGHT);

        // now lightswitch on

        assertEquals(countEntityOfType(currentDungeon, "light_bulb_on"), 1);
        currentDungeon = controller.tick(Direction.DOWN);
        currentDungeon = controller.tick(Direction.RIGHT);

        // two entities nearby so off
        assertEquals(countEntityOfType(currentDungeon, "light_bulb_off"), 1);
    }

    @Test
    public void coandLightBulbFail() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse currentDungeon = controller.newGame("d_co_andlightbulb", "simple");

        // there is one off lightbulb
        assertEquals(countEntityOfType(currentDungeon, "light_bulb_off"), 1);

        // push a bolder onto a switch
        currentDungeon = controller.tick(Direction.RIGHT);

        // not on

        assertEquals(countEntityOfType(currentDungeon, "light_bulb_on"), 0);
        currentDungeon = controller.tick(Direction.DOWN);
        currentDungeon = controller.tick(Direction.RIGHT);

        // two entities now nearby, but not on same tick
        assertEquals(countEntityOfType(currentDungeon, "light_bulb_on"), 0);
    }

    @Test
    public void coandLightBulbWorks() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse currentDungeon = controller.newGame("d_co_andlightbulbworks", "simple");

        // there is one off lightbulb
        assertEquals(countEntityOfType(currentDungeon, "light_bulb_off"), 1);

        // push a bolder onto a switch
        currentDungeon = controller.tick(Direction.RIGHT);

        // Wires now active on same tick

        assertEquals(countEntityOfType(currentDungeon, "light_bulb_on"), 1);
    }

    @Test
    public void onlyCardinal() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse currentDungeon = controller.newGame("d_light", "simple");

        // there is one off lightbulb
        assertEquals(countEntityOfType(currentDungeon, "light_bulb_off"), 1);

        // push a bolder onto a switch
        currentDungeon = controller.tick(Direction.RIGHT);

        assertEquals(countEntityOfType(currentDungeon, "light_bulb_off"), 1);
    }
}