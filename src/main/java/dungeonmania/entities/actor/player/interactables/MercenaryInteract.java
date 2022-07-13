package dungeonmania.entities.actor.player.interactables;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.actor.player.helpers.ItemGetterHelpers;
import dungeonmania.util.BoxRadius;
import dungeonmania.util.Position;

public class MercenaryInteract implements InteractBehaviour {
    @Override
    public boolean interact(Dungeon dungeon, Player player, String interactingWithId) {

        DungeonObject merc = dungeon.getDungeonObject(interactingWithId);

        int bribeRadius = dungeon.getConfig("bribe_radius");

        int bribeAmount = dungeon.getConfig("bribe_amount");

        List<Position> inRangePositions = BoxRadius.getBoxRadiusPositions(bribeRadius, merc.getPosition());

        if (!inRangePositions.contains(player.getPosition())) {
            return false;
        }

        if (ItemGetterHelpers.getNumTreasure(player) >= bribeAmount) {
            ItemGetterHelpers.removeTreasuresFromInventory(bribeAmount, player);
            // create and add to player's ally
            return true;
        }

        return false;
    }
}
