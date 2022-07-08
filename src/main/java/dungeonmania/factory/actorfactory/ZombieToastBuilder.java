package dungeonmania.factory.actorfactory;

import java.util.UUID;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.enemy.Enemy;
import dungeonmania.util.Position;

public class ZombieToastBuilder implements ActorBuilder {
    @Override
    public void buildActor(Position position, String type, Dungeon dungeon) {
        Enemy zombieToast = new Enemy();
        zombieToast.setUniqueId(UUID.randomUUID().toString());
        zombieToast.setPosition(position);
        zombieToast.setType(type);
        zombieToast.setAttackPoints(dungeon.getConfig("zombie_attack"));
        zombieToast.setHealthPoints(dungeon.getConfig("zombie_health"));
        zombieToast.setDefencePoints(0);

        // set mercenary host and visitor behaviour
        zombieToast.setHostBehaviour(null);
        zombieToast.setVisitorBehaviour(null);
        // need to set observer
        // set default movement
        // set current movement
        dungeon.addToActiveEnemies(zombieToast);
    }
}
