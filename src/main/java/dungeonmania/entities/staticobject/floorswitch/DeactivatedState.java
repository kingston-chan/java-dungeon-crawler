package main.java.dungeonmania.entities.staticobject.floorswitch;

import dungeonmania.entities.staticobject.floorswitch.FloorSwitch;

public class DeactivatedState extends FloorSwitch implements SwitchState {
    private FloorSwitch floorSwitch;

    public DeactivatedState(FloorSwitch floorSwitch) {
        this.floorSwitch = floorSwitch;
    }

    public void activate() {

    }
    public void deactivate() {

    }
    public boolean isActivated() {

        return floorSwitch.isActivated();
    }

}
