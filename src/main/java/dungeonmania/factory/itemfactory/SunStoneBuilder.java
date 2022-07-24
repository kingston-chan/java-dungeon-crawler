package dungeonmania.factory.itemfactory;

import java.util.UUID;

import org.json.JSONObject;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.item.collectables.treasure.SunStone;
import dungeonmania.entities.item.collectables.treasure.Treasure;
import dungeonmania.factory.FactoryHelpers;

public class SunStoneBuilder implements ItemBuilder {
    @Override
    public void buildItem(JSONObject item) {
        Treasure sunStone = new SunStone();
        sunStone.setPosition(FactoryHelpers.extractPosition(item));
        sunStone.setType(FactoryHelpers.extractType(item));
        sunStone.setUniqueId(UUID.randomUUID().toString());
        DungeonManiaController.getDungeon().addDungeonObject(sunStone.getUniqueId(), sunStone);
    }
}