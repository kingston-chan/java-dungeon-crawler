package dungeonmania.factory.itemfactory;

import java.util.UUID;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.item.collectables.treasure.BribableTreasure;
import dungeonmania.entities.item.collectables.treasure.Treasure;
import dungeonmania.util.Position;

public class BribableTreasureBuilder implements ItemBuilder {

    @Override
    public void buildItem(Position position, String type, int keyNum) {
        Treasure bribableTreasure = new BribableTreasure();
        bribableTreasure.setPosition(position);
        bribableTreasure.setType(type);
        bribableTreasure.setUniqueId(UUID.randomUUID().toString());
        DungeonManiaController.getDungeon().addDungeonObject(bribableTreasure.getUniqueId(), bribableTreasure);
    }
}
