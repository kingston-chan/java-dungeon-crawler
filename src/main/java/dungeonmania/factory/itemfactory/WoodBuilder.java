package dungeonmania.factory.itemfactory;

import java.util.UUID;

import org.json.JSONObject;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.item.collectables.Wood;
import dungeonmania.factory.FactoryHelpers;

public class WoodBuilder implements ItemBuilder {
    @Override
    public void buildItem(JSONObject item) {
        Wood wood = new Wood();
        wood.setPosition(FactoryHelpers.extractPosition(item));
        wood.setType(FactoryHelpers.extractType(item));
        wood.setUniqueId(UUID.randomUUID().toString());
        DungeonManiaController.getDungeon().addDungeonObject(wood.getUniqueId(), wood);
    }
}