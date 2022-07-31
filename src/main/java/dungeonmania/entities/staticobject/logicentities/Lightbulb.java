package dungeonmania.entities.staticobject.logicentities;

import dungeonmania.behaviours.logicalrules.LogicRules;
import dungeonmania.entities.staticobject.StaticObject;

public class Lightbulb extends StaticObject implements CircuitObserver {
    private LogicRules logicRule;
    private boolean isActive = false;

    public Lightbulb(LogicRules logicRule) {
        this.logicRule = logicRule;
    }

    @Override
    public boolean isInteractable() {
        return false;
    }

    @Override
    public void updateLogic() {
        isActive = logicRule.canActivate(this);
        if (isActive) {
            setType("light_bulb_on");
        } else {
            setType("light_bulb_off");
        }
    }

}
