package dungeonmania.behaviours.logicalrules;

import dungeonmania.entities.staticobject.logicentities.CircuitObserver;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.staticobject.floorswitch.ActivatedEntities;
<<<<<<< HEAD
import dungeonmania.entities.staticobject.logicentities.CircuitObserver;
=======
>>>>>>> cd89a8b7c79ed54d850c07bc3236c602de069dac
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

<<<<<<< HEAD
        System.out.println(activatedEntities.size());
=======
>>>>>>> cd89a8b7c79ed54d850c07bc3236c602de069dac
        return activatedEntities.size() == 1;
    }

}
