package dungeonmania.entities.staticobject.logicentities;

import java.io.Serializable;

import dungeonmania.util.Position;

public interface CircuitObserver extends Serializable {
    public void updateActivate();

    public void updateDeactivate();

    public Position getCircuitObserverPosition();
}
