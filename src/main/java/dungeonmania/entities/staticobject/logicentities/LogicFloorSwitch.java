package dungeonmania.entities.staticobject.logicentities;

import dungeonmania.entities.staticobject.floorswitch.FloorSwitch;
import dungeonmania.util.Position;

public class LogicFloorSwitch extends FloorSwitch implements CircuitObserver {

    @Override
    public boolean isActivated() {
        // If super.isActivated is true return true
        // return logic behaviour rule
        return super.isActivated();
    }

    @Override
    public void updateActivate() {
        // if super.isActivated, return
        // if already activated, return
        // check using behaviour and do function
        // if activated by logic rule notifyActivate();
    }

    @Override
    public void updateDeactivate() {
        // if super.isActivated, return
        // if already deactivated, return
        // check using behaviour and do function
        // if deactivated by logic rule notifyDeactivate();
    }

    @Override
    public Position getCircuitObserverPosition() {
        // TODO Auto-generated method stub
        return getPosition();
    }

}
