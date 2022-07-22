package dungeonmania.factory.itemfactory;

import java.util.UUID;

import org.json.JSONObject;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.item.collectables.treasure.BribableTreasure;
import dungeonmania.entities.item.collectables.treasure.Treasure;
import dungeonmania.factory.FactoryHelpers;

public class BribableTreasureBuilder implements ItemBuilder {

    @Override
    public void buildItem(JSONObject item) {
        Treasure bribableTreasure = new BribableTreasure();
        bribableTreasure.setPosition(FactoryHelpers.extractPosition(item));
        bribableTreasure.setType(FactoryHelpers.extractType(item));
        bribableTreasure.setUniqueId(UUID.randomUUID().toString());
        DungeonManiaController.getDungeon().addDungeonObject(bribableTreasure.getUniqueId(), bribableTreasure);
    }
}
