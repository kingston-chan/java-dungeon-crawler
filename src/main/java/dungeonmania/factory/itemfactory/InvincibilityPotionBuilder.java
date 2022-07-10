package dungeonmania.factory.itemfactory;

import java.util.UUID;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.item.Potion;
import dungeonmania.util.Position;

public class InvincibilityPotionBuilder implements ItemBuilder {

    @Override
    public DungeonObject buildItem(Position position, String type, Dungeon dungeon, int keyNum) {
        Potion invincibilityPotion = new Potion(
                new VisiblePlayerHost(),
                new VisiblePlayerEnemyHost(),
                new MoveAwayFromPlayer(),
                dungeon.getConfig("invincibility_potion_duration"));
        invincibilityPotion.setHostBehaviour(new ItemHost());
        invincibilityPotion.setPosition(position);
        invincibilityPotion.setType(type);
        invincibilityPotion.setUniqueId(UUID.randomUUID().toString());

        return invincibilityPotion;
    }
}
