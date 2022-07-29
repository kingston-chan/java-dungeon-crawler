package dungeonmania.entities.staticobject.logicentities;

import dungeonmania.DungeonManiaController;
import dungeonmania.behaviours.logicalrules.LogicRules;
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
        // If super.isActivated is true return true
        if (super.isActivated())
            return true;
        // return logic behaviour rule
        return circuitActivated;
    }

    @Override
    public void updateAdjacent(boolean doActivate) {
        // if we are mechanically activated don't do anything
        if (super.isActivated())
            return;

        // update circuitActivated
        boolean oldCircuitActivated = circuitActivated;
        circuitActivated = logicRules.canActivate(this);
        if (oldCircuitActivated != circuitActivated) {
            // this means that it was not activated before
            if (circuitActivated) {
                setTickActivated(DungeonManiaController.getDungeon().getTick());
            }

            // update wires
            listActivatedEntities().stream().filter(o -> o instanceof Wire)
                    .forEach(o -> o.updateAdjacent(circuitActivated));
            // update logic switches
            listActivatedEntities().stream().filter(o -> o instanceof LogicFloorSwitch)
                    .forEach(o -> o.updateAdjacent(circuitActivated));
            // update observers
            listCircuitObservers().stream().forEach(o -> o.updateLogic());
        }
    }

}
