package dungeonmania.entities.item;

import java.util.stream.Stream;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.floorswitch.FloorSwitch;
import dungeonmania.entities.staticobject.staticbomb.StaticBomb;
import dungeonmania.util.BombHelper;
import dungeonmania.util.Position;

public class Bomb extends Item {
    private Stream<DungeonObject> getAdjacentSwitches(Dungeon dungeon, Position currentPosition) {
        return dungeon.getDungeonObjects().stream().filter(dungeonObject -> dungeonObject instanceof FloorSwitch)
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
        staticBomb.setHostBehaviour(new DoesNothingHost);
        getAdjacentSwitches(dungeon, itemBomb.getPosition()).forEach(floorSwtich -> floorSwitch.add(staticBomb));
        return staticBomb;
    }

    @Override
<<<<<<< HEAD
    public boolean playerUse(Player player, Dungeon dungeon) {
        // generate a new "static bomb" in that player's position
        // check static bomb's adjacent at the same time(method should be provided in
        // static bomb class)
        player.removeFromInventory(this);

        if (checkActiveSwitches(dungeon, this.getPosition())) {
            BombHelper.explode(dungeon, this.getPosition());
            return true;
        }
        StaticBomb newStaticBomb = createNewStaticBomb(dungeon, this);
        dungeon.removeDungeonObject(this.getUniqueId());
        dungeon.addDungeonObject(newStaticBomb.getUniqueId(), newStaticBomb);
        return true;
=======
    public boolean provideAttack(Player player) {
        return false;
    }

    @Override
    public boolean provideDefense(Player player) {
        return false;
>>>>>>> dde6ed2eff588145557401c74e82e0ecae700137
    }
}
