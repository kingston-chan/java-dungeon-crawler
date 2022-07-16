package dungeonmania.dungeon;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;

public class ControllerErrorThrowingTests {
    @Test
    public void testDungeonNameDoesNotExist() {
        DungeonManiaController dmc = new DungeonManiaController();
        assertThrows(IllegalArgumentException.class, () -> dmc.newGame("notadungeonname", "simple"));
    }

    @Test
    public void testDungeonConfigDoesNotExist() {
        DungeonManiaController dmc = new DungeonManiaController();
        assertThrows(IllegalArgumentException.class, () -> dmc.newGame("d_2keys", "notaconfig"));
    }

    @Test
    public void testBothDungeonConfigInvalid() {
        DungeonManiaController dmc = new DungeonManiaController();
        assertThrows(IllegalArgumentException.class, () -> dmc.newGame("notadungeonname", "notaconfig"));
    }
}
