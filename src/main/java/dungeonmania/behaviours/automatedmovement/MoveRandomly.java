package dungeonmania.behaviours.automatedmovement;

import java.util.List;

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

        int index = (int) Math.random() * (0 - (possibleMoves.size()-1));
        Position nextMove = possibleMoves.get(index);
        // checking whether can move to chosen position
        // while (!(dungeon.getObjectsAtPosition(nextMove).stream().allMatch(obj -> obj.canAccept(npa)))) {
        //     possibleMoves.remove(index);
        //     index = (int) Math.random() * (0 - (possibleMoves.size()-1));
        //     nextMove = possibleMoves.get(index);
        // }

        npa.setPosition(nextMove);

    }

    public Position random() {
        int x = (int) Math.random() * (1 - -1);
        int y = (int) Math.random() * (1 - -1);
        return new Position(x, y);
    }
}
