package dungeonmania.entities.item;

import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.floorswitch.FloorSwitch;
import dungeonmania.entities.staticobject.staticbomb.StaticBomb;
import dungeonmania.util.BombHelper;
import dungeonmania.util.Position;

public class Bomb extends Item {
    private List<FloorSwitch> getAdjacentSwitches(Dungeon dungeon, Position currentPosition) {
        return dungeon.getDungeonObjects().stream().filter(dungeonObject -> dungeonObject instanceof FloorSwitch)
                .map(floorSwitch -> (FloorSwitch) floorSwitch)
                .filter(floorSwitch -> Position.isAdjacent(currentPosition, floorSwitch.getPosition()))
                .collect(Collectors.toList());
    }

    private boolean checkActiveSwitches(Dungeon dungeon, Position currentPosition) {
        return getAdjacentSwitches(dungeon, currentPosition).stream().anyMatch(FloorSwitch::isActivated);
    }

    private StaticBomb createNewStaticBomb(Dungeon dungeon, Bomb itemBomb, Position currentPosition) {
        StaticBomb staticBomb = new StaticBomb();
        staticBomb.setPosition(currentPosition);
        staticBomb.setType(itemBomb.getType());
        staticBomb.setUniqueId(itemBomb.getUniqueId());
        getAdjacentSwitches(dungeon, itemBomb.getPosition()).stream()
                .forEach(floorSwitch -> floorSwitch.add(staticBomb));
        return staticBomb;
    }

    @Override
    public boolean playerUse(Player player) {
        Dungeon dungeon = DungeonManiaController.getDungeon();

        player.removeFromInventory(this);

        if (checkActiveSwitches(dungeon, player.getPosition())) {
            BombHelper.explode(dungeon, player.getPosition());
            return true;
        }
        StaticBomb newStaticBomb = createNewStaticBomb(dungeon, this, player.getPosition());
        dungeon.removeDungeonObject(this.getUniqueId());
        dungeon.addDungeonObject(newStaticBomb.getUniqueId(), newStaticBomb);
        return true;
    }

    @Override
    public void doAccept(Player player) {
        player.visit(this);
    }
}
