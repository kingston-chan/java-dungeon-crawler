package dungeonmania.entities.staticobject.floorswitch;

public class ActivatedState implements SwitchState {
    private FloorSwitch floorSwitch;

    public ActivatedState(FloorSwitch floorSwitch) {
        this.floorSwitch = floorSwitch;
    }

    @Override
    public boolean activate() {
        return false;
    }

    @Override
    public boolean deactivate() {
        this.floorSwitch.setState(this.floorSwitch.getDeactivatedState());
        return true;
    }

    public boolean isSwitchActivated() {
        return true;
    }

}
