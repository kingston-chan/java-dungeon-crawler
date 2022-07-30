package dungeonmania.morebuildables;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;

public class MidnightArmorUnitTest {
    @Test
    public void testSuccessfullyBuildsMidnightArmour(){
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("d_build_mdarmor", "c_mdarmor_simple");

        controller.tick(Direction.RIGHT);
        DungeonResponse dres = controller.tick(Direction.RIGHT);
        assertTrue(dres.getBuildables().stream().anyMatch(buildbale -> buildbale.equals("midnight_armour")));

        assertDoesNotThrow(() -> controller.build("midnight_armour"));
        dres = controller.tick(Direction.RIGHT);
        assertTrue(dres.getInventory().stream().anyMatch(item -> item.getType().equals("midnight_armour")));
    }

    @Test
    public void testFailedToBuildMidnightArmour1(){
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("d_build_mdarmor", "c_mdarmor_simple");

        controller.tick(Direction.RIGHT);
        assertThrows(InvalidActionException.class, () -> controller.build("midnight_armour"));
        DungeonResponse dres = controller.tick(Direction.UP);
        assertFalse(dres.getInventory().stream().anyMatch(item -> item.getType().equals("midnight_armour")));
    }

    @Test
    public void testFailedToBuildMidnightArmour2(){
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("d_build_mdarmor", "c_mdarmor_simple");

        controller.tick(Direction.UP);
        controller.tick(Direction.RIGHT);
        controller.tick(Direction.RIGHT);
        controller.tick(Direction.DOWN);

        assertThrows(InvalidActionException.class, () -> controller.build("midnight_armour"));
        DungeonResponse dres = controller.tick(Direction.UP);
        assertFalse(dres.getInventory().stream().anyMatch(item -> item.getType().equals("midnight_armour")));
    }

    @Test
    public void testMidNightArmourCantBuildIfThereExistZombie() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("d_build_mdarmour_zombie_exist", "c_mdarmor_simple");

        controller.tick(Direction.RIGHT);
        DungeonResponse dres = controller.tick(Direction.RIGHT);
        assertFalse(dres.getBuildables().stream().anyMatch(buildbale -> buildbale.equals("midnight_armour")));

        assertThrows(InvalidActionException.class, () -> controller.build("midnight_armour"));
        dres = controller.tick(Direction.RIGHT);
        assertFalse(dres.getInventory().stream().anyMatch(item -> item.getType().equals("midnight_armour")));
    }

    @Test
    public void testMidNightArmorProvideBothDefenseAndAttack(){
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("d_mdarmorbattle", "c_mdarmor_simple");

        controller.tick(Direction.RIGHT);
        controller.tick(Direction.RIGHT);
        assertDoesNotThrow(() -> controller.build("midnight_armour"));
        DungeonResponse dres = controller.tick(Direction.RIGHT);
        assertEquals(-0.0, dres.getBattles().get(0).getRounds().get(0).getDeltaCharacterHealth());
        assertEquals(-4.0, dres.getBattles().get(0).getRounds().get(0).getDeltaEnemyHealth());
    }

    @Test
    public void testMidNightArmorInfinityDurability(){
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("d_mdarmordurability", "c_mdarmor_simple");

        controller.tick(Direction.DOWN);
        controller.tick(Direction.DOWN);
        assertDoesNotThrow(() -> controller.build("midnight_armour"));

        for (int i = 0; i < 100; i++){
            controller.tick(Direction.UP);
            controller.tick(Direction.DOWN);
        }
        DungeonResponse dres = controller.tick(Direction.DOWN);

        assertTrue(dres.getBattles().stream().allMatch(round -> round.getRounds().get(0).getDeltaCharacterHealth() == -0.0));
    }
}
