package dungeonmania.factory.itemfactory;

import java.util.UUID;

import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.item.potions.InvisibilityPotion;
import dungeonmania.util.Position;

public class InvisibilityPotionBuilder implements ItemBuilder {

    @Override
    public DungeonObject buildItem(Position position, String type, int keyNum) {
        InvisibilityPotion invisibilityPotion = new InvisibilityPotion();
        invisibilityPotion.setPosition(position);
        invisibilityPotion.setType(type);
        invisibilityPotion.setUniqueId(UUID.randomUUID().toString());

        return invisibilityPotion;
    }
}
