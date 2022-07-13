package dungeonmania.entities.actor.nonplayableactor;

import dungeonmania.behaviours.movement.MovementBehaviour;
import dungeonmania.entities.actor.nonplayableactor.MercenaryState.MercenaryState;

public class Mercenary extends NonPlayableActor {

    MercenaryState enemyState;
    MercenaryState allyState;
    MercenaryState currentState;

    public void setMercenaryState(MercenaryState mercenaryState) {

    }

    public MercenaryState getAllyState() {
        return this.allyState;
    }

    public MercenaryState getEnemyState() {
        return this.enemyState;
    }

    public boolean isAlly() {
        return this.currentState.isAlly();
    }

    @Override
    public void update(MovementBehaviour movementBehaviour) {

    }

    @Override
    public boolean isInteractable() {
        return this.currentState.canInteract();
    }

}
