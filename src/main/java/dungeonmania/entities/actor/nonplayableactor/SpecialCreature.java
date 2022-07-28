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

    @Override
    public void movePlayerIsInvincible() {
        setCurrentMovement(new MoveAwayFromPlayer());
        if (isStuck())
            return;
        doMove();
    }

    @Override
    public void movePlayerIsNormal() {
        setCurrentMovement(getDefaultMovement());
        if (isStuck())
            return;
        doMove();
    }

    @Override
    public void movePlayerIsInvisible() {
        movePlayerIsNormal();
    }
}
