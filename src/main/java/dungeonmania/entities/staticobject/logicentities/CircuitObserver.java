package dungeonmania.entities.staticobject.logicentities;

import dungeonmania.util.Position;

public interface CircuitObserver {
    public void updateLogic();

    public Position getPosition();
}
