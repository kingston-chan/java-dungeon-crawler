package dungeonmania.behaviours.movement;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.DungeonObject;
import dungeonmania.entities.actor.nonplayableactor.NonPlayableActor;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class SpiderMovement implements MovementBehaviour {

    // first move = (0, 1) (If blocked by boulder, spider should not move)
    // https://edstem.org/au/courses/8675/discussion/929191?comment=2084281
    // [(1, 0), (0, -1), (0, -1), (-1, 0), (-1, 0), (0, 1), (0, 1), (1, 0)]

    private final Direction firstMove = Direction.UP;
    private int nextMoveIndex;
    private boolean clockwise;
    private boolean firstMoved;
    private List<Position> positions = new ArrayList<>();

    public SpiderMovement(Position spiderPos) {
        this.nextMoveIndex = 2;
        this.clockwise = true;
        this.firstMoved = false;
        this.positions = spiderPos.getAdjacentPositions();
    }

    @Override
    public void move(NonPlayableActor spider) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Position nextMove;
        if (firstMoved) {

            Position newPos = this.positions.get(nextMoveIndex);

            // checks if spider can move to next space (not a boulder)
            if (!(dungeon.getObjectsAtPosition(newPos).stream().allMatch(obj -> obj.canAccept(spider)))) {
                clockwise = !(clockwise);
                int currentMoveIndex = nextMoveIndex;
                if (!clockwise) {
                    nextMoveIndex = (nextMoveIndex - 2 + 8) % 8;
                } else {
                    nextMoveIndex = (nextMoveIndex + 2) % 8;
                }
                newPos = positions.get(nextMoveIndex);
                if(!(dungeon.getObjectsAtPosition(newPos).stream().allMatch(obj -> obj.canAccept(spider)))){
                    newPos = spider.getPosition();
                    nextMoveIndex = currentMoveIndex;
                }
            }

            dungeon.getObjectsAtPosition(newPos).stream().forEach(obj -> obj.doAccept(spider));
            spider.setPosition(newPos);

            if (clockwise) {
                nextMoveIndex = (nextMoveIndex + 1) % 8;
            } else {
                nextMoveIndex = (nextMoveIndex - 1 + 8) % 8;
            }

        } else { // first tick move after spawn
            nextMove = spider.getPosition().translateBy(firstMove);
            List<DungeonObject> occupants = dungeon.getObjectsAtPosition(nextMove);

            // checks whether chosen position can be moved unto, if not stay still
            if (occupants.stream().allMatch(obj -> obj.canAccept(spider))) {
                occupants.stream().forEach(obj -> obj.doAccept(spider));
                spider.setPosition(nextMove);
                firstMoved = true;
            }

        }
    }

}
