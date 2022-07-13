package dungeonmania.entities.actor.nonplayableactor;

import dungeonmania.behaviours.automatedmovement.MovementBehaviour;
import dungeonmania.entities.actor.nonplayableactor.MercenaryState.MercenaryState;

public class Mercenary extends NonPlayableActor {

    MercenaryState enemyState;
    MercenaryState allyState;
    MercenaryState currentState;

    public void setMercenaryState(MercenaryState mercenaryState) {
        this.currentState = mercenaryState;
    }

    public MercenaryState getAllyState() {
        return this.allyState;
    }

    public MercenaryState getEnemyState() {
        return this.enemyState;
    }

    @Override
    public void update(MovementBehaviour movementBehaviour) {

    }

    @Override
    public boolean isInteractable() {
        return true;
    }

}
