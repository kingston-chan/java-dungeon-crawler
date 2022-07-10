package dungeonmania.factory.itemfactory;

import java.util.UUID;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.item.potions.InvisibilityPotion;
import dungeonmania.util.Position;

public class InvisibilityPotionBuilder implements ItemBuilder{

    @Override
    public DungeonObject buildItem(Position position, String type, Dungeon dungeon) {
        InvisibilityPotion invisibilityPotion = new InvisibilityPotion();// some behaviour needed
        invisibilityPotion.setHostBehaviour(null);
        invisibilityPotion.setPosition(position);
        invisibilityPotion.setType(type);
        invisibilityPotion.setUniqueId(UUID.randomUUID().toString());

        // TODO Auto-generated method stub
        return invisibilityPotion;
    }
}
