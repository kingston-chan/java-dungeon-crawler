package dungeonmania.factory.itemfactory;

import java.util.UUID;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.item.collectables.treasure.SunStone;
import dungeonmania.entities.item.collectables.treasure.Treasure;
import dungeonmania.util.Position;

public class SunStoneBuilder implements ItemBuilder {

    @Override
    public void buildItem(Position position, String type, int keyNum) {
        Treasure sunStone = new SunStone();
        sunStone.setPosition(position);
        sunStone.setType(type);
        sunStone.setUniqueId(UUID.randomUUID().toString());
        DungeonManiaController.getDungeon().addDungeonObject(sunStone.getUniqueId(), sunStone);
    }
}