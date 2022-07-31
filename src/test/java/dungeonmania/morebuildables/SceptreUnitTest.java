package dungeonmania.morebuildables;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.TestUtils;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;
import static dungeonmania.TestUtils.getEntities;

public class SceptreUnitTest {
    @Test
    public void testBuildSceptreWithWood_Key_SunStone() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("d_build_sceptre", "c_scptre_simple");
        controller.tick(Direction.RIGHT);
        controller.tick(Direction.RIGHT);
        DungeonResponse res = controller.tick(Direction.RIGHT);
        assertTrue(res.getBuildables().stream().anyMatch(item -> item.equals("sceptre")));

        assertDoesNotThrow(() -> controller.build("sceptre"));

        res = controller.tick(Direction.UP);
        assertTrue(res.getInventory().stream().anyMatch(item -> item.getType().equals("sceptre")));
    }

    @Test
    public void testBuildSceptreWithWood_Treasure_SunStone() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("d_build_sceptre2", "c_scptre_simple");
        controller.tick(Direction.RIGHT);
        controller.tick(Direction.RIGHT);
        DungeonResponse res = controller.tick(Direction.RIGHT);
        assertTrue(res.getBuildables().stream().anyMatch(item -> item.equals("sceptre")));

        assertDoesNotThrow(() -> controller.build("sceptre"));

        res = controller.tick(Direction.UP);
        assertTrue(res.getInventory().stream().anyMatch(item -> item.getType().equals("sceptre")));

    }

    @Test
    public void testBuildSceptreWithArrows_Key_SunStone() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("d_build_sceptre3", "c_scptre_simple");
        controller.tick(Direction.RIGHT);
        controller.tick(Direction.RIGHT);
        controller.tick(Direction.RIGHT);
        DungeonResponse res = controller.tick(Direction.RIGHT);
        assertTrue(res.getBuildables().stream().anyMatch(item -> item.equals("sceptre")));

        assertDoesNotThrow(() -> controller.build("sceptre"));

        res = controller.tick(Direction.UP);
        assertTrue(res.getInventory().stream().anyMatch(item -> item.getType().equals("sceptre")));

    }

    @Test
    public void testBuildSceptreWithArrows_Treasure_SunStone() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("d_build_sceptre4", "c_scptre_simple");
        controller.tick(Direction.RIGHT);
        controller.tick(Direction.RIGHT);
        controller.tick(Direction.RIGHT);
        DungeonResponse res = controller.tick(Direction.RIGHT);
        assertTrue(res.getBuildables().stream().anyMatch(item -> item.equals("sceptre")));

        assertDoesNotThrow(() -> controller.build("sceptre"));

        res = controller.tick(Direction.UP);
        assertTrue(res.getInventory().stream().anyMatch(item -> item.getType().equals("sceptre")));

    }

    @Test
    public void testBuild_with_2_sunstones_arrows() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("d_build_sceptre5", "c_scptre_simple");
        controller.tick(Direction.RIGHT);
        controller.tick(Direction.RIGHT);
        controller.tick(Direction.RIGHT);
        DungeonResponse res = controller.tick(Direction.RIGHT);
        assertTrue(res.getBuildables().stream().anyMatch(item -> item.equals("sceptre")));

        assertDoesNotThrow(() -> controller.build("sceptre"));

        res = controller.tick(Direction.UP);
        assertTrue(res.getInventory().stream().anyMatch(item -> item.getType().equals("sceptre")));
    }

    @Test
    public void testBuild_with_2_sunstones_wood() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("d_build_sceptre6", "c_scptre_simple");
        controller.tick(Direction.RIGHT);
        controller.tick(Direction.RIGHT);
        DungeonResponse res = controller.tick(Direction.RIGHT);
        assertTrue(res.getBuildables().stream().anyMatch(item -> item.equals("sceptre")));

        assertDoesNotThrow(() -> controller.build("sceptre"));

        res = controller.tick(Direction.UP);
        assertTrue(res.getInventory().stream().anyMatch(item -> item.getType().equals("sceptre")));
    }

    @Test
    public void testFailedToBuildSceptre_lack_wood() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("d_build_sceptre", "c_scptre_simple");
        controller.tick(Direction.RIGHT);
        DungeonResponse res = controller.tick(Direction.RIGHT);
        assertFalse(res.getBuildables().stream().anyMatch(item -> item.equals("sceptre")));

        assertThrows(InvalidActionException.class, () -> controller.build("sceptre"));

        res = controller.tick(Direction.UP);
        assertFalse(res.getInventory().stream().anyMatch(item -> item.getType().equals("sceptre")));

    }

    @Test
    public void testFailedToBuildSceptre_lack_sunstone() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("d_build_sceptre", "c_scptre_simple");
        controller.tick(Direction.RIGHT);
        controller.tick(Direction.UP);
        controller.tick(Direction.RIGHT);
        controller.tick(Direction.RIGHT);
        controller.tick(Direction.DOWN);
        DungeonResponse res = controller.tick(Direction.RIGHT);
        assertFalse(res.getBuildables().stream().anyMatch(item -> item.equals("sceptre")));

        assertThrows(InvalidActionException.class, () -> controller.build("sceptre"));

        res = controller.tick(Direction.UP);
        assertFalse(res.getInventory().stream().anyMatch(item -> item.getType().equals("sceptre")));
    }

    @Test
    public void testFailedToBuildSceptre_lack_treasure() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("d_build_sceptre2", "c_scptre_simple");
        controller.tick(Direction.UP);
        controller.tick(Direction.RIGHT);
        controller.tick(Direction.RIGHT);
        controller.tick(Direction.DOWN);
        controller.tick(Direction.RIGHT);

        DungeonResponse res = controller.tick(Direction.RIGHT);
        assertFalse(res.getBuildables().stream().anyMatch(item -> item.equals("sceptre")));
        assertThrows(InvalidActionException.class, () -> controller.build("sceptre"));
        res = controller.tick(Direction.UP);
        assertFalse(res.getInventory().stream().anyMatch(item -> item.getType().equals("sceptre")));
    }

    @Test
    public void testFailedToBuildSceptre_lack_1arrow() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("d_build_sceptre3", "c_scptre_simple");
        controller.tick(Direction.RIGHT);
        controller.tick(Direction.RIGHT);

        DungeonResponse res = controller.tick(Direction.RIGHT);
        assertFalse(res.getBuildables().stream().anyMatch(item -> item.equals("sceptre")));
        assertThrows(InvalidActionException.class, () -> controller.build("sceptre"));
        res = controller.tick(Direction.UP);
        assertFalse(res.getInventory().stream().anyMatch(item -> item.getType().equals("sceptre")));
    }

    @Test
    public void testFailedToBuildSceptre_lack_2arrows() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("d_build_sceptre3", "c_scptre_simple");
        controller.tick(Direction.RIGHT);

        DungeonResponse res = controller.tick(Direction.RIGHT);
        assertFalse(res.getBuildables().stream().anyMatch(item -> item.equals("sceptre")));
        assertThrows(InvalidActionException.class, () -> controller.build("sceptre"));
        res = controller.tick(Direction.UP);
        assertFalse(res.getInventory().stream().anyMatch(item -> item.getType().equals("sceptre")));
    }

    @Test
    public void testFailedToBuildSceptre_lack_key() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("d_build_sceptre3", "c_scptre_simple");
        controller.tick(Direction.UP);
        controller.tick(Direction.RIGHT);
        controller.tick(Direction.RIGHT);
        controller.tick(Direction.DOWN);
        controller.tick(Direction.RIGHT);
        controller.tick(Direction.RIGHT);

        DungeonResponse res = controller.tick(Direction.RIGHT);
        assertFalse(res.getBuildables().stream().anyMatch(item -> item.equals("sceptre")));
        assertThrows(InvalidActionException.class, () -> controller.build("sceptre"));
        res = controller.tick(Direction.UP);
        assertFalse(res.getInventory().stream().anyMatch(item -> item.getType().equals("sceptre")));
    }

    @Test
    public void testMindControlUsingSceptreSuccessInDuration() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse resp = controller.newGame("d_mindcontrol", "c_scptre_simple");
        String mercenary_id = getEntities(resp, "mercenary").get(0).getId();
        controller.tick(Direction.RIGHT);
        controller.tick(Direction.RIGHT);
        resp = controller.tick(Direction.RIGHT);
        assertDoesNotThrow(() -> controller.build("sceptre"));

        assertDoesNotThrow(() -> controller.interact(mercenary_id));
        resp = controller.tick(Direction.RIGHT);
        // merceanry still alive beacsue mind control is in an ally state
        assertTrue(resp.getEntities().stream().anyMatch(entity -> entity.getType().equals("mercenary")));

        controller.tick(Direction.RIGHT);
        controller.tick(Direction.RIGHT);

        resp = controller.tick(Direction.LEFT);
        assertFalse(resp.getEntities().stream().anyMatch(entity -> entity.getType().equals("mercenary")));
    }

    @Test
    public void testSceptreLimitedDurability() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse resp = controller.newGame("d_mindcontrol", "c_scptre_simple");
        String mercenary_id = getEntities(resp, "mercenary").get(0).getId();

        controller.tick(Direction.RIGHT);
        controller.tick(Direction.RIGHT);
        resp = controller.tick(Direction.RIGHT);
        assertDoesNotThrow(() -> controller.build("sceptre"));

        for (int times = 0; times < 3; times++) {
            assertDoesNotThrow(() -> controller.interact(mercenary_id));
            resp = controller.tick(Direction.RIGHT);
            // merceanry still alive beacsue mind control is in an ally state
            assertTrue(resp.getEntities().stream().anyMatch(entity -> entity.getType().equals("mercenary")));

            controller.tick(Direction.RIGHT);
            assertTrue(resp.getEntities().stream().anyMatch(entity -> entity.getType().equals("mercenary")));
        }

        controller.tick(Direction.RIGHT);
        resp = controller.tick(Direction.LEFT);
        assertFalse(resp.getEntities().stream().anyMatch(entity -> entity.getType().equals("mercenary")));
        assertFalse(resp.getInventory().stream().anyMatch(item -> item.getType().equals("sceptre")));
    }

    @Test
    public void testMindControlAssassin() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_mindcontrolAssassin", "c_assassinRecon");
        String assassinId = TestUtils.getEntities(res, "assassin").get(0).getId();
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        // should have all the materials to build a sceptre
        assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertDoesNotThrow(() -> dmc.interact(assassinId));
        res = dmc.tick(Direction.RIGHT);
        // assassin is ally therefore should not be interactable
        assertFalse(TestUtils.getEntities(res, "assassin").get(0).isInteractable());
    }

}
