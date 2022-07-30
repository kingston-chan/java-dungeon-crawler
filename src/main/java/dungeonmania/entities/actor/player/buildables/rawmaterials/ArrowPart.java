package dungeonmania.entities.actor.player.buildables.rawmaterials;

import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.actor.player.helpers.ItemGetterHelpers;

public class ArrowPart implements rawMaterial{
    private int numRequired;

    public ArrowPart(int numRequired) {
        this.numRequired = numRequired;
    }

    @Override
    public void playerUseForBuild(Player player) {
        ItemGetterHelpers.removeArrowsFromInventory(numRequired, player);
    }

    @Override
    public boolean playerHas(Player player) {
        return ItemGetterHelpers.getNumArrows(player) >= numRequired;
    }

}
