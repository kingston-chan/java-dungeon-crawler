package dungeonmania.entities.staticobject.boulder;

import dungeonmania.entities.actor.player.Player;
import dungeonmania.util.Position;

public class BoulderHelper {
    public static Position getBoulderPushedPostion(Boulder boulder, Player player) {
        Position dirPlayerVisitingFrom = Position.calculatePositionBetween(player.getPosition(), boulder.getPosition());

        Position boulderNewPosition = new Position(boulder.getPosition().getX() + dirPlayerVisitingFrom.getX(),
                boulder.getPosition().getY() + dirPlayerVisitingFrom.getY());
        return boulderNewPosition;
    }
}
