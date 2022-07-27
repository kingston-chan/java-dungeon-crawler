package dungeonmania.entities.staticobject.logicentities;

import dungeonmania.DungeonManiaController;
import dungeonmania.behaviours.logicalrules.LogicRules;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.util.BombHelper;
import dungeonmania.util.Position;

public class LogicStaticBomb extends StaticObject implements CircuitObserver {
    private LogicRules logicRules;

    public LogicStaticBomb(LogicRules logicRules) {
        this.logicRules = logicRules;
    }

    @Override
    public void updateActivate() {
        // if behaviour is true explode
        if (logicRules.canActivate(this))
            BombHelper.explode(DungeonManiaController.getDungeon(), getPosition());
    }

    @Override
    public void updateDeactivate() {
        // do nothing
    }

    @Override
    public boolean isInteractable() {
        return false;
    }

    @Override
    public Position getCircuitObserverPosition() {
        return getPosition();
    }

}
