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

        // collect a list of moves to adjacent positions that is valid
        List<Position> possibleMoves = MovementHelper.getMovableAdjacentPositions(npa);

        if (possibleMoves.isEmpty()) {
            return;
        }

        Random rand = new Random();

        Position randPos = possibleMoves.get(rand.nextInt(possibleMoves.size()));
        npa.setPosition(randPos);
        dungeon.getObjectsAtPosition(randPos).forEach(o -> o.doAccept(npa));
    }
}
