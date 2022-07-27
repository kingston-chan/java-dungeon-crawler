package dungeonmania.entities.staticobject.logicentities;

import dungeonmania.entities.staticobject.floorswitch.FloorSwitch;

public class LogicFloorSwitch extends FloorSwitch implements CircuitObserver {

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

}
