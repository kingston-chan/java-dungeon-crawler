package dungeonmania.factory.actorfactory;

import java.util.UUID;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.actor.nonplayableactor.SpiderEnemy;
import dungeonmania.util.Position;

public class SpiderBuilder implements ActorBuilder {
    @Override
    public DungeonObject buildActor(Position position, String type, Dungeon dungeon) {
        SpiderEnemy spider = new SpiderEnemy();
        spider.setUniqueId(UUID.randomUUID().toString());
        spider.setPosition(position);
        spider.setType(type);
        spider.setAttackPoints(dungeon.getConfig("spider_attack"));
        spider.setHealthPoints(dungeon.getConfig("spider_health"));
        spider.setDefencePoints(0);

        // set spider host and visitor behaviour
        spider.setHostBehaviour(null);
        spider.setVisitorBehaviour(null);
        // need to set observer
        // set default movement
        // set current movement
        dungeon.addDungeonObject(spider.getUniqueId(), spider);
        return spider;
    }
}
