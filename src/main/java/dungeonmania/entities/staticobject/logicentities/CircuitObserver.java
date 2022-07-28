package dungeonmania.entities.staticobject.logicentities;

import dungeonmania.util.Position;

public interface CircuitObserver {
    public void updateActivate();

    public void updateDeactivate();

    public Position getCircuitObserverPosition();
}
