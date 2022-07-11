package dungeonmania.entities.staticobject.floorswitch;

import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.boulder.Boulder;

public class FloorSwitch extends StaticObject {
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

    public boolean accept(Player player) {
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
        this.currentState = this.state;
    }

    public void add(SwitchObserver state) {
        //idk what this is suppost to do rn
    }

    public void notifySwitchObservers(SwitchObserver state) {
        //idk what this is suppost to do rn
    }

}
