package dungeonmania.entities.staticobject.logicentities;

import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.util.Position;

public class Lightbulb extends StaticObject implements CircuitObserver {

    @Override
    public void updateActivate() {
        // TODO Auto-generated method stub
        // turn on, if not on
    }

    @Override
    public void updateDeactivate() {
        // TODO Auto-generated method stub
        // turn off, if not off
    }

    @Override
    public boolean isInteractable() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Position getCircuitObserverPosition() {
        // TODO Auto-generated method stub
        return getPosition();
    }

}
