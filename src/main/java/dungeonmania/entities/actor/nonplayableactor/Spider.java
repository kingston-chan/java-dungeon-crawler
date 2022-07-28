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

    private void move() {
        if (isStuck())
            return;
        doMove();
    }

    @Override
    public void movePlayerIsNormal() {
        move();
    }

    @Override
    public void movePlayerIsInvisible() {
        move();
    }

    @Override
    public void movePlayerIsInvincible() {
        move();
    }
}
