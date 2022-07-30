package dungeonmania.entities.staticobject.floorswitch;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.boulder.Boulder;
import dungeonmania.entities.staticobject.logicentities.CircuitObserver;
import dungeonmania.entities.staticobject.logicentities.LogicFloorSwitch;
import dungeonmania.entities.staticobject.staticbomb.SwitchObserver;
import dungeonmania.entities.staticobject.wire.Wire;

import java.util.ArrayList;
import java.util.List;

public class FloorSwitch extends StaticObject implements SwitchSubject, ActivatedEntity {
    private SwitchState activatedState;
    private SwitchState deactivatedState = null;
    private SwitchState currentState;
    private List<SwitchObserver> switchObservers = new ArrayList<SwitchObserver>();
    private List<CircuitObserver> circuitObservers = new ArrayList<CircuitObserver>();
    private List<ActivatedEntity> activatedEntities = new ArrayList<ActivatedEntity>();
    private int tickActivated = 0;

    public FloorSwitch() {
        this.activatedState = new ActivatedState(this);
    }

    public void setDeactivatedState(SwitchState deactivatedState) {
        this.deactivatedState = deactivatedState;
        this.currentState = deactivatedState;
    }

    public boolean isRemoved() {
        return DungeonManiaController.getDungeon().getDungeonObject(getUniqueId()) == null;
    }

    public boolean isMechanicallyActivated() {
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

    public List<ActivatedEntity> listActivatedEntities() {
        return this.activatedEntities;
    }

    public List<CircuitObserver> listCircuitObservers() {
        return this.circuitObservers;
    }

    @Override
    public boolean canAccept(Boulder boulder) {
        return !this.currentState.isSwitchActivated();
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
    public int getActivatedTick() {
        return this.tickActivated;
    }

    @Override
    public void add(ActivatedEntity activatedEntity) {
        this.activatedEntities.add(activatedEntity);
    }

    @Override
    public boolean updateAdjacent(boolean doActivate, ActivatedEntity notifier) {
        // update wires
        this.activatedEntities.stream().filter(o -> o instanceof Wire)
                .forEach(o -> o.updateAdjacent(doActivate, notifier));
        // update logic switches
        this.activatedEntities.stream().filter(o -> o instanceof LogicFloorSwitch)
                .forEach(o -> o.updateAdjacent(doActivate, notifier));
        // update observers
        this.circuitObservers.stream().forEach(o -> o.updateLogic());

        return doActivate;
    }

    @Override
    public boolean isActivated() {
        return this.isMechanicallyActivated();
    }
}
