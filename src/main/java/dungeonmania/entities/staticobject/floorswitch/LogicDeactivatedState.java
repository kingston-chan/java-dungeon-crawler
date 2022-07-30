package dungeonmania.entities.staticobject.floorswitch;

public class LogicDeactivatedState extends DeactivatedState {
    private FloorSwitch floorSwitch;

    public LogicDeactivatedState(FloorSwitch floorSwitch) {
        super(floorSwitch);
        this.floorSwitch = floorSwitch;
    }

    @Override
    public boolean activate() {
        if (floorSwitch.isActivated()) {
            this.floorSwitch.setState(this.floorSwitch.getActivatedState());
            this.floorSwitch.notifySwitchObservers(); // bombs
            // check if bomb breaks so circuit should not activate/deactivate
            if (this.floorSwitch.isRemoved()) {
                return true;
            }
            this.floorSwitch.updateAdjacent(true, this.floorSwitch);
            return true;
        }
        return super.activate();
    }
}
