package dungeonmania.entities.staticobject.floorswitch;

public class LogicDeactivatedState extends DeactivatedState {
    public LogicDeactivatedState(FloorSwitch floorSwitch) {
        super(floorSwitch);
    }

    @Override
    public boolean activate() {
        FloorSwitch floorSwitch = getFloorSwitch();

        if (floorSwitch.isActivated()) {
            floorSwitch.setState(floorSwitch.getActivatedState());
            floorSwitch.notifySwitchObservers(); // bombs
            // check if bomb breaks so circuit should not activate/deactivate
            if (floorSwitch.isRemoved()) {
                return true;
            }
            floorSwitch.updateAdjacent(true, floorSwitch);
            return true;
        }

        return super.activate();
    }
}
