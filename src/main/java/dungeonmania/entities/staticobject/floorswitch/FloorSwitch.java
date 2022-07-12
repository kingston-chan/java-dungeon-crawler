package dungeonmania.entities.staticobject.floorswitch;

import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.boulder.Boulder;
import dungeonmania.entities.staticobject.floorswitch.SwitchState;
import dungeonmania.entities.staticobject.floorswitch.SwitchSubject;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.SwitchObserver;

public class FloorSwitch extends StaticObject implements SwitchSubject {
    private SwitchState activatedState;
    private SwitchState deactivatedState;
    private SwitchState currentState;

    @Override
    public boolean isInteractable() {
        return false;
    }

    public boolean accept(Boulder boulder) {
        return true;
    }

    public boolean isActivated() {        
        if (this.currentState == this.activatedState) {
            return true;
        }
        return false;
    }
    
    public boolean doActivate() {

        // if bolder already on switch
       
        return this.currentState.activate();
    }

    public boolean doDeactivate() {
        this.currentState = this.deactivatedState;
        return true;
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
        //idk what this is suppost to do rn
    }

    @Override
    public void notifySwitchObservers() {
        // TODO Auto-generated method stub
        
    }

}
