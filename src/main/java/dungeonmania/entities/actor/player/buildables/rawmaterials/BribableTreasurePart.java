package dungeonmania.entities.actor.player.buildables.rawmaterials;

import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.actor.player.helpers.ItemGetterHelpers;

public class BribableTreasurePart implements rawMaterial{
    private int numRequired;

    public BribableTreasurePart(int numRequired) {
        this.numRequired = numRequired;
    }

    @Override
    public void playerUseForBuild(Player player) {
        ItemGetterHelpers.removeBribableTreasuresFromInventory(numRequired, player);
    }

    @Override
    public boolean playerHas(Player player) {
        return ItemGetterHelpers.getNumBribableTreasure(player) >= numRequired;
    }

}
