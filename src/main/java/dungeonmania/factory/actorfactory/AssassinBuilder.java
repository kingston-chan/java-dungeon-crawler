package dungeonmania.factory.actorfactory;

import java.util.UUID;

import org.json.JSONObject;

import dungeonmania.DungeonManiaController;
import dungeonmania.behaviours.movement.MoveTowardsPlayer;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.nonplayableactor.Mercenary;
import dungeonmania.entities.actor.nonplayableactor.MercenaryState.AssassinState;
import dungeonmania.factory.FactoryHelpers;

public class AssassinBuilder implements ActorBuilder {
        @Override
        public void buildActor(JSONObject actor) {
                Dungeon dungeon = DungeonManiaController.getDungeon();
                Mercenary assassin = new Mercenary();
                assassin.setStates(new AssassinState(assassin, dungeon.getIntConfig("assassin_recon_radius")));
                assassin.setUniqueId(UUID.randomUUID().toString());
                assassin.setPosition(FactoryHelpers.extractPosition(actor));
                assassin.setType(FactoryHelpers.extractType(actor));
                assassin.setAttackPoints(dungeon.getIntConfig("assassin_attack"));
                assassin.setHealthPoints(dungeon.getIntConfig("assassin_health"));
                assassin.setDefaultMovement(new MoveTowardsPlayer());
                assassin.setCurrentMovement(new MoveTowardsPlayer());
                dungeon.addDungeonObject(assassin.getUniqueId(), assassin);
        }
}
