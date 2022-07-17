package dungeonmania.nonplayableactors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.util.Position;

public class SpiderWhiteBoxTests {
    @Test
    public void testStaticObjectAcceptSpider() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("spidertests/d_staticObjectsSpider", "c_noSpawns");
        Dungeon testDungeon = DungeonManiaController.getDungeon();
        NonPlayableActor spider = testDungeon.getNonPlayableActors().get(0);
        // wall
        assertTrue(
                testDungeon.getStaticObjectsAtPosition(new Position(3, 1)).stream()
                        .allMatch(o -> o.canAccept(spider)));
        // boulder
        assertFalse(
                testDungeon.getStaticObjectsAtPosition(new Position(3, 0)).stream()
                        .allMatch(o -> o.canAccept(spider)));
        // locked door
        assertTrue(
                testDungeon.getStaticObjectsAtPosition(new Position(1, 0)).stream()
                        .allMatch(o -> o.canAccept(spider)));
        // exit
        assertTrue(
                testDungeon.getStaticObjectsAtPosition(new Position(1, 1)).stream()
                        .allMatch(o -> o.canAccept(spider)));
        // switch
        assertTrue(
                testDungeon.getStaticObjectsAtPosition(new Position(2, 0)).stream()
                        .allMatch(o -> o.canAccept(spider)));
        // spawner
        assertTrue(
                testDungeon.getStaticObjectsAtPosition(new Position(4, 2)).stream()
                        .allMatch(o -> o.canAccept(spider)));
    }
}
