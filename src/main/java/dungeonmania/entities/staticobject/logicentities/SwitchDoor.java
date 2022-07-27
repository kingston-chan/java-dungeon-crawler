package dungeonmania.entities.staticobject.logicentities;

import dungeonmania.entities.staticobject.door.Door;
import dungeonmania.util.Position;

public class SwitchDoor extends Door implements CircuitObserver {

    public SwitchDoor(int key) {
        super(key);
    }

    @Override
    public void updateActivate() {
        // if logic rule true and not already open, set to open
    }

    @Override
    public void updateDeactivate() {
        // if logic rule true and not logic opened (i.e. player opened with key), set to
        // closed
    }

    @Override
    public Position getCircuitObserverPosition() {
        // TODO Auto-generated method stub
        return getPosition();
    }

}
