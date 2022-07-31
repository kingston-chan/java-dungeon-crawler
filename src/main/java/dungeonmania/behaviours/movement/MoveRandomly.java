package dungeonmania.behaviours.movement;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
        List<Position> possibleMoves = npa.getPosition().getAdjacentPositions().stream()
                .filter(pos -> dungeon.getObjectsAtPosition(pos).stream().allMatch(o -> o.canAccept(npa)))
                .collect(Collectors.toList());

        if (possibleMoves.isEmpty()) {
            return;
        }

        Random rand = new Random();

        Position randPos = possibleMoves.get(rand.nextInt(possibleMoves.size()));
        Position oldPos = npa.getPosition();
        dungeon.getObjectsAtPosition(randPos).forEach(o -> o.doAccept(npa));

        // for merc, if they teleport
        if (npa.getPosition().equals(oldPos)) {
            npa.setPosition(randPos);
        }

    }
}
