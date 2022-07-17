package dungeonmania.nonplayableactors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.util.Position;

public class ZombieToastWhiteBoxTests {
    @Test
    public void testStaticObjectAcceptZombie() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("zombietests/d_staticObjectsZombie", "c_noSpawns");
        Dungeon testDungeon = DungeonManiaController.getDungeon();
        NonPlayableActor zombieToast = testDungeon.getNonPlayableActors().get(0);
        // wall
        assertFalse(
                testDungeon.getStaticObjectsAtPosition(new Position(3, 1)).stream()
                        .allMatch(o -> o.canAccept(zombieToast)));
        // boulder
        assertFalse(
                testDungeon.getStaticObjectsAtPosition(new Position(3, 0)).stream()
                        .allMatch(o -> o.canAccept(zombieToast)));
        // locked door
        assertFalse(
                testDungeon.getStaticObjectsAtPosition(new Position(1, 0)).stream()
                        .allMatch(o -> o.canAccept(zombieToast)));
        // exit
        assertTrue(
                testDungeon.getStaticObjectsAtPosition(new Position(1, 1)).stream()
                        .allMatch(o -> o.canAccept(zombieToast)));
        // switch
        assertTrue(
                testDungeon.getStaticObjectsAtPosition(new Position(2, 0)).stream()
                        .allMatch(o -> o.canAccept(zombieToast)));
        // spawner
        assertTrue(
                testDungeon.getStaticObjectsAtPosition(new Position(4, 2)).stream()
                        .allMatch(o -> o.canAccept(zombieToast)));
    }
}
