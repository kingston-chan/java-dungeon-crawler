package dungeonmania.factory.actorfactory;

import java.util.UUID;

import dungeonmania.DungeonManiaController;
import dungeonmania.behaviours.movement.MoveRandomly;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.actor.nonplayableactor.ZombieToast;
import dungeonmania.util.Position;

public class ZombieToastBuilder implements ActorBuilder {
    @Override
    public DungeonObject buildActor(Position position, String type) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        ZombieToast zombieToast = new ZombieToast();
        zombieToast.setUniqueId(UUID.randomUUID().toString());
        zombieToast.setPosition(position);
        zombieToast.setType(type);
        zombieToast.setAttackPoints(dungeon.getConfig("zombie_attack"));
        zombieToast.setHealthPoints(dungeon.getConfig("zombie_health"));
        zombieToast.setDefaultMovement(new MoveRandomly());
        zombieToast.setCurrentMovement(new MoveRandomly());
        dungeon.addDungeonObject(zombieToast.getUniqueId(), zombieToast);
        return zombieToast;
    }
}
