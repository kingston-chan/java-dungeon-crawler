package dungeonmania.factory.itemfactory;

import java.util.UUID;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.item.potions.InvincibilityPotion;
import dungeonmania.util.Position;

public class InvincibilityPotionBuilder implements ItemBuilder {

    @Override
    public DungeonObject buildItem(Position position, String type, Dungeon dungeon) {
        InvincibilityPotion invincibilityPotion = new InvincibilityPotion();
        invincibilityPotion.setHostBehaviour(null);// some behaviour needed
        invincibilityPotion.setPosition(position);
        invincibilityPotion.setType(type);
        invincibilityPotion.setUniqueId(UUID.randomUUID().toString());

        // TODO Auto-generated method stub
        return invincibilityPotion;
    }
}
