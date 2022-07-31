package dungeonmania.entities.staticobject.floorswitch;

import dungeonmania.DungeonManiaController;

public class DeactivatedState implements SwitchState {
    private FloorSwitch floorSwitch;

    public DeactivatedState(FloorSwitch floorSwitch) {
        this.floorSwitch = floorSwitch;
    }

    public FloorSwitch getFloorSwitch() {
        return this.floorSwitch;
    }

    @Override
    public boolean activate() {
        this.floorSwitch.setState(this.floorSwitch.getActivatedState());
        this.floorSwitch.setTickActivated(DungeonManiaController.getDungeon().getTick());
        this.floorSwitch.notifySwitchObservers(); // bombs
        // recheck list
        if (this.floorSwitch.isRemoved()) {
            return true;
        }
        this.floorSwitch.updateAdjacent(true, this.floorSwitch);
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
