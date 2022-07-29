package dungeonmania.entities.actor.nonplayableactor;

import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.door.Door;
import dungeonmania.entities.staticobject.portal.Portal;

public class Spider extends NonPlayableActor {

    @Override
    public void doAccept(Player player) {
        player.visit(this);
    }

    @Override
    public boolean isInteractable() {
        return false;
    }

    @Override
    public boolean canVisitWall() {
        return true;
    }

    @Override
    public boolean canVisitPortal(Portal portal) {
        return true;
    }

    @Override
    public boolean canVisitDoor(Door door) {
        return true;
    }

    @Override
    public void visitInvisiblePlayer(Player player) {
    }

    private void tryMove() {
        if (!isStuck())
            doMove();
    }

    @Override
    public void movePlayerIsNormal() {
        tryMove();
    }

    @Override
    public void movePlayerIsInvisible() {
        tryMove();
    }

    @Override
    public void movePlayerIsInvincible() {
        tryMove();
    }
}
