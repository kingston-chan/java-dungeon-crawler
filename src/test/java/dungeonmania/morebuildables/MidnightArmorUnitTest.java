package dungeonmania.morebuildables;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;

public class MidnightArmorUnitTest {
    @Test
    public void testSuccessfullyBuildsMidnightArmour(){
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("d_build_mdarmor", "c_mercenaryBribeRadiusTwo");

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
        controller.newGame("d_build_mdarmor", "c_mercenaryBribeRadiusTwo");

        controller.tick(Direction.RIGHT);
        assertThrows(IllegalArgumentException.class, () -> controller.build("midnight_armour"));
        DungeonResponse dres = controller.tick(Direction.UP);
        assertFalse(dres.getInventory().stream().anyMatch(item -> item.getType().equals("midnight_armour")));
    }

    @Test
    public void testFailedToBuildMidnightArmour2(){
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("d_build_mdarmor", "c_mercenaryBribeRadiusTwo");

        controller.tick(Direction.UP);
        controller.tick(Direction.RIGHT);
        controller.tick(Direction.RIGHT);
        controller.tick(Direction.DOWN);

        assertThrows(IllegalArgumentException.class, () -> controller.build("midnight_armour"));
        DungeonResponse dres = controller.tick(Direction.UP);
        assertFalse(dres.getInventory().stream().anyMatch(item -> item.getType().equals("midnight_armour")));
    }

    @Test
    public void testMidNightArmorProvideBothDefenseAndAttack(){
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("d_mdarmorbattle", "c_mdarmor_simple");

        controller.tick(Direction.RIGHT);
        controller.tick(Direction.RIGHT);

        DungeonResponse dres = controller.tick(Direction.RIGHT);
        assertEquals(0, dres.getBattles().get(0).getRounds().get(0).getDeltaCharacterHealth());
        assertEquals(4, dres.getBattles().get(0).getRounds().get(0).getDeltaEnemyHealth());
    }

    @Test
    public void testMidNightArmorInfinityDurability(){
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("d_mdarmorbattle", "c_mdarmor_simple");
        Dungeon current_dungeon = controller.getDungeon();

        DungeonResponse dres = controller.tick(Direction.RIGHT);

    }
}
