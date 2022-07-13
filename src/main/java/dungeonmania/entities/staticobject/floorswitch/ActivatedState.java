package dungeonmania.entities.staticobject.floorswitch;

<<<<<<< HEAD
public class ActivatedState extends FloorSwitch implements SwitchState {
=======
import dungeonmania.entities.staticobject.floorswitch.FloorSwitch;

public class ActivatedState extends FloorSwitch {
>>>>>>> a27c47dd9425e0398f4397a78ef321e5db8fd525
    private FloorSwitch floorSwitch;

    public ActivatedState(FloorSwitch floorSwitch) {
        this.floorSwitch = floorSwitch;
    }

<<<<<<< HEAD
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
=======
    public void activate() {

    }
    public void deactivate() {

    }
    public boolean isSwitchActivated() {
        return floorSwitch.isActivated();
>>>>>>> a27c47dd9425e0398f4397a78ef321e5db8fd525
    }

}
