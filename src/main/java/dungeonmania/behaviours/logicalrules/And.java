package dungeonmania.behaviours.logicalrules;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.staticobject.floorswitch.ActivatedEntities;
import dungeonmania.entities.staticobject.floorswitch.FloorSwitch;
import dungeonmania.entities.staticobject.logicentities.CircuitObserver;
import dungeonmania.entities.staticobject.wire.Wire;
import dungeonmania.util.Position;

public class And implements LogicRules {

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

        return (activatedEntities.stream().filter(e -> e instanceof FloorSwitch).count() >= 2
                && activatedEntities.stream().filter(e -> e instanceof FloorSwitch)
                .allMatch(e -> e.isActivated()))
               || activatedEntities.stream().filter(e -> e instanceof Wire).count() >= 2 ;
    }

}
