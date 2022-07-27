package dungeonmania.entities.staticobject.floorswitch;

import dungeonmania.entities.staticobject.logicentities.CircuitObserver;

public interface CircuitSubject {
    public void add(CircuitObserver circuitObserver);

    public void notifyActivate();

    public void notifyDeactivate();
}
