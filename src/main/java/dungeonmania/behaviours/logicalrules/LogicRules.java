package dungeonmania.behaviours.logicalrules;

<<<<<<< HEAD
import dungeonmania.entities.staticobject.logicentities.CircuitObserver;

public interface LogicRules {
=======
import java.io.Serializable;

import dungeonmania.entities.staticobject.logicentities.CircuitObserver;

public interface LogicRules extends Serializable {
>>>>>>> cd89a8b7c79ed54d850c07bc3236c602de069dac
    public boolean canActivate(CircuitObserver circuitObserver);
}
