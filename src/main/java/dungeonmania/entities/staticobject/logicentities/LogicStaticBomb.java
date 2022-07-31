package dungeonmania.entities.staticobject.logicentities;

import dungeonmania.DungeonManiaController;
import dungeonmania.behaviours.logicalrules.LogicRules;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.util.BombHelper;

public class LogicStaticBomb extends StaticObject implements CircuitObserver {
    private LogicRules logicRules;

    public LogicStaticBomb(LogicRules logicRules) {
        this.logicRules = logicRules;
    }

    @Override
    public boolean isInteractable() {
        return false;
    }

    @Override
    public void updateLogic() {
        if (logicRules.canActivate(this))
            BombHelper.explode(DungeonManiaController.getDungeon(), getPosition());
    }

}
