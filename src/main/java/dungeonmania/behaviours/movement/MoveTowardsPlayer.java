package dungeonmania.behaviours.movement;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
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
        Map<Position, Position> visited= new HashMap<>();    //adjList
        boolean playerFound = false; 

        visited.put(npa.getPosition(), null);
        queue.add(npa.getPosition());

        while(!(queue.isEmpty()) && !(playerFound)) {
            Position curr = queue.poll();

            // for portals
            //
            // for (DungeonObject obj : dungeon.getObjectsAtPosition(curr)) {
            //     if (obj instanceof Portal) {
            //         obj = (Portal) obj;
            //         curr = obj.getDestination();
            //     }
            // }

            if (curr.equals(playerPosition)) {
                playerFound = true;
            } else {
                // checks for all adjacent valid positions and adds to the queue and visited
                for (Position pos : curr.getAdjacentPositions()) {
                    if (Position.isAdjacent(pos, curr)) {
                        // checking whether adj pos are valid or not
                        //
                        // List<DungeonObject> occupant = dungeon.getObjectsAtPosition(pos);
                        // for (DungeonObject object : occupant) {
                        //      if (!(visited.containsKey(pos)) && object.accept(npa)) {
                        //          visited.put(pos, curr);
                        //          queue.add(pos);
                        //      }
                        // }

                        // List<DungeonObject> occupants = dungeon.getObjectsAtPosition(pos);
                        // if (occupants.stream().allMatch(obj -> obj.canAccept(npa)) && !(visited.containsKey(pos))) {
                        //     visited.put(pos, curr);
                        //     queue.add(pos);
                        // }  
                        
                        // temporary
                        if (!(visited.containsKey(pos))) {
                            visited.put(pos, curr);
                            queue.add(pos);
                        }
                    }
                    
                }
            }
        }
        
        // get the step to the closest path
        Position next = visited.get(playerPosition);
        while (visited.get(next) != null) {
            next = visited.get(next);
        }

        // set Position  
        Position diff = Position.calculatePositionBetween(next, npa.getPosition());
        npa.setPosition(diff);

    }

}
