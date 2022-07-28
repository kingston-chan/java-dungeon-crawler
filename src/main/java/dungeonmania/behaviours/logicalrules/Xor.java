package dungeonmania.behaviours.logicalrules;

import dungeonmania.entities.staticobject.logicentities.CircuitObserver;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.staticobject.floorswitch.ActivatedEntities;
import dungeonmania.util.Position;

public class Xor implements LogicRules {

    @Override
    public boolean canActivate(CircuitObserver circuitObserver) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Position pos = circuitObserver.getCircuitObserverPosition();
        List<ActivatedEntities> activatedEntities = new ArrayList<>();

        pos.getAdjacentCardinalPositions().forEach(p -> {
            dungeon.getObjectsAtPosition(p).stream().filter(o -> o instanceof ActivatedEntities)
                    .map(o -> (ActivatedEntities) o).filter(ActivatedEntities::isActivated)
                    .forEach(o -> activatedEntities.add(o));
        });

        System.out.println(activatedEntities.size());
        return activatedEntities.size() == 1;
    }

}
