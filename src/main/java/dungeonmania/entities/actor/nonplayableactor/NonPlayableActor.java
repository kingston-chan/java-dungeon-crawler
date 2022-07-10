package dungeonmania.entities.actor.nonplayableactor;

import dungeonmania.behaviours.automatedmovement.AutomatedMovementBehaviour;
import dungeonmania.behaviours.host.HostBehaviour;
import dungeonmania.entities.actor.Actor;

public abstract class NonPlayableActor extends Actor {
    
    private AutomatedMovementBehaviour currentMovement;

    public void setCurrentMovement(AutomatedMovementBehaviour movement) {
        this.currentMovement = movement;
    }

    public abstract void update(AutomatedMovementBehaviour movement, HostBehaviour hostBehaviour);

    @Override
    public boolean isInteractable() {
        return false;
    }
}
