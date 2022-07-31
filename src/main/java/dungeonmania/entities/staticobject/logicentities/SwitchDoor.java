package dungeonmania.entities.staticobject.logicentities;

import dungeonmania.behaviours.logicalrules.LogicRules;
import dungeonmania.entities.staticobject.door.Door;

public class SwitchDoor extends Door implements CircuitObserver {
    private LogicRules logicRules;

    public SwitchDoor(int key) {
        super(key);
    }

    public void setLogicRules(LogicRules logicRules) {
        this.logicRules = logicRules;
    }

    @Override
    public void updateLogic() {
        if (isUnlocked())
            return;
        if (logicRules.canActivate(this)) {
            setOpened();
        } else {
            setClosed();
        }
    }

}
