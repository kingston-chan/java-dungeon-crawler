package dungeonmania.factory.itemfactory;

import java.util.UUID;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.item.equiments.Sword;
import dungeonmania.util.Position;

public class SwordBuilder implements ItemBuilder {

    @Override
    public DungeonObject buildItem(Position position, String type, Dungeon dungeon) {
        Sword sword = new Sword();
        sword.setDurability(dungeon.getConfig("sword_durability"));
        sword.setPosition(position);
        sword.setType(type);
        sword.setUniqueId(UUID.randomUUID().toString());

        // TODO Auto-generated method stub
        return sword;
    }

}