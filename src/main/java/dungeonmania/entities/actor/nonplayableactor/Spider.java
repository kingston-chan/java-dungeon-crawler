package dungeonmania.entities.actor.nonplayableactor;

import dungeonmania.behaviours.movement.MovementBehaviour;
import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.portal.Portal;

public class Spider extends NonPlayableActor {

    @Override
    public void doAccept(Player player) {
        player.visit(this);
    }

    @Override
    public void update(MovementBehaviour movementBehaviour) {
        this.doMove(this);
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

}
