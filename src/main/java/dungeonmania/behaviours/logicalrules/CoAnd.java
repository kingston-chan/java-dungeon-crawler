package dungeonmania.behaviours.logicalrules;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.staticobject.floorswitch.ActivatedEntities;
import dungeonmania.entities.staticobject.logicentities.CircuitObserver;
import dungeonmania.util.Position;

public class CoAnd implements LogicRules {

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

        return activatedEntities.size() >= 2 && activatedEntities.stream().allMatch(o -> activatedEntities.stream()
                .map(ActivatedEntities::getActivatedTick).allMatch(t -> t == o.getActivatedTick()));
    }

}
