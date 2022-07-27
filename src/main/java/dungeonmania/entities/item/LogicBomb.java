package dungeonmania.entities.item;

import dungeonmania.DungeonManiaController;
import dungeonmania.behaviours.logicalrules.LogicRules;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.logicentities.CircuitObserver;
import dungeonmania.entities.staticobject.logicentities.LogicStaticBomb;
import dungeonmania.util.BombHelper;
import dungeonmania.util.Position;

public class LogicBomb extends Bomb implements CircuitObserver {
    // logic behaviour
    private LogicRules logicRules;

    public LogicBomb(LogicRules logicRules) {
        this.logicRules = logicRules;
    }

    private LogicStaticBomb setLogicStaticBomb(Position currentPos) {
        LogicStaticBomb lsb = new LogicStaticBomb(logicRules);
        lsb.setPosition(currentPos);
        lsb.setType(this.getType());
        lsb.setUniqueId(this.getUniqueId());
        return lsb;
    }

    @Override
    public boolean playerUse(Player player) {
        // check if logic behaviour rule returns true, if true explode
        // set new logic static bomb
        Dungeon dungeon = DungeonManiaController.getDungeon();
        player.removeFromInventory(this);

        if (logicRules.canActivate(this)) {
            BombHelper.explode(dungeon, player.getPosition());
        } else {
            dungeon.removeDungeonObject(this.getUniqueId());
            LogicStaticBomb lsb = setLogicStaticBomb(player.getPosition());
            dungeon.addDungeonObject(lsb.getUniqueId(), lsb);
        }
        return true;
    }

    @Override
    public void updateActivate() {
    }

    @Override
    public void updateDeactivate() {
    }

    @Override
    public Position getCircuitObserverPosition() {
        return getPosition();
    }
}
