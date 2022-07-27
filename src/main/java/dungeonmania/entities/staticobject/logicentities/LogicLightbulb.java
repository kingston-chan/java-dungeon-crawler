package dungeonmania.entities.staticobject.logicentities;

import dungeonmania.behaviours.logicalrules.LogicRules;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.util.Position;

public class LogicLightbulb extends StaticObject implements CircuitObserver {
    private LogicRules logicRule;
    private boolean isActive = false;

    public LogicLightbulb(LogicRules logicRule) {
        this.logicRule = logicRule;
    }

    private void updateType() {
        if (isActive) {
            setType("light_bulb_on");
        } else {
            setType("light_bulb_off");
        }
    }

    @Override
    public void updateActivate() {
        isActive = logicRule.canActivate(this);
        updateType();
    }

    @Override
    public void updateDeactivate() {
        isActive = logicRule.canActivate(this);
        updateType();
    }

    @Override
    public Position getCircuitObserverPosition() {
        return getPosition();
    }

    @Override
    public boolean isInteractable() {
        return false;
    }

}
