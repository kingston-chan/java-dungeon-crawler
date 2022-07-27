package dungeonmania.entities.staticobject.logicentities;

import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.util.Position;

public class LogicStaticBomb extends StaticObject implements CircuitObserver {

    @Override
    public void updateActivate() {
        // if behaviour is true explode
    }

    @Override
    public void updateDeactivate() {
        // do nothing
    }

    @Override
    public boolean isInteractable() {
        return false;
    }

    @Override
    public Position getCircuitObserverPosition() {
        return getPosition();
    }

}
