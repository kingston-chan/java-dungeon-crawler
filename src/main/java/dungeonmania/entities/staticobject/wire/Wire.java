package dungeonmania.entities.staticobject.wire;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.DungeonManiaController;
import dungeonmania.behaviours.logicalrules.LogicHelpers;
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

    public int activatedTick() {
        return this.tickActivated;
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
    public void updateAdjacent(boolean doActivate) {
        if (doActivate == isActivated)
            return;

        boolean hasAdjacentMechActivatedSwitch = LogicHelpers.getAdjacentActivatedEntities(getPosition()).stream()
                .filter(o -> o instanceof FloorSwitch).filter(o -> ((FloorSwitch) o).isActivated()).count() >= 1;

        if (!doActivate && hasAdjacentMechActivatedSwitch) {
            // update wires
            activatedEntities.stream().filter(o -> o instanceof Wire).forEach(o -> o.updateAdjacent(true));
            // update logic switches
            activatedEntities.stream().filter(o -> o instanceof LogicFloorSwitch)
                    .forEach(o -> o.updateAdjacent(true));
            // update observers
            circuitObservers.stream().forEach(o -> o.updateLogic());
        }

        isActivated = doActivate;

        if (isActivated) {
            this.tickActivated = DungeonManiaController.getDungeon().getTick();
        }

        // update wires
        activatedEntities.stream().filter(o -> o instanceof Wire).forEach(o -> o.updateAdjacent(isActivated));
        // update logic switches
        activatedEntities.stream().filter(o -> o instanceof LogicFloorSwitch)
                .forEach(o -> o.updateAdjacent(isActivated));
        // update observers
        circuitObservers.stream().forEach(o -> o.updateLogic());
    }

}
