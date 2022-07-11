package dungeonmania.entities.staticobject.floorswitch;

import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.boulder.Boulder;
import main.java.dungeonmania.entities.staticobject.floorswitch.SwitchState;
import main.java.dungeonmania.entities.staticobject.floorswitch.SwitchSubject;
import dungeonmania.entities.actor.player.Player;
import main.java.dungeonmania.entities.SwitchObserver;

public class FloorSwitch extends StaticObject implements SwitchSubject {
    private SwitchState activatedState;
    private SwitchState deactivatedState;
    private SwitchState currentState;

    @Override
    public boolean isInteractable() {
        return false;
    }

    public boolean accept(Player player) {
        return true;
    }

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
    }

    public boolean doDeactivate() {
        this.currentState = this.deactivatedState;
        return true;
    }

    public void setState(SwitchState state) {
        this.currentState = state;
    }

    @Override
    public void add(SwitchObserver state) {
        //idk what this is suppost to do rn
    }

    @Override
    public void notifySwitchObservers() {
        // TODO Auto-generated method stub
        
    }

}
