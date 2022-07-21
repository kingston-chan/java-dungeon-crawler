package dungeonmania.entities.actor.nonplayableactor;

import dungeonmania.behaviours.movement.MovementBehaviour;
import dungeonmania.entities.actor.Actor;
import dungeonmania.entities.staticobject.boulder.Boulder;
import dungeonmania.entities.staticobject.door.Door;
import dungeonmania.entities.staticobject.portal.Portal;

public abstract class NonPlayableActor extends Actor {
    private MovementBehaviour defaultMovement;
    private MovementBehaviour currentMovement;
    private int stuckTicks;

    public boolean canAccept(Boulder boulder) {
        return false;
    }

    public MovementBehaviour getDefaultMovement() {
        return defaultMovement;
    }

    public void setDefaultMovement(MovementBehaviour movementBehaviour) {
        this.defaultMovement = movementBehaviour;
    }

    public MovementBehaviour getCurrentMovement() {
        return currentMovement;
    }

    public void setCurrentMovement(MovementBehaviour movementBehaviour) {
        this.currentMovement = movementBehaviour;
    }

    public void doMove(NonPlayableActor npa) {
        this.currentMovement.move(npa);
    }

    public int getStuckTicks() {
        return 0;
    }

    public void reduceStuckTick() {
    }

    public abstract boolean canVisitWall();

    public abstract boolean canVisitPortal(Portal portal);

    public abstract boolean canVisitDoor(Door door);

    public abstract void update(MovementBehaviour movementBehaviour);

}
