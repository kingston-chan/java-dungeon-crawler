package dungeonmania.factory.actorfactory;

import java.util.UUID;

import org.json.JSONObject;

import dungeonmania.DungeonManiaController;
import dungeonmania.behaviours.movement.MoveRandomly;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.nonplayableactor.ZombieToast;
import dungeonmania.factory.FactoryHelpers;
import dungeonmania.util.Position;

public class ZombieToastBuilder implements ActorBuilder {

    public ZombieToast buildZombie(Position position) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        ZombieToast zombieToast = new ZombieToast();
        zombieToast.setUniqueId(UUID.randomUUID().toString());
        zombieToast.setPosition(position);
        zombieToast.setType("zombie_toast");
        zombieToast.setAttackPoints(dungeon.getIntConfig("zombie_attack"));
        zombieToast.setHealthPoints(dungeon.getIntConfig("zombie_health"));
        zombieToast.setDefaultMovement(new MoveRandomly());
        zombieToast.setCurrentMovement(new MoveRandomly());
        dungeon.addDungeonObject(zombieToast.getUniqueId(), zombieToast);
        return zombieToast;
    }

    @Override
    public void buildActor(JSONObject actor) {
        buildZombie(FactoryHelpers.extractPosition(actor));
    }
}
