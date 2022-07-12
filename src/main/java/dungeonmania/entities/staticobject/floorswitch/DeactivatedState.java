package dungeonmania.entities.staticobject.floorswitch;

import dungeonmania.entities.staticobject.floorswitch.FloorSwitch;

public class DeactivatedState extends FloorSwitch implements SwitchState {
    private FloorSwitch floorSwitch;

    public DeactivatedState(FloorSwitch floorSwitch) {
        this.floorSwitch = floorSwitch;
    }

    @Override
    public boolean activate() {
        this.floorSwitch.setState(this.floorSwitch.getActivatedState());
        return true;
    }
    public boolean deactivate() {
        this.floorSwitch.setState(this.floorSwitch.getDeactivatedState());
        return true;
    }
    public boolean isActivated() {

        return floorSwitch.isActivated();
    }

}
