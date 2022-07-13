package dungeonmania.behaviours.movement;

import java.util.List;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.util.Position;

public class MoveAwayFromPlayer implements MovementBehaviour {

    @Override
    public void move(NonPlayableActor npa) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Player player = dungeon.getPlayer();
        List<Position> moveablePositions = npa.getPosition().getAdjacentCardinalPositions();

        Position best_position = npa.getPosition();
        for(Position position : moveablePositions){
            List<DungeonObject> objects = dungeon.getObjectsAtPosition(position);
            if (objects.isEmpty() || (objects.stream().allMatch(obj -> obj.canAccept(npa)))) {
                Position best_cmp = Position.calculatePositionBetween(best_position, player.getPosition());
                int cmpBest = best_cmp.getX() + best_cmp.getY();
                // calculate distance between current best one and player's

                Position current_cmp = Position.calculatePositionBetween(position, player.getPosition());
                int cmpCurrent = current_cmp.getX() + current_cmp.getY();
                // calculate distance between current one and player's

                if (cmpBest < cmpCurrent){
                    best_position = position;
                }
            }
        }
        npa.setPosition(best_position);

        // TODO Auto-generated method stub

    }
}
