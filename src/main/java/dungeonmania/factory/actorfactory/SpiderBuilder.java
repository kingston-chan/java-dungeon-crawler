package dungeonmania.factory.actorfactory;

import java.util.UUID;

import dungeonmania.DungeonManiaController;
import dungeonmania.behaviours.movement.SpiderMovement;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.actor.nonplayableactor.Spider;
import dungeonmania.util.Position;

public class SpiderBuilder implements ActorBuilder {
    @Override
    public DungeonObject buildActor(Position position, String type) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Spider spider = new Spider();
        spider.setUniqueId(UUID.randomUUID().toString());
        spider.setPosition(position);
        spider.setType(type);
        spider.setAttackPoints(dungeon.getConfig("spider_attack"));
        spider.setHealthPoints(dungeon.getConfig("spider_health"));
        spider.setDefencePoints(0);
        spider.setDefaultMovement(new SpiderMovement(spider));
        spider.setCurrentMovement(new SpiderMovement(spider));
        dungeon.addDungeonObject(spider.getUniqueId(), spider);
        return spider;
    }
}
