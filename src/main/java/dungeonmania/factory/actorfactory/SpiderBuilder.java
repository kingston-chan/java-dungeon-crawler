package dungeonmania.factory.actorfactory;

import java.util.UUID;

import org.json.JSONObject;

import dungeonmania.DungeonManiaController;
import dungeonmania.behaviours.movement.SpiderMovement;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.nonplayableactor.Spider;
import dungeonmania.factory.FactoryHelpers;

public class SpiderBuilder implements ActorBuilder {

    @Override
    public void buildActor(JSONObject actor) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Spider spider = new Spider();
        spider.setUniqueId(UUID.randomUUID().toString());
        spider.setPosition(FactoryHelpers.extractPosition(actor));
        spider.setType(FactoryHelpers.extractType(actor));
        spider.setAttackPoints(dungeon.getIntConfig("spider_attack"));
        spider.setHealthPoints(dungeon.getIntConfig("spider_health"));
        spider.setDefaultMovement(new SpiderMovement(spider.getPosition()));
        spider.setCurrentMovement(new SpiderMovement(spider.getPosition()));
        dungeon.addDungeonObject(spider.getUniqueId(), spider);
    }
}
