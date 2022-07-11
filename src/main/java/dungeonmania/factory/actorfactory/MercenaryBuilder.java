package dungeonmania.factory.actorfactory;

import java.util.UUID;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.actor.nonplayableactor.Enemy;
import dungeonmania.entities.actor.nonplayableactor.MercenaryEnemy;
import dungeonmania.util.Position;

public class MercenaryBuilder implements ActorBuilder {
    @Override
    public DungeonObject buildActor(Position position, String type, Dungeon dungeon) {
        Enemy mercenary = new MercenaryEnemy();
        mercenary.setUniqueId(UUID.randomUUID().toString());
        mercenary.setPosition(position);
        mercenary.setType(type);
        mercenary.setAttackPoints(dungeon.getConfig("mercenary_attack"));
        mercenary.setHealthPoints(dungeon.getConfig("mercenary_health"));
        mercenary.setDefencePoints(0);

        // set mercenary host and visitor behaviour
        mercenary.setHostBehaviour(null);
        mercenary.setVisitorBehaviour(null);
        // need to set observer
        // set default movement
        // set current movement
        dungeon.addDungeonObject(mercenary.getUniqueId(), mercenary);
        return mercenary;
    }
}
