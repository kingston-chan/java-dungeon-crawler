package dungeonmania.entities.actor.nonplayableactor;

import dungeonmania.behaviours.movement.MoveAwayFromPlayer;
import dungeonmania.entities.staticobject.door.Door;
import dungeonmania.entities.staticobject.portal.Portal;

public abstract class SpecialCreature extends NonPlayableActor {

    @Override
    public boolean isInteractable() {
        return false;
    }

    @Override
    public boolean canVisitWall() {
        return false;
    }

    @Override
    public boolean canVisitPortal(Portal portal) {
        return true;
    }

    @Override
    public boolean canVisitDoor(Door door) {
        return door.isOpened();
    }

    private void tryMove() {
        if (!isStuck())
            doMove();
    }

    @Override
    public void movePlayerIsInvincible() {
        setCurrentMovement(new MoveAwayFromPlayer());
        tryMove();
    }

    @Override
    public void movePlayerIsNormal() {
        setCurrentMovement(getDefaultMovement());
        tryMove();
    }

    @Override
    public void movePlayerIsInvisible() {
        movePlayerIsNormal();
    }
}
