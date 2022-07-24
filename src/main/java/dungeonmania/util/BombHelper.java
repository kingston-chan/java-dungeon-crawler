package dungeonmania.util;

import java.util.List;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;

public class BombHelper {
    public static void explode(Dungeon dungeon, Position currentPosition) {
        int radius = dungeon.getIntConfig("bomb_radius");

        List<Position> explosionRadius = BoxRadius.getBoxRadiusPositions(radius, currentPosition);

        dungeon.getDungeonObjects().stream()
                .filter(dungeonObject -> explosionRadius.stream()
                        .anyMatch(explosionPosition -> explosionPosition.equals(dungeonObject.getPosition())))
                .filter(dungeonObject -> !(dungeonObject instanceof Player))
                .forEach(dungeonObject -> dungeon.removeDungeonObject(dungeonObject.getUniqueId()));

    }
}
