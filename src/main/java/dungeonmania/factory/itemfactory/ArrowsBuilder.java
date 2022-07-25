package dungeonmania.factory.itemfactory;

import java.util.UUID;

import org.json.JSONObject;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.item.collectables.Arrows;
import dungeonmania.factory.FactoryHelpers;

public class ArrowsBuilder implements ItemBuilder {
    @Override
    public void buildItem(JSONObject item) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Arrows arrows = new Arrows();
        arrows.setPosition(FactoryHelpers.extractPosition(item));
        arrows.setType(FactoryHelpers.extractType(item));
        arrows.setUniqueId(UUID.randomUUID().toString());
        dungeon.addDungeonObject(arrows.getUniqueId(), arrows);
    }
}
