package dungeonmania.behaviours.movement;

import java.util.List;
import java.util.Random;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.util.Position;

public class MoveRandomly implements MovementBehaviour {

    // actor should be able to move randomly (cardinal and diagonal) by one space

    @Override
    public void move(NonPlayableActor npa) { 
        Dungeon dungeon = DungeonManiaController.getDungeon();
        
        List<Position> possibleMoves = npa.getPosition().getAdjacentPositions();

        Random rand = new Random();

        int index = rand.nextInt(possibleMoves.size());
        Position nextMove = possibleMoves.get(index);
        // checking whether can move to chosen position
        while (!(dungeon.getObjectsAtPosition(nextMove).stream().allMatch(obj -> obj.canAccept(npa)))) {
            possibleMoves.remove(index);
            index = rand.nextInt(possibleMoves.size());
            nextMove = possibleMoves.get(index);
        }

        npa.setPosition(nextMove);

    }
}
