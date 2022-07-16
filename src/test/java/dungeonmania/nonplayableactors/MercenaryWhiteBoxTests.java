package dungeonmania.nonplayableactors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.util.Position;

public class MercenaryWhiteBoxTests {
        @Test
        public void testStaticObjectAcceptMercenary() {
                DungeonManiaController controller = new DungeonManiaController();
                controller.newGame("mercenarytests/d_staticObjectsMercenary", "simple");
                Dungeon testDungeon = DungeonManiaController.getDungeon();
                NonPlayableActor merc = testDungeon.getNonPlayableActors().get(0);
                // wall
                assertFalse(
                                testDungeon.getStaticObjectsAtPosition(new Position(3, 1)).stream()
                                                .allMatch(o -> o.canAccept(merc)));
                // boulder
                assertFalse(
                                testDungeon.getStaticObjectsAtPosition(new Position(3, 0)).stream()
                                                .allMatch(o -> o.canAccept(merc)));
                // locked door
                assertFalse(
                                testDungeon.getStaticObjectsAtPosition(new Position(1, 0)).stream()
                                                .allMatch(o -> o.canAccept(merc)));
                // exit
                assertTrue(
                                testDungeon.getStaticObjectsAtPosition(new Position(1, 1)).stream()
                                                .allMatch(o -> o.canAccept(merc)));
                // switch
                assertTrue(
                                testDungeon.getStaticObjectsAtPosition(new Position(2, 0)).stream()
                                                .allMatch(o -> o.canAccept(merc)));
                // spawner
                assertTrue(
                                testDungeon.getStaticObjectsAtPosition(new Position(4, 2)).stream()
                                                .allMatch(o -> o.canAccept(merc)));
        }
}
