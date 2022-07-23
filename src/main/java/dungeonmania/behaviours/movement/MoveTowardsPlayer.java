package dungeonmania.behaviours.movement;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.portal.Portal;
import dungeonmania.util.BoxRadius;
import dungeonmania.util.Position;

import java.util.Queue;

public class MoveTowardsPlayer implements MovementBehaviour {
    private final int MAX_SEARCHABLE_RADIUS = 60;

    @Override
    public void move(NonPlayableActor npa) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Player player = dungeon.getPlayer();

        Map<Position, Integer> dist = new HashMap<>();
        Map<Position, Position> prev = new HashMap<>();

        List<Position> searchPositions = BoxRadius.getBoxRadiusPositions(MAX_SEARCHABLE_RADIUS, npa.getPosition());

        for (Position p : searchPositions) {
            dist.put(p, -1);
            prev.put(p, null);
        }

        Queue<Position> moveablePositions = new LinkedList<>();

        moveablePositions.add(npa.getPosition());
        dist.replace(npa.getPosition(), 0);

        while (!moveablePositions.isEmpty()) {
            Position curr = moveablePositions.poll();

            for (Position pos : curr.getAdjacentCardinalPositions()) {
                // checks if in range of searching positions
                if (!dist.containsKey(pos)) {
                    continue;
                }
                List<DungeonObject> posOccupants = dungeon.getObjectsAtPosition(pos);
                if (posOccupants.stream().allMatch(obj -> obj.canAccept(npa))) {
                    int costToAdj = dist.get(curr)
                            + dungeon.getStaticObjectsAtPosition(curr).stream().mapToInt(o -> o.tickCost()).sum()
                            + 1;
                    if (costToAdj < dist.get(pos) || dist.get(pos) == -1) {
                        dist.replace(pos, costToAdj);
                        prev.replace(pos, curr);
                        moveablePositions.add(pos);
                        try {
                            Portal portal = posOccupants.stream().filter(o -> o instanceof Portal).map(o -> (Portal) o)
                                    .findFirst().get();
                            Position destination = portal.getExitPosition(curr);
                            // check if shortest path to portal
                            if (costToAdj < dist.get(destination) || dist.get(destination) == -1) {
                                dist.replace(destination, costToAdj);
                                prev.replace(destination, pos);
                                moveablePositions.add(destination);
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }

        // no path to player
        if (prev.get(player.getPosition()) == null) {
            return;
        }

        Position currPosition = npa.getPosition();

        // get the step to the closest path
        Position newPosition = prev.get(player.getPosition());
        if (newPosition.equals(currPosition)) {
            newPosition = player.getPosition();
        } else {
            while (prev.get(newPosition) != currPosition) {
                newPosition = prev.get(newPosition);
            }
        }
        // visit objects at new position
        dungeon.getObjectsAtPosition(newPosition).stream().forEach(o -> o.doAccept(npa));

        // checks if it took another portal in above for each
        if (npa.getPosition().equals(currPosition)) {
            npa.setPosition(newPosition);
        }
    }
}
