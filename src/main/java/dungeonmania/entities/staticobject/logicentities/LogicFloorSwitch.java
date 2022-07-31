package dungeonmania.entities.staticobject.logicentities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.DungeonManiaController;
import dungeonmania.behaviours.logicalrules.LogicRules;
import dungeonmania.entities.staticobject.floorswitch.ActivatedEntity;
import dungeonmania.entities.staticobject.floorswitch.FloorSwitch;
import dungeonmania.entities.staticobject.wire.Wire;

public class LogicFloorSwitch extends FloorSwitch {
    private LogicRules logicRules;
    private boolean circuitActivated = false;

    public LogicFloorSwitch(LogicRules logicRules) {
        this.logicRules = logicRules;
    }

    @Override
    public boolean isActivated() {
        return super.isMechanicallyActivated() || circuitActivated;
    }

    @Override
    public boolean updateAdjacent(boolean doActivate, ActivatedEntity notifier) {
        // if we are mechanically activated don't do anything
        if (isMechanicallyActivated()) {
            super.updateAdjacent(doActivate, this);
            return true;
        }

        List<ActivatedEntity> updatees = listActivatedEntities().stream().filter(a -> !a.equals(notifier))
                .filter(o -> o instanceof LogicFloorSwitch || o instanceof Wire)
                .collect(Collectors.toList());
        if (!doActivate) {
            circuitActivated = doActivate;

            List<ActivatedEntity> notifyAgain = new ArrayList<>();

            for (ActivatedEntity a : updatees) {
                if (!a.updateAdjacent(doActivate, this)) {
                    notifyAgain.add(a);
                }
            }

            circuitActivated = logicRules.canActivate(this);

            if (circuitActivated) {
                notifyAgain.stream().filter(o -> o instanceof Wire)
                        .forEach(o -> o.updateAdjacent(circuitActivated, this));
                notifyAgain.stream().filter(o -> o instanceof LogicFloorSwitch)
                        .forEach(o -> o.updateAdjacent(circuitActivated, this));
            }

            listCircuitObservers().stream().forEach(o -> o.updateLogic());

            return circuitActivated;
        }
        // update circuitActivated
        boolean oldCircuitActivated = circuitActivated;
        circuitActivated = logicRules.canActivate(this);

        // check if already activated
        if (circuitActivated != oldCircuitActivated) {
            // cases for xor -> doActivate is true -> but circuitActivated is true
            // but changed to false
            if (circuitActivated) {
                // was not activated before
                setTickActivated(DungeonManiaController.getDungeon().getTick());
            }

            // update wires
            updatees.stream().filter(o -> o instanceof Wire)
                    .forEach(o -> o.updateAdjacent(circuitActivated, this));
            // update logic switches
            updatees.stream().filter(o -> o instanceof LogicFloorSwitch)
                    .forEach(o -> o.updateAdjacent(circuitActivated, this));
            // update observers
            listCircuitObservers().stream().forEach(o -> o.updateLogic());

            return circuitActivated;
        }

        return circuitActivated;
    }

}
