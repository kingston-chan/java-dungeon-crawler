package dungeonmania.entities.staticobject.floorswitch;

public class DeactivatedState implements SwitchState {
    private FloorSwitch floorSwitch;

    public DeactivatedState(FloorSwitch floorSwitch) {
        this.floorSwitch = floorSwitch;
    }

    @Override
    public boolean activate() {
        this.floorSwitch.notifySwitchObservers();
        this.floorSwitch.setState(this.floorSwitch.getActivatedState());
        return true;
    }

    @Override
    public boolean deactivate() {
        return false;
    }

    @Override
    public boolean isSwitchActivated() {
        return false;
    }

}
