package dungeonmania.dungeon;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.StaticObject;
import dungeonmania.util.Position;

public class MazeTestHelper {
    public static boolean hasPath(Dungeon dungeon) {
        Player player = dungeon.getPlayer();

        StaticObject exit = dungeon.getStaticObjects().stream().filter(o -> o.getType().equals("exit")).findFirst()
                .get();

        Queue<Position> positions = new LinkedList<>();

        Map<Position, Boolean> visited = new HashMap<>();

        positions.add(player.getPosition());

        while (!positions.isEmpty()) {
            Position curr = positions.poll();

            if (curr.equals(exit.getPosition())) {
                return true;
            }

            for (Position p : curr.getAdjacentCardinalPositions().stream()
                    .filter(p -> dungeon.getObjectsAtPosition(p).stream().allMatch(o -> o.canAccept(player)))
                    .collect(Collectors.toList())) {
                if (!visited.containsKey(p)) {
                    visited.put(p, true);
                    positions.add(p);
                }
            }
        }

        return false;
    }
}
