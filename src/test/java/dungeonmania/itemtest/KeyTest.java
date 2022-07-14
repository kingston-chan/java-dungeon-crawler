package dungeonmania.itemtest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.eclipse.jetty.server.handler.ContextHandler.StaticContext;
import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.item.Item;
import dungeonmania.entities.item.Key;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.util.Position;

public class KeyTest {
    @Test
    public void testUsingKeyOpenMatchedDoor() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("d_2door_test",
        "c_battleTests_basicMercenaryMercenaryDies");
        Dungeon testDungeon = controller.getDungeon();
        Player player = testDungeon.getPlayer();
        player.setPosition(new Position(2, 3));
        Item key1 = testDungeon.getItems().get(0);
        player.tryPickUpKey((Key) key1);
        assertTrue(player.getInventory().contains(key1));

        Item key2 = testDungeon.getItems().get(1);
        // player.tryPickUpKey((Key) key2);
        player.getInventory().get(0);
        StaticObject door1 = testDungeon.getStaticObjects().get(0);
        StaticObject door2 = testDungeon.getStaticObjects().get(1);
        // using key open door seems not implemented yet
    }

    @Test
    public void testUsingKeyTryToOpenNotmatchedDoor() {
    }

    @Test
    public void testTryToPickUpMultipleKey() {
    }
}
