package dungeonmania.entities.staticobject.floorswitch;

import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.boulder.Boulder;
<<<<<<< HEAD
import dungeonmania.entities.staticobject.staticbomb.SwitchObserver;

import java.util.ArrayList;
import java.util.List;

=======
import dungeonmania.entities.staticobject.floorswitch.SwitchState;
import dungeonmania.entities.staticobject.floorswitch.SwitchSubject;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.SwitchObserver;

>>>>>>> a27c47dd9425e0398f4397a78ef321e5db8fd525
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

<<<<<<< HEAD
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
=======
    public boolean accept(Boulder boulder) {
        return true;
    }

    public boolean isActivated() {
        if (currentState == deactivatedState) {
            return false;
        }
        return true;
    }
    
    public boolean doActivate() {
        this.currentState = this.activatedState;
        return true;
>>>>>>> a27c47dd9425e0398f4397a78ef321e5db8fd525
    }

    public SwitchState getActivatedState() {
        return this.activatedState;
    }

<<<<<<< HEAD
    public SwitchState getDeactivatedState() {
        return this.deactivatedState;
    }

    @Override
    public boolean canAccept(Boulder boulder) {
        return this.currentState.isSwitchActivated();
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
=======
    public void setState(SwitchState state) {
        this.currentState = state;
    }

    @Override
    public void add(SwitchObserver switchObserver) {
        //idk what this is suppost to do rn
    }

    @Override
    public void notifySwitchObservers() {
        // TODO Auto-generated method stub
        
>>>>>>> a27c47dd9425e0398f4397a78ef321e5db8fd525
    }

}
