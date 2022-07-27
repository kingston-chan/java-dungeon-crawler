package dungeonmania.entities.staticobject.logicentities;

import dungeonmania.behaviours.logicalrules.LogicRules;
import dungeonmania.entities.staticobject.door.Door;
import dungeonmania.util.Position;

public class SwitchDoor extends Door implements CircuitObserver {
    private LogicRules logicRules;

    public SwitchDoor(int key) {
        super(key);
    }

    public void setLogicRules(LogicRules logicRules) {
        this.logicRules = logicRules;
    }

    @Override
    public void updateActivate() {
        // if logic rule true and not already open, set to open
        if (logicRules.canActivate(this) && !isOpened())
            setOpened();
    }

    @Override
    public void updateDeactivate() {
        // if logic rule false and not logic opened (i.e. player opened with key), set
        // to closed
        if (!logicRules.canActivate(this) && !isUnlocked())
            setClosed();
    }

    @Override
    public Position getCircuitObserverPosition() {
        return getPosition();
    }

}
