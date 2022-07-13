package dungeonmania.factory.itemfactory;

import java.util.UUID;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.item.collectables.Wood;
import dungeonmania.util.Position;

public class WoodBuilder implements ItemBuilder {

    @Override
    public void buildItem(Position position, String type, int keyNum) {
        Wood wood = new Wood();
        wood.setPosition(position);
        wood.setType(type);
        wood.setUniqueId(UUID.randomUUID().toString());
        DungeonManiaController.getDungeon().addDungeonObject(wood.getUniqueId(), wood);
    }
}