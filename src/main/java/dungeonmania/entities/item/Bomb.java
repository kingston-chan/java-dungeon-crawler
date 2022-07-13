package dungeonmania.entities.item;

import java.util.stream.Stream;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.floorswitch.FloorSwitch;
import dungeonmania.entities.staticobject.staticbomb.StaticBomb;
import dungeonmania.util.BombHelper;
import dungeonmania.util.Position;

public class Bomb extends Item {
    private Stream<FloorSwitch> getAdjacentSwitches(Dungeon dungeon, Position currentPosition) {
        return dungeon.getDungeonObjects().stream().filter(dungeonObject -> dungeonObject instanceof FloorSwitch)
                .map(floorSwitch -> (FloorSwitch) floorSwitch)
                .filter(floorSwitch -> Position.isAdjacent(currentPosition, floorSwitch.getPosition()));
    }

    private boolean checkActiveSwitches(Dungeon dungeon, Position currentPosition) {
        return getAdjacentSwitches(dungeon, currentPosition).anyMatch(FloorSwitch::isActivated);
    }

    private StaticBomb createNewStaticBomb(Dungeon dungeon, Bomb itemBomb) {
        StaticBomb staticBomb = new StaticBomb();
        staticBomb.setPosition(itemBomb.getPosition());
        staticBomb.setType(itemBomb.getType());
        staticBomb.setUniqueId(itemBomb.getUniqueId());
        getAdjacentSwitches(dungeon, itemBomb.getPosition()).forEach(floorSwitch -> floorSwitch.add(staticBomb));
        return staticBomb;
    }

    @Override
    public boolean playerUse(Player player) {
        Dungeon dungeon = DungeonManiaController.getDungeon();

        player.removeFromInventory(this);

        if (checkActiveSwitches(dungeon, this.getPosition())) {
            BombHelper.explode(dungeon, this.getPosition());
            return true;
        }
        StaticBomb newStaticBomb = createNewStaticBomb(dungeon, this);
        dungeon.removeDungeonObject(this.getUniqueId());
        dungeon.addDungeonObject(newStaticBomb.getUniqueId(), newStaticBomb);
        return true;
    }
}
