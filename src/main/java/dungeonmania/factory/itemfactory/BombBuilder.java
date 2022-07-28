package dungeonmania.factory.itemfactory;

import java.util.UUID;

import org.json.JSONObject;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.item.Bomb;
import dungeonmania.entities.item.LogicBomb;
import dungeonmania.factory.FactoryHelpers;
import dungeonmania.factory.LogicFactory;

public class BombBuilder implements ItemBuilder {
    @Override
    public void buildItem(JSONObject item) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Bomb bomb;
        try {
            String logic = FactoryHelpers.extractLogic(item);
            bomb = new LogicBomb(LogicFactory.getLogicType(logic));
        } catch (Exception e) {
            bomb = new Bomb();
        }
        bomb.setPosition(FactoryHelpers.extractPosition(item));
        bomb.setType(FactoryHelpers.extractType(item));
        bomb.setUniqueId(UUID.randomUUID().toString());
        dungeon.addDungeonObject(bomb.getUniqueId(), bomb);
    }
}
