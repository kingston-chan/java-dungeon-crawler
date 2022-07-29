package dungeonmania.behaviours.logicalrules;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.staticobject.floorswitch.ActivatedEntity;
import dungeonmania.util.Position;

public class LogicHelpers {
    public static List<ActivatedEntity> getAdjacentActivatedEntities(Position position) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        List<ActivatedEntity> adjacentActivatedEntities = new ArrayList<>();

        position.getAdjacentCardinalPositions().forEach(p -> {
            dungeon.getObjectsAtPosition(p).stream().filter(o -> o instanceof ActivatedEntity)
                    .map(o -> (ActivatedEntity) o)
                    .forEach(o -> adjacentActivatedEntities.add(o));
        });

        return adjacentActivatedEntities;
    }
}
