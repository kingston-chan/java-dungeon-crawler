package dungeonmania.factory.actorfactory;

import java.util.UUID;

import org.json.JSONObject;

import dungeonmania.DungeonManiaController;
import dungeonmania.behaviours.movement.MoveTowardsPlayer;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.nonplayableactor.Mercenary;
import dungeonmania.entities.actor.nonplayableactor.MercenaryState.EnemyState;
import dungeonmania.factory.FactoryHelpers;

public class MercenaryBuilder implements ActorBuilder {
    @Override
    public void buildActor(JSONObject actor) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Mercenary mercenary = new Mercenary();
        mercenary.setStates(new EnemyState(mercenary));
        mercenary.setUniqueId(UUID.randomUUID().toString());
        mercenary.setPosition(FactoryHelpers.extractPosition(actor));
        mercenary.setType(FactoryHelpers.extractType(actor));
        mercenary.setAttackPoints(dungeon.getIntConfig("mercenary_attack"));
        mercenary.setHealthPoints(dungeon.getIntConfig("mercenary_health"));
        mercenary.setDefaultMovement(new MoveTowardsPlayer());
        mercenary.setCurrentMovement(new MoveTowardsPlayer());
        dungeon.addDungeonObject(mercenary.getUniqueId(), mercenary);
    }

}
