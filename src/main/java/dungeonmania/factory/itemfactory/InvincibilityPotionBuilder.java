package dungeonmania.factory.itemfactory;

import java.util.UUID;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.item.Potion;
import dungeonmania.util.Position;

public class InvincibilityPotionBuilder implements ItemBuilder {

    @Override
<<<<<<< HEAD
    public DungeonObject buildItem(Position position, String type, Dungeon dungeon, int keyNum) {
        Potion invincibilityPotion = new Potion(
                new VisiblePlayerHost(),
                new VisiblePlayerEnemyHost(),
                new MoveAwayFromPlayer(),
                dungeon.getConfig("invincibility_potion_duration"));
        invincibilityPotion.setHostBehaviour(new ItemHost());
=======
    public DungeonObject buildItem(Position position, String type, Dungeon dungeon) {
        InvincibilityPotion invincibilityPotion = new InvincibilityPotion();
        invincibilityPotion.setHostBehaviour(null);// some behaviour needed
>>>>>>> dde6ed2eff588145557401c74e82e0ecae700137
        invincibilityPotion.setPosition(position);
        invincibilityPotion.setType(type);
        invincibilityPotion.setUniqueId(UUID.randomUUID().toString());

        return invincibilityPotion;
    }
}
