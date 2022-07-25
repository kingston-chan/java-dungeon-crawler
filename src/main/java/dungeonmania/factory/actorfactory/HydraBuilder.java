package dungeonmania.factory.actorfactory;

import java.util.UUID;

import org.json.JSONObject;

import dungeonmania.DungeonManiaController;
import dungeonmania.behaviours.movement.MoveRandomly;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.nonplayableactor.Hydra;
import dungeonmania.factory.FactoryHelpers;

public class HydraBuilder implements ActorBuilder {

    @Override
    public void buildActor(JSONObject actor) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Hydra hydra = new Hydra();
        hydra.setUniqueId(UUID.randomUUID().toString());
        hydra.setPosition(FactoryHelpers.extractPosition(actor));
        hydra.setType(FactoryHelpers.extractType(actor));
        hydra.setAttackPoints(dungeon.getIntConfig("hydra_attack"));
        hydra.setHealthPoints(dungeon.getIntConfig("hydra_health"));
        hydra.setDefaultMovement(new MoveRandomly());
        hydra.setCurrentMovement(new MoveRandomly());
        dungeon.addDungeonObject(hydra.getUniqueId(), hydra);
    }

}
