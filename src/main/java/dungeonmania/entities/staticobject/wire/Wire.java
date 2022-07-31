package dungeonmania.entities.staticobject.wire;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.entities.staticobject.floorswitch.ActivatedEntity;
import dungeonmania.entities.staticobject.floorswitch.FloorSwitch;
import dungeonmania.entities.staticobject.logicentities.CircuitObserver;
import dungeonmania.entities.staticobject.logicentities.LogicFloorSwitch;

public class Wire extends StaticObject implements ActivatedEntity {

    private int tickActivated = 0;
    private boolean isActivated = false;
    private List<CircuitObserver> circuitObservers = new ArrayList<CircuitObserver>();
    private List<ActivatedEntity> activatedEntities = new ArrayList<ActivatedEntity>();

    public boolean isActivated() {
        return this.isActivated;
    }

    private boolean isRemoved() {
        return DungeonManiaController.getDungeon().getDungeonObject(getUniqueId()) == null;
    }

    @Override
    public boolean isInteractable() {
        return false;
    }

    @Override
    public void add(CircuitObserver circuitObserver) {
        this.circuitObservers.add(circuitObserver);
    }

    @Override
    public int getActivatedTick() {
        return this.tickActivated;
    }

    @Override
    public void add(ActivatedEntity activatedEntity) {
        this.activatedEntities.add(activatedEntity);
    }

    @Override
    public boolean updateAdjacent(boolean doActivate, ActivatedEntity notifier) {
        if (doActivate == isActivated)
            return doActivate;

        boolean hasAdjacentMechActivatedSwitch = this.activatedEntities.stream()
                .filter(o -> o instanceof FloorSwitch).filter(o -> ((FloorSwitch) o).isMechanicallyActivated())
                .count() >= 1;

        if (!doActivate && hasAdjacentMechActivatedSwitch && !isRemoved()) {
            return true;
            // case when there is still a mechanically activated switch in circuit
            // update wires
        }

        if (!doActivate) {
            List<ActivatedEntity> updatees = this.activatedEntities.stream().filter(a -> !a.equals(notifier))
                    .collect(Collectors.toList());

            isActivated = doActivate;

            // update wires
            boolean stillActive = updatees.stream().filter(o -> o instanceof Wire)
                    .anyMatch(o -> o.updateAdjacent(isActivated, this));

            if (stillActive) {
                // adjacent wire can still be activated
                isActivated = true;
                return true;
            }

            // update logic switches
            stillActive = updatees.stream().filter(o -> o instanceof LogicFloorSwitch)
                    .anyMatch(o -> o.updateAdjacent(isActivated, this));

            if (stillActive) {
                // adjacent switch can still be activated
                isActivated = true;
                return true;
            }

            // update observers
            circuitObservers.stream().forEach(o -> o.updateLogic());

            return isActivated;
        } else {
            // if deactivate need to ensure that there is no more mechanically active switch
            List<ActivatedEntity> updatees = this.activatedEntities.stream().filter(a -> !a.equals(notifier))
                    .collect(Collectors.toList());

            this.tickActivated = DungeonManiaController.getDungeon().getTick();

            isActivated = doActivate;

            // update wires
            updatees.stream().filter(o -> o instanceof Wire)
                    .forEach(o -> o.updateAdjacent(isActivated, this));

            // update logic switches
            updatees.stream().filter(o -> o instanceof LogicFloorSwitch)
                    .forEach(o -> o.updateAdjacent(isActivated, this));

            // update observers
            circuitObservers.stream().forEach(o -> o.updateLogic());

            return isActivated;
        }
    }

}
