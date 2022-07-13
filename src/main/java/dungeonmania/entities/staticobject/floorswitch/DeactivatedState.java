package dungeonmania.entities.staticobject.floorswitch;

<<<<<<< HEAD
=======
import dungeonmania.entities.staticobject.floorswitch.FloorSwitch;

>>>>>>> a27c47dd9425e0398f4397a78ef321e5db8fd525
public class DeactivatedState extends FloorSwitch implements SwitchState {
    private FloorSwitch floorSwitch;

    public DeactivatedState(FloorSwitch floorSwitch) {
        this.floorSwitch = floorSwitch;
    }

<<<<<<< HEAD
    @Override
    public boolean activate() {
        this.notifySwitchObservers();
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
=======
    public void activate() {

    }
    public void deactivate() {

    }
    public boolean isActivated() {

        return floorSwitch.isActivated();
>>>>>>> a27c47dd9425e0398f4397a78ef321e5db8fd525
    }

}
