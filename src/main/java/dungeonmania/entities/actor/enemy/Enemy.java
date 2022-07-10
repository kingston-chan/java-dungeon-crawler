package dungeonmania.entities.actor.enemy;

import dungeonmania.behaviours.automatedmovement.AutomatedMovementBehaviour;
import dungeonmania.behaviours.host.HostBehaviour;
import dungeonmania.behaviours.observer.PlayerObserverEnemies;
import dungeonmania.entities.actor.Actor;

public abstract class Enemy extends Actor {

    private PlayerObserverEnemies observer;
    private AutomatedMovementBehaviour defaultMovement;
    private AutomatedMovementBehaviour currentMovement;

    public void setObserver(PlayerObserverEnemies observer) {
        this.observer = observer;
    }

    public void setCurrentMovement(AutomatedMovementBehaviour movement) {
        this.currentMovement = movement;
    }

    public void setDefaultMovement(AutomatedMovementBehaviour movement) {
        this.defaultMovement = movement;
    }

    public AutomatedMovementBehaviour getDefaultMovementBehaviour() {
        return this.defaultMovement;
    }

    public abstract void update(AutomatedMovementBehaviour movement, HostBehaviour hostBehaviour);

    @Override
    public boolean isInteractable() {
        return false;
    }
}
