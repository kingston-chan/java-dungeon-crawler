package dungeonmania.entities.staticobject.logicentities;

import dungeonmania.behaviours.logicalrules.LogicRules;
import dungeonmania.entities.staticobject.floorswitch.FloorSwitch;
import dungeonmania.util.Position;

public class LogicFloorSwitch extends FloorSwitch implements CircuitObserver {
    private LogicRules logicRules;
    private boolean isActive = false;

    public LogicFloorSwitch(LogicRules logicRules) {
        this.logicRules = logicRules;
    }

    @Override
    public boolean isActivated() {
        // If super.isActivated is true return true
        if (super.isActivated())
            return true;
        // return logic behaviour rule
        return logicRules.canActivate(this);
    }

    @Override
    public void updateActivate() {
        // if super.isActivated, return
        if (super.isActivated())
            return;
        // if already activated, return
        // check using behaviour and do function
        // if activated by logic rule notifyActivate();
        isActive = logicRules.canActivate(this);
        if (isActive) {
            notifyActivate();
        } else {
            notifyDeactivate();
        }
    }

    @Override
    public void updateDeactivate() {
        // if super.isActivated, return because boulder ontop
        if (super.isActivated())
            return;
        // if already deactivated, return
        // check using behaviour and do function
        // if deactivated by logic rule notifyDeactivate();
        isActive = logicRules.canActivate(this);
        if (isActive) {
            notifyActivate();
        } else {
            notifyDeactivate();
        }
    }

    @Override
    public Position getCircuitObserverPosition() {
        return getPosition();
    }

}
