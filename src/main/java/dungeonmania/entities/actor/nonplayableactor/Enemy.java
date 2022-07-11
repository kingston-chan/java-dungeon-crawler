package dungeonmania.entities.actor.nonplayableactor;

import dungeonmania.behaviours.automatedmovement.AutomatedMovementBehaviour;

public abstract class Enemy extends NonPlayableActor {

    private AutomatedMovementBehaviour defaultMovement;

    public void setDefaultMovement(AutomatedMovementBehaviour movement) {
        this.defaultMovement = movement;
    }

    public AutomatedMovementBehaviour getDefaultMovementBehaviour() {
        return this.defaultMovement;
    }
}
