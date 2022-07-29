package dungeonmania.behaviours.logicalrules;

import java.io.Serializable;

import dungeonmania.entities.staticobject.logicentities.CircuitObserver;

public interface LogicRules extends Serializable {
    public boolean canActivate(CircuitObserver circuitObserver);
}
