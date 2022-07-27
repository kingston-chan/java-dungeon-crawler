package dungeonmania.entities.staticobject.floorswitch;

import java.io.Serializable;

import dungeonmania.entities.staticobject.logicentities.CircuitObserver;

public interface CircuitSubject extends Serializable {
    public void add(CircuitObserver circuitObserver);

    public void notifyActivate();

    public void notifyDeactivate();
}
