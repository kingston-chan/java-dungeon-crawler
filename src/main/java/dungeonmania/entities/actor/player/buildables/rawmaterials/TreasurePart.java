package dungeonmania.entities.actor.player.buildables.rawmaterials;

import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.actor.player.helpers.ItemGetterHelpers;

public class TreasurePart implements rawMaterial{
    private int numRequired;

    public TreasurePart(int numRequired) {
        this.numRequired = numRequired;
    }

    @Override
    public void playerUseForBuild(Player player) {
        ItemGetterHelpers.removeTreasuresFromInventory(numRequired, player);
    }

    @Override
    public boolean playerHas(Player player) {
        return ItemGetterHelpers.getNumTreasure(player) >= numRequired;
    }

}
