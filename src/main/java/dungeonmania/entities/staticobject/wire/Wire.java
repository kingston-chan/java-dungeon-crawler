package dungeonmania.entities.staticobject.wire;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.floorswitch.CircuitSubject;
import dungeonmania.entities.staticobject.logicentities.CircuitObserver;

public class Wire extends StaticObject implements CircuitObserver, CircuitSubject {

    private int tickActivated = 0;
    private boolean isActivated = false;
    private List<CircuitObserver> circuitObservers = new ArrayList<CircuitObserver>();

    public void activate() {
        if (!isActivated) {
            this.tickActivated = DungeonManiaController.getDungeon().getTick();
            this.isActivated = true;
        }
    }

    public void deactivate() {
        if (isActivated)
            this.isActivated = false;
    }

    public boolean isActivated() {
        return this.isActivated;
    }

    public int activatedTick() {
        return this.tickActivated;
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
        this.circuitObservers.stream().filter(c -> c instanceof Wire).forEach(w -> w.updateActivate());
        this.circuitObservers.stream().filter(c -> !(c instanceof Wire)).forEach(c -> c.updateActivate());
    }

    @Override
    public void updateActivate() {
        activate();
        notifyActivate();
    }

    @Override
    public void updateDeactivate() {
        deactivate();
        notifyDeactivate();
    }

}
