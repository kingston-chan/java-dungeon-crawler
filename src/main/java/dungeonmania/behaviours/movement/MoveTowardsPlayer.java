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
import dungeonmania.util.Position;

import java.util.Queue;

public class MoveTowardsPlayer implements MovementBehaviour {

    @Override
    public void move(NonPlayableActor npa) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Player player = dungeon.getPlayer();

        // using BFS
        Position playerPosition = player.getPosition();
        Queue<Position> queue = new LinkedList<>();
        Map<Position, Position> visited = new HashMap<>(); // adjList
        boolean playerFound = false;

        visited.put(npa.getPosition(), null);
        queue.add(npa.getPosition());

        while (!(queue.isEmpty()) && !(playerFound)) {
            Position curr = queue.poll();

            if (curr.equals(playerPosition)) {
                playerFound = true;
            } else {
                // checks for all adjacent valid positions and adds to the queue and visited
                for (Position pos : curr.getAdjacentCardinalPositions()) {
                    if (!(visited.containsKey(pos))) {
                        // checking whether cardinally adj pos are valid or not
                        List<DungeonObject> occupants = dungeon.getObjectsAtPosition(pos);
                        if (occupants.stream().allMatch(obj -> obj.canAccept(npa))) {
                            visited.put(pos, curr);
                            queue.add(pos);
                        }

                        // Checks for portal aswell
                        try {
                            Portal portal = occupants.stream().filter(o -> o instanceof Portal).map(o -> (Portal) o)
                                    .findFirst().get();
                            Position destination = portal.getDestination();
                            for (Position p : destination.getAdjacentCardinalPositions()) {
                                if (!(visited.containsKey(p))) {
                                    List<DungeonObject> dungeonObjects = dungeon.getObjectsAtPosition(p);
                                    if (dungeonObjects.stream().allMatch(obj -> obj.canAccept(npa))) {
                                        visited.put(p, curr);
                                        queue.add(p);
                                    }
                                }
                            }
                        } catch (Exception e) {
                            continue;
                        }
                    }

                }
            }
        }

        // no path found
        if (!playerFound) {
            return;
        }

        // get the step to the closest path
        if (visited.get(playerPosition).equals(npa.getPosition())) {
            npa.setPosition(playerPosition);
        } else {
            Position next = visited.get(playerPosition);
            while (visited.get(next) != npa.getPosition()) {
                next = visited.get(next);
            }
            npa.setPosition(next);
        }

        // set Position
        dungeon.getObjectsAtPosition(npa.getPosition()).stream().forEach(o -> o.doAccept(npa));
    }

}
