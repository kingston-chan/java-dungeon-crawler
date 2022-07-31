package dungeonmania.entities.item;

import dungeonmania.DungeonManiaController;
import dungeonmania.behaviours.logicalrules.LogicHelpers;
import dungeonmania.behaviours.logicalrules.LogicRules;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.logicentities.LogicStaticBomb;
import dungeonmania.util.Position;

public class LogicBomb extends Bomb {
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
            doExplode(dungeon, player);
        } else {
            dungeon.removeDungeonObject(this.getUniqueId());
            LogicStaticBomb lsb = setLogicStaticBomb(player.getPosition());
            dungeon.addDungeonObject(lsb.getUniqueId(), lsb);
            // adjacent activated entities should add this
            LogicHelpers.getAdjacentActivatedEntities(player.getPosition()).forEach(a -> a.add(lsb));
        }
        return true;
    }
}
