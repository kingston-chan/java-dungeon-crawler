package dungeonmania.behaviours.movement;

import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.util.Position;

public class MovementHelper {
    public static List<Position> getMovableAdjacentPositions(NonPlayableActor npa) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        return npa.getPosition().getAdjacentCardinalPositions().stream()
                .filter(pos -> dungeon.getObjectsAtPosition(pos).stream().allMatch(o -> o.canAccept(npa)))
                .collect(Collectors.toList());
    }
}
