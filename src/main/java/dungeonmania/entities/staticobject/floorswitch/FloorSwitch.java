package dungeonmania.entities.staticobject.floorswitch;

import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.boulder.Boulder;
import dungeonmania.entities.staticobject.staticbomb.SwitchObserver;

import java.util.ArrayList;
import java.util.List;

public class FloorSwitch extends StaticObject implements SwitchSubject {
    private SwitchState activatedState;
    private SwitchState deactivatedState;
    private SwitchState currentState;
    private List<SwitchObserver> switchObservers = new ArrayList<SwitchObserver>();

    public FloorSwitch() {
        this.activatedState = new ActivatedState(this);
        this.deactivatedState = new DeactivatedState(this);
        this.currentState = deactivatedState;
    }

    public boolean isActivated() {
        return this.currentState.isSwitchActivated();
    }

    public boolean doActivate() {
        return this.currentState.activate();
    }

    public boolean doDeactivate() {
        return this.currentState.deactivate();
    }

    public void setState(SwitchState state) {
        this.currentState = state;
    }

    public SwitchState getActivatedState() {
        return this.activatedState;
    }

    public SwitchState getDeactivatedState() {
        return this.deactivatedState;
    }

    @Override
    public boolean canAccept(Boulder boulder) {
        return !this.currentState.isSwitchActivated();
    }

    @Override
    public void add(SwitchObserver switchObserver) {
        switchObservers.add(switchObserver);
    }

    @Override
    public void remove(SwitchObserver switchObserver) {
        switchObservers.remove(switchObserver);
    }

    @Override
    public void notifySwitchObservers() {
        switchObservers.stream().forEach(o -> o.update(this));
    }

    @Override
    public boolean isInteractable() {
        return false;
    }

}
