package dungeonmania.factory.itemfactory;

import java.util.UUID;

import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.item.potions.InvincibilityPotion;
import dungeonmania.util.Position;

public class InvincibilityPotionBuilder implements ItemBuilder {

    @Override
    public DungeonObject buildItem(Position position, String type, int keyNum) {
        InvincibilityPotion invincibilityPotion = new InvincibilityPotion();
        invincibilityPotion.setPosition(position);
        invincibilityPotion.setType(type);
        invincibilityPotion.setUniqueId(UUID.randomUUID().toString());

        return invincibilityPotion;
    }
}
