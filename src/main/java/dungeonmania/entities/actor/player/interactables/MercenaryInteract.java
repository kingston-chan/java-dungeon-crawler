package dungeonmania.entities.actor.player.interactables;

import java.util.List;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.nonplayableactor.Mercenary;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.actor.player.helpers.ItemGetterHelpers;
import dungeonmania.entities.item.Sceptre;
import dungeonmania.util.BoxRadius;
import dungeonmania.util.Position;

public class MercenaryInteract implements InteractBehaviour {

    @Override
    public boolean interact(Player player, String interactingWithId) {
        Dungeon dungeon = DungeonManiaController.getDungeon();

        Mercenary merc = dungeon.getDungeonObjects().stream()
                .filter(dungeonObject -> dungeon.getDungeonObject(interactingWithId).equals(dungeonObject))
                .filter(dungeonObject -> dungeonObject instanceof Mercenary)
                .map(dungeonObject -> ((Mercenary) dungeonObject))
                .findFirst().get();

        if (player.getInventory().stream().anyMatch(item -> item instanceof Sceptre)) {
            Sceptre sceptre = ItemGetterHelpers.getSceptreFromInventory(player);
            sceptre.playerUse(player);
            player.addAlly();
            merc.mindcontrol();
            return true;
        }

        int bribeRadius = dungeon.getIntConfig("bribe_radius");

        List<Position> inRangePositions = BoxRadius.getBoxRadiusPositions(bribeRadius, player.getPosition());

        if (!inRangePositions.contains(merc.getPosition())) {
            return false;
        }

        if (ItemGetterHelpers.getNumBribableTreasure(player) >= merc.getBribeAmount()) {
            ItemGetterHelpers.removeBribableTreasuresFromInventory(merc.getBribeAmount(), player);
            merc.recruitedBy(player);
            return true;
        }

        return false;
    }
}
