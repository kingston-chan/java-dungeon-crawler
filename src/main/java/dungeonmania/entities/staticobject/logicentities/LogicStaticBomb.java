package dungeonmania.entities.staticobject.logicentities;

import dungeonmania.entities.staticobject.StaticObject;

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

}
