package dungeonmania.entities.staticobject.boulder;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.util.Position;

public class BoulderHelper {
    public static Position getBoulderPushedPostion(Boulder boulder, Player player) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Position dirPlayerVisitingFrom = Position.calculatePositionBetween(boulder.getPosition(), player.getPosition());
        Position boulderNewPosition = new Position(boulder.getPosition().getX() - dirPlayerVisitingFrom.getX(),
                boulder.getPosition().getY() - dirPlayerVisitingFrom.getY());
        return boulderNewPosition;
    }
}
