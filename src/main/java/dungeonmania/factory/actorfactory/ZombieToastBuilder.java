package dungeonmania.factory.actorfactory;

import java.util.UUID;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.actor.nonplayableactor.ZombieEnemy;
import dungeonmania.util.Position;

public class ZombieToastBuilder implements ActorBuilder {
    @Override
    public DungeonObject buildActor(Position position, String type, Dungeon dungeon) {
        ZombieEnemy zombieToast = new ZombieEnemy();
        zombieToast.setUniqueId(UUID.randomUUID().toString());
        zombieToast.setPosition(position);
        zombieToast.setType(type);
        zombieToast.setAttackPoints(dungeon.getConfig("zombie_attack"));
        zombieToast.setHealthPoints(dungeon.getConfig("zombie_health"));
        zombieToast.setDefencePoints(0);

        // set mercenary host and visitor behaviour
        zombieToast.setVisitorBehaviour(null);
        // need to set observer
        // set default movement
        // set current movement
        dungeon.addDungeonObject(zombieToast.getUniqueId(), zombieToast);
        return zombieToast;
    }
}
