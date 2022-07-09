package dungeonmania.entities.actor.player.interactables;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.enemy.Enemy;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.util.Position;

public class MercenaryInteract implements InteractBehaviour {
    @Override
    public boolean interact(Dungeon dungeon, Player player, String interactingWithId) {

        Enemy merc = dungeon.getActiveEnemy(interactingWithId);

        List<Position> inRangePositions = new ArrayList<>();

        int bribeRadius = dungeon.getConfig("bribe_radius");

        int bribeAmount = dungeon.getConfig("bribe_amount");

        int start_x = merc.getPosition().getX() - bribeRadius;
        int start_y = merc.getPosition().getY() - bribeRadius;

        for (int i = start_y; i > start_y - (bribeRadius * 2 + 1); i--) {
            for (int j = start_x; j < bribeRadius * 2 + 1; i++) {
                inRangePositions.add(new Position(j, i));
            }
        }

        if (!inRangePositions.contains(player.getPosition())) {
            return false;
        }

        return player.removeTreasures(bribeAmount);
    }
}
