package dungeonmania.factory.actorfactory;

import java.util.UUID;

import dungeonmania.DungeonManiaController;
import dungeonmania.behaviours.movement.MoveTowardsPlayer;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.nonplayableactor.Mercenary;
import dungeonmania.util.Position;

public class MercenaryBuilder implements ActorBuilder {
    @Override
    public void buildActor(Position position, String type) {
        Dungeon dungeon = DungeonManiaController.getDungeon();

        Mercenary mercenary = new Mercenary();
        mercenary.setUniqueId(UUID.randomUUID().toString());
        mercenary.setPosition(position);
        mercenary.setType(type);
        mercenary.setAttackPoints(dungeon.getConfig("mercenary_attack"));
        mercenary.setHealthPoints(dungeon.getConfig("mercenary_health"));
        mercenary.setDefencePoints(0);
        mercenary.setDefaultMovement(new MoveTowardsPlayer());
        mercenary.setCurrentMovement(new MoveTowardsPlayer());
        dungeon.addDungeonObject(mercenary.getUniqueId(), mercenary);
    }
}
