package dungeonmania.entities.staticobject.floorswitch;

import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.boulder.Boulder;
import dungeonmania.entities.staticobject.floorswitch.SwitchState;
import dungeonmania.entities.staticobject.floorswitch.SwitchSubject;
import dungeonmania.entities.actor.player.Player;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.entities.SwitchObserver;

public class FloorSwitch extends StaticObject implements SwitchSubject {
    private SwitchState activatedState;
    private SwitchState deactivatedState;
    private SwitchState currentState;
    private List<SwitchObserver> switchObservers = new ArrayList<SwitchObserver>();


    @Override
    public boolean isInteractable() {
        return false;
    }

    public boolean accept(Boulder boulder) {
        return true;
    }

    public boolean isActivated() {        
        return this.currentState.isSwitchActivated();
    }
    
    public boolean doActivate() {

        // if bolder already on switch
       
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
    
}
