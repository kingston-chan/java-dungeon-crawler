package dungeonmania.factory.itemfactory;

import java.util.UUID;

import org.json.JSONObject;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.item.equipment.Sword;
import dungeonmania.factory.FactoryHelpers;

public class SwordBuilder implements ItemBuilder {
    @Override
    public void buildItem(JSONObject item) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Sword sword = new Sword(dungeon.getIntConfig("sword_attack"), dungeon.getIntConfig("sword_durability"));
        sword.setPosition(FactoryHelpers.extractPosition(item));
        sword.setType(FactoryHelpers.extractType(item));
        sword.setUniqueId(UUID.randomUUID().toString());
        dungeon.addDungeonObject(sword.getUniqueId(), sword);
    }

}
