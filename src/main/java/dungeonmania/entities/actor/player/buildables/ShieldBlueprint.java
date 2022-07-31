package dungeonmania.entities.actor.player.buildables;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.actor.player.buildables.shieldparts.ShieldPartA;
import dungeonmania.entities.actor.player.buildables.shieldparts.ShieldPratB;
import dungeonmania.entities.item.Item;
import dungeonmania.entities.item.equipment.Shield;

public class ShieldBlueprint implements BuildableBlueprint {
    private List<BuildableBlueprint> parts = new ArrayList<>();
    private List<BuildableBlueprint> AvailableForBuild = new ArrayList<>();
    private static final String ITEM_TYPE = "shield";

    public ShieldBlueprint() {
        parts.add(new ShieldPartA());
        parts.add(new ShieldPratB());
    }

    private Item createNewShield() {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Shield shield = new Shield(
                dungeon.getIntConfig("shield_defence"),
                dungeon.getIntConfig("shield_durability"));
        shield.setPosition(null);
        shield.setType(ITEM_TYPE);
        shield.setUniqueId(UUID.randomUUID().toString());

        return shield;
    }

    @Override
    public boolean canPlayerBuild(Player player) {
        parts.stream().filter(part -> part.canPlayerBuild(player))
                    .forEach(part -> AvailableForBuild.add(part));
        return !AvailableForBuild.isEmpty();
    }

    @Override
    public void playerBuild(Player player) {
        AvailableForBuild.get(0).playerBuild(player);
        player.addToInventory(createNewShield());
    }

}
