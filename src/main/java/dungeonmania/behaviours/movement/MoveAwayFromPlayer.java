package dungeonmania.behaviours.movement;

import java.util.List;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.util.Position;

public class MoveAwayFromPlayer implements MovementBehaviour {

    @Override
    public void move(NonPlayableActor npa) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Player player = dungeon.getPlayer();
        List<Position> moveablePositions = MovementHelper.getMovableAdjacentPositions(npa);

        if (moveablePositions.isEmpty()) {
            return;
        }

        Position best_position = moveablePositions.get(0);
        for (Position position : moveablePositions) {
            Position best_cmp = Position.calculatePositionBetween(best_position, player.getPosition());
            int cmpBest = Math.abs(best_cmp.getX()) + Math.abs(best_cmp.getY());
            // calculate distance between current best one and player's

            Position current_cmp = Position.calculatePositionBetween(position, player.getPosition());
            int cmpCurrent = Math.abs(current_cmp.getX()) + Math.abs(current_cmp.getY());
            // calculate distance between current one and player's

            if (cmpBest < cmpCurrent) {
                best_position = position;
            }
        }

        Position oldPos = npa.getPosition();

        dungeon.getObjectsAtPosition(best_position).forEach(o -> o.doAccept(npa));

        // for merc, if they teleport
        if (oldPos.equals(npa.getPosition())) {
            npa.setPosition(best_position);
        }
    }
}
