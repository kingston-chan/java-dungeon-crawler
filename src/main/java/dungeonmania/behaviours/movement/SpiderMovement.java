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

    private final Direction firstMove = Direction.UP;
    private int nextMoveIndex;
    private boolean clockwise;
    private boolean firstMoved;
    private ArrayList<Direction> moves = new ArrayList<>();

    public SpiderMovement() {
        this.nextMoveIndex = 0;
        this.clockwise = true;
        this.firstMoved = false;
        this.moves.add(Direction.RIGHT);
        this.moves.add(Direction.DOWN);
        this.moves.add(Direction.DOWN);
        this.moves.add(Direction.LEFT);
        this.moves.add(Direction.LEFT);
        this.moves.add(Direction.UP);
        this.moves.add(Direction.UP);
        this.moves.add(Direction.RIGHT);
    }

    @Override
    public void move(NonPlayableActor spider) {
        Dungeon dungeon = DungeonManiaController.getDungeon();
        Position nextMove;
        if (firstMoved) {
            Direction moveDir = this.moves.get(nextMoveIndex);

            Position newPos = spider.getPosition().translateBy(moveDir);

            // checks if spider can move to next space (not a boulder)
            if (!(dungeon.getObjectsAtPosition(newPos).stream().allMatch(obj -> obj.canAccept(spider)))) {
                clockwise = !(clockwise);
            }

            if (clockwise) {
                nextMoveIndex += 1;
                if (nextMoveIndex >= moves.size()) {
                    nextMoveIndex -= moves.size();
                }
            } else {
                nextMoveIndex -= 1;
                if (nextMoveIndex <= -1) {
                    nextMoveIndex += moves.size();
                }
            }
            

        } else {    // first tick move after spawn
            nextMove = spider.getPosition().translateBy(firstMove);
            List<DungeonObject> occupants = dungeon.getObjectsAtPosition(nextMove);

            // checks whether chosen position can be moved unto, if not stay still
            if (occupants.stream().allMatch(obj -> obj.canAccept(spider))) {
                spider.setPosition(nextMove);
                firstMoved = true;
            }

        }
    }

}
// first move = (0, 1) (If blocked by boulder, spider should not move)
// https://edstem.org/au/courses/8675/discussion/929191?comment=2084281
// [(1, 0), (0, -1), (0, -1), (-1, 0), (-1, 0), (0, 1), (0, 1), (1, 0)]
