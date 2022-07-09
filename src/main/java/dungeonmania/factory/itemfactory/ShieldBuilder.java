package dungeonmania.factory.itemfactory;

import java.util.UUID;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.item.equiments.Shield;
import dungeonmania.util.Position;

public class ShieldBuilder implements ItemBuilder{

    @Override
    public DungeonObject buildItem(Position position, String type, Dungeon dungeon) {
        Shield shield = new Shield();
        shield.setDurability(dungeon.getConfig("shield_durability"));
        shield.setHostBehaviour(null);
        shield.setPosition(position);
        shield.setType(type);
        shield.setUniqueId(UUID.randomUUID().toString());

        return shield;
    }
}
