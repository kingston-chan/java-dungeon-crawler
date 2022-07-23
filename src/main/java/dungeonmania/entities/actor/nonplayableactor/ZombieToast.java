package dungeonmania.entities.actor.nonplayableactor;

import dungeonmania.entities.actor.player.Player;
import dungeonmania.entities.staticobject.door.Door;
import dungeonmania.entities.staticobject.portal.Portal;
import dungeonmania.behaviours.movement.MovementBehaviour;

public class ZombieToast extends NonPlayableActor {

    @Override
    public void doAccept(Player player) {
        player.visit(this);
    }

    @Override
    public void update(MovementBehaviour movementBehaviour) {
        if (getStuckTicks() > 0) {
            reduceStuckTick();
            return;
        }
        this.setCurrentMovement(movementBehaviour);
        this.doMove(this);
    }

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
}
