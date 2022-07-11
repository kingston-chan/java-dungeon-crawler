package dungeonmania.factory.actorfactory;

import java.util.UUID;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.actor.nonplayableactor.Mercenary;
import dungeonmania.util.Position;

public class MercenaryBuilder implements ActorBuilder {
    @Override
    public DungeonObject buildActor(Position position, String type, Dungeon dungeon) {
        Mercenary mercenary = new Mercenary();
        mercenary.setUniqueId(UUID.randomUUID().toString());
        mercenary.setPosition(position);
        mercenary.setType(type);
        mercenary.setAttackPoints(dungeon.getConfig("mercenary_attack"));
        mercenary.setHealthPoints(dungeon.getConfig("mercenary_health"));
        mercenary.setDefencePoints(0);
        // set current movement
        dungeon.addDungeonObject(mercenary.getUniqueId(), mercenary);
        return mercenary;
    }
}
