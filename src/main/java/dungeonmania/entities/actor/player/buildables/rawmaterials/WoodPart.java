package dungeonmania.entities.actor.player.buildables.rawmaterials;

import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.actor.player.helpers.ItemGetterHelpers;

public class WoodPart implements rawMaterial{
    private int numRequired;

    public WoodPart(int numRequired) {
        this.numRequired = numRequired;
    }

    @Override
    public void playerUseForBuild(Player player) {
        ItemGetterHelpers.removeWoodFromInventory(numRequired, player);
    }

    @Override
    public boolean playerHas(Player player) {
        return ItemGetterHelpers.getNumWood(player) >= numRequired;
    }
}
