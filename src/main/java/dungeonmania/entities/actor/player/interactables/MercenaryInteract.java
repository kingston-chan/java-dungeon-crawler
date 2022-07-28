package dungeonmania.entities.actor.player.interactables;

import java.util.List;

import dungeonmania.DungeonManiaController;
import dungeonmania.behaviours.movement.FollowPlayer;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
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

        DungeonObject merc = dungeon.getDungeonObject(interactingWithId);

        if (player.getInventory().stream().anyMatch(item -> item instanceof Sceptre)) {
            Sceptre sceptre = ItemGetterHelpers.getSceptreFromInventory(player);
            sceptre.playerUse(player);
            player.addAlly();
            dungeon.getDungeonObjects().stream()
                    .filter(dungeonObject -> dungeonObject.equals(merc))
                    .filter(dungeonObject -> dungeonObject instanceof Mercenary)
                    .forEach(dungeonObject -> ((Mercenary) dungeonObject).mindcontrol());

            return true;
        }

        int bribeRadius = dungeon.getIntConfig("bribe_radius");

        List<Position> inRangePositions = BoxRadius.getBoxRadiusPositions(bribeRadius, merc.getPosition());

        if (!inRangePositions.contains(player.getPosition())) {
            return false;
        }

        int bribeAmount = dungeon.getDungeonObjects().stream()
                .filter(dungeonObject -> dungeonObject.equals(merc))
                .filter(dungeonObject -> dungeonObject instanceof Mercenary)
                .map(dungeonObject -> ((Mercenary) dungeonObject).getBribeAmount())
                .findFirst().get();

        if (ItemGetterHelpers.getNumBribableTreasure(player) >= bribeAmount) {
<<<<<<< HEAD
            ItemGetterHelpers.removeTreasuresFromInventory(bribeAmount, player);
=======
            ItemGetterHelpers.removeBribableTreasuresFromInventory(bribeAmount, player);
>>>>>>> e5ce0254cca0a742955f4177de29731bb2367a9a
            // mercenary is now in ally state
            dungeon.getDungeonObjects().stream()
                    .filter(dungeonObject -> dungeonObject.equals(merc))
                    .filter(dungeonObject -> dungeonObject instanceof Mercenary)
                    .forEach(dungeonObject -> ((Mercenary) dungeonObject).recruitedBy(player));
            return true;
        }

        return false;
    }
}
