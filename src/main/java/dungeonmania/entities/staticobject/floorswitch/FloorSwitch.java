package dungeonmania.entities.staticobject.floorswitch;

import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.boulder.Boulder;
import dungeonmania.entities.staticobject.logicentities.CircuitObserver;
import dungeonmania.entities.staticobject.staticbomb.SwitchObserver;
import dungeonmania.entities.staticobject.wire.Wire;

import java.util.ArrayList;
import java.util.List;

public class FloorSwitch extends StaticObject implements SwitchSubject, CircuitSubject, ActivatedEntities {
    private SwitchState activatedState;
    private SwitchState deactivatedState;
    private SwitchState currentState;
    private List<SwitchObserver> switchObservers = new ArrayList<SwitchObserver>();
    private List<CircuitObserver> circuitObservers = new ArrayList<CircuitObserver>();
    private int tickActivated = 0;

    public FloorSwitch() {
        this.activatedState = new ActivatedState(this);
        this.deactivatedState = new DeactivatedState(this);
        this.currentState = deactivatedState;
    }

    @Override
    public boolean isActivated() {
        return this.currentState.isSwitchActivated();
    }

    public void setTickActivated(int tick) {
        this.tickActivated = tick;
    }

    public boolean boulderActivate() {
        return this.currentState.activate();
    }

    public boolean playerDeactivate() {
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
    public void doAccept(Player player) {
        player.visit(this);
    }

    @Override
    public void doAccept(Boulder boulder) {
        boulder.visit(this);
    }

    @Override
    public void add(SwitchObserver switchObserver) {
        switchObservers.add(switchObserver);
    }

    @Override
    public void notifySwitchObservers() {
        switchObservers.stream().forEach(o -> o.update());
    }

    @Override
    public boolean isInteractable() {
        return false;
    }

    @Override
    public void add(CircuitObserver circuitObserver) {
        this.circuitObservers.add(circuitObserver);
    }

    @Override
    public void notifyActivate() {
        this.circuitObservers.stream().filter(c -> c instanceof Wire).forEach(w -> w.updateActivate());
        this.circuitObservers.stream().filter(c -> !(c instanceof Wire)).forEach(c -> c.updateActivate());
    }

    @Override
    public void notifyDeactivate() {
        this.circuitObservers.stream().filter(c -> c instanceof Wire).forEach(w -> w.updateDeactivate());
        this.circuitObservers.stream().filter(c -> !(c instanceof Wire)).forEach(c -> c.updateDeactivate());
    }

    @Override
    public int getActivatedTick() {
        return this.tickActivated;
    }

}
