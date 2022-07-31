package dungeonmania.entities.staticobject.floorswitch;

import dungeonmania.entities.staticobject.logicentities.CircuitObserver;

public interface ActivatedEntity {
    public void add(CircuitObserver circuitObserver);

    public void add(ActivatedEntity activatedEntity);

    public boolean updateAdjacent(boolean doActivate, ActivatedEntity notifier);

    public int getActivatedTick();

    public boolean isActivated();
}
