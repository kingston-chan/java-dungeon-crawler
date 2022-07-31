package dungeonmania.entities.actor.player.buildables;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.actor.player.buildables.bowparts.BowPartA;
import dungeonmania.entities.item.Item;
import dungeonmania.entities.item.equipment.Bow;

public class BowBlueprint implements BuildableBlueprint {
    private List<BuildableBlueprint> parts = new ArrayList<>();
    private List<BuildableBlueprint> AvailableForBuild = new ArrayList<>();
    private static final String ITEM_TYPE = "bow";

    public BowBlueprint() {
        parts.add(new BowPartA());
    }

    private Item createNewBow() {
        Bow bow = new Bow(
                2,
                DungeonManiaController.getDungeon().getIntConfig("bow_durability"));
        bow.setUniqueId(UUID.randomUUID().toString());
        bow.setPosition(null);
        bow.setType(ITEM_TYPE);
        return bow;
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
        player.addToInventory(createNewBow());
    }
}
