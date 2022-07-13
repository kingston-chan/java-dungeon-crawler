package dungeonmania.entities.actor.nonplayableactor;

import dungeonmania.behaviours.movement.MovementBehaviour;
import dungeonmania.entities.actor.Actor;
import dungeonmania.entities.staticobject.boulder.Boulder;

public abstract class NonPlayableActor extends Actor {
    private MovementBehaviour defaultMovement;
    private MovementBehaviour currentMovement;

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

    public abstract boolean canVisitWall();

    public abstract void update(MovementBehaviour movementBehaviour);

}
