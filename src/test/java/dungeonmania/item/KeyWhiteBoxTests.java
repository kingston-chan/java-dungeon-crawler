package dungeonmania.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.item.Key;
import dungeonmania.entities.staticobject.door.Door;
import dungeonmania.util.Position;

public class KeyWhiteBoxTests {
    @Test
    public void testKeyOpensDoor() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_2door_test", "c_potionDurationHigh");
        Dungeon testDungeon = DungeonManiaController.getDungeon();

        Key keyOne = (Key) testDungeon.getObjectsAtPosition(new Position(2, 3)).get(0);
        Door doorOne = (Door) testDungeon.getObjectsAtPosition(new Position(3, 4)).get(0);

        Key keyTwo = (Key) testDungeon.getObjectsAtPosition(new Position(3, 2)).get(0);
        Door doorTwo = (Door) testDungeon.getObjectsAtPosition(new Position(4, 3)).get(0);

        assertTrue(keyOne.canOpenDoor(doorOne));
        testDungeon.getPlayer().tryPickUpKey(keyOne);
        assertTrue(doorOne.canAccept(testDungeon.getPlayer()));

        assertTrue(keyTwo.canOpenDoor(doorTwo));
        testDungeon.getPlayer().tryPickUpKey(keyTwo);
        assertTrue(doorTwo.canAccept(testDungeon.getPlayer()));
    }

    @Test
    public void testKeyDoesNotOpensDoor() {
        DungeonManiaController dmc = new DungeonManiaController();
        dmc.newGame("d_2door_test", "c_potionDurationHigh");
        Dungeon testDungeon = DungeonManiaController.getDungeon();

        Key keyOne = (Key) testDungeon.getObjectsAtPosition(new Position(2, 3)).get(0);
        Door doorTwo = (Door) testDungeon.getObjectsAtPosition(new Position(4, 3)).get(0);

        assertFalse(keyOne.canOpenDoor(doorTwo));
    }
}
