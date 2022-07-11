package main.java.dungeonmania.entities.staticobject.floorswitch;

import dungeonmania.entities.staticobject.floorswitch.FloorSwitch;

public class ActivatedState extends FloorSwitch {
    private FloorSwitch floorSwitch;

    public ActivatedState(FloorSwitch floorSwitch) {
        this.floorSwitch = floorSwitch;
    }

    public void activate() {

    }
    public void deactivate() {

    }
    public boolean isSwitchActivated() {
        return floorSwitch.isActivated();
    }

}
